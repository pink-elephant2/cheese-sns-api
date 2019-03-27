package com.api.sns.cheese.resources;

import java.util.Date;

import com.api.sns.cheese.enums.ActivityTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * アクティビティAPIレスポンス
 */
@Data
@AllArgsConstructor
public class ActivityResource {

	/** 行動種別 */
	private ActivityTypeEnum activityType;

	/** 写真 */
	private PhotoResource photo;

	/** 日時 */
	private Date createAt;

	/** ユーザー */
	private AccountResource account;
}
