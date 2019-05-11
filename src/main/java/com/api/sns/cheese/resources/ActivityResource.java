package com.api.sns.cheese.resources;

import java.util.Date;

import com.api.sns.cheese.enums.ActivityTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * アクティビティAPIレスポンス
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityResource {

	/** 行動種別 */
	private ActivityTypeEnum activityType;

	/** 写真 */
	private PhotoResource photo;

	/** 日時 */
	private Date createdAt;

	/** ユーザー */
	private AccountResource account;
}
