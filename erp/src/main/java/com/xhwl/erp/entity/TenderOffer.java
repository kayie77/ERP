package com.xhwl.erp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mk_tenderOffer")
@ApiModel
public class TenderOffer implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "表格地址")
	private String url;
    
	@Column(columnDefinition="int default 0",insertable=false)
    @ApiModelProperty(value = "状态")
	private int status;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Business business;//所属商机

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

}