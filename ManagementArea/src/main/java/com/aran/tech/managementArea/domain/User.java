package com.aran.tech.managementArea.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author oawon
 */
@Entity
public class User implements UserDetails {
    
	private static final long serialVersionUID = -883972384100920994L;
	
	public static final char STATUS_LOCK = 'L' ;
	public static final char STATUS_ACTIVE = 'A' ;

	
	@Getter
    @Setter
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Getter
    @Setter
	@Column(unique = true , updatable = false)
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
    private String blackguardImage ;
    
	@Getter
    @Setter
    @Transient
	private String blackguardImageFileSource ;
	
	@Getter
    @Setter
    @Transient
	private boolean blackguardImageIsDownload ;
    
    @Getter
    @Setter
    private String profileImage ;
    
	@Getter
    @Setter
    @Transient
	private String profileImageFileSource ;
	
	@Getter
    @Setter
    @Transient
	private boolean profileImageIsDownload ;
    
    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date created_At;
    
    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated_At;

    /*OneToMany with Project */
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY , mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();
    
    /*OneToMany with ImageInfo */
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY , mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<ImageInfo> imageInfos = new ArrayList<>();
    
    
    /*OneToMany with Fetch */
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY , mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<Fetch> fetchs = new ArrayList<>();
    
    /*OneToMany with FetchItem */
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY , mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<FetchItem> fetchItems = new ArrayList<>();
    
    /*ManyToMany with groupUsers */
    @Getter
    @Setter
    @ManyToMany( fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    /**
    @JoinTable(
    		  name = "user_group_user_link", 
    		  joinColumns = @JoinColumn(name = "user_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "group_user_id"))
    */
    @JsonIgnore
    private List<GroupUser> groupUsers = new ArrayList<>();

    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
        this.userunique = UUID.randomUUID().toString()  ;
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }
    /** Override */
    /**  UserDetails interface methods */

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
    
    public User() {
    }
    
    /** getter setter */
    

	
}
