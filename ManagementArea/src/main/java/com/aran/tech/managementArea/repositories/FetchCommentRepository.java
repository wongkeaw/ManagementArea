/**
 * 
 */
package com.aran.tech.managementArea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aran.tech.managementArea.domain.FetchComment;

/**
 * @author oawon
 *
 */
@Repository
public interface FetchCommentRepository extends JpaRepository<FetchComment, Long> {

}
