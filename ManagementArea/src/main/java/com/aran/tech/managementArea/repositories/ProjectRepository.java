package com.aran.tech.managementArea.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aran.tech.managementArea.domain.Project;
import com.aran.tech.managementArea.domain.User;
/**
 * @author oawon
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();

    Iterable<Project> findAllByProjectLeader(String username);
    
    Iterable<Project> findAllByUser(User user);
    
}
