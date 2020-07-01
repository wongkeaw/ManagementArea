/**
 * 
 */
package com.aran.tech.managementArea.payload;

import com.aran.tech.managementArea.domain.FetchComment;

import lombok.Getter;
import lombok.Setter;

/**
 * @author oawon
 *
 */
public class CommentReponse {

	@Getter
    @Setter
    private Long id;
	
	@Getter
    @Setter
	private FetchComment fetchComment ;
}
