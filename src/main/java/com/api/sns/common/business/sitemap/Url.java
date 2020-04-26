package com.api.sns.common.business.sitemap;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Url {

	private String loc;

	// JSONは以下アノテーションでOKだが、XMLは変換されなかったので文字列でやる
	// @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Tokyo")
	private String lastmod;

	private String changefreq;

	private double priority;

}
