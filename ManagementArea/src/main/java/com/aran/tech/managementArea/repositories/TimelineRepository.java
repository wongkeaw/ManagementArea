/**
 * 
 */
package com.aran.tech.managementArea.repositories;

/*import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aran.tech.managementArea.domain.Timeline;

/**
 * @author oawon
 *
 */
@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {
	//Page<Timeline> findByGroupUser(List<GroupUser> groupUsers ,Pageable pageable);
}
