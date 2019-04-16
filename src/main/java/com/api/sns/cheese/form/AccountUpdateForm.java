package com.api.sns.cheese.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * アカウント更新フォーム
 */
@Data
public class AccountUpdateForm {

	/** 画像ファイル */
	private MultipartFile upfile;

	/** アカウント名 */
	@NotNull
	@Size(max = 30)
	private String name;

	/** 自己紹介 */
	@Size(max = 120)
	private String description;

	/** 場所 */
	@Size(max = 30)
	private String place;

	/** ウェブサイト */
	@Size(max = 100)
	private String url;
}
