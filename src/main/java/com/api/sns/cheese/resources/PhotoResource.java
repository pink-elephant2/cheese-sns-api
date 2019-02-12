package com.api.sns.cheese.resources;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhotoResource {

	/** ID */
	private Long id;

	/** コード */
	private String code;

	/** 説明 */
	private String caption;

	/** 画像パス */ // TODO 画像情報クラス
	private String imageUrl;

	/** 投稿日時 */
	private Date createAt;

	/** 投稿ユーザー */ // TODO クラス
	//	  public Account account;
}
