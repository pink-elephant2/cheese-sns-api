package com.api.sns.cheese.form;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * アカウント画像フォーム
 */
@Data
public class AccountImageForm {

	/** 画像ファイル */
	@NotNull
	private MultipartFile upfile;

}
