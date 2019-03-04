package com.xhwl.erp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mk_businessHistory")
@ApiModel
public class BusinessHistory implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "修改人",hidden = true)
    private String person;
    
    @ApiModelProperty(value = "修改时间",hidden = true)
    private Date time;
    
    @ApiModelProperty(value = "修改内容")
    private String content;
    
    @ApiModelProperty(value = "商机修改间隔（天）")
    private int dNumber;
    
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Business business;//所属商机

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getdNumber() {
		return dNumber;
	}

	public void setdNumber(int dNumber) {
		this.dNumber = dNumber;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	@Override
	public String toString() {
		return "BusinessHistory [id=" + id + ", person=" + person + ", time=" + time + ", content=" + content
				+ ", dNumber=" + dNumber + ", business=" + business + "]";
	}
	
}