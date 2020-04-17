package com.api.sns.cheese.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

/**
 * 検閲結果
 */
@Getter
public enum ModerationResultEnum implements BaseEnum {

	/** 未検閲 */
	UNCENSORED("Uncensored", "未検閲", 1),
	/** OK */
	OK("OK", "OK", 2),
	/** NG */
	NG("NG", "NG", 3);

	/** ステータス名称（英語） */
	private final String nameEn;

	/** ステータス名称（日本語） */
	private final String nameJa;

	/** ソート番号 */
	@JsonValue
	private final Integer sortOrder;

	ModerationResultEnum(String nameEn, String nameJa, Integer sortOrder) {
		this.nameEn = nameEn;
		this.nameJa = nameJa;
		this.sortOrder = sortOrder;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	/** 未検閲か */
	public boolean isUncensored() {
		return this == UNCENSORED;
	}

	/** OKか */
	public boolean isOk() {
		return this == OK;
	}

	/** NGか */
	public boolean isNg() {
		return this == NG;
	}
}
