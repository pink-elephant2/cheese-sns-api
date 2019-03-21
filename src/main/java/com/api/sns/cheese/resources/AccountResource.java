package com.api.sns.cheese.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * アカウントAPIレスポンス
 */
@Data
@AllArgsConstructor
public class AccountResource {

	/** アカウントID */
	private Long id;

	/** ログインID */
	private String loginId;

	/** アカウント名 */
	private String name;

	/** 自己紹介 */
	private String description;

	/** 画像パス */ // TODO 画像情報クラス
	@JsonProperty("imgUrl")
	private String imageUrl;

	/** 場所 */
	private String place;

	/** ウェブサイト */
	private String url;

	/** Twitterアカウント */
	private String twitter;

	/** Instagramアカウント */
	private String instagram;
}
