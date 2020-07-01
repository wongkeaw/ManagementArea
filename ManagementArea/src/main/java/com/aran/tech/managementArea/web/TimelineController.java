/**
 * 
 */
package com.aran.tech.managementArea.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aran.tech.managementArea.domain.Timeline;
import com.aran.tech.managementArea.services.TimelineService;

/**
 * @author oawon
 *
 */
@RestController
@RequestMapping("/api/timeline")
public class TimelineController {
	
	@Autowired
	TimelineService timelineService ; 
	
	@GetMapping("/all")
	public Iterable<Timeline> getAllTimelines(Principal principal) {
		Iterable<Timeline> timelines = timelineService.findTimeline(principal.getName()) ;
		return timelines ;
	}

}
