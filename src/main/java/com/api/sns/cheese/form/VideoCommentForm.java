package com.api.sns.cheese.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 動画コメントフォーム
 */
@Data
public class VideoCommentForm {

	/** コメント */
	@NotNull
	private String comment;
}
