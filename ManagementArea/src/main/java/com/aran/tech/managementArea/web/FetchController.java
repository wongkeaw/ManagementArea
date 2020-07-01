/**
 * 
 */
package com.aran.tech.managementArea.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aran.tech.managementArea.domain.Fetch;
import com.aran.tech.managementArea.domain.FetchComment;
import com.aran.tech.managementArea.domain.FetchEmotion;
import com.aran.tech.managementArea.payload.CommentReponse;
import com.aran.tech.managementArea.payload.CommentRequest;
import com.aran.tech.managementArea.payload.EmotionReponse;
import com.aran.tech.managementArea.payload.EmotionRequest;
import com.aran.tech.managementArea.services.FetchCommentService;
import com.aran.tech.managementArea.services.FetchEmotionService;
import com.aran.tech.managementArea.services.FetchService;

/**
 * @author oawon
 *
 */
@RestController
@RequestMapping("/api/fetch")
public class FetchController {
	
	@Autowired
	FetchService fetchService ; 
	
	@Autowired
	FetchCommentService fetchCommentService;  
	
	@Autowired
	FetchEmotionService fetchEmotionService ;
	
	@GetMapping("/all/{page}")
	public Iterable<Fetch> findAll(@PathVariable Integer page , Principal principal) {
		System.out.println("page : "+ page);
		Iterable<Fetch> fetchs = fetchService.findFetchAll(page , principal.getName()) ;
		return fetchs ;
	}
	
	@GetMapping("/findByUser/{page}/{userunique}")
	public Iterable<Fetch> findByUser(@PathVariable Integer page ,@PathVariable String  userunique , Principal principal) {
		System.out.println("page : "+ page);
		System.out.println("userunique : "+ userunique);
		Iterable<Fetch> fetchs = fetchService.findFetchByUser(page , userunique, principal.getName()) ;
		return fetchs ;
	}
    @PostMapping("/comment")
    public ResponseEntity<?> fetchComment(@Valid @RequestBody CommentRequest comment, BindingResult result , Principal principal){
        FetchComment fetchComment = fetchCommentService.saveOrUpdateFetchComment(comment, principal.getName()) ;
        CommentReponse commentReponse = new CommentReponse() ;
        commentReponse.setId(comment.getId());
        commentReponse.setFetchComment(fetchComment);
        return  new ResponseEntity<CommentReponse>(commentReponse, HttpStatus.OK);
    }
    @PostMapping("/emotion")
    public ResponseEntity<?> fetchEmotion(@Valid @RequestBody EmotionRequest emotion, BindingResult result , Principal principal){
        FetchEmotion fetchEmotion = fetchEmotionService.saveOrUpdateFetchEmotion(emotion, principal.getName()) ;
        EmotionReponse emotionReponse = new EmotionReponse() ;
        emotionReponse.setId(emotion.getId());
        emotionReponse.setFetchEmotion(fetchEmotion);
        return  new ResponseEntity<EmotionReponse>(emotionReponse, HttpStatus.OK);
    }
    
    @PostMapping("/item/comment")
    public ResponseEntity<?> fetchItemComment(@Valid @RequestBody CommentRequest comment, BindingResult result , Principal principal){
        FetchComment fetchComment = fetchCommentService.saveOrUpdateFetchItemComment(comment, principal.getName()) ;
        CommentReponse commentReponse = new CommentReponse() ;
        commentReponse.setId(comment.getId());
        commentReponse.setFetchComment(fetchComment);
        return  new ResponseEntity<CommentReponse>(commentReponse, HttpStatus.OK);
    }
    
    @PostMapping("/item/emotion")
    public ResponseEntity<?> fetchItemCmotion(@Valid @RequestBody EmotionRequest emotion, BindingResult result , Principal principal){
        FetchEmotion fetchEmotion = fetchEmotionService.saveOrUpdateFetchItemEmotion(emotion, principal.getName()) ;
        EmotionReponse emotionReponse = new EmotionReponse() ;
        emotionReponse.setId(emotion.getId());
        emotionReponse.setFetchEmotion(fetchEmotion);
        return  new ResponseEntity<EmotionReponse>(emotionReponse, HttpStatus.OK);
    }
    

}
