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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class FetchItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5170218271847933774L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
    @Setter
    private Long id;
	
	@Getter
    @Setter
	private String fileName ;
	
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
    
    //ManyToOne with Fetch
	
	@Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
    private Fetch fetch;
	
    //OneToMany with FetchComment
	@Getter
    @Setter
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER , mappedBy = "fetchItem", orphanRemoval = true)
    private List<FetchComment> fetchComments = new ArrayList<>();
	
    //OneToMany with FetchEmotion
	@Getter
    @Setter
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY , mappedBy = "fetchItem", orphanRemoval = true)
    private List<FetchEmotion> fetchEmotions = new ArrayList<>();

	@PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        // not do some thing.
    }
}
