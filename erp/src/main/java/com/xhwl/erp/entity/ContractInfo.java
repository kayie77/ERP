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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name="fi_contractInfo")
@ApiModel
public class ContractInfo implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "合同名称")
    private String name;

    @ApiModelProperty(value = "合同编码")
    private String code;
    
    @ApiModelProperty(value = "合同金额")
    private double originalAmount;
    
    @ApiModelProperty(value = "变更金额")
    private double changeAmount;
    
    @ApiModelProperty(value = "合同总额")
    private double contractTotalAmount;
    
    @ApiModelProperty(value = "已开票金额")
    private double invoicedAmount;
    
    @ApiModelProperty(value = "已回款金额")
    private double receivedAmount;
    
    @ApiModelProperty(value = "预算成本金额")
    private double budgetAmount;
    
    @ApiModelProperty(value = "合同签订时间")
    private Date signDate;
    
    @ApiModelProperty(value = "合同所属期")
    private Date term;
    
    @ApiModelProperty(value = "合同期限（起始日期）")
    private Date startDate;
    
    @ApiModelProperty(value = "合同期限（结束日期）")
    private Date endDate;
	
	@ApiModelProperty(value = "合同文本")
    private String text;
	
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    private Business business;//所属商机
	
	@Transient
	private String oldCity;

	public String getOldCity() {
		return oldCity;
	}

	public void setOldCity(String oldCity) {
		this.oldCity = oldCity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public double getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(double originalAmount) {
		this.originalAmount = originalAmount;
	}

	public double getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(double changeAmount) {
		this.changeAmount = changeAmount;
	}

	public double getContractTotalAmount() {
		return contractTotalAmount;
	}

	public void setContractTotalAmount(double contractTotalAmount) {
		this.contractTotalAmount = contractTotalAmount;
	}

	public double getInvoicedAmount() {
		return invoicedAmount;
	}

	public void setInvoicedAmount(double invoicedAmount) {
		this.invoicedAmount = invoicedAmount;
	}

	public double getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(double receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public double getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Date getTerm() {
		return term;
	}

	public void setTerm(Date term) {
		this.term = term;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

}