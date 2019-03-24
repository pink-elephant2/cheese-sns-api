package com.api.sns.cheese.service;

import com.api.sns.cheese.form.ContactForm;

/**
 * お問合せサービス
 */
public interface ContactService {

	/**
	 * お問合せ登録する
	 *
	 * @param form
	 *            お問合せフォーム
	 */
	public boolean save(ContactForm form);
}
