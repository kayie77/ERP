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
@Table(name="cost_outboundList")
@ApiModel
public class OutboundList implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "出库单编号")
    private String code;
    
    @ApiModelProperty(value = "出库成本核算表号")
    private String tableCode;
    
    @ApiModelProperty(value = "制表人",hidden = true)
    private String person;

    @ApiModelProperty(value = "出库日期")
    private Date date;
    
    @ApiModelProperty(value = "状态")
    private String state;

    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private InboundList inboundList;//入库单信息
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Project project;//项目信息
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Region region;//区域信息
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public InboundList getInboundList() {
		return inboundList;
	}

	public void setInboundList(InboundList inboundList) {
		this.inboundList = inboundList;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
	
	
}