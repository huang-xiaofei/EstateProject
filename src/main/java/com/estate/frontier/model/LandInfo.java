/**
 * 
 */
package com.estate.frontier.model;

import com.estate.frontier.model.base.BaseEstate;

/**
 * 土地报告实体
 * 
 * @author lenovo
 * @data 2019年6月25日 上午9:26:40
 */
public class LandInfo extends BaseEstate {
	private String landSite;// 宗地位置
	private double landArea;// 宗地面积
	private double landAmount;// 宗地数
	private double assessUnitPrice;// 评估单价
	private double assessTotalPrice;// 评估总价
	private String client;// 委托方
	private String clientEmail;// 委托方邮箱
	private String clientPhone;// 委托方电话
	private String firstReporter;// 第一报告人
	private String firstReporterRgNum;// 第一报告人注册号
	private String partReporter1;// 参与报告人1
	private String partReporter1RgNum;// 参与报告人1注册号
	private String partReporter2;// 参与报告人2
	private String partReporter2RgNum;// 参与报告人2注册号
	private String serviceSource;// 业务来源
	private double serviceCharge;// 业务收费
	//private String checker;// 审核员
	private String assessOrg;// 评估机构
	private String clientAddr;//委托人地址
	private String quarter;//季度
	private String pl;//项目负责人
	
	public String getLandSite() {
		return landSite;
	}

	public void setLandSite(String landSite) {
		this.landSite = landSite;
	}

	public double getLandArea() {
		return landArea;
	}

	public void setLandArea(double landArea) {
		this.landArea = landArea;
	}

	public double getLandAmount() {
		return landAmount;
	}

	public void setLandAmount(double landAmount) {
		this.landAmount = landAmount;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
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

	public double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}


	public String getAssessOrg() {
		return assessOrg;
	}

	public void setAssessOrg(String assessOrg) {
		this.assessOrg = assessOrg;
	}

	public String getClientAddr() {
		return clientAddr;
	}

	public void setClientAddr(String clientAddr) {
		this.clientAddr = clientAddr;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}

}
