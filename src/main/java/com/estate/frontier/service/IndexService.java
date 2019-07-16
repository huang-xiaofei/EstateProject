/**
 * 
 */
package com.estate.frontier.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.estate.frontier.mapper.AssessInterface;
import com.estate.frontier.mapper.AssetsInterface;
import com.estate.frontier.mapper.LandInterface;
import com.estate.frontier.mapper.RealEstateInterface;
import com.estate.frontier.mapper.ReportInterFace;
import com.estate.frontier.mapper.base.BaseInterface;
import com.estate.frontier.model.ReportInfo;
import com.estate.frontier.model.base.BaseEstate;
import com.estate.frontier.model.constant.EnumField;
import com.estate.frontier.model.constant.NormalConstant;
import com.estate.frontier.model.factory.ResultFactory;
import com.estate.frontier.model.factory.ResultModel;
import com.estate.frontier.util.FileUtil;
import com.estate.frontier.util.FileUtil.FileResult;
import com.estate.frontier.util.Word2PdfUtil;

/**
 * 
 * @author lenovo
 * @data 2019年6月25日 上午9:12:53
 */
@Service
public class IndexService {
	private static final Logger log = LogManager.getLogger(IndexService.class);
	@Autowired
	private RealEstateInterface realEstateInterface;

	@Autowired
	private ReportInterFace reportInterFace;

	@Autowired
	private LandInterface landInterface;

	@Autowired
	private AssetsInterface assetsInterface;
	@Autowired
	private AssessInterface assessInterface;

	public ResultModel updateRptState(String state, String id) {
		reportInterFace.updateRptState(state, id);
		return ResultFactory.newResultModel(EnumField.sucess, null);
	}

	private boolean validate(String reportType) {
		if (!NormalConstant.REPORT_TYPES.contains(reportType)) {
			return false;
		}
		return true;
	}

	private BaseInterface getReportDao(String reportType) throws Exception {
		if (!validate(reportType)) {
			throw new Exception("不支持的报告类型:" + reportType);
		}
		if (null == NormalConstant.REPORT_TYPE_DAO.get(reportType)) {
			//// 存放type-Dao映射,存放时候保证顺序
			NormalConstant.putDao(realEstateInterface, landInterface, assetsInterface, assessInterface);
			if (null == NormalConstant.REPORT_TYPE_DAO.get(reportType)) {
				throw new Exception("请检查dao是否已加载!!");
			}
		}
		return NormalConstant.REPORT_TYPE_DAO.get(reportType);
	}

	/**
	 * 
	 * @param base
	 * @return
	 * @author lenovo
	 * @date 2019年6月25日 上午9:15:45
	 */
	@Transactional
	public ResultModel saveEstateData(BaseEstate base, MultipartFile file) {
		try {
			ReportInfo reportInfo = getReportInfo(base);
			reportInterFace.save(reportInfo);// 保存到report_list表

			int id = reportInfo.getId();
			FileResult result = FileUtil.upLoad(file, id);// 上传文件

			base.setId(id);
			if (null != result) {
				base.setWordUri(result.getWordPath());
				base.setPdfUri(result.getPdfPath());
			}

			getReportDao(base.getReportType()).save(base);// 保存报告明细
			return ResultFactory.newResultModel(EnumField.sucess, result.getRealPath());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultFactory.newResultModel(EnumField.fail, null);
		}
	}

	/**
	 * 
	 * @param base
	 * @return
	 * @author lenovo
	 * @date 2019年6月25日 上午9:16:37
	 */
	public ResultModel updateEstateData(BaseEstate base, MultipartFile file) {
		try {
			ReportInfo reportInfo = getReportInfo(base);
			reportInterFace.update(reportInfo);// 更新report_list表

			FileResult result = FileUtil.upLoad(file, base.getId());// 上传文件
			if (null != result) {
				base.setWordUri(result.getWordPath());
				base.setPdfUri(result.getPdfPath());
			}

			getReportDao(base.getReportType()).update(base);// 保存报告明细
			return ResultFactory.newResultModel(EnumField.sucess, result.getRealPath());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultFactory.newResultModel(EnumField.fail, null);
		}
	}

	/**
	 * 
	 * @param primaryKey
	 * @param reportType
	 * @param fileURI
	 * @return
	 * @author lenovo
	 * @date 2019年6月25日 上午9:16:20
	 */
	@Transactional
	public ResultModel deleteEstateData(int primaryKey, String reportType) {
		try {
			int id = primaryKey;
			reportInterFace.delete(id);
			getReportDao(reportType).delete(id);
			FileUtil.deleteFold(id + "");
			return ResultFactory.newResultModel(EnumField.sucess, null);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultFactory.newResultModel(EnumField.fail, null);
		}
	}

	/**
	 * 获取报告详细信息
	 * 
	 * @param strId
	 * @param reportType
	 * @return
	 * @author lenovo
	 * @date 2019年6月25日 上午9:16:48
	 */
	public ResultModel getDetailReport(int id, String reportType) {
		BaseEstate baseEstate = null;
		try {
			baseEstate = getReportDao(reportType).get(id);
			if (null == baseEstate) {
				baseEstate = new BaseEstate();
			}
			return ResultFactory.newResultModel(EnumField.sucess, baseEstate);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultFactory.newResultModel(EnumField.fail, baseEstate);
		}
	}

	/**
	 * 
	 * @param base
	 * @return
	 * @author lenovo
	 * @date 2019年6月25日 上午9:16:57
	 */
	private ReportInfo getReportInfo(BaseEstate base) {
		ReportInfo reportInfo = new ReportInfo();
		reportInfo.setApplicant("王麻子");
		reportInfo.setApplicationDate(new Date());
		reportInfo.setApplicationNum(base.getApplicationNum());
		reportInfo.setId(base.getId());
		reportInfo.setAssessAim(base.getAssessAim());
		reportInfo.setBranchOffice(base.getBranchOffice());
		reportInfo.setCheckResult("未通过");
		reportInfo.setReportType(base.getReportType());
		reportInfo.setOrderNum("003");
		reportInfo.setState("未审核");
		return reportInfo;
	}

	public List<ReportInfo> getAllReportsData() {
		return reportInterFace.getAllReports();
	}

	/**
	 * 
	 * @param branchOffice
	 * @param state
	 * @return
	 * @author lenovo
	 * @date 2019年6月25日 上午9:17:13
	 */
	public ResultModel getReportsDatas(String branchOffice, String state) {
		try {
			List<ReportInfo> lists = reportInterFace.getReportsByCondition(branchOffice, state);
			return ResultFactory.newResultModel(EnumField.sucess, lists);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultFactory.newResultModel(EnumField.fail, null);
		}
	}

	@Async("asyncPoolTaskExecutor")
	public void word2Pdf(Object obj) {
		log.warn("word2Pdf线程名:{},obj={}", Thread.currentThread().getName(), obj);
		if (null == obj || obj.toString().isEmpty()) {
			return;
		}
		// Word2PdfUtil.doc2pdf(obj.toString());
		log.warn("word2Pdf转换结果:{}", Word2PdfUtil.doc2pdf(obj.toString()));
	}
}
