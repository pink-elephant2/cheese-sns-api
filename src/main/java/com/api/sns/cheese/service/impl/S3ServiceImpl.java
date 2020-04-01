package com.api.sns.cheese.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.api.sns.cheese.config.AppConfig;
import com.api.sns.cheese.enums.DocumentTypeEnum;
import com.api.sns.cheese.service.S3Service;

/**
 * S3サービス
 */
@Service
public class S3ServiceImpl implements S3Service {

	@Autowired
	private AmazonS3 amazonS3;

	@Autowired
	private AppConfig appConfig;

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
	@Override
	public String upload(DocumentTypeEnum documentType, String fileName, MultipartFile inputFile) throws IOException {
		String filePath = createFilePath(documentType, fileName);
		amazonS3.putObject(createRequest("assets/" + filePath, inputFile));

		return appConfig.getCloudHostUrl() + "/" + filePath;
	}

	/**
	 * リクエスト情報を生成する
	 *
	 * @param fileName
	 *            ファイル名
	 * @param inputFile
	 *            マルチパートファイル
	 * @throws IOException
	 */
	private PutObjectRequest createRequest(String filePath, MultipartFile inputFile) throws IOException {
		// バイト長設定
		byte[] file = inputFile.getBytes();
		ObjectMetadata metaData = new ObjectMetadata();
		metaData.setContentLength(file.length);
		metaData.setCacheControl("max-age=2592000");
		metaData.setContentType(inputFile.getContentType());

		// アップロード対象のオブジェクトを作成
		PutObjectRequest putRequest = new PutObjectRequest(appConfig.getS3Bucket(), filePath,
				new ByteArrayInputStream(file), metaData);

		// アップロード対象ファイルの権限を設定する
		putRequest.setCannedAcl(CannedAccessControlList.PublicRead);

		return putRequest;
	}

	/**
	 * ファイルパスを生成する
	 *
	 * @return ファイルパス
	 */
	private String createFilePath(DocumentTypeEnum documentType, String fileName) {
		return documentType.getUploadPath() + "/" + fileName;
	}
}
