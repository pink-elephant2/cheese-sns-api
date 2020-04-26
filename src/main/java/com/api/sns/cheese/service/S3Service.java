package com.api.sns.cheese.service;

import java.io.ByteArrayOutputStream;
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
	 * @param fileName
	 *            ファイル名
	 * @param inputFile
	 *            マルチパートファイル
	 * @return ファイルパス
	 * @throws IOException
	 */
	public String upload(DocumentTypeEnum documentType, String fileName, MultipartFile inputFile) throws IOException;

	/**
	 * アップロード
	 *
	 * @param fileName
	 *            ファイル名
	 * @param OutputStream
	 *            バイトストリーム
	 * @param contentType
	 *            コンテンツタイプ
	 * @return ファイルパス
	 */
	public String upload(DocumentTypeEnum documentType, String fileName, ByteArrayOutputStream outputStream,
			String contentType);
}
