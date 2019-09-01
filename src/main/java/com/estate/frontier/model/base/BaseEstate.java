/**
 * 
 */
package com.estate.frontier.model.base;

import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
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
	private String assessMethod;// 估价方法,可能有多个
	private String reportType;// 报告类型
	private String upFileURI;// 上传文件的uri
	private String branchOffice;// 分公司
	private String applicationNum;// 申请编号
	private String wordUri;// --id/a.doc
	private String pdfUri;// --id/a.pdf

	private String applicant;// 申请人
	private String checkResult;// 审核结果
	private String orderNum;// 序号
	private String state;// 状态
	private String login;//登录人
	private String checker;// 审核员
	private String propertyOwner;//产权人
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
		this.assessAim =assessAim;
//		System.out.println(assessAim);
//		if(assessAim instanceof String[]) {
//			String[] as =(String[]) assessAim;
//			if (null != as && as.length > 0) {
//				this.assessAim =StringUtils.join(as, ",");
//			}
//		}else if(assessAim instanceof String) {
//			this.assessAim =(String) assessAim;
//		}else if(assessAim instanceof JSONArray) {
//			JSONArray js =(JSONArray) assessAim;
//			Iterator<Object> it =js.iterator();
//			String s="";
//			while(it.hasNext()) {
//				s += it.next()+",";
//			}
//			if(!s.isEmpty()) {
//				this.assessAim = s.substring(0,s.length()-1);
//			}
//		}
	}

	public String getAssessMethod() {
		return assessMethod;
	}

	public void setAssessMethod(Object assessMethod) {
		if(assessMethod instanceof String[]) {
			String[] as =(String[]) assessMethod;
			if (null != as && as.length > 0) {
				this.assessMethod =StringUtils.join(as, ",");
			}
		}else if(assessMethod instanceof String) {
			this.assessMethod =(String) assessMethod;
		}else if(assessMethod instanceof JSONArray) {
			JSONArray js =(JSONArray) assessMethod;
			Iterator<Object> it =js.iterator();
			String s="";
			while(it.hasNext()) {
				s += it.next()+",";
			}
			if(!s.isEmpty()) {
				this.assessMethod = s.substring(0,s.length()-1);
			}
		}else {
			this.assessMethod = (String) assessMethod;
		}
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

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getPropertyOwner() {
		return propertyOwner;
	}

	public void setPropertyOwner(String propertyOwner) {
		this.propertyOwner = propertyOwner;
	}


}
