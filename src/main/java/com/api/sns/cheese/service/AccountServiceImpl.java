package com.api.sns.cheese.service;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.domain.TAccount;
import com.api.sns.cheese.domain.TAccountExample;
import com.api.sns.cheese.form.AccountForm;
import com.api.sns.cheese.repository.TAccountMapper;
import com.api.sns.cheese.resources.AccountResource;
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
	public boolean saveProfile(AccountForm form) throws NotFoundException {
		String loginId = "my_melody"; // TODO セッション情報から取得

		// プロフィールを更新する
		TAccount account = mapper.map(form, TAccount.class);
		account.setImgUrl(ImageUtils.getDataUrl(form.getUpfile()));

		TAccountExample example = new TAccountExample();
		example.createCriteria().andLoginIdEqualTo(loginId);
		return BooleanUtils.toBoolean(tAccountMapper.updateByExampleSelective(account, example));
	}
}
