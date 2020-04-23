package com.api.sns.cheese.form;

import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 動画フォーム
 */
@Data
public class VideoForm {

	/** 画像ファイル */
	@NotNull
	private MultipartFile upfile;

	/** キャプション */
	@Size(max = 1000)
	private String caption;

	/** タグ */
	private List<String> tags;

	/** 緯度 */
	@DecimalMax("90.0")
	@DecimalMin("-90.0")
	private Double lat;

	/** 経度 */
	@DecimalMax("180.0")
	@DecimalMin("-180.0")
	private Double lng;

	/** 住所 */
	@Size(max = 50)
	private String address;
}
