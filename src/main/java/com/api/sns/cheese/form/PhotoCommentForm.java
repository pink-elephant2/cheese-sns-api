package com.api.sns.cheese.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 写真コメントフォーム
 */
@Data
public class PhotoCommentForm {

	/** コメント */
	@NotNull
	private String comment;
}
