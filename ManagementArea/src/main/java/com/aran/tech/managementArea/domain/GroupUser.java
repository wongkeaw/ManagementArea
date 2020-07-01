/**
 * 
 */
package com.aran.tech.managementArea.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author oawon
 *
 */
@Entity
public class GroupUser implements Serializable  {

	private static final long serialVersionUID = -3282005324314247332L;
	public static final char TYPE_PRIVATE = 'P' ;
	public static final char STATUS_ACTIVE = 'A' ;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name ;
    private char status ;
    
    private char groupType ;
    
    private String blackguardImage ;
    private String profileImage ;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date created_At;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated_At;
    
    //ManyToMany with User
    @ManyToMany(mappedBy = "groupUsers")
    @JsonIgnore
    private List<User> users = new ArrayList<>();
    
    //OneToMany with Permission
    @ManyToMany( fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    /**
    @JoinTable(
    		  name = "group_user_permission_action_link", 
    		  joinColumns = @javax.persistence.JoinColumn(name = "group_user_id"), 
    		  inverseJoinColumns = @javax.persistence.JoinColumn(name = "permission_action_id"))
    */
    @JsonIgnore
    private List<PermissionAction> permissionActions = new ArrayList<>();
	
    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }
        
    /** getter setter */
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	public char getGroupType() {
		return groupType;
	}

	public void setGroupType(char groupType) {
		this.groupType = groupType;
	}

	public String getBlackguardImage() {
		return blackguardImage;
	}

	public void setBlackguardImage(String blackguardImage) {
		this.blackguardImage = blackguardImage;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

	public Date getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}
	
	public List<PermissionAction> getPermissionActions() {
		return permissionActions;
	}

	public void setPermissionActions(List<PermissionAction> permissionActions) {
		this.permissionActions = permissionActions;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
