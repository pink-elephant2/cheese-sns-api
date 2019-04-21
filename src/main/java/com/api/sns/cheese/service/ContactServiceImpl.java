package com.api.sns.cheese.service;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.domain.TContact;
import com.api.sns.cheese.form.ContactForm;
import com.api.sns.cheese.repository.TContactMapper;

/**
 * お問合せサービス
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private TContactMapper tContactMapper;

	@Autowired
	private Mapper mapper;

	/**
	 * お問合せ登録する
	 *
	 * @param form
	 *            お問合せフォーム
	 */
	@Override
	public boolean save(ContactForm form) {

		// DB登録
		TContact contact = mapper.map(form, TContact.class);
		contact.setReadFlag(false);

		// TODO 共通項目は親クラスで設定する
		contact.setDeleted("0");
		contact.setCreatedAt(new Date());
		contact.setCreatedBy(1); // TODO システムユーザ(1) TODO ログイン済みならそのユーザ
		contact.setUpdatedAt(new Date());
		contact.setUpdatedBy(1); // TODO システムユーザ(1) TODO ログイン済みならそのユーザ
		tContactMapper.insert(contact);

		// TODO 運営にメール送信

		// TODO サンキューメール送信

		return true;
	}
}
