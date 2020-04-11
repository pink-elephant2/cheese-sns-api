package com.api.sns.cheese.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 写真フォーム
 */
@Data
public class BookmarkForm {

	/** 写真CD */
	@NotNull
	@Size(max = 128)
	private String photoCd;
}
