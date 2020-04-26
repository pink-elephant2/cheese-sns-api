package com.api.sns.common.business.sitemap;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement
@Data
public class Sitemapindex {

	private List<Sitemap> sitemap;

	@XmlAttribute
	public String getXmlns() {
		return "http://www.sitemaps.org/schemassitemap/0.9";
	}

}
