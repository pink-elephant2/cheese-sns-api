package com.api.sns.cheese.service;

import javax.validation.constraints.NotNull;

import com.api.sns.cheese.domain.TAccount;
import com.api.sns.cheese.enums.ReportReasonEnum;
import com.api.sns.cheese.form.AccountCreateForm;
import com.api.sns.cheese.form.AccountImageForm;
import com.api.sns.cheese.form.AccountUpdateForm;
import com.api.sns.cheese.form.PasswordResetForm;
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
	public AccountResource find(String loginId);

	/**
	 * アカウントを取得する
	 *
	 * @param accountId
	 *            アカウントID
	 * @return アカウント情報
	 */
	public AccountResource find(Integer accountId);

	/**
	 * アカウントを通報する
	 *
	 * @param loginId
	 *            ログインID
	 * @param reason
	 *            理由
	 */
	public boolean report(String loginId, ReportReasonEnum reason);

	/**
	 * アカウントをブロックする
	 *
	 * @param loginId
	 *            ログインID
	 */
	public boolean block(String loginId);

	/**
	 * プロフィールを更新する
	 *
	 * @param form
	 *            プロフィールフォーム
	 */
	public boolean saveProfile(AccountUpdateForm form);

	/**
	 * パスワードを更新する
	 */
	public boolean savePassword(@NotNull String loginId, PasswordResetForm form);

	/**
	 * アカウント画像を更新する
	 *
	 * @param form
	 *            画像フォーム
	 */
	public boolean saveImage(AccountImageForm form);

	/**
	 * FacebookIDからアカウントを取得する
	 */
	public TAccount findByFacebookId(@NotNull String facebookId);

	/**
	 * メールアドレスからアカウントを取得する
	 *
	 * @param mail メールアドレス
	 */
	public TAccount findByMail(@NotNull String mail);
}
