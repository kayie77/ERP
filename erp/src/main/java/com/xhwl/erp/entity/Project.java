package com.xhwl.erp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name="mk_project")
@ApiModel
public class Project implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "管理处名称")
    private String name;
    
    @ApiModelProperty(value = "管理处地址")
    private String address;

    @ApiModelProperty(value = "楼栋及单元数量")
    private String buildNum;
    
    @ApiModelProperty(value = "首期入伙时间")
    private Date firstEntry;
    
    @ApiModelProperty(value = "总收费面积")
    private String chargeArea;
    
    @ApiModelProperty(value = "总占地面积")
    private String landArea;
    
    @ApiModelProperty(value = "总建筑面积")
    private String builtArea;
   
    @ApiModelProperty(value = "容积率")
    private String volumetricRate;
    
    @ApiModelProperty(value = "建筑业态")
    private String archFormat;
    
    @ApiModelProperty(value = "物业管理费")
    private String manageFee;
    
    @ApiModelProperty(value = "总户数")
    private String roomNum;
    
    @ApiModelProperty(value = "小区类型")
    private String communityType;
    
    @ApiModelProperty(value = "合约模式")
    private String contractMode;
    
    @ApiModelProperty(value = "车位总数")
    private String parkingNum;
    
    @ApiModelProperty(value = "车位比")
    private String carRatio;
    
    @ApiModelProperty(value = "地面车位数量")
    private String groundParkingNum;
    
    @ApiModelProperty(value = "地库车位数量")
    private String basementParkingNum;
    
    @ApiModelProperty(value = "人防车位数量")
    private String defenseParkingNum;
    
    @ApiModelProperty(value = "地面车位收费标准")
    private String groundParkingFee;
    
    @ApiModelProperty(value = "地库车位收费标准")
    private String basementParkingFee;
    
    @ApiModelProperty(value = "人防车位收费标准")
    private String defenseParkingFee;
    
    @ApiModelProperty(value = "小区配套")
    private String facility;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Region region;//办事处
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Client client;//公司名称
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private City city;//城市
    
	@OneToMany(cascade={CascadeType.ALL},mappedBy="project",fetch=FetchType.LAZY)
	private List<ProjectDesign> projectDesigns = new ArrayList<ProjectDesign>();
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="project",fetch=FetchType.LAZY)
	private List<ProjectReform> projectReforms = new ArrayList<ProjectReform>();
	
	@Transient
	private String oldCity;
    
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

	public String getBuildNum() {
		return buildNum;
	}

	public void setBuildNum(String buildNum) {
		this.buildNum = buildNum;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getFirstEntry() {
		return firstEntry;
	}

	public void setFirstEntry(Date firstEntry) {
		this.firstEntry = firstEntry;
	}

	public String getChargeArea() {
		return chargeArea;
	}

	public void setChargeArea(String chargeArea) {
		this.chargeArea = chargeArea;
	}

	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}

	public String getBuiltArea() {
		return builtArea;
	}

	public void setBuiltArea(String builtArea) {
		this.builtArea = builtArea;
	}

	public String getVolumetricRate() {
		return volumetricRate;
	}

	public void setVolumetricRate(String volumetricRate) {
		this.volumetricRate = volumetricRate;
	}

	public String getArchFormat() {
		return archFormat;
	}

	public void setArchFormat(String archFormat) {
		this.archFormat = archFormat;
	}

	public String getManageFee() {
		return manageFee;
	}

	public void setManageFee(String manageFee) {
		this.manageFee = manageFee;
	}

	public String getCommunityType() {
		return communityType;
	}

	public void setCommunityType(String communityType) {
		this.communityType = communityType;
	}

	public String getContractMode() {
		return contractMode;
	}

	public void setContractMode(String contractMode) {
		this.contractMode = contractMode;
	}

	public String getParkingNum() {
		return parkingNum;
	}

	public void setParkingNum(String parkingNum) {
		this.parkingNum = parkingNum;
	}

	public String getCarRatio() {
		return carRatio;
	}

	public void setCarRatio(String carRatio) {
		this.carRatio = carRatio;
	}

	public String getGroundParkingNum() {
		return groundParkingNum;
	}

	public void setGroundParkingNum(String groundParkingNum) {
		this.groundParkingNum = groundParkingNum;
	}

	public String getBasementParkingNum() {
		return basementParkingNum;
	}

	public void setBasementParkingNum(String basementParkingNum) {
		this.basementParkingNum = basementParkingNum;
	}

	public String getDefenseParkingNum() {
		return defenseParkingNum;
	}

	public void setDefenseParkingNum(String defenseParkingNum) {
		this.defenseParkingNum = defenseParkingNum;
	}

	public String getGroundParkingFee() {
		return groundParkingFee;
	}

	public void setGroundParkingFee(String groundParkingFee) {
		this.groundParkingFee = groundParkingFee;
	}

	public String getBasementParkingFee() {
		return basementParkingFee;
	}

	public void setBasementParkingFee(String basementParkingFee) {
		this.basementParkingFee = basementParkingFee;
	}

	public String getDefenseParkingFee() {
		return defenseParkingFee;
	}

	public void setDefenseParkingFee(String defenseParkingFee) {
		this.defenseParkingFee = defenseParkingFee;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
    public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<ProjectDesign> getProjectDesigns() {
		return projectDesigns;
	}

	public void setProjectDesigns(List<ProjectDesign> projectDesigns) {
		this.projectDesigns = projectDesigns;
	}

	public String getOldCity() {
		return oldCity;
	}

	public void setOldCity(String oldCity) {
		this.oldCity = oldCity;
	}

	public List<ProjectReform> getProjectReforms() {
		return projectReforms;
	}

	public void setProjectReforms(List<ProjectReform> projectReforms) {
		this.projectReforms = projectReforms;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", buildNum=" + buildNum + ", roomNum=" + roomNum + ", address="
				+ address + ", firstEntry=" + firstEntry + ", chargeArea=" + chargeArea + ", landArea=" + landArea
				+ ", builtArea=" + builtArea + ", volumetricRate=" + volumetricRate + ", archFormat=" + archFormat
				+ ", manageFee=" + manageFee + ", communityType=" + communityType + ", contractMode=" + contractMode
				+ ", parkingNum=" + parkingNum + ", carRatio=" + carRatio + ", groundParkingNum=" + groundParkingNum
				+ ", basementParkingNum=" + basementParkingNum + ", defenseParkingNum=" + defenseParkingNum
				+ ", groundParkingFee=" + groundParkingFee + ", basementParkingFee=" + basementParkingFee
				+ ", defenseParkingFee=" + defenseParkingFee + ", facility=" + facility + ", region=" + region
				+ ", client=" + client + ", city=" + city + ", projectDesigns=" + projectDesigns + "]";
	}

}