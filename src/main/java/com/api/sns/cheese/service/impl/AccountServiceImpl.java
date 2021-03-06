package com.api.sns.cheese.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.aop.SessionInfoContextHolder;
import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TAccount;
import com.api.sns.cheese.domain.TAccountExample;
import com.api.sns.cheese.domain.TBanReport;
import com.api.sns.cheese.domain.TFollow;
import com.api.sns.cheese.domain.TFollowExample;
import com.api.sns.cheese.enums.DocumentTypeEnum;
import com.api.sns.cheese.enums.ReportReasonEnum;
import com.api.sns.cheese.enums.ReportTargetEnum;
import com.api.sns.cheese.exception.NotFoundException;
import com.api.sns.cheese.form.AccountCreateForm;
import com.api.sns.cheese.form.AccountImageForm;
import com.api.sns.cheese.form.AccountUpdateForm;
import com.api.sns.cheese.form.PasswordResetForm;
import com.api.sns.cheese.repository.TAccountRepository;
import com.api.sns.cheese.repository.TBanReportRepository;
import com.api.sns.cheese.repository.TFollowRepository;
import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.service.AccountService;
import com.api.sns.cheese.service.MailService;
import com.api.sns.cheese.service.S3Service;

/**
 * アカウントサービス
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private TAccountRepository tAccountRepository;

	@Autowired
	private TFollowRepository tFollowRepository;

	@Autowired
	private TBanReportRepository tBanReportRepository;

	@Autowired
	private Mapper mapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MailService mailService;

	@Autowired
	private S3Service s3Service;

	/** 不許可アカウント */
	private List<String> RESERVED_ACCOUNT_LIST = Arrays.asList("admin", "torochee");

	/**
	 * アカウントを登録する
	 *
	 * @param form
	 *            アカウント作成フォーム
	 */
	@Override
	public boolean create(AccountCreateForm form) {
		// 不許可アカウント
		if (RESERVED_ACCOUNT_LIST.stream().filter(loginId -> form.getLoginId().toLowerCase().indexOf(loginId) != -1)
				.count() > 0) {
			throw new ValidationException("既に登録されています");
		}

		// 既存ユーザー存在チェック
		TAccountExample example = new TAccountExample();
		example.or().andLoginIdEqualTo(form.getLoginId()).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		example.or().andMailEqualTo(form.getMail()).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		List<TAccount> accountList = tAccountRepository.findAllBy(example);
		if (!accountList.isEmpty()) {
			throw new ValidationException("既に登録されています");
		}

		// アカウントを登録する
		TAccount account = mapper.map(form, TAccount.class);

		// パスワード生成
		account.setPassword(passwordEncoder.encode(form.getPassword()));
		account.setPasswordChangeDate(new Date());

		// TODO エラーメッセージ
		boolean ret = tAccountRepository.create(account);
		if (ret && !StringUtils.isEmpty(form.getMail())) {
			// ソーシャルからメールが取得できない場合がある
			// 登録完了メール送信
			ret = mailService.sendAccountRegistComplete(form);
		}
		return ret;
	}

	/**
	 * アカウントを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @return アカウント情報
	 */
	@Override
	public AccountResource find(String loginId) {
		TAccount account = tAccountRepository.findOneByLoginId(loginId);

		if (account == null) {
			throw new NotFoundException("アカウントが存在しません");
		}
		AccountResource resource = mapper.map(account, AccountResource.class);

		// ログイン済みの場合
		if (SessionInfoContextHolder.isAuthenticated()
				&& !SessionInfoContextHolder.getSessionInfo().getAccountId().equals(account.getAccountId())) {
			// フォロー情報を取得する
			resource = getFollow(account.getAccountId(), resource);
		}

		return resource;
	}

	/**
	 * アカウントを取得する
	 *
	 * @param accountId
	 *            アカウントID
	 * @return アカウント情報
	 */
	@Override
	public AccountResource find(Integer accountId) {
		TAccount account = tAccountRepository.findOneById(accountId);

		if (account == null) {
			throw new NotFoundException("アカウントが存在しません");
		}
		AccountResource resource = mapper.map(account, AccountResource.class);

		// ログイン済みの場合
		if (!(SecurityContextHolder.getContext() == null
				|| SecurityContextHolder.getContext().getAuthentication() == null
				|| SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			// フォロー情報を取得する
			resource = getFollow(account.getAccountId(), resource);
		}

		return resource;
	}

	/**
	 * フォロー情報を取得する
	 */
	private AccountResource getFollow(Integer accountId, AccountResource resource) {
		// フォローしているか
		TFollowExample followExample = new TFollowExample();
		followExample.createCriteria().andAccountIdEqualTo(SessionInfoContextHolder.getSessionInfo().getAccountId())
				.andFollowAccountIdEqualTo(accountId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		TFollow follow = tFollowRepository.findOneBy(followExample);

		resource.setFollow(follow != null);

		// フォローされているか
		TFollowExample followerExample = new TFollowExample();
		followerExample.createCriteria().andAccountIdEqualTo(accountId)
				.andFollowAccountIdEqualTo(SessionInfoContextHolder.getSessionInfo().getAccountId());
		TFollow follower = tFollowRepository.findOneBy(followerExample);

		if (follower != null) {
			resource.setFollower(CommonConst.DeletedFlag.OFF.equals(follower.getDeleted()));
			resource.setBlocked(BooleanUtils.toBoolean(follower.getBlockFlag()));
		}
		return resource;
	}

	/**
	 * アカウントを通報する
	 *
	 * @param loginId
	 *            ログインID
	 * @param reason
	 *            理由
	 */
	@Override
	public boolean report(String loginId, ReportReasonEnum reason) {
		TAccount account = tAccountRepository.findOneByLoginId(loginId);

		// レコード登録
		TBanReport entity = new TBanReport();
		entity.setReportTarget(ReportTargetEnum.ACCOUNT);
		entity.setReason(reason);
		entity.setAccountId(account.getAccountId());
		entity.setReadFlag(false);
		entity.setDoneFlag(false);
		return tBanReportRepository.create(entity);
	}

	/**
	 * アカウントをブロックする
	 *
	 * @param loginId
	 *            ログインID
	 */
	@Override
	public boolean block(String loginId) {
		if (!SessionInfoContextHolder.isAuthenticated()) {
			return false;
		}

		TAccount account = tAccountRepository.findOneByLoginId(loginId);
		Integer accountId = SessionInfoContextHolder.getSessionInfo().getAccountId();

		// ログイン済みの場合、フォローしているか
		TFollowExample followExample = new TFollowExample();
		followExample.createCriteria().andAccountIdEqualTo(accountId)
				.andFollowAccountIdEqualTo(account.getAccountId());
		TFollow follower = tFollowRepository.findOneBy(followExample);

		boolean ret;
		if (follower == null) {
			// 登録内容の設定
			TFollow follow = new TFollow();
			follow.setAccountId(accountId);
			follow.setFollowAccountId(account.getAccountId());
			follow.setBlockFlag(true);
			follow.setDeleted(CommonConst.DeletedFlag.ON);

			// レコード登録
			ret = tFollowRepository.create(follow);
		} else {
			// 更新内容の設定
			follower.setBlockFlag(true);
			follower.setDeleted(CommonConst.DeletedFlag.ON);

			// レコード更新
			ret = tFollowRepository.update(follower);
		}
		return ret;
	}

	/**
	 * プロフィールを更新する
	 *
	 * @param form
	 *            プロフィールフォーム
	 */
	@Override
	public boolean saveProfile(AccountUpdateForm form) {
		// プロフィールを更新する
		TAccount account = mapper.map(form, TAccount.class);

		TAccountExample example = new TAccountExample();
		example.createCriteria().andAccountIdEqualTo(SessionInfoContextHolder.getSessionInfo().getAccountId());
		return BooleanUtils.toBoolean(tAccountRepository.updatePartiallyBy(account, example));
	}

	/**
	 * パスワードを更新する
	 */
	@Override
	public boolean savePassword(@NotNull String loginId, PasswordResetForm form) {
		// アカウントを取得
		TAccount account = tAccountRepository.findOneByLoginId(loginId);

		if (!form.getMail().equals(account.getMail())) {
			// フォームのメールとDBのメールが異なる場合エラー
			throw new NotFoundException("アカウントが存在しません");
		}

		account.setPassword(passwordEncoder.encode(form.getPassword()));
		account.setPasswordChangeDate(new Date());

		return tAccountRepository.updatePartially(account);
	}

	/**
	 * アカウント画像を更新する
	 *
	 * @param form
	 *            画像フォーム
	 */
	public boolean saveImage(AccountImageForm form) {
		try {
			// ランダム文字列発行
			String cd = RandomStringUtils.randomAlphanumeric(10);

			// S3に保存、URLを設定する
			String fileName = SecurityContextHolder.getContext().getAuthentication().getName() + "/" + cd + ".png"; // TODO ファイル拡張子
			String filePath = s3Service.upload(DocumentTypeEnum.ACCOUNT, fileName, form.getUpfile());

			// プロフィールを更新する
			TAccount account = mapper.map(form, TAccount.class);
			account.setImgUrl(filePath);

			TAccountExample example = new TAccountExample();
			example.createCriteria().andAccountIdEqualTo(SessionInfoContextHolder.getSessionInfo().getAccountId());
			return BooleanUtils.toBoolean(tAccountRepository.updatePartiallyBy(account, example));

		} catch (IOException e) {
			e.printStackTrace();
			// TODO エラーメッセージ
			return false;
		}
	}

	/**
	 * FacebookIDからアカウントを取得する
	 */
	@Override
	public TAccount findByFacebookId(@NotNull String facebookId) {
		TAccountExample example = new TAccountExample();
		example.createCriteria().andFacebookEqualTo(facebookId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		return tAccountRepository.findOneBy(example);
	}

	/**
	 * メールアドレスからアカウントを取得する
	 *
	 * @param mail メールアドレス
	 */
	@Override
	public TAccount findByMail(@NotNull String mail) {
		TAccountExample example = new TAccountExample();
		example.createCriteria().andMailEqualTo(mail).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		TAccount account = tAccountRepository.findOneBy(example); // TODO 2件以上HITした場合

		if (account == null) {
			throw new NotFoundException("アカウントが存在しません");
		}
		return account;
	}
}
