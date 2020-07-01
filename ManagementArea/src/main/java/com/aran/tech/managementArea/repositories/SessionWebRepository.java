/**
 * 
 */
package com.aran.tech.managementArea.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aran.tech.managementArea.domain.SessionWeb;

/**
 * @author oawon
 *
 */
@Repository
public interface SessionWebRepository extends CrudRepository<SessionWeb, Long> {
	SessionWeb getById(Long id);
	

	
}
