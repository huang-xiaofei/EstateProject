/**
 * 
 */
package com.estate.frontier.model.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.estate.frontier.mapper.base.BaseInterface;
import com.estate.frontier.model.AssessInfo;
import com.estate.frontier.model.AssetsInfo;
import com.estate.frontier.model.LandInfo;
import com.estate.frontier.model.RealEstateInfo;

/**
 * @author lenovo
 * @data 2019年7月5日 下午4:21:31
 */
public abstract class NormalConstant {
	public static final List<String> REPORT_TYPES = new ArrayList<String>();
	/**
	 * 用于根据报告类型反序列化
	 */
	public static final Map<String, Class<?>> REPORT_TYPE_CLASS = new HashMap<String, Class<?>>();
	/**
	 * 用于根据报告类型获取Dao层
	 */
	public static final Map<String, BaseInterface> REPORT_TYPE_DAO = new HashMap<String, BaseInterface>();
	/**
	 * 房地产估价报告
	 */
	public static final String REAL_ESTATE = "1";
	/**
	 * 土地估价报告
	 */
	public static final String LAND_ESTATE = "2";
	/**
	 * 资产评估报告
	 */
	public static final String ASSETS_ESTATE = "3";
	/**
	 * 预评估
	 */
	public static final String ASSESS_ESTATE = "4";

	public static final String un_check = "0";// 未审核
	public static final String pre_check = "1";// 准备提交审核
	public static final String checked = "2";// 已审核
	public static final String check_pass = "3";// 审核通过
	public static final String check_reject = "4";// 审核拒绝
	/**
	 * 未审核
	 */
	public static final String UN_CHECK_STATE = "0";

	/**
	 * 已审核
	 */
	public static final String CHECKED_STATE = "0";
	static {
		// 存放type-class映射
		REPORT_TYPE_CLASS.put(REAL_ESTATE, RealEstateInfo.class);
		REPORT_TYPE_CLASS.put(LAND_ESTATE, LandInfo.class);
		REPORT_TYPE_CLASS.put(ASSETS_ESTATE, AssetsInfo.class);
		REPORT_TYPE_CLASS.put(ASSESS_ESTATE, AssessInfo.class);

		REPORT_TYPES.add(REAL_ESTATE);
		REPORT_TYPES.add(LAND_ESTATE);
		REPORT_TYPES.add(ASSETS_ESTATE);
		REPORT_TYPES.add(ASSESS_ESTATE);
	}

	public static void putDao(BaseInterface... interfaces) {
		List<BaseInterface> list = Arrays.asList(interfaces);
		REPORT_TYPE_DAO.put(REAL_ESTATE, list.get(0));
		REPORT_TYPE_DAO.put(LAND_ESTATE, list.get(1));
		REPORT_TYPE_DAO.put(ASSETS_ESTATE, list.get(2));
		REPORT_TYPE_DAO.put(ASSESS_ESTATE, list.get(3));
	}
}
