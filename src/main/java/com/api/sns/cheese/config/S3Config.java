package com.api.sns.cheese.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * アプリケーション固有定義 AWS S3定義
 */
@Configuration
public class S3Config {

	// TODO application.ymlに移植
	private final String S3_ACCESS_KEY = "";
	private final String S3_SECRET_KEY = "";
	private final int TIMEOUT = 21600;
	private final String S3_SERVICE_END_POINT = "";
	private final String S3_REGION = "";
	private final String S3_BUCKET = "";

	@Bean
	public BasicAWSCredentials basicAWSCredentials() {
		return new BasicAWSCredentials(S3_ACCESS_KEY, S3_SECRET_KEY);
	}

	@Bean
	public AmazonS3 amazonS3Client(AWSCredentials awsCredentials) {

		// AWSの認証情報
		AWSCredentials credentials = new BasicAWSCredentials(S3_ACCESS_KEY, S3_SECRET_KEY);

		// クライアント設定
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTPS); // プロトコル
		clientConfig.setConnectionTimeout(TIMEOUT); // 接続タイムアウト(ms)

		// エンドポイント設定
		EndpointConfiguration endpointConfiguration = new EndpointConfiguration(S3_SERVICE_END_POINT, S3_REGION);

		// S3アクセスクライアントの生成
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withClientConfiguration(clientConfig).withEndpointConfiguration(endpointConfiguration).build();
	}
}
