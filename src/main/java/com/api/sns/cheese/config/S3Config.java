package com.api.sns.cheese.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * アプリケーション固有定義 AWS S3定義
 */
@Configuration
@EnableDynamoDBRepositories(basePackages = "com.api.sns.cheese.repository")
public class S3Config {

	@Autowired
	private AppConfig appConfig;

	@Bean
	public BasicAWSCredentials basicAWSCredentials() {
		return new BasicAWSCredentials(appConfig.getS3AccessKey(), appConfig.getS3SecretKey());
	}

	@Bean
	public AmazonS3 amazonS3Client(AWSCredentials awsCredentials) {

		// AWSの認証情報
		AWSCredentials credentials = new BasicAWSCredentials(appConfig.getS3AccessKey(), appConfig.getS3SecretKey());

		// クライアント設定
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTPS); // プロトコル
		clientConfig.setConnectionTimeout(appConfig.getTimeout()); // 接続タイムアウト(ms)

		// エンドポイント設定
		EndpointConfiguration endpointConfiguration = new EndpointConfiguration(appConfig.getS3ServiceEndPoint(),
				appConfig.getS3Region());

		// S3アクセスクライアントの生成
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withClientConfiguration(clientConfig).withEndpointConfiguration(endpointConfiguration).build();
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(basicAWSCredentials());
		amazonDynamoDB.setEndpoint(appConfig.getDynamodbEndpoint());
		return amazonDynamoDB;
	}
}
