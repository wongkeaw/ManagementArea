package com.aran.tech.managementArea.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.domain.Backlog;
import com.aran.tech.managementArea.domain.PermissionAction;
import com.aran.tech.managementArea.domain.Project;
import com.aran.tech.managementArea.domain.User;
import com.aran.tech.managementArea.exceptions.ProjectIdException;
import com.aran.tech.managementArea.exceptions.ProjectNotFoundException;
import com.aran.tech.managementArea.repositories.BacklogRepository;
import com.aran.tech.managementArea.repositories.ProjectRepository;
import com.aran.tech.managementArea.repositories.UserRepository;
/**
 * @author oawon
 */
@Service
public class ProjectService {
	
	@Autowired
	private PermissionActionService permissionActionService ;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    public Project saveOrUpdateProject(Project project, String username){

        if(project.getId() != null){
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
            if(existingProject !=null &&(!existingProject.getProjectLeader().equals(username))){
                throw new ProjectNotFoundException("Project not found in your account");
            }else if(existingProject == null){
                throw new ProjectNotFoundException("Project with ID: '"+project.getProjectIdentifier()+"' cannot be updated because it doesn't exist");
            }
        }

        try{

            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if(project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if(project.getId()!=null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

            return projectRepository.save(project);

        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }

    }


    public Project findProjectByIdentifier(String projectId, String username){

        //Only want to return the project if the user looking for it is the owner
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Project ID '"+projectId+"' does not exist");
        }
		/*
        if(!project.getProjectLeader().equals(username)){
            throw new ProjectNotFoundException("Project not found in your account");
        }
        */
		List<Project> uniqueList = (List<Project>)this.findAllProjects(username) ;
        
		Optional<Project> projectx = uniqueList.stream().filter(proj -> projectId.equals( proj.getProjectIdentifier() ) ).findFirst() ;
		if (projectx.isPresent() == false) {
			throw new ProjectNotFoundException("Project not found in your account");
		}
		
        return projectx.get() ;
    }

    public Iterable<Project> findAllProjects(String username){
    	//Iterable<Project> ret = projectRepository.findAllByProjectLeader(username);
    	
		List<PermissionAction> permissionActions = permissionActionService.findAllPermissionActions(username) ;
		List<Project> listWithDuplicateValues = new ArrayList<Project>() ;
		Iterable<Project>  projects = this.findByPermission(permissionActions) ;
		Iterable<Project>  projects2 = this.findByOwner(username) ;
		listWithDuplicateValues.addAll( (List<Project>)projects) ;
		listWithDuplicateValues.addAll( (List<Project>)projects2) ;
		System.out.println("listDistinct:"+listWithDuplicateValues.size());
		List<Project> uniqueList = (ArrayList<Project>) listWithDuplicateValues.stream().distinct().collect(Collectors.toList());
    	
    	return uniqueList ;
    }
    
    public Iterable<Project> findByOwner(String username){
    	User user = userRepository.findByUsername(username);
    	Iterable<Project> ret = projectRepository.findAllByUser(user) ;
    	return ret ;
    	
    }
    
    public Iterable<Project> findByPermission(List<PermissionAction> permissionActions){
    	System.out.println("findByPermission");
    	List<Long> linkIds = permissionActions.stream().filter(pa -> PermissionAction.PRODUCT_PROJECT.equals(pa.getProduct())).map(PermissionAction::getLinkId).collect(Collectors.toList());
    	System.out.println("linkIds :"+linkIds);
    	Iterable<Project> ret = projectRepository.findAllById(linkIds) ;
    	return ret ;
    }
    
    public void deleteProjectByIdentifier(String projectid, String username){
        projectRepository.delete(findProjectByIdentifier(projectid, username));
    }

}
