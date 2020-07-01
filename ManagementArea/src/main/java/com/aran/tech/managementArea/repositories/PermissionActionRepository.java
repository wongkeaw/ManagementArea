package com.aran.tech.managementArea.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aran.tech.managementArea.domain.PermissionAction;
/**
 * @author oawon
 */
@Repository
public interface PermissionActionRepository extends CrudRepository<PermissionAction, Long> {

}
