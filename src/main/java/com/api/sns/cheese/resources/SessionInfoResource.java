package com.api.sns.cheese.resources;

import lombok.Data;

/**
 * セッション情報
 */
@Data
public class SessionInfoResource {

	/** アカウントID */
	private Integer accountId;

	/** ログインID */
	private String loginId;

	/** ユーザ名 */
	private String userName;
}
