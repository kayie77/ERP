package com.xhwl.erp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cost_purchaseList")
@ApiModel
public class PurchaseList implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "物料名称")
    private String name;

    @ApiModelProperty(value = "规格型号")
    private String model;
    
    @ApiModelProperty(value = "品牌")
    private String brand;
    
    @ApiModelProperty(value = "单价")
    private double unitPrice;
    
	@ApiModelProperty(value = "计划数量")
    private int adNumber;
	
	@ApiModelProperty(value = "计划金额")
    private double adAmount;
	
	@ApiModelProperty(value = "实际数量")
    private int acNumber;
	
	@ApiModelProperty(value = "实际金额")
    private double acAmount;
    
    @ApiModelProperty(value = "单位")
    private String unit;

    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="paymentContract_id")
	private PaymentContract paymentContract;//所属付款合同
    

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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public PaymentContract getPaymentContract() {
		return paymentContract;
	}

	public void setPaymentContract(PaymentContract paymentContract) {
		this.paymentContract = paymentContract;
	}

	public int getAdNumber() {
		return adNumber;
	}

	public void setAdNumber(int adNumber) {
		this.adNumber = adNumber;
	}

	public int getAcNumber() {
		return acNumber;
	}

	public void setAcNumber(int acNumber) {
		this.acNumber = acNumber;
	}

	public void setAcAmount(float acAmount) {
		this.acAmount = acAmount;
	}
	
}