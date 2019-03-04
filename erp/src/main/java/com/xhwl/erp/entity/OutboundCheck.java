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
@Table(name="cost_outboundCheck")
@ApiModel
public class OutboundCheck implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "审批步骤")
    private String step ;

    @ApiModelProperty(value = "操作人",hidden = true)
    private String stepPerson;
    
    @ApiModelProperty(value = "操作时间",hidden = true)
    private Date time;
    
    @ApiModelProperty(value = "下一步骤",hidden = true)
    private String nextStep;

    @ApiModelProperty(value = "下一步骤审批人",hidden = true)
    private String nextStepPerson;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private OutboundList outboundList;//所属出库清单

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getStepPerson() {
		return stepPerson;
	}

	public void setStepPerson(String stepPerson) {
		this.stepPerson = stepPerson;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getNextStep() {
		return nextStep;
	}

	public void setNextStep(String nextStep) {
		this.nextStep = nextStep;
	}

	public String getNextStepPerson() {
		return nextStepPerson;
	}

	public void setNextStepPerson(String nextStepPerson) {
		this.nextStepPerson = nextStepPerson;
	}

	public OutboundList getOutboundList() {
		return outboundList;
	}

	public void setOutboundList(OutboundList outboundList) {
		this.outboundList = outboundList;
	}

	@Override
	public String toString() {
		return "OutboundCheck [id=" + id + ", step=" + step + ", stepPerson=" + stepPerson + ", time=" + time
				+ ", nextStep=" + nextStep + ", nextStepPerson=" + nextStepPerson + ", outboundList=" + outboundList
				+ "]";
	}
	
}