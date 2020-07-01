/**
 * 
 */
package com.aran.tech.managementArea.services;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.domain.ImageInfo;
import com.aran.tech.managementArea.domain.Timeline;
import com.aran.tech.managementArea.domain.User;
import com.aran.tech.managementArea.repositories.ImageInfoRepository;
import com.aran.tech.managementArea.repositories.TimelineRepository;
import com.aran.tech.managementArea.repositories.UserRepository;

/**
 * @author oawon
 *
 */
@Service
public class ImageInfoService {
	
    @Autowired
    private ImageInfoRepository imageInfoRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TimelineRepository timelineRepository ; 
    
    public ImageInfo createImageInfo(ImageInfo imageInfo , Principal principal) {
    	User user = userRepository.findByUsername(principal.getName());
    	Timeline timeline = new Timeline() ;
    	imageInfo.setUser(user);
    	if (ImageInfo.UPLOAD_TYPE_BLACKGUARD.equals(imageInfo.getUploadType())) {
    		user.setBlackguardImage(imageInfo.getFileName());
    		userRepository.save(user) ;
    		timeline.setHashtag(Timeline.HASKTAG_BLACKGUARD);
    	}else if (ImageInfo.UPLOAD_TYPE_PROFILE.equals(imageInfo.getUploadType())) {
    		user.setProfileImage(imageInfo.getFileName());
    		userRepository.save(user) ;
    		timeline.setHashtag(Timeline.HASKTAG_PROFILE);
    	}
    	
    	timelineRepository.save(timeline) ;
    	imageInfo.setTimeline(timeline);
    	
    	ImageInfo ii =  imageInfoRepository.save(imageInfo) ;
    	ii.setUser(user);
    	return ii ;
    }

}
