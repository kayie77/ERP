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

@Entity
@Table(name="sys_city")
@ApiModel
public class City implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "pid")
    private City city;
    
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", pid=" + city +
                '}';
    }

}