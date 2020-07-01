/**
 * 
 */
package com.aran.tech.managementArea.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author oawon
 *
 */
@Entity
public class FetchEmotion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7838378143560450575L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
    @Setter
    private Long id;
	
	@Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" , "password", "confirmPassword" })
    private User user;
	
	@Getter
    @Setter
	private Integer emotion ;
	
    //ManyToOne with Fetch
	@Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
    private Fetch fetch;
	
    //ManyToOne with FetchItem
	
	@Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
    private FetchItem fetchItem;
	
	@Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date created_At;
	
	
	@PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        // not do some thing.
    }

}
