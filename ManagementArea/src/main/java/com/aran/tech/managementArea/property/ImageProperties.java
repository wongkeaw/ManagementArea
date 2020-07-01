package com.aran.tech.managementArea.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "images")
public class ImageProperties {

	@Getter
	@Setter
	private String blackguard ;
	
	@Getter
	@Setter
	private String profile ;
}
