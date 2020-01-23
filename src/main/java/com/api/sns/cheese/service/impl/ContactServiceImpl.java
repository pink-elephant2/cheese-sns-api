package com.api.sns.cheese.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.domain.TContact;
import com.api.sns.cheese.form.ContactForm;
import com.api.sns.cheese.repository.TContactRepository;
import com.api.sns.cheese.service.ContactService;
import com.api.sns.cheese.service.MailService;
import com.api.sns.cheese.service.SlackService;

/**
 * お問合せサービス
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private TContactRepository tContactRepository;

	@Autowired
	private Mapper mapper;

	@Autowired
	private MailService mailService;

	@Autowired
	private SlackService slackService;

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
		tContactRepository.create(contact);

		// 運営にSlack送信
		slackService.sendContactComplete(form);

		// サンキューメール送信
		mailService.sendContactComplete(form);

		return true;
	}
}
