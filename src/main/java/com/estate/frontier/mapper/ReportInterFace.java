/**
 * 
 */
package com.estate.frontier.mapper;

import java.util.List;

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

	public List<ReportInfo> getReportsByCondition(@Param("branchOffice") String branchOffice,
			@Param("state") String state);

}
