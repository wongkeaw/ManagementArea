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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author oawon
 *
 */
@Entity(name = "fetch_list")
public class Fetch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3089375040603753949L;


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
    @Lob
	private String describeText ;
	
    //OneToMany with FetchItem
	@Getter
    @Setter
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER , mappedBy = "fetch", orphanRemoval = true)
    private List<FetchItem> fetchItems = new ArrayList<>();
	
    //OneToMany with FetchComment
	@Getter
    @Setter
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY , mappedBy = "fetch", orphanRemoval = true)
    private List<FetchComment> fetchComments = new ArrayList<>();

    //OneToMany with FetchEmotion
	@Getter
    @Setter
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY , mappedBy = "fetch", orphanRemoval = true)
    private List<FetchEmotion> fetchEmotions = new ArrayList<>();
	
	@Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" , "password", "confirmPassword" })
	private User user;
    
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
