/**
 * 
 */
package com.aran.tech.managementArea.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.domain.Fetch;
import com.aran.tech.managementArea.domain.FetchItem;
import com.aran.tech.managementArea.domain.User;
import com.aran.tech.managementArea.repositories.FetchItemRepository;
import com.aran.tech.managementArea.repositories.FetchRepository;
import com.aran.tech.managementArea.repositories.UserRepository;

/**
 * @author oawon
 *
 */
@Service
public class FetchService {

	@Autowired
	FetchRepository fetchRepository ;
	
	@Autowired
	FetchItemRepository fetchItemRepository ;
	
	@Autowired
    private UserRepository userRepository;
	
	public Iterable<Fetch> findFetchAll(Integer page ,  String username){
		
		User user = userRepository.findByUsername(username);
		user.getId() ;
		
		Pageable pageable = PageRequest.of(page,5 ,Sort.by("id").descending());
		Page<Fetch>  ret =  fetchRepository.findAll(pageable) ;
		for (Fetch ft : ret) {
			ft.getFetchComments().size() ;
			System.out.println(" comment : "+ft.getFetchComments().size());
		}
		
		return ret ;
	}
	
	public Iterable<Fetch> findFetchByUser(Integer page ,String userunique ,  String username){
		
		User user = userRepository.findByUserunique(userunique);
		user.getId() ;
		
		Pageable pageable = PageRequest.of(page,20 ,Sort.by("id").descending());
		Page<Fetch>  ret =  fetchRepository.findByUserId(user.getId() , pageable) ;
		for (Fetch ft : ret) {
			ft.getFetchComments().size() ;
			System.out.println(" comment : "+ft.getFetchComments().size());
		}
		
		return ret ;
	}
	
	public Fetch saveOrUpdateFetch(List<String> photos , String comment, String username){
		
		
		User user = userRepository.findByUsername(username);
		
		Fetch fetch =  new Fetch() ;
		fetch.setDescribeText(comment);
		fetch.setUser(user);
		Fetch newFetch = fetchRepository.save(fetch) ;
		List<FetchItem> fetchItems = new ArrayList<>();
		
		for (String photo : photos) {
			FetchItem fetchItem = new FetchItem() ;
			fetchItem.setFileName(photo);
			fetchItem.setFetch(newFetch);
			fetchItem.setUser(user);
			FetchItem newfetchItem = fetchItemRepository.save(fetchItem) ;
			fetchItems.add(newfetchItem) ;
		}
		
		newFetch.setFetchItems(fetchItems);
		return newFetch ;
		
	}
}
