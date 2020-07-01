package com.aran.tech.managementArea.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author oawon
 *
 */
@Entity
public class SessionWeb {
	@Getter
    @Setter
	@Id
	private Long id;
	
	@Getter
    @Setter
	@Column(name = "authorization", columnDefinition = "varchar(500)")
	private String authorization ;
	
	@Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date created_At;
	
	@Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated_At;
    
	@PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }

}
