/**
 * 
 */
package com.estate.frontier.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.estate.frontier.model.base.BaseEstate;

/**
 * 资产报告实体
 * 
 * @author lenovo
 * @data 2019年6月25日 上午9:26:10
 */
public class AssetsInfo extends BaseEstate {

	private String assessedUnitName;// 被评估单位名称
	private boolean isStateAssets;// 是否国有资产
	private boolean isPrivateAsset;// 是否私有资产
	private String valueType;// 价值类型
	private double assessResult;// 评估结论
	@JSONField(format = "yyyy-MM-dd")
	private Date assessDate;// 评估基准日
	@JSONField(format = "yyyy-MM-dd")
	private Date assetsReportDate;// 资产评估报告日
	private double actualFee;// 实际收费金额
	private double assetsFee;// 总资产账面值
	private boolean noAssetsFee;// 无总资产账面值
	private double debtFee;// 总负债面值
	private boolean noDebtFee;// 无总负债面值
	private double netAssets;// 净资产账面值
	private boolean noNetAssets;// 无净资产账面值ֵ
	private String assessObj;// 评估对象

	private String client;//委托方
	public String getAssessObj() {
		return assessObj;
	}

	public void setAssessObj(String assessObj) {
		this.assessObj = assessObj;
	}

	public String getAssessedUnitName() {
		return assessedUnitName;
	}

	public void setAssessedUnitName(String assessedUnitName) {
		this.assessedUnitName = assessedUnitName;
	}

	public boolean getIsStateAssets() {
		return isStateAssets;
	}

	public void setIsStateAssets(boolean isStateAssets) {
		this.isStateAssets = isStateAssets;
	}

	public boolean getIsPrivateAsset() {
		return isPrivateAsset;
	}

	public void setIsPrivateAsset(boolean isPrivateAsset) {
		this.isPrivateAsset = isPrivateAsset;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public double getAssessResult() {
		return assessResult;
	}

	public void setAssessResult(double assessResult) {
		this.assessResult = assessResult;
	}

	public Date getAssessDate() {
		return assessDate;
	}

	public void setAssessDate(Date assessDate) {
		this.assessDate = assessDate;
	}

	public Date getAssetsReportDate() {
		return assetsReportDate;
	}

	public void setAssetsReportDate(Date assetsReportDate) {
		this.assetsReportDate = assetsReportDate;
	}

	public double getActualFee() {
		return actualFee;
	}

	public void setActualFee(double actualFee) {
		this.actualFee = actualFee;
	}

	public double getAssetsFee() {
		return assetsFee;
	}

	public void setAssetsFee(double assetsFee) {
		this.assetsFee = assetsFee;
	}

	public boolean getNoAssetsFee() {
		return noAssetsFee;
	}

	public void setNoAssetsFee(boolean noAssetsFee) {
		this.noAssetsFee = noAssetsFee;
	}

	public double getDebtFee() {
		return debtFee;
	}

	public void setDebtFee(double debtFee) {
		this.debtFee = debtFee;
	}

	public boolean getNoDebtFee() {
		return noDebtFee;
	}

	public void setNoDebtFee(boolean noDebtFee) {
		this.noDebtFee = noDebtFee;
	}

	public double getNetAssets() {
		return netAssets;
	}

	public void setNetAssets(double netAssets) {
		this.netAssets = netAssets;
	}

	public boolean getNoNetAssets() {
		return noNetAssets;
	}

	public void setNoNetAssets(boolean noNetAssets) {
		this.noNetAssets = noNetAssets;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

}
