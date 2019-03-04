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
@Table(name="fi_contractPayment")
@ApiModel
public class ContractPayment implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "投入金额（总额）")
    private double totalAmount;

    @ApiModelProperty(value = "材料支出")
    private double materialCost;
    
    @ApiModelProperty(value = "材料支出差额")
    private double diffMaterialCost;
    
    @ApiModelProperty(value = "人工支出")
    private double artificialCost;
    
    @ApiModelProperty(value = "人工支出差额")
    private double diffArtificialCost;
    
    @ApiModelProperty(value = "综合支出")
    private double comprehensiveCost;
    
    @ApiModelProperty(value = "综合支出差额")
    private double diffComprehensiveCost;
    
    @ApiModelProperty(value = "管理费用")
    private double manageCost;
    
    @ApiModelProperty(value = "管理费用差额")
    private double diffManageCost;
    
    @ApiModelProperty(value = "税金")
    private double tax;
    
    @ApiModelProperty(value = "税金差额")
    private double diffTax;
    
    @ApiModelProperty(value = "毛利润")
    private String profit;
    
    @ApiModelProperty(value = "毛利润率")
    private String profitRate;
    
    @ApiModelProperty(value = "投入日期")
    private Date inputDate;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private ContractInfo contractInfo;//所属合同

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public double getMaterialCost() {
		return materialCost;
	}

	public void setMaterialCost(double materialCost) {
		this.materialCost = materialCost;
	}

	public double getArtificialCost() {
		return artificialCost;
	}

	public void setArtificialCost(double artificialCost) {
		this.artificialCost = artificialCost;
	}

	public double getComprehensiveCost() {
		return comprehensiveCost;
	}

	public void setComprehensiveCost(double comprehensiveCost) {
		this.comprehensiveCost = comprehensiveCost;
	}

	public double getManageCost() {
		return manageCost;
	}

	public void setManageCost(double manageCost) {
		this.manageCost = manageCost;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setDiffMaterialCost(double diffMaterialCost) {
		this.diffMaterialCost = diffMaterialCost;
	}

	public void setDiffArtificialCost(double diffArtificialCost) {
		this.diffArtificialCost = diffArtificialCost;
	}

	public void setDiffComprehensiveCost(double diffComprehensiveCost) {
		this.diffComprehensiveCost = diffComprehensiveCost;
	}

	public void setDiffManageCost(double diffManageCost) {
		this.diffManageCost = diffManageCost;
	}

	public void setDiffTax(double diffTax) {
		this.diffTax = diffTax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(String profitRate) {
		this.profitRate = profitRate;
	}

	public ContractInfo getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(ContractInfo contractInfo) {
		this.contractInfo = contractInfo;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public double getDiffMaterialCost() {
		return diffMaterialCost;
	}

	public double getDiffArtificialCost() {
		return diffArtificialCost;
	}

	public double getDiffComprehensiveCost() {
		return diffComprehensiveCost;
	}

	public double getDiffManageCost() {
		return diffManageCost;
	}

	public double getDiffTax() {
		return diffTax;
	}

	@Override
	public String toString() {
		return "ContractPayment [id=" + id + ", materialCost=" + materialCost + ", artificialCost=" + artificialCost
				+ ", comprehensiveCost=" + comprehensiveCost + ", manageCost=" + manageCost + ", tax=" + tax
				+ ", profit=" + profit + ", profitRate=" + profitRate + ", contractInfo=" + contractInfo + "]";
	}

	
}