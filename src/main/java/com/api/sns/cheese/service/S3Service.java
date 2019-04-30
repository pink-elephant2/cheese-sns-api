package com.api.sns.cheese.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.api.sns.cheese.enums.DocumentTypeEnum;

/**
 * S3サービス
 */
public interface S3Service {

	/**
	 * アップロード
	 *
	 * @param inputFile
	 *            マルチパートファイル
	 * @return ファイルパス
	 * @throws IOException
	 */
	public String upload(DocumentTypeEnum documentType, MultipartFile inputFile) throws IOException;
}
