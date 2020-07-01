/**
 * 
 */
package com.aran.tech.managementArea.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author oawon
 *
 */
@Entity
public class Transaction implements Serializable  {
	
	private static final long serialVersionUID = 8234848500308992747L;

	@Getter
    @Setter
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Getter
    @Setter
    private String principalName ;
	
	@Getter
    @Setter
    private String RequestURL ;
    
	@Getter
    @Setter
    @Column(updatable = false)
    private Date created_At;
	
    /** persist */
	@PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        // not do something.
    }

    /** getter setter */
    
	    
}
