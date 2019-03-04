package com.xhwl.erp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="fi_contractBasis")
@ApiModel
public class ContractBasis implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "资金来源")
    private String sourceFunds;
    
    @ApiModelProperty(value = "材料支出")
    private double materialCost;
    
    @ApiModelProperty(value = "人工支出")
    private double artificialCost;
    
    @ApiModelProperty(value = "综合支出")
    private double comprehensiveCost;
    
    @ApiModelProperty(value = "管理费用")
    private double manageCost;
    
    @ApiModelProperty(value = "税金")
    private double tax;
    
    @ApiModelProperty(value = "毛利润")
    private double profit;
    
    @ApiModelProperty(value = "毛利润率")
    private double profitRate;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private ContractInfo contractInfo;//所属合同

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSourceFunds() {
		return sourceFunds;
	}

	public void setSourceFunds(String sourceFunds) {
		this.sourceFunds = sourceFunds;
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

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public double getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(double profitRate) {
		this.profitRate = profitRate;
	}

	public ContractInfo getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(ContractInfo contractInfo) {
		this.contractInfo = contractInfo;
	}

	@Override
	public String toString() {
		return "ContractBasis [id=" + id + ", sourceFunds=" + sourceFunds + ", materialCost=" + materialCost
				+ ", artificialCost=" + artificialCost + ", comprehensiveCost=" + comprehensiveCost + ", manageCost="
				+ manageCost + ", tax=" + tax + ", profit=" + profit + ", profitRate=" + profitRate + ", contractInfo="
				+ contractInfo + "]";
	}
	
}