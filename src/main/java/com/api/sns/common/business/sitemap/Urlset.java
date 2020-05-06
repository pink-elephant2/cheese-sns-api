package com.api.sns.common.business.sitemap;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Urlset {

	private List<Url> url;

	@XmlAttribute
	public String getXmlns() {
		return "http://www.sitemaps.org/schemas/sitemap/0.9";
	}

	@XmlAttribute(name = "xmlns:xsi")
	public String getXmlnsXsi() {
		return "http://www.w3.org/2001/XMLSchema-instance";
	}

}
