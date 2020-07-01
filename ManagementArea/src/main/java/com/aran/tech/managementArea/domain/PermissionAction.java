/**
 * 
 */
package com.aran.tech.managementArea.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class PermissionAction implements Serializable  {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8504912591019037776L;

	public static final String PRODUCT_PROJECT = "project" ;
	public static final String PRODUCT_TIME_LINE = "timeline" ;
	
	public static final String PRODUCT_TIME_LINE_READ = "timeline_read" ;
	public static final String PRODUCT_TIME_LINE_ADD = "timeline_add" ;
	
	public static final String PERMISSION_ACTIVE_READ = "read" ;
	public static final char STATUS_ACTIVE = 'A' ;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private String name ;
    private char status ;
    
    @Column(updatable = false)
    private Date created_At;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated_At;
    
    @ManyToMany(mappedBy = "permissionActions" )
    @JsonIgnore
    private List<GroupUser> groupUsers = new ArrayList<>();
    
    
    /** to indicator table */
    private String product ;
    /** to indicator id of table */
    private Long linkId;
    
    private String permission ;
    
    /** persist */
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

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public List<GroupUser> getGroupUsers() {
		return groupUsers;
	}

	public void setGroupUsers(List<GroupUser> groupUsers) {
		this.groupUsers = groupUsers;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
