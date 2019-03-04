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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cost_inboundList")
@ApiModel
public class InboundList implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "入库单编号",hidden = true)
    private String code;
    
    @ApiModelProperty(value = "制表人",hidden = true)
    private String person;
    
    @ApiModelProperty(value = "入库成本核算表号",hidden = true)
    private String tableCode;
    
    @ApiModelProperty(value = "入库日期",hidden = true)
    private Date date;
    
    @ApiModelProperty(value = "状态",hidden = true)
    private String state;
    
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private PaymentContract paymentContract;//所属付款合同
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PaymentContract getPaymentContract() {
		return paymentContract;
	}

	public void setPaymentContract(PaymentContract paymentContract) {
		this.paymentContract = paymentContract;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
	
	

}