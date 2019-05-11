package com.api.sns.cheese.service.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.api.sns.cheese.form.AccountCreateForm;
import com.api.sns.cheese.form.AccountImageForm;
import com.api.sns.cheese.form.AccountUpdateForm;
import com.api.sns.cheese.repository.TAccountRepository;
import com.api.sns.cheese.repository.TBanReportRepository;
import com.api.sns.cheese.repository.TFollowRepository;
import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.service.AccountService;
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
	private S3Service s3Service;

	/**
	 * アカウントを登録する
	 *
	 * @param form
	 *            アカウント作成フォーム
	 */
	@Override
	public boolean create(AccountCreateForm form) {
		// アカウントを登録する
		TAccount account = mapper.map(form, TAccount.class);
		account.setName(form.getLoginId());

		// パスワード生成
		account.setPassword(passwordEncoder.encode(form.getPassword()));
		account.setPasswordChangeDate(new Date());

		// TODO エラーメッセージ
		return tAccountRepository.create(account);
	}

	/**
	 * アカウントを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @return アカウント情報
	 */
	@Override
	public AccountResource find(String loginId) throws NotFoundException {
		TAccount account = tAccountRepository.findOneByLoginId(loginId);

		if (account == null) {
			// TODO 404を返す
			throw new NotFoundException("アカウントが存在しません");
		}
		AccountResource resource = mapper.map(account, AccountResource.class);

		if (SessionInfoContextHolder.isAuthenticated()) {
			// ログイン済みの場合、フォローしているか
			TFollowExample followExample = new TFollowExample();
			followExample.createCriteria().andAccountIdEqualTo(SessionInfoContextHolder.getSessionInfo().getAccountId())
					.andFollowAccountIdEqualTo(account.getAccountId()).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
			TFollow follow = tFollowRepository.findOneBy(followExample);
			resource.setFollow(follow != null);
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
	public boolean saveProfile(AccountUpdateForm form) throws NotFoundException {
		// プロフィールを更新する
		TAccount account = mapper.map(form, TAccount.class);

		TAccountExample example = new TAccountExample();
		example.createCriteria().andAccountIdEqualTo(SessionInfoContextHolder.getSessionInfo().getAccountId());
		return BooleanUtils.toBoolean(tAccountRepository.updatePartiallyBy(account, example));
	}

	/**
	 * アカウント画像を更新する
	 *
	 * @param form
	 *            画像フォーム
	 */
	public boolean saveImage(AccountImageForm form) {
		try {
			// S3に保存、URLを設定する
			String fileName = SessionInfoContextHolder.getSessionInfo().getLoginId() + ".png";
			// TODO ファイル名
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
}
