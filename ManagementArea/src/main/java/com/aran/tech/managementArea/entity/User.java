/**
 * 
 */
package com.aran.tech.managementArea.entity;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
/**
 * @author oawon
 *
 */
@Entity
public class User implements UserDetails {
	
	
	public static final char STATUS_LOCK = 'L' ;
	public static final char STATUS_ACTIVE = 'A' ;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2200971221014391910L;
	
	
	@Getter
    @Setter
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Getter
    @Setter
	@Column(unique = true)
	private String userunique ;

	@Getter
    @Setter
    @Email(message = "Username needs to be an email")
    @NotBlank(message = "username is required")
    @Column(unique = true)
    private String username;
    
    @Getter
    @Setter
    @NotBlank(message = "Please enter your full name")
    private String fullName;
    
    @Getter
    @Setter
    @NotBlank(message = "Password field is required")
    private String password;
    
    @Getter
    @Setter
    @Transient
    private String confirmPassword;
    
    @Getter
    @Setter
    private char status ;
    
    
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
        this.userunique = UUID.randomUUID().toString()  ;
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }
    
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
    	if ( this.getStatus() == STATUS_LOCK) {
    		return false ;
    	}
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    } 

}
