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
@Table(name="cost_price")
@ApiModel
public class Price implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产品名称")
    private String name;

    @ApiModelProperty(value = "产品编码")
    private String code;
    
    @ApiModelProperty(value = "产品类型")
    private String type;
    
    @ApiModelProperty(value = "单位")
    private String unit;
    
    @ApiModelProperty(value = "所属系统")
    private String system;
    
    @ApiModelProperty(value = "规格型号")
    private String specModel;
    
    @ApiModelProperty(value = "品牌")
    private String brand;

    @ApiModelProperty(value = "产品报价")
    private double productQuotation;
    
    @ApiModelProperty(value = "价格来源")
    private String source;
    
    @ApiModelProperty(value = "供货周期")
    private String supplyCycle;
    
    @ApiModelProperty(value = "有效期限(开始)")
    private Date startDate;
    
    @ApiModelProperty(value = "有效期限(结束)")
    private Date endDate;
    
    @ApiModelProperty(value = "参数描述")
    private String description;
    
    @ApiModelProperty(value = "文件链接")
    private String url;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Supply supply;//所属供应商
    
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getSpecModel() {
		return specModel;
	}

	public void setSpecModel(String specModel) {
		this.specModel = specModel;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getProductQuotation() {
		return productQuotation;
	}

	public void setProductQuotation(double productQuotation) {
		this.productQuotation = productQuotation;
	}

	public String getSupplyCycle() {
		return supplyCycle;
	}

	public void setSupplyCycle(String supplyCycle) {
		this.supplyCycle = supplyCycle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}