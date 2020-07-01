/**
 * 
 */
package com.aran.tech.managementArea.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aran.tech.managementArea.domain.GroupUser;

/**
 * @author oawon
 *
 */
@Repository
public interface GroupUserRepository extends CrudRepository<GroupUser, Long> {

	GroupUser getById(Long id);
	
}
