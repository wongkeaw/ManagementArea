/**
 * 
 */
package com.aran.tech.managementArea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.domain.Fetch;
import com.aran.tech.managementArea.domain.FetchComment;
import com.aran.tech.managementArea.domain.FetchItem;
import com.aran.tech.managementArea.domain.User;
import com.aran.tech.managementArea.payload.CommentRequest;
import com.aran.tech.managementArea.repositories.FetchCommentRepository;
import com.aran.tech.managementArea.repositories.UserRepository;;

/**
 * @author oawon
 *
 */
@Service
public class FetchCommentService {

	@Autowired
	FetchCommentRepository fetchCommentRepository ;
	
	@Autowired
	UserRepository userRepository ;
	
	public FetchComment saveOrUpdateFetchComment(CommentRequest comment, String username){
		
		User user = userRepository.findByUsername(username);
		Fetch ft = new Fetch() ;
		ft.setId(comment.getId());
		
		FetchComment fetch = new FetchComment() ;
		fetch.setFetch(ft);
		fetch.setCommentText(comment.getCommentText());
		fetch.setUser(user);
		
		FetchComment newFetchComment = fetchCommentRepository.save(fetch) ;
		return newFetchComment ;
	}
	
	public FetchComment saveOrUpdateFetchItemComment(CommentRequest comment, String username){
		
		User user = userRepository.findByUsername(username);
		FetchItem ft = new FetchItem() ;
		ft.setId(comment.getId());
		
		FetchComment fetch = new FetchComment() ;
		fetch.setFetchItem(ft);
		fetch.setCommentText(comment.getCommentText());
		fetch.setUser(user);
		
		FetchComment newFetchComment = fetchCommentRepository.save(fetch) ;
		return newFetchComment ;
	}
}
