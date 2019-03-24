package com.api.sns.cheese.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.form.ContactForm;

/**
 * お問合せサービス
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	/**
	 * お問合せ登録する
	 *
	 * @param form
	 *            お問合せフォーム
	 */
	@Override
	public boolean save(ContactForm form) {

		// TODO DB登録

		// TODO 運営にメール送信

		// TODO サンキューメール送信

		return true;
	}
}
