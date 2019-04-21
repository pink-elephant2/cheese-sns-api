package com.api.sns.cheese.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.domain.TAccount;
import com.api.sns.cheese.domain.TAccountExample;
import com.api.sns.cheese.form.AccountCreateForm;
import com.api.sns.cheese.form.AccountUpdateForm;
import com.api.sns.cheese.repository.TAccountMapper;
import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.service.AccountService;
import com.api.sns.cheese.util.ImageUtils;

/**
 * アカウントサービス
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private TAccountMapper tAccountMapper;

	@Autowired
	private Mapper mapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

		// TODO 共通項目は親クラスで設定する
		account.setDeleted("0");
		account.setCreatedAt(new Date());
		account.setCreatedBy(1); // TODO システムユーザ(1)
		account.setUpdatedAt(new Date());
		account.setUpdatedBy(1); // TODO システムユーザ(1)

		// TODO エラーメッセージ
		return BooleanUtils.toBoolean(tAccountMapper.insert(account));
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
		TAccountExample example = new TAccountExample();
		example.createCriteria().andLoginIdEqualTo(loginId);
		List<TAccount> account = tAccountMapper.selectByExample(example);
		if (account.isEmpty()) {
			// TODO 404を返す
			throw new NotFoundException("アカウントが存在しません");
		}
		return mapper.map(account.get(0), AccountResource.class);
	}

	/**
	 * プロフィールを更新する
	 *
	 * @param form
	 *            プロフィールフォーム
	 */
	@Override
	public boolean saveProfile(AccountUpdateForm form) throws NotFoundException {
		String loginId = "my_melody"; // TODO セッション情報から取得

		// プロフィールを更新する
		TAccount account = mapper.map(form, TAccount.class);
		account.setImgUrl(ImageUtils.getDataUrl(form.getUpfile()));

		TAccountExample example = new TAccountExample();
		example.createCriteria().andLoginIdEqualTo(loginId);
		return BooleanUtils.toBoolean(tAccountMapper.updateByExampleSelective(account, example));
	}
}
