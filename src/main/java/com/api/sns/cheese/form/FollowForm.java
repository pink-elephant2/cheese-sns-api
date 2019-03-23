package com.api.sns.cheese.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * フォローフォーム
 */
@Data
public class FollowForm {

	/** ログインID(フォロー対象) */
	@NotNull
	private String loginId;
}
