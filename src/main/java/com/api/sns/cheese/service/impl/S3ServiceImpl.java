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
	 * @param inputFile
	 *            マルチパートファイル
	 * @return ファイルパス
	 * @throws IOException
	 */
	@Override
	public String upload(DocumentTypeEnum documentType, MultipartFile inputFile) throws IOException {
		String fileName = createFileName();
		String filePath = createFilePath(documentType, fileName);
		amazonS3.putObject(createRequest(filePath, inputFile.getBytes()));

		return appConfig.getS3ServiceEndPoint() + "/" + appConfig.getS3Bucket() + "/" + filePath;
	}

	/**
	 * リクエスト情報を生成する
	 *
	 * @param fileName
	 *            ファイル名
	 * @param file
	 *            バイトデータ
	 */
	private PutObjectRequest createRequest(String filePath, byte[] file) {
		// バイト長設定
		ObjectMetadata metaData = new ObjectMetadata();
		metaData.setContentLength(file.length);

		// アップロード対象のオブジェクトを作成
		PutObjectRequest putRequest = new PutObjectRequest(appConfig.getS3Bucket(), filePath,
				new ByteArrayInputStream(file), metaData);

		// アップロード対象ファイルの権限を設定する
		putRequest.setCannedAcl(CannedAccessControlList.PublicRead);

		return putRequest;
	}

	/**
	 * ランダムファイル名を生成する
	 */
	private String createFileName() {
		// TODO ランダム文字列を生成する
		String fileName = "photo1.png";
		return fileName;
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
