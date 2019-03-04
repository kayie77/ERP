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
@Table(name="fi_contractReceived")
@ApiModel
public class ContractReceived implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "回款金额")
    private double amount;
    
    @ApiModelProperty(value = "回款差额")
    private double diffAmount;
    
    @ApiModelProperty(value = "回款日期")
    private Date date;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private ContractBilling contractBilling;//所属发票

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

	public ContractBilling getContractBilling() {
		return contractBilling;
	}

	public void setContractBilling(ContractBilling contractBilling) {
		this.contractBilling = contractBilling;
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getDiffAmount() {
		return diffAmount;
	}

	public void setDiffAmount(double diffAmount) {
		this.diffAmount = diffAmount;
	}

	@Override
	public String toString() {
		return "ContractReceived [id=" + id + ", amount=" + amount + ", date=" + date 
				+ ", contractBilling=" + contractBilling + "]";
	}

}