/**
 * 
 */
package com.estate.frontier.model;

import com.estate.frontier.model.base.BaseEstate;

/**
 * 预评估实体
 * 
 * @author lenovo
 * @data 2019年6月25日 下午3:07:30
 */
public class AssessInfo extends BaseEstate {

	private String serviceSource;// 业务来源
	//private String checker;// 审核员
	private String assessOrg;// 评估机构
	private double buildingArea;// 建筑面积
	private double assessTotalPrice;// 评估总价
	public String getServiceSource() {
		return serviceSource;
	}

	public void setServiceSource(String serviceSource) {
		this.serviceSource = serviceSource;
	}


	public String getAssessOrg() {
		return assessOrg;
	}

	public void setAssessOrg(String assessOrg) {
		this.assessOrg = assessOrg;
	}

	public double getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(double buildingArea) {
		this.buildingArea = buildingArea;
	}

	public double getAssessTotalPrice() {
		return assessTotalPrice;
	}

	public void setAssessTotalPrice(double assessTotalPrice) {
		this.assessTotalPrice = assessTotalPrice;
	}

}
