/**
 * 
 */
package com.aran.tech.managementArea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.domain.Fetch;
import com.aran.tech.managementArea.domain.FetchEmotion;
import com.aran.tech.managementArea.domain.FetchItem;
import com.aran.tech.managementArea.domain.User;
import com.aran.tech.managementArea.payload.EmotionRequest;
import com.aran.tech.managementArea.repositories.FetchEmotionRepository;
import com.aran.tech.managementArea.repositories.UserRepository;

/**
 * @author oawon
 *
 */
@Service
public class FetchEmotionService {

	@Autowired
	FetchEmotionRepository fetchEmotionRepository ;
	
	@Autowired
	UserRepository userRepository ;
	
	public FetchEmotion saveOrUpdateFetchEmotion(EmotionRequest emotion, String username){
		
		User user = userRepository.findByUsername(username);
		Fetch ft = new Fetch() ;
		ft.setId(emotion.getId());
		
		FetchEmotion fEmotion = new FetchEmotion() ;
		fEmotion.setFetch(ft);
		Integer i = 0 ;
		if ("HeartBroken".equals(emotion.getEmotion())) {
			i = 1 ;
		} else if ("Heart".equals(emotion.getEmotion())) {
			i = 2 ;
		} else if ("Heartbeat".equals(emotion.getEmotion())) {
			i = 3 ;
		} else if ("MiddleFinger".equals(emotion.getEmotion())) {
			i = 4 ;
		}
		fEmotion.setEmotion(i);
		fEmotion.setUser(user);
		FetchEmotion newFetchEmotion = fetchEmotionRepository.save(fEmotion) ;
		return newFetchEmotion ;
	}
	
	public FetchEmotion saveOrUpdateFetchItemEmotion(EmotionRequest emotion, String username){
		
		User user = userRepository.findByUsername(username);
		FetchItem ft = new FetchItem() ;
		ft.setId(emotion.getId());
		
		FetchEmotion fEmotion = new FetchEmotion() ;
		fEmotion.setFetchItem(ft);
		Integer i = 0 ;
		if ("HeartBroken".equals(emotion.getEmotion())) {
			i = 1 ;
		} else if ("Heart".equals(emotion.getEmotion())) {
			i = 2 ;
		} else if ("Heartbeat".equals(emotion.getEmotion())) {
			i = 3 ;
		} else if ("MiddleFinger".equals(emotion.getEmotion())) {
			i = 4 ;
		}
		fEmotion.setEmotion(i);
		fEmotion.setUser(user);
		FetchEmotion newFetchEmotion = fetchEmotionRepository.save(fEmotion) ;
		return newFetchEmotion ;
	}
}
