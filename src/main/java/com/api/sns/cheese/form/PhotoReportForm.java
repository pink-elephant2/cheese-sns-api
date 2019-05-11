package com.api.sns.cheese.form;

import javax.validation.constraints.NotNull;

import com.api.sns.cheese.enums.ReportReasonEnum;

import lombok.Data;

/**
 * 写真通報フォーム
 */
@Data
public class PhotoReportForm {

	/** 理由 */
	@NotNull
	private ReportReasonEnum reason;
}
