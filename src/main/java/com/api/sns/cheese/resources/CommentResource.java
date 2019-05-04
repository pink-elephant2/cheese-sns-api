package com.api.sns.cheese.resources;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * コメントAPIレスポンス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResource {

	/** ID */
	private Long id;

	/** コード */
	@JsonProperty("cd")
	private String commentCd;

	/** コメント */
	private String content;

	/** コメント日時 */
	private Date createdAt;

	/** コメントユーザー */
	private AccountResource account;

	/** 自分がいいねしたか */
	@JsonProperty("isLike")
	private boolean isLike;
}
