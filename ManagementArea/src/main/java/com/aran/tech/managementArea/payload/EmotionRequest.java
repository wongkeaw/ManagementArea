/**
 * 
 */
package com.aran.tech.managementArea.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * @author oawon
 *
 */
public class EmotionRequest {

	@Getter
    @Setter
    private Long id;
	
	@Getter
    @Setter
	private String emotion ;
}
