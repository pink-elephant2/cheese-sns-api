package com.api.sns.cheese.service;

import org.apache.ibatis.javassist.NotFoundException;

import com.api.sns.cheese.form.AccountCreateForm;
import com.api.sns.cheese.form.AccountImageForm;
import com.api.sns.cheese.form.AccountUpdateForm;
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
	public boolean saveProfile(AccountUpdateForm form) throws NotFoundException;

	/**
	 * アカウント画像を更新する
	 *
	 * @param form
	 *            画像フォーム
	 */
	public boolean saveImage(AccountImageForm form);
}
