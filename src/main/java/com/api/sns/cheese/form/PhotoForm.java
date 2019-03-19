package com.api.sns.cheese.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 写真フォーム
 */
@Data
public class PhotoForm {

	/** 画像ファイル */
	@NotNull
	private MultipartFile upfile;

	/** キャプション */
	@Size(max = 1000)
	private String caption;
}
