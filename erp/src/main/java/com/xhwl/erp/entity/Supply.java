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
@Table(name="cost_supply")
@ApiModel
public class Supply implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "供应商名称")
    private String name;

    @ApiModelProperty(value = "企业法人")
    private String enterprisePerson;
    
    @ApiModelProperty(value = "注册资金")
    private String regCapital;
    
    @ApiModelProperty(value = "物资类别")
    private String materialCategory;
    
    @ApiModelProperty(value = "物资明细")
    private String materialDetails;
    
    @ApiModelProperty(value = "企业性质")
    private String enterpriseNature;
    
    @ApiModelProperty(value = "营业执照号")
    private String licenseNumber;

    @ApiModelProperty(value = "注册地址")
    private String regAddress;
    
    @ApiModelProperty(value = "现址")
    private String address;
    
    @ApiModelProperty(value = "联系人")
    private String person;
    
    @ApiModelProperty(value = "联系电话")
    private String phone;
    
    @ApiModelProperty(value = "QQ号")
    private String qq;
    
    @ApiModelProperty(value = "开户银行")
    private String bank;
    
    @ApiModelProperty(value = "银行帐号")
    private String bankAccount;
    
    @ApiModelProperty(value = "发票类型")
    private String invoiceType;
    
    @ApiModelProperty(value = "税率")
    private String taxRate;
    
    @ApiModelProperty(value = "供应商类型")
    private String type;
    
    @ApiModelProperty(value = "供应商类别")
    private String category;
    
    @ApiModelProperty(value = "供应商来源")
    private String source;
    
    @ApiModelProperty(value = "供应商等级")
    private String grade;
    
    @ApiModelProperty(value = "等级评审处理")
    private String reviewHandle;
    
    @ApiModelProperty(value = "等级评审说明")
    private String reviewDescription;
    
    @ApiModelProperty(value = "合作商类型")
    private String cooperativeType;
    
    @ApiModelProperty(value = "供货周期")
    private String supplyCycle;
    
    @ApiModelProperty(value = "评审状态")
    private String reviewState;
    
    @ApiModelProperty(value = "结算方式")
    private String settlementMethod;
    
    @ApiModelProperty(value = "处理有效期限（开始）")
    private Date startDate;
    
    @ApiModelProperty(value = "处理有效期限（结束）")
    private Date endDate;
    
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private SupplyRegion supplyRegion;//所属供应商区域
    
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnterprisePerson() {
		return enterprisePerson;
	}

	public void setEnterprisePerson(String enterprisePerson) {
		this.enterprisePerson = enterprisePerson;
	}

	public String getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}

	public String getMaterialCategory() {
		return materialCategory;
	}

	public void setMaterialCategory(String materialCategory) {
		this.materialCategory = materialCategory;
	}

	public String getEnterpriseNature() {
		return enterpriseNature;
	}

	public void setEnterpriseNature(String enterpriseNature) {
		this.enterpriseNature = enterpriseNature;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getCooperativeType() {
		return cooperativeType;
	}

	public void setCooperativeType(String cooperativeType) {
		this.cooperativeType = cooperativeType;
	}

	public String getSupplyCycle() {
		return supplyCycle;
	}

	public void setSupplyCycle(String supplyCycle) {
		this.supplyCycle = supplyCycle;
	}

	public String getReviewState() {
		return reviewState;
	}

	public void setReviewState(String reviewState) {
		this.reviewState = reviewState;
	}

	public String getSettlementMethod() {
		return settlementMethod;
	}

	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public SupplyRegion getSupplyRegion() {
		return supplyRegion;
	}

	public void setSupplyRegion(SupplyRegion supplyRegion) {
		this.supplyRegion = supplyRegion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMaterialDetails() {
		return materialDetails;
	}

	public void setMaterialDetails(String materialDetails) {
		this.materialDetails = materialDetails;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getReviewHandle() {
		return reviewHandle;
	}

	public void setReviewHandle(String reviewHandle) {
		this.reviewHandle = reviewHandle;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}



}