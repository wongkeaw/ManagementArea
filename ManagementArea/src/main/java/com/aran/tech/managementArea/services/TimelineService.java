/**
 * 
 */
package com.aran.tech.managementArea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.domain.Timeline;
import com.aran.tech.managementArea.domain.User;
import com.aran.tech.managementArea.repositories.TimelineRepository;
import com.aran.tech.managementArea.repositories.UserRepository;

/**
 * @author oawon
 *
 */
@Service
public class TimelineService {
	
	@Autowired
	TimelineRepository timelineRepository ;
	
    @Autowired
    private UserRepository userRepository;
	
	public Iterable<Timeline> findTimeline( String username){
		
		User user = userRepository.findByUsername(username);
		user.getId() ;
		
		Pageable pageable = PageRequest.of(0,200 ,Sort.by("id").descending());
		return timelineRepository.findAll(pageable) ;
		//return timelineRepository.findByGroupUser(user.getGroupUsers() ,pageable) ;
		//Optional<Timeline> timelines = timelineRepository.findById(1L) ;
		//return timelines.map(Collections::singleton).orElseGet(Collections::emptySet);
	}
}
