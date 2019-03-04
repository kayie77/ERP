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
@Table(name="cost_paymentContract")
@ApiModel
public class PaymentContract implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "申请时间")
    private Date applicationTime;

    @ApiModelProperty(value = "申请人")
    private String applicationPerson;
    
    @ApiModelProperty(value = "订单编号")
    private String orderCode;
    
    @ApiModelProperty(value = "付款合同编号")
    private String code;
    
    @ApiModelProperty(value = "优化成本")
    private String optCost;
    
    @ApiModelProperty(value = "使用部门")
    private String department;
    
    @ApiModelProperty(value = "使用项目")
    private String project;

    @ApiModelProperty(value = "会签单号")
    private String SignNumber;
    
    @ApiModelProperty(value = "凭单号")
    private String oddNumber;
    
    @ApiModelProperty(value = "发货状态")
    private String deliveryStatus;
    
    @ApiModelProperty(value = "业务分类")
    private String category;
    
    @ApiModelProperty(value = "应付金额")
    private String payableAmount;
    
    @ApiModelProperty(value = "支付对象")
    private String paymentObject;
    
    @ApiModelProperty(value = "到付时间")
    private Date payTime;
    
    @ApiModelProperty(value = "账期")
    private String term;
    
    @ApiModelProperty(value = "是否自提")
    private String mention;
    
    @ApiModelProperty(value = "计划采购金额")
    private double adAmount;
    
    @ApiModelProperty(value = "实际采购金额")
    private double acAmount;
    
    @ApiModelProperty(value = "已开票金额")
    private double billingAmount;
    
    @ApiModelProperty(value = "已付款金额")
    private double paymentAmount;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private ContractInfo contractInfo;//所属合同
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Supply supply;//所属供应商
    
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private MaterialCategory materialCategory;//所属材料类型
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getApplicationPerson() {
		return applicationPerson;
	}

	public void setApplicationPerson(String applicationPerson) {
		this.applicationPerson = applicationPerson;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOptCost() {
		return optCost;
	}

	public void setOptCost(String optCost) {
		this.optCost = optCost;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSignNumber() {
		return SignNumber;
	}

	public void setSignNumber(String signNumber) {
		SignNumber = signNumber;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public MaterialCategory getMaterialCategory() {
		return materialCategory;
	}

	public void setMaterialCategory(MaterialCategory materialCategory) {
		this.materialCategory = materialCategory;
	}

	public String getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(String payableAmount) {
		this.payableAmount = payableAmount;
	}

	public String getPaymentObject() {
		return paymentObject;
	}

	public void setPaymentObject(String paymentObject) {
		this.paymentObject = paymentObject;
	}

	public String getMention() {
		return mention;
	}
	
	public Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public void setMention(String mention) {
		this.mention = mention;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public ContractInfo getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(ContractInfo contractInfo) {
		this.contractInfo = contractInfo;
	}
	
	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	public String getOddNumber() {
		return oddNumber;
	}

	public void setOddNumber(String oddNumber) {
		this.oddNumber = oddNumber;
	}

	public double getAdAmount() {
		return adAmount;
	}

	public void setAdAmount(double adAmount) {
		this.adAmount = adAmount;
	}

	public double getAcAmount() {
		return acAmount;
	}

	public void setAcAmount(double acAmount) {
		this.acAmount = acAmount;
	}

	public double getBillingAmount() {
		return billingAmount;
	}

	public void setBillingAmount(double billingAmount) {
		this.billingAmount = billingAmount;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

}