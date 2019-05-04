package com.api.sns.cheese.resources;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * コメントAPIレスポンス
 */
@Data
@AllArgsConstructor
public class CommentResource {

	/** ID */
	private Long id;

	/** コード */
	private String cd;

	/** コメント */
	private String comment;

	/** コメント日時 */
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date createdAt;

	/** コメントユーザー */
	private AccountResource account;

	/** 自分がいいねしたか */
	@JsonProperty("isLike")
	private boolean isLike;
}
