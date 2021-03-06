package com.api.sns.cheese.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

/**
 * 通報対象
 */
@Getter
public enum ReportTargetEnum implements BaseEnum {

	/** アカウント */
	ACCOUNT("Account", "アカウント", 1),
	/** 写真 */
	PHOTO("Photo", "写真", 2),
	/** コメント */
	COMMENT("Comment", "コメント", 3);

	/** ステータス名称（英語） */
	private final String nameEn;

	/** ステータス名称（日本語） */
	private final String nameJa;

	/** ソート番号 */
	@JsonValue
	private final Integer sortOrder;

	ReportTargetEnum(String nameEn, String nameJa, Integer sortOrder) {
		this.nameEn = nameEn;
		this.nameJa = nameJa;
		this.sortOrder = sortOrder;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	/** アカウントか */
	public boolean isAccount() {
		return this == ACCOUNT;
	}

	/** 写真か */
	public boolean isPhoto() {
		return this == PHOTO;
	}

	/** コメントか */
	public boolean isComment() {
		return this == COMMENT;
	}
}
