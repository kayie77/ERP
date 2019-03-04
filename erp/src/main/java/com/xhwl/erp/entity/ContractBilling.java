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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="fi_contractBilling",uniqueConstraints={@UniqueConstraint(columnNames={"number"})})
@ApiModel
public class ContractBilling implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "发票抬头名称")
    private String name;

    @ApiModelProperty(value = "开票金额")
    private double amount;
    
    @ApiModelProperty(value = "开票差额")
    private double diffAmount;
    
    @ApiModelProperty(value = "开票日期")
    private Date date;
    
    @ApiModelProperty(value = "发票号码")
    private String number;
    
    @ApiModelProperty(value = "开票内容")
    private String content;
    
    @ApiModelProperty(value = "税率")
    private String taxRate;
    
    @ApiModelProperty(value = "税金")
    private String tax;
    
    @ApiModelProperty(value = "收入（不含税）")
    private String income;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private ContractInfo contractInfo;//所属合同

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ContractInfo getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(ContractInfo contractInfo) {
		this.contractInfo = contractInfo;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
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
		return "ContractBilling [id=" + id + ", name=" + name + ", amount=" + amount + ", date=" + date + ", number="
				+ number + ", content=" + content + ", taxRate=" + taxRate + ", tax=" + tax + ", income=" + income
				+ ", contractInfo=" + contractInfo + "]";
	}
	
	
}