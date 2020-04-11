package com.api.sns.cheese.resources;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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

	/** 動画パス */
	private String videoUrl;

	/** 投稿日時 */
	private Date createdAt;

	/** 投稿ユーザー */
	public AccountResource account;

	/** いいね件数 */
	private long likeCount;

	/** 自分がいいねしたか */
	@JsonProperty("isLike")
	private boolean isLike;

	/** 自分がブックマークしているか */
	@JsonProperty("isBookmark")
	private boolean isBookmark;

	/** コメント */
	private List<CommentResource> comments;

	/** 緯度 */
	private Double lat;

	/** 経度 */
	private Double lng;

	/** 住所 */
	private String address;
}
