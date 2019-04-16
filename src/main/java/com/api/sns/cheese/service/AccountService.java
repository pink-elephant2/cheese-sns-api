package com.api.sns.cheese.service;

import org.apache.ibatis.javassist.NotFoundException;

import com.api.sns.cheese.form.AccountCreateForm;
import com.api.sns.cheese.form.AccountForm;
import com.api.sns.cheese.resources.AccountResource;

/**
 * アカウントサービス
 */
public interface AccountService {

	/**
	 * アカウントを登録する
	 *
	 * @param form
	 *            アカウント作成フォーム
	 */
	public boolean create(AccountCreateForm form);

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
