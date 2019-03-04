package com.xhwl.erp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name="mk_business")
@ApiModel
public class Business implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    /************基础信息***************/
    @ApiModelProperty(value = "商机编码")
    private String code;
    
    @ApiModelProperty(value = "商机名称")
    private String name;
    
    @ApiModelProperty(value = "创建人")
    private String createPerson;
    
    @ApiModelProperty(value = "商机录入日期")
    private Date date;
    
    @ApiModelProperty(value = "商机提供人")
    private String sourcePerson;
    
	@ApiModelProperty(value = "商机审批状态")
	@Column(columnDefinition="varchar(128) default '商机线索'",insertable=false)
    private String examineState;
    
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Region region;//所属区域
    
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private City city;//所属城市
    
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Client client;//所属客户
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private BusinessCategory businessCategory;//所属业务分类
	
	/************商务信息***************/
	
    @ApiModelProperty(value = "项目需求描述")
	private String keyword;
    
    @ApiModelProperty(value = "项目预计启动时间")
    private Date startDate;
    
    @ApiModelProperty(value = "甲方决策人")
    private String firstPerson;
    
    @ApiModelProperty(value = "甲方决策人联系方式")
    private String firstPersonPhone;
    
    @ApiModelProperty(value = "商机执行状态")
    private String executeState;
    
    @ApiModelProperty(value = "预计成交金额")
    private double amount;
    
    /************人员信息***************/
    
    @ApiModelProperty(value = "业务负责人")
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private User businessPerson;
    
    @ApiModelProperty(value = "设计负责人")
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private User designPerson;
    
    @ApiModelProperty(value = "成本负责人")
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private User costPerson;
	
    @ApiModelProperty(value = "工程负责人")
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private User projectPerson;
    
    @ApiModelProperty(value = "项目经理")
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private User projectManager;
	
	@Transient
	private String oldCity;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSourcePerson() {
		return sourcePerson;
	}

	public void setSourcePerson(String sourcePerson) {
		this.sourcePerson = sourcePerson;
	}

	public String getExamineState() {
		return examineState;
	}

	public void setExamineState(String examineState) {
		this.examineState = examineState;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public BusinessCategory getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(BusinessCategory businessCategory) {
		this.businessCategory = businessCategory;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getFirstPerson() {
		return firstPerson;
	}

	public void setFirstPerson(String firstPerson) {
		this.firstPerson = firstPerson;
	}

	public String getFirstPersonPhone() {
		return firstPersonPhone;
	}

	public void setFirstPersonPhone(String firstPersonPhone) {
		this.firstPersonPhone = firstPersonPhone;
	}

	public String getExecuteState() {
		return executeState;
	}

	public void setExecuteState(String executeState) {
		this.executeState = executeState;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getBusinessPerson() {
		return businessPerson;
	}

	public void setBusinessPerson(User businessPerson) {
		this.businessPerson = businessPerson;
	}

	public User getDesignPerson() {
		return designPerson;
	}

	public void setDesignPerson(User designPerson) {
		this.designPerson = designPerson;
	}

	public User getCostPerson() {
		return costPerson;
	}

	public void setCostPerson(User costPerson) {
		this.costPerson = costPerson;
	}

	public User getProjectPerson() {
		return projectPerson;
	}

	public void setProjectPerson(User projectPerson) {
		this.projectPerson = projectPerson;
	}

	public User getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(User projectManager) {
		this.projectManager = projectManager;
	}

	public String getOldCity() {
		return oldCity;
	}

	public void setOldCity(String oldCity) {
		this.oldCity = oldCity;
	}

}