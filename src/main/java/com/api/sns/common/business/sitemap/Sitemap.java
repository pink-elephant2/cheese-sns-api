package com.api.sns.common.business.sitemap;

import lombok.Data;

@Data
public class Sitemap {

	private String loc;

	// JSONは以下アノテーションでOKだが、XMLは変換されなかったので文字列でやる
	// @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Tokyo")
	private String lastmod;

}
