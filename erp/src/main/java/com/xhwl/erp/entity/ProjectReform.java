package com.xhwl.erp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="mk_project_ref")
@ApiModel
public class ProjectReform implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "改造年份")
	private String year;
    
    @ApiModelProperty(value = "投入金额",hidden = true)
    private double amount;
	
	@JsonIgnore
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="project_id")
	private Project project;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="projectReform",fetch=FetchType.LAZY)
	private List<ProjectReformDetaile> projectReformDetailes = new ArrayList<ProjectReformDetaile>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<ProjectReformDetaile> getProjectReformDetailes() {
		return projectReformDetailes;
	}

	public void setProjectReformDetailes(List<ProjectReformDetaile> projectReformDetailes) {
		this.projectReformDetailes = projectReformDetailes;
	}

	@Override
	public String toString() {
		return "ProjectReform [id=" + id + ", year=" + year + ", amount=" + amount + ", project=" + project + "]";
	}
	
}