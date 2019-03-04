package com.xhwl.erp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="fi_contractAnalysis")
@ApiModel
public class ContractAnalysis implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "累计开票金额")
    private double totalBillingAmount;
    
    @ApiModelProperty(value = "累计回款金额")
    private double totalReceivedAmount;
    
    @ApiModelProperty(value = "累计采购金额")
    private double totalPurchaseAmount;
    
    @ApiModelProperty(value = "合同总额")
    private double contractTotalAmount;
    
    @ApiModelProperty(value = "材料支出成本（交底）")
    private double basisMaterialCost;
    
    @ApiModelProperty(value = "人工支出成本（交底）")
    private double basisArtificialCost;
    
    @ApiModelProperty(value = "综合支出成本（交底）")
    private double basisComprehensiveCost;

    @ApiModelProperty(value = "累计投入金额（付款）")
    private double payTotalAmount;
    
    @ApiModelProperty(value = "材料支出成本（付款）")
    private double payMaterialCost;
    
    @ApiModelProperty(value = "人工累计投入（付款）")
    private double payArtificialCost;
    
    @ApiModelProperty(value = "综合累计投入（付款）")
    private double payComprehensiveCost;
    
    @ApiModelProperty(value = "管理累计投入（付款）")
    private double payManageCost;
    
    @ApiModelProperty(value = "税金累计投入（付款）")
    private double payTaxCost;
    
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private ContractInfo contractInfo;//所属合同

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotalBillingAmount() {
		return totalBillingAmount;
	}

	public void setTotalBillingAmount(double totalBillingAmount) {
		this.totalBillingAmount = totalBillingAmount;
	}

	public double getTotalReceivedAmount() {
		return totalReceivedAmount;
	}

	public void setTotalReceivedAmount(double totalReceivedAmount) {
		this.totalReceivedAmount = totalReceivedAmount;
	}

	public double getTotalPurchaseAmount() {
		return totalPurchaseAmount;
	}

	public void setTotalPurchaseAmount(double totalPurchaseAmount) {
		this.totalPurchaseAmount = totalPurchaseAmount;
	}

	public double getContractTotalAmount() {
		return contractTotalAmount;
	}

	public void setContractTotalAmount(double contractTotalAmount) {
		this.contractTotalAmount = contractTotalAmount;
	}

	public double getBasisMaterialCost() {
		return basisMaterialCost;
	}

	public void setBasisMaterialCost(double basisMaterialCost) {
		this.basisMaterialCost = basisMaterialCost;
	}

	public double getBasisArtificialCost() {
		return basisArtificialCost;
	}

	public void setBasisArtificialCost(double basisArtificialCost) {
		this.basisArtificialCost = basisArtificialCost;
	}

	public double getBasisComprehensiveCost() {
		return basisComprehensiveCost;
	}

	public void setBasisComprehensiveCost(double basisComprehensiveCost) {
		this.basisComprehensiveCost = basisComprehensiveCost;
	}

	public double getPayTotalAmount() {
		return payTotalAmount;
	}

	public void setPayTotalAmount(double payTotalAmount) {
		this.payTotalAmount = payTotalAmount;
	}

	public double getPayMaterialCost() {
		return payMaterialCost;
	}

	public void setPayMaterialCost(double payMaterialCost) {
		this.payMaterialCost = payMaterialCost;
	}

	public double getPayArtificialCost() {
		return payArtificialCost;
	}

	public void setPayArtificialCost(double payArtificialCost) {
		this.payArtificialCost = payArtificialCost;
	}

	public double getPayComprehensiveCost() {
		return payComprehensiveCost;
	}

	public void setPayComprehensiveCost(double payComprehensiveCost) {
		this.payComprehensiveCost = payComprehensiveCost;
	}

	public void setPayComprehensiveCost(float payComprehensiveCost) {
		this.payComprehensiveCost = payComprehensiveCost;
	}

	public double getPayManageCost() {
		return payManageCost;
	}

	public void setPayManageCost(double payManageCost) {
		this.payManageCost = payManageCost;
	}

	public double getPayTaxCost() {
		return payTaxCost;
	}

	public void setPayTaxCost(double payTaxCost) {
		this.payTaxCost = payTaxCost;
	}

	public ContractInfo getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(ContractInfo contractInfo) {
		this.contractInfo = contractInfo;
	}
	
	
}