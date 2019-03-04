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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="mk_project_des")
@ApiModel
public class ProjectDesign implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "外围车行口 入口数量")
	private String a;
    
    @ApiModelProperty(value = "外围车行口 出口数量")
    private String b;

    @ApiModelProperty(value = "地库车行口 入口数量")
    private String c;
    
    @ApiModelProperty(value = "地库车行口 出口数量")
    private String a1;

    @ApiModelProperty(value = "园区车行口 入口数量")
    private String b1;
    
    @ApiModelProperty(value = "园区车行口 出口数量")
    private String c1;
    
	//@JsonSerialize(using = ProjectDesignCategorySerializer.class)
    private String category;
	
	@JsonIgnore
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="project_id")
	private Project project;
	
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

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public String getB1() {
		return b1;
	}

	public void setB1(String b1) {
		this.b1 = b1;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
    public String toString() {
        return "ProjectDesign{" +
                "a='" + a + '\'' +
                ", b=" + b +
                ", c=" + c +
                ", a1=" + a1 +
                ", b1=" + b1 +
                ", c1=" + c1 +
                ", category=" + category +
                '}';
    }

}