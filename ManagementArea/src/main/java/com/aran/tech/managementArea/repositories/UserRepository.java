package com.aran.tech.managementArea.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aran.tech.managementArea.domain.User;
/**
 * @author oawon
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    User findByUsername(String username);
    User findByUserunique(String userunique);
    User getById(Long id);
    
}
