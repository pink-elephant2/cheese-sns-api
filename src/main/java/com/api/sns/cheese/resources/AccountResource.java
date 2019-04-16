package com.api.sns.cheese.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * アカウントAPIレスポンス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResource {

	/** アカウントID */
	@JsonProperty("id")
	private Long accountId;

	/** ログインID */
	private String loginId;

	/** アカウント名 */
	private String name;

	/** 自己紹介 */
	private String description;

	/** 画像パス */ // TODO 画像情報クラス
	private String imgUrl;

	/** 場所 */
	private String place;

	/** ウェブサイト */
	private String url;

	/** Twitterアカウント */
	private String twitter;

	/** Instagramアカウント */
	private String instagram;

	/** ログイン状態の場合、フォローしているかどうか */
	@JsonProperty("isFollow")
	private boolean isFollow;
}
