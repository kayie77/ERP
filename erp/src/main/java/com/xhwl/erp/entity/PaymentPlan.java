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
@Table(name="fi_paymentPlan")
@ApiModel
public class PaymentPlan implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "回款条件")
    private String paymentCondition;
    
    @ApiModelProperty(value = "计划回款时间")
    private Date date;
    
    @ApiModelProperty(value = "计划回款比例")
    private String ratio;
    
    @ApiModelProperty(value = "计划回款金额")
    private double amount;
    
    @ApiModelProperty(value = "计划回款累计金额")
    private double cumulativeAmount;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private ContractBasis contractBasis;//所属合同交底信息

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCumulativeAmount() {
		return cumulativeAmount;
	}

	public void setCumulativeAmount(double cumulativeAmount) {
		this.cumulativeAmount = cumulativeAmount;
	}

	public void setCumulativeAmount(float cumulativeAmount) {
		this.cumulativeAmount = cumulativeAmount;
	}

	public ContractBasis getContractBasis() {
		return contractBasis;
	}

	public void setContractBasis(ContractBasis contractBasis) {
		this.contractBasis = contractBasis;
	}

	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}
	
}