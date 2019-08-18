/**
 * 
 */
package com.estate.frontier.model;

import com.estate.frontier.model.base.BaseEstate;

/**
 * 用于封装统计信息
 * @author lenovo
 * @data 2019年8月14日 下午9:51:02
 */
public class StaticsModel {

	private ReportInfo report;
	private BaseEstate detailReport;
	
	/**
	 * @param report
	 * @param detailReport
	 */
	public StaticsModel(ReportInfo report, BaseEstate detailReport) {
		super();
		this.report = report;
		this.detailReport = detailReport;
	}
	public ReportInfo getReport() {
		return report;
	}
	public void setReport(ReportInfo report) {
		this.report = report;
	}
	public BaseEstate getDetailReport() {
		return detailReport;
	}
	public void setDetailReport(BaseEstate detailReport) {
		this.detailReport = detailReport;
	}
	
	/**
	 * @param reportInfo
	 * @param estate
	 */
	
}
