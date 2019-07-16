/**
 * 
 */
package com.estate.frontier.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 报告信息列表(审批列表)
 * 
 * @author lenovo
 * @data 2019年6月20日 上午10:45:01
 */
public class ReportInfo {

	private int id;// 主键
	private String orderNum;// 序号
	private String applicationNum;// 申请编号
	private String branchOffice;// 分公司
	private String assessAim;// 评估目的
	private String reportType;// 报告类型
	private String applicant;// 申请人
	@JSONField(format = "yyyy-MM-dd")
	private Date applicationDate;// 申请时间
	private String state;// 状态
	private String checkResult;// 审核结果

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getApplicationNum() {
		return applicationNum;
	}

	public void setApplicationNum(String applicationNum) {
		this.applicationNum = applicationNum;
	}

	public String getBranchOffice() {
		return branchOffice;
	}

	public void setBranchOffice(String branchOffice) {
		this.branchOffice = branchOffice;
	}

	public String getAssessAim() {
		return assessAim;
	}

	public void setAssessAim(String assessAim) {
		this.assessAim = assessAim;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

}
