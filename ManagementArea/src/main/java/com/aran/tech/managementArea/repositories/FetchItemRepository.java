/**
 * 
 */
package com.aran.tech.managementArea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aran.tech.managementArea.domain.FetchItem;

/**
 * @author oawon
 *
 */
@Repository
public interface FetchItemRepository extends JpaRepository<FetchItem, Long> {

}
