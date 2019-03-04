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
@Table(name="cost_priceHistory")
@ApiModel
public class PriceHistory implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产品报价")
    private double productQuotation;

    @ApiModelProperty(value = "修改人")
    private String person;
    
    @ApiModelProperty(value = "修改时间")
    private Date time;
    
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Price price;//所属价格体系

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getProductQuotation() {
		return productQuotation;
	}

	public void setProductQuotation(double productQuotation) {
		this.productQuotation = productQuotation;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PriceHistory [id=" + id + ", productQuotation=" + productQuotation + ", person=" + person + ", time="
				+ time + ", price=" + price + "]";
	}
	
}