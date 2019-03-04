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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cost_outboundDetaile")
@ApiModel
public class OutboundDetaile implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "出库数量")
    private int number;
    
    @ApiModelProperty(value = "出库差额数量")
    private int diffNumber;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="outboundList_id")
	private OutboundList outboundList;//所属出库清单

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

	public PurchaseList getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(PurchaseList purchaseList) {
		this.purchaseList = purchaseList;
	}

	public OutboundList getOutboundList() {
		return outboundList;
	}

	public void setOutboundList(OutboundList outboundList) {
		this.outboundList = outboundList;
	}
	
	public int getDiffNumber() {
		return diffNumber;
	}

	public void setDiffNumber(int diffNumber) {
		this.diffNumber = diffNumber;
	}

	@Override
	public String toString() {
		return "OutboundDetaile [id=" + id + ", number=" + number + ", purchaseList=" + purchaseList + "]";
	}
	
}