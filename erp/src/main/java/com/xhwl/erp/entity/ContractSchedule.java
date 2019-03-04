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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="fi_contractSchedule")
@ApiModel
public class ContractSchedule implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    /****************** 进度分析 **********************/
    @ApiModelProperty(value = "项目所属阶段(进度分析)")
    @Column(columnDefinition="varchar(128) default '未进场'",insertable=false)
    private String stage;

    @Column(columnDefinition="float default 0",insertable=false)
    @ApiModelProperty(value = "完工百分比(进度分析)")
    private double finishPercentage;
    
    @Column(columnDefinition="float default 0",insertable=false)
    @ApiModelProperty(value = "开票百分比(进度分析)")
    private double billingPercentage;
    
    @Column(columnDefinition="varchar(128) default '正常'",insertable=false)
    @ApiModelProperty(value = "项目状态(进度分析)")
    private String projectStatus;
    
    /****************** 回款分析 **********************/
    @Column(columnDefinition="float default 0",insertable=false)
    @ApiModelProperty(value = "回款比例(回款分析)")
    private double receivedPercentage;
    
    @Column(columnDefinition="varchar(128) default '正常'",insertable=false)
    @ApiModelProperty(value = "回款状态(回款分析)")
    private String receivedStatus;
    
    /****************** 支出分析 **********************/
    @Column(columnDefinition="float default 0",insertable=false)
    @ApiModelProperty(value = "材料采购比例(支出分析)")
    private double materialPercentage;
    
    @Column(columnDefinition="varchar(128) default '正常'",insertable=false)
    @ApiModelProperty(value = "材料进度匹配度(支出分析)")
    private String materialStatus;
    
    @Column(columnDefinition="float default 0",insertable=false)
    @ApiModelProperty(value = "人工支出比例(支出分析)")
    private double artificialPercentage;
    
    @Column(columnDefinition="varchar(128) default '正常'",insertable=false)
    @ApiModelProperty(value = "人工进度匹配度(支出分析)")
    private String artificialStatus;
    
    @Column(columnDefinition="float default 0",insertable=false)
    @ApiModelProperty(value = "综合支出比例(支出分析)")
    private double comprehensivePercentage;
    
    @Column(columnDefinition="varchar(128) default '正常'",insertable=false)
    @ApiModelProperty(value = "综合进度匹配度(支出分析)")
    private String comprehensiveStatus;
    
    /****************** 收支差异分析 **********************/
    
    @Column(columnDefinition="float default 0",insertable=false)
    @ApiModelProperty(value = "收支差额(收支差异分析)")
    private double paymentBalance;
    
    @Column(columnDefinition="varchar(128) default '正常'",insertable=false)
    @ApiModelProperty(value = "收支差额状态(收支差异分析)")
    private String paymentBalanceStatus;
    
    @Column(columnDefinition="float default 0",insertable=false)
    @ApiModelProperty(value = "付现差额(收支差异分析)")
    private double cashBalance;
    
    @Column(columnDefinition="varchar(128) default '正常'",insertable=false)
    @ApiModelProperty(value = "付现差额状态(收支差异分析)")
    private String cashBalanceStatus;

    /********************* 其他 *************************/
    @ApiModelProperty(value = "更新时间")
    private Date time;

    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private ContractInfo contractInfo;//所属合同

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public double getFinishPercentage() {
		return finishPercentage;
	}

	public void setFinishPercentage(double finishPercentage) {
		this.finishPercentage = finishPercentage;
	}

	public double getBillingPercentage() {
		return billingPercentage;
	}

	public void setBillingPercentage(double billingPercentage) {
		this.billingPercentage = billingPercentage;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public double getReceivedPercentage() {
		return receivedPercentage;
	}

	public void setReceivedPercentage(double receivedPercentage) {
		this.receivedPercentage = receivedPercentage;
	}

	public String getReceivedStatus() {
		return receivedStatus;
	}

	public void setReceivedStatus(String receivedStatus) {
		this.receivedStatus = receivedStatus;
	}

	public double getMaterialPercentage() {
		return materialPercentage;
	}

	public void setMaterialPercentage(double materialPercentage) {
		this.materialPercentage = materialPercentage;
	}

	public String getMaterialStatus() {
		return materialStatus;
	}

	public void setMaterialStatus(String materialStatus) {
		this.materialStatus = materialStatus;
	}

	public double getArtificialPercentage() {
		return artificialPercentage;
	}

	public void setArtificialPercentage(double artificialPercentage) {
		this.artificialPercentage = artificialPercentage;
	}

	public String getArtificialStatus() {
		return artificialStatus;
	}

	public void setArtificialStatus(String artificialStatus) {
		this.artificialStatus = artificialStatus;
	}

	public double getComprehensivePercentage() {
		return comprehensivePercentage;
	}

	public void setComprehensivePercentage(double comprehensivePercentage) {
		this.comprehensivePercentage = comprehensivePercentage;
	}

	public String getComprehensiveStatus() {
		return comprehensiveStatus;
	}

	public void setComprehensiveStatus(String comprehensiveStatus) {
		this.comprehensiveStatus = comprehensiveStatus;
	}

	public double getPaymentBalance() {
		return paymentBalance;
	}

	public void setPaymentBalance(double paymentBalance) {
		this.paymentBalance = paymentBalance;
	}

	public String getPaymentBalanceStatus() {
		return paymentBalanceStatus;
	}

	public void setPaymentBalanceStatus(String paymentBalanceStatus) {
		this.paymentBalanceStatus = paymentBalanceStatus;
	}

	public double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}

	public String getCashBalanceStatus() {
		return cashBalanceStatus;
	}

	public void setCashBalanceStatus(String cashBalanceStatus) {
		this.cashBalanceStatus = cashBalanceStatus;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public ContractInfo getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(ContractInfo contractInfo) {
		this.contractInfo = contractInfo;
	}

	

}