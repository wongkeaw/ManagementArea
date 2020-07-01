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
import javax.persistence.OneToMany;
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
public class Timeline implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3351873859195041317L;
	
	public static final String HASKTAG_PROFILE = "#profile" ;
	public static final String HASKTAG_BLACKGUARD = "#blackguard" ;
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
    @Setter
    private Long id;
	
	@Getter
    @Setter
	private String hashtag ;
	
	@Getter
    @Setter
	private String describeText ;
	
    //OneToMany with ImageInfo
	@Getter
    @Setter
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER , mappedBy = "timeline", orphanRemoval = true)
    // @JsonIgnore
    private List<ImageInfo> imageInfos = new ArrayList<>();

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
        // not do some thing.
    }
}
