/**
 * 
 */
package com.aran.tech.managementArea.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author oawon
 *
 */
@ConfigurationProperties(prefix = "app.security.constants")
public class ApplicationProperties {
	
	private long expirationTime ;

	public long getExpirationTime() {
		return expirationTime;
	}
	 @Value("${app.security.constants.expiration_time:0}")
	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}
	 
	

}
