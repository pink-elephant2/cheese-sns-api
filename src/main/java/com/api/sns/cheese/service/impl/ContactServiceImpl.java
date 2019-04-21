package com.api.sns.cheese.service.impl;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TContact;
import com.api.sns.cheese.form.ContactForm;
import com.api.sns.cheese.repository.TContactMapper;
import com.api.sns.cheese.service.ContactService;

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
		contact.setDeleted(CommonConst.DeletedFlag.OFF);
		contact.setCreatedAt(new Date());
		// TODO ログイン済みならそのユーザ
		contact.setCreatedBy(CommonConst.SystemAccount.ADMIN_ID);
		contact.setUpdatedAt(new Date());
		// TODO ログイン済みならそのユーザ
		contact.setUpdatedBy(CommonConst.SystemAccount.ADMIN_ID);
		tContactMapper.insert(contact);

		// TODO 運営にメール送信

		// TODO サンキューメール送信

		return true;
	}
}
