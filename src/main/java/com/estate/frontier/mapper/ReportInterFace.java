/**
 * 
 */
package com.estate.frontier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.estate.frontier.model.ReportInfo;

/**
 * 
 * @author lenovo
 * @data 2019年6月25日 上午9:30:03
 */
@Repository
public interface ReportInterFace {

	public int save(ReportInfo reportInfo);

	public int update(ReportInfo reportInfo);

	public int updateRptState(@Param("state") String state, @Param("id") String id);

	public void delete(int id);

	public List<ReportInfo> getAllReports();

	public List<ReportInfo> getCheckReportsByConditions(@Param("params") Map<String, Object> params);
	
	public List<ReportInfo> getReportsByConditions(@Param("params") Map<String, Object> params);
	//根据多个状态查询列表
	public List<ReportInfo> selectReportByStates(List<String> states);
	
	public int updateTransfer(@Param("transferTo") String transferTo, @Param("id") int id);
	//更新盖章状态
	//public int updateStampState(@Param("stampState") String stampState, @Param("id") String id);
	//更新备注
	public int updateRemarkOrStampState(@Param("params") Map<String, Object> params);
}
