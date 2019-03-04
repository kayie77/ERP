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
@Table(name="fi_contractBasisEnclosure")
@ApiModel
public class ContractBasisEnclosure implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "附件名称")
    private String fileName;
    
    @ApiModelProperty(value = "附件说明")
    private String describtion;
    
    @ApiModelProperty(value = "上传人")
    private String person;
    
    @ApiModelProperty(value = "上传时间")
    private Date date;
    
    @ApiModelProperty(value = "文件地址")
    private String url;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private ContractBasis contractBasis;//所属合同交底

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public ContractBasis getContractBasis() {
		return contractBasis;
	}

	public void setContractBasis(ContractBasis contractBasis) {
		this.contractBasis = contractBasis;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


}