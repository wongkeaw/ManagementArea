/**
 * 
 */
package com.aran.tech.managementArea.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aran.tech.managementArea.domain.ImageInfo;

/**
 * @author oawon
 *
 */
@Repository
public interface ImageInfoRepository extends CrudRepository<ImageInfo, Long> {
	
    @Override
    Iterable<ImageInfo> findAll();

}
