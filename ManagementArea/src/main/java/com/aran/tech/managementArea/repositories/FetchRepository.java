/**
 * 
 */
package com.aran.tech.managementArea.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aran.tech.managementArea.domain.Fetch;

/**
 * @author oawon
 *
 */
@Repository
public interface FetchRepository extends JpaRepository<Fetch, Long> {

	Page<Fetch> findByUserId(Long user , Pageable pageable);

	//findallbyUserId() ;
}
