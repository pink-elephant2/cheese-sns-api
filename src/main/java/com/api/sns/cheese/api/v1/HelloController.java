package com.api.sns.cheese.api.v1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;
import com.api.sns.cheese.domain.Test;
import com.api.sns.cheese.domain.TestExample;
import com.api.sns.cheese.repository.TestMapper;

@RestController
public class HelloController {

	@Autowired
	private TestMapper testMapper;

	@Autowired
	private AmazonS3 amazonS3;

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello, World!";
	}

	@RequestMapping("/sqlsample")
	@ResponseBody
	public List<Test> sqlSample() {

		TestExample example = new TestExample();
		example.createCriteria().andIdIsNotNull();

		return testMapper.selectByExample(example);
	}

	/** Content-Type */
	private static final String CONTENT_TYPE = "application/force-download;";

	/** Content-Dispositionの文字列置換フォーマット */
	private static final String CONTENT_DISPOSITION_FORMAT = "attachment; filename=\"%s\"; filename*=UTF-8''%s";

	@RequestMapping("/s3sample")
	@ResponseBody
	private HttpEntity<byte[]> s3Sample() {
		byte[] b = null;
		HttpHeaders header = null;

		try {
			// S3よりファイルを取得
			S3Object s3Object = amazonS3.getObject("sns-cheese-dev", "photo/sample-1.jpg");
			InputStream s3ObjectContent = s3Object.getObjectContent();

			// byteへ変換
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			int c;
			while ((c = s3ObjectContent.read()) != -1) {
				bout.write(c);
			}
			b = bout.toByteArray();

			// ファイルダウンロード
			header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE);
			header.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(b.length));
			header.add(HttpHeaders.CONTENT_DISPOSITION, String.format(CONTENT_DISPOSITION_FORMAT, "sample-1.jpg",
					UriUtils.encode("sample-1.jpg", StandardCharsets.UTF_8.name())));
		} catch (AmazonS3Exception e) {
			// S3エラー
			e.printStackTrace();
		} catch (IOException e) {
			// ファイルが破損していますエラー
			e.printStackTrace();
		}
		return new HttpEntity<byte[]>(b, header);
	}
}
