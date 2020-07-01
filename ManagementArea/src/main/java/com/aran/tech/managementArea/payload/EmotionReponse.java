/**
 * 
 */
package com.aran.tech.managementArea.payload;

import com.aran.tech.managementArea.domain.FetchEmotion;

import lombok.Getter;
import lombok.Setter;

/**
 * @author oawon
 *
 */
public class EmotionReponse {
	

	@Getter
    @Setter
    private Long id;
	
	@Getter
    @Setter
	private FetchEmotion fetchEmotion ;

}
