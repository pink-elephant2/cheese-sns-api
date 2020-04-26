package com.api.sns.cheese.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.config.AppConfig;
import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TAccountExample;
import com.api.sns.cheese.domain.TPhotoExample;
import com.api.sns.cheese.enums.DocumentTypeEnum;
import com.api.sns.cheese.enums.ModerationResultEnum;
import com.api.sns.cheese.repository.TAccountRepository;
import com.api.sns.cheese.repository.TPhotoRepository;
import com.api.sns.cheese.service.S3Service;
import com.api.sns.cheese.service.TaskService;
import com.api.sns.common.business.sitemap.Url;
import com.api.sns.common.business.sitemap.Urlset;
import com.api.sns.common.util.DateUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * バッチサービス
 */
@Slf4j
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TAccountRepository tAccountRepository;

	@Autowired
	private TPhotoRepository tPhotoRepository;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private AppConfig appConfig;

	/**
	 * サイトマップXMLを生成する
	 */
	@Scheduled(cron = "0 35 22 * * ? ")
	@Override
	public void createSitemapXml() {
		log.info("Start createSitemapXml()");

		createSitemapAccount();
		createSitemapPhoto();

		log.info("End createSitemapXml()");
	}

	/**
	 * アカウントサイトマップXMLを生成する
	 */
	private void createSitemapAccount() {
		log.info("Start createSitemapAccountXml()");

		// すべてのアカウントを取得
		TAccountExample example = new TAccountExample();
		example.createCriteria().andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		List<Url> urlList = tAccountRepository.findAllBy(example).stream().map(tAccount -> {
			Url url = new Url(
					appConfig.getUrl() + tAccount.getLoginId(),
					DateUtils.formatDate(tAccount.getUpdatedAt(), "yyyy-MM-dd"),
					"always",
					0.8);
			return url;
		}).collect(Collectors.toList());

		// XML生成
		Urlset urlset = new Urlset(urlList);
		ByteArrayOutputStream xml = new ByteArrayOutputStream();
		JAXB.marshal(urlset, xml);

		// S3に格納
		s3Service.upload(DocumentTypeEnum.SITEMAP, "sitemap-account.xml", xml, "application/xml");

		log.info("End createSitemapAccountXml()");
	}

	/**
	 * 画像サイトマップXMLを生成する
	 */
	private void createSitemapPhoto() {
		log.info("Start createSitemapPhotoXml()");

		// すべての画像を取得
		TPhotoExample example = new TPhotoExample();
		example.createCriteria()
				.andModerationResultNotEqualTo(ModerationResultEnum.NG)
				.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		List<Url> urlList = tPhotoRepository.findAllBy(example).stream().map(tPhoto -> {
			Url url = new Url(
					appConfig.getUrl() + "photo/" + tPhoto.getPhotoCd(),
					DateUtils.formatDate(tPhoto.getUpdatedAt(), "yyyy-MM-dd"),
					"always",
					0.8);
			return url;
		}).collect(Collectors.toList());

		// XML生成
		Urlset urlset = new Urlset(urlList);
		ByteArrayOutputStream xml = new ByteArrayOutputStream();
		JAXB.marshal(urlset, xml);

		// S3に格納
		s3Service.upload(DocumentTypeEnum.SITEMAP, "sitemap-photo.xml", xml, "application/xml");

		log.info("End createSitemapPhotoXml()");
	}

}
