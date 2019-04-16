package com.api.sns.cheese.service;

import org.apache.ibatis.javassist.NotFoundException;

import com.api.sns.cheese.form.AccountForm;
import com.api.sns.cheese.resources.AccountResource;

/**
 * アカウントサービス
 */
public interface AccountService {

	/**
	 * アカウントを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @return アカウント情報
	 */
	public AccountResource find(String loginId) throws NotFoundException;

	/**
	 * プロフィールを更新する
	 *
	 * @param form
	 *            プロフィールフォーム
	 */
	public boolean saveProfile(AccountForm form) throws NotFoundException;
}
