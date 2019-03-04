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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="mk_project_ref_dtl")
@ApiModel
public class ProjectReformDetaile implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "数量、方式、品牌、面积、年份")
	private String a;
    
    @ApiModelProperty(value = "数量、方式、品牌、面积、年份")
	private String b;

    @ApiModelProperty(value = "数量、方式、品牌、面积、年份")
	private String c;
    
    @ApiModelProperty(value = "数量、方式、品牌、面积、年份")
	private String d;
    
    @ApiModelProperty(value = "合约期限（起始日期）")
    private Date startDate;
    
    @ApiModelProperty(value = "合约期限（结束日期）")
    private Date endDate;
    
    @ApiModelProperty(value = "投入金额")
    private double amount;
    
    @ApiModelProperty(value = "系统类型")
    private String category;
	
	@JsonIgnore
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="projectReform_id")
	private ProjectReform projectReform;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ProjectReform getProjectReform() {
		return projectReform;
	}

	public void setProjectReform(ProjectReform projectReform) {
		this.projectReform = projectReform;
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

	@Override
	public String toString() {
		return "ProjectReformDetaile [id=" + id + ", a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", amount="
				+ amount + ", category=" + category + ", projectReform=" + projectReform + "]";
	}
	
}