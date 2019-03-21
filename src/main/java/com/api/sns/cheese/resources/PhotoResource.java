package com.api.sns.cheese.resources;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 写真APIレスポンス
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PhotoResource {

	/** ID */
	private final Long id;

	/** コード */
	@JsonProperty("cd")
	private final String code;

	/** 説明 */
	private String caption;

	/** 画像パス */ // TODO 画像情報クラス
	@JsonProperty("imgUrl")
	private String imageUrl;

	/** 投稿日時 */
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date createAt;

	/** 投稿ユーザー */
	public AccountResource account;

	/** いいね件数 */
	private Integer likeCount;

	/** 自分がいいねしたか */
	@JsonProperty("isLike")
	private boolean isLike;

	/** コメント */
	private List<CommentResource> comments;
}
