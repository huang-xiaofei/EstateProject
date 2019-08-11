package com.estate.frontier.model;

import com.estate.frontier.model.base.BaseEstate;

/**
 * 房地产报告实体
 * 
 * @author lenovo
 * @data 2019年6月25日 上午9:27:01
 */
public class RealEstateInfo extends BaseEstate {
	// private int id;
	// private String projectName;// 项目名称
	// private String assessReportNum;// 估计报告编号
	// @JSONField(format = "yyyy-MM-dd")
	// private Date assessStartTime;// 估价作业开始时间
	// @JSONField(format = "yyyy-MM-dd")
	// private Date assessEndTime;// 估价作业结束时间
	// private String assessObject;// 估价对象
	// @JSONField(format = "yyyy-MM-dd")
	// private String valueTime;// 价值时点
	// private String assessAim;// 估价目的
	// private String assessMethod;// 估价方法
	private String valueType;// 价值类型
	private double buildingArea;// 建筑面积
	private double floorArea;// 占地面积
	private double assessUnitPrice;// 评估单价
	private double assessTotalPrice;// 评估总价
	private String client;// 委托方
	private String firstReporter;// 第一报告人
	private String firstReporterRgNum;// 第一报告人注册号
	private String partReporter1;// 参与报告人1
	private String partReporter1RgNum;// 参与报告人1注册号
	private String partReporter2;// 参与报告人2
	private String partReporter2RgNum;// 参与报告人2注册号
	private String serviceSource;// 业务来源
	// private String branchOffice;// 分公司
	private String serviceCharge;// 业务收费
	//private String checker;// 审核员
	private String assessOrg;// 评估机构
	// private String reportType;// 报告类型
	// private String upFileURI;// 上传文件的uri

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public double getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(double buildingArea) {
		this.buildingArea = buildingArea;
	}

	public double getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(double floorArea) {
		this.floorArea = floorArea;
	}

	public double getAssessUnitPrice() {
		return assessUnitPrice;
	}

	public void setAssessUnitPrice(double assessUnitPrice) {
		this.assessUnitPrice = assessUnitPrice;
	}

	public double getAssessTotalPrice() {
		return assessTotalPrice;
	}

	public void setAssessTotalPrice(double assessTotalPrice) {
		this.assessTotalPrice = assessTotalPrice;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getFirstReporter() {
		return firstReporter;
	}

	public void setFirstReporter(String firstReporter) {
		this.firstReporter = firstReporter;
	}

	public String getFirstReporterRgNum() {
		return firstReporterRgNum;
	}

	public void setFirstReporterRgNum(String firstReporterRgNum) {
		this.firstReporterRgNum = firstReporterRgNum;
	}

	public String getPartReporter1() {
		return partReporter1;
	}

	public void setPartReporter1(String partReporter1) {
		this.partReporter1 = partReporter1;
	}

	public String getPartReporter1RgNum() {
		return partReporter1RgNum;
	}

	public void setPartReporter1RgNum(String partReporter1RgNum) {
		this.partReporter1RgNum = partReporter1RgNum;
	}

	public String getPartReporter2() {
		return partReporter2;
	}

	public void setPartReporter2(String partReporter2) {
		this.partReporter2 = partReporter2;
	}

	public String getPartReporter2RgNum() {
		return partReporter2RgNum;
	}

	public void setPartReporter2RgNum(String partReporter2RgNum) {
		this.partReporter2RgNum = partReporter2RgNum;
	}

	public String getServiceSource() {
		return serviceSource;
	}

	public void setServiceSource(String serviceSource) {
		this.serviceSource = serviceSource;
	}

	public String getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}


	public String getAssessOrg() {
		return assessOrg;
	}

	public void setAssessOrg(String assessOrg) {
		this.assessOrg = assessOrg;
	}

}
