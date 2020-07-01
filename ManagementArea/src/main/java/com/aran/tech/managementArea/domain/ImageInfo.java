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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * @author oawon
 *
 */
@Entity
public class ImageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6217232329774496506L;
	public static final String UPLOAD_TYPE_BLACKGUARD = "blackguard" ;
	public static final String UPLOAD_TYPE_PROFILE = "profile" ;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
    @Setter
    private Long id;
	
	@Getter
    @Setter
	private String imageToken ;
	
	@Getter
    @Setter
	private String fileName ;
	
	@Getter
    @Setter
	private String uploadType ;
	
	@Getter
    @Setter
	private String type ;
	
	@Getter
    @Setter
	private long size ;
	
	@Getter
    @Setter
    @Transient
	private String fileSource ;
	
	@Getter
    @Setter
    @Transient
	private boolean isDownload ;
	
	
	@Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date created_At;
    
	@Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    
    //ManyToOne with Timeline
	@Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
    private Timeline timeline;
    
    public ImageInfo(String imageToken, String fileName, String uploadType, String type, Long size ) {
		super();
		this.imageToken = imageToken;
		this.fileName = fileName;
		this.uploadType = uploadType;
		this.type = type;
		this.size = size;
	}
    
	public ImageInfo() {
		super();
	}

	@PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        // not do some thing.
    }

}
