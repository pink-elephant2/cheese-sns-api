package com.api.sns.cheese.service;

import javax.validation.constraints.NotNull;

import com.api.sns.cheese.form.AccountCreateForm;
import com.api.sns.cheese.form.ContactForm;

/**
 * メールサービス
 */
public interface MailService {

	/**
	 * アカウント登録完了メールを送信する
	 *
	 * @param form
	 *            アカウント作成フォーム
	 */
	public boolean sendAccountRegistComplete(AccountCreateForm form);

	/**
	 * お問合せ完了メールを送信する
	 *
	 * @param form
	 *            お問合せフォーム
	 */
	public boolean sendContactComplete(ContactForm form);

	/**
	 * パスワードリマインダーメールを送信する
	 *
	 * @param mail メールアドレス
	 * @param token ワンタイムトークン
	 */
	public boolean sendPasswordReminder(@NotNull String mail, @NotNull String token);
}
