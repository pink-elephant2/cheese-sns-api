package com.api.sns.cheese.resources;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 写真APIレスポンス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoResource {

	/** ID */
	@JsonProperty("id")
	private Long photoId;

	/** コード */
	@JsonProperty("cd")
	private String photoCd;

	/** 説明 */
	private String caption;

	/** 画像パス */ // TODO 画像情報クラス
	private String imgUrl;

	/** 投稿日時 */
	private Date createdAt;

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
