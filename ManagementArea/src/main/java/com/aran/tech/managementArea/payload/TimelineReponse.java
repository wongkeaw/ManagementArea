/**
 * 
 */
package com.aran.tech.managementArea.payload;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author oawon
 *
 */
public class TimelineReponse {

	@Getter
    @Setter
    private Long id;
	
	@Getter
    @Setter
	private String hashtag ;
	
	@Getter
    @Setter
	private String describeText ;
	
	@Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created_At;
	
	@Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated_At;
}
