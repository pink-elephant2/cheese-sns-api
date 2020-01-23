package com.api.sns.cheese.service;

import com.api.sns.cheese.form.ContactForm;

/**
 * Slackサービス
 */
public interface SlackService {

	/**
	 * お問合せ内容を送信する
	 *
	 * @param form
	 *            お問合せフォーム
	 */
	public boolean sendContactComplete(ContactForm form);
}
