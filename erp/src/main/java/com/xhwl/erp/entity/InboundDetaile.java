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
@Table(name="cost_inboundDetaile")
@ApiModel
public class InboundDetaile implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "实际收取数量")
    private int number;
    
	@ApiModelProperty(value = "剩余数量")
    private int surplusNumber;

    @ApiModelProperty(value = "规格型号")
    private String model;
    
    @ApiModelProperty(value = "质量外观")
    private String quality;
    
    @ApiModelProperty(value = "合格证")
    private String certificate;
    
    @ApiModelProperty(value = "合计")
    private double totalAmount;
    
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private PurchaseList purchaseList;//所属采购清单

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public PurchaseList getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(PurchaseList purchaseList) {
		this.purchaseList = purchaseList;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getSurplusNumber() {
		return surplusNumber;
	}

	public void setSurplusNumber(int surplusNumber) {
		this.surplusNumber = surplusNumber;
	}

	@Override
	public String toString() {
		return "InboundDetaile [id=" + id + ", number=" + number + ", model=" + model + ", quality=" + quality
				+ ", certificate=" + certificate + ", purchaseList=" + purchaseList + "]";
	}
	
}