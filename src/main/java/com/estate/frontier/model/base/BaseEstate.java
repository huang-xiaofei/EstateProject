/**
 * 
 */
package com.estate.frontier.model.base;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 报告基类
 * 
 * @author lenovo
 * @data 2019年6月25日 上午9:26:24
 */
public class BaseEstate {
	private int id;// 报告id
	private String projectName;// 项目名称
	private String assessReportNum;// 估计报告编号
	@JSONField(format = "yyyy-MM-dd")
	private Date assessStartTime;// 估价作业开始时间
	@JSONField(format = "yyyy-MM-dd")
	private Date assessEndTime;// 估价作业结束时间
	private String assessObject;// 估价对象
	@JSONField(format = "yyyy-MM-dd")
	private Date valueTime;// 价值时点
	private String assessAim;// 估价目的
	private String assessMethod;// 估价方法
	private String reportType;// 报告类型
	private String upFileURI;// 上传文件的uri
	private String branchOffice;// 分公司
	private String applicationNum;// 申请编号
	private String wordUri;// --id/a.doc
	private String pdfUri;// --id/a.pdf

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getAssessReportNum() {
		return assessReportNum;
	}

	public void setAssessReportNum(String assessReportNum) {
		this.assessReportNum = assessReportNum;
	}

	public Date getAssessStartTime() {
		return assessStartTime;
	}

	public void setAssessStartTime(Date assessStartTime) {
		this.assessStartTime = assessStartTime;
	}

	public Date getAssessEndTime() {
		return assessEndTime;
	}

	public void setAssessEndTime(Date assessEndTime) {
		this.assessEndTime = assessEndTime;
	}

	public String getAssessObject() {
		return assessObject;
	}

	public void setAssessObject(String assessObject) {
		this.assessObject = assessObject;
	}

	public Date getValueTime() {
		return valueTime;
	}

	public void setValueTime(Date valueTime) {
		this.valueTime = valueTime;
	}

	public String getAssessAim() {
		return assessAim;
	}

	public void setAssessAim(String assessAim) {
		this.assessAim = assessAim;
	}

	public String getAssessMethod() {
		return assessMethod;
	}

	public void setAssessMethod(String assessMethod) {
		this.assessMethod = assessMethod;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getUpFileURI() {
		return upFileURI;
	}

	public void setUpFileURI(String upFileURI) {
		this.upFileURI = upFileURI;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBranchOffice() {
		return branchOffice;
	}

	public void setBranchOffice(String branchOffice) {
		this.branchOffice = branchOffice;
	}

	public String getApplicationNum() {
		return applicationNum;
	}

	public void setApplicationNum(String applicationNum) {
		this.applicationNum = applicationNum;
	}

	public String getWordUri() {
		return wordUri;
	}

	public void setWordUri(String wordUri) {
		this.wordUri = wordUri;
	}

	public String getPdfUri() {
		return pdfUri;
	}

	public void setPdfUri(String pdfUri) {
		this.pdfUri = pdfUri;
	}

}
