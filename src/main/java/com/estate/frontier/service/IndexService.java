/**
 * 
 */
package com.estate.frontier.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.estate.frontier.mapper.AssessInterface;
import com.estate.frontier.mapper.AssetsInterface;
import com.estate.frontier.mapper.LandInterface;
import com.estate.frontier.mapper.RealEstateInterface;
import com.estate.frontier.mapper.ReportInterFace;
import com.estate.frontier.mapper.base.BaseInterface;
import com.estate.frontier.model.ReportInfo;
import com.estate.frontier.model.StaticsModel;
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
	public ResultModel saveEstateData(BaseEstate base, List<MultipartFile> files) {
		try {
			ReportInfo reportInfo = getReportInfo(base);
			reportInterFace.save(reportInfo);// 保存到report_list表

			int id = reportInfo.getId();
			base.setId(id);
//			List<FileResult> result = FileUtil.upLoad(files, base);// 上传文件
//
//			base.setId(id);
//			for (FileResult fileResult : result) {
//				if (FileUtil.isZip(fileResult.getRealPath())) {
//					base.setUpFileURI(fileResult.getWordPath());
//				} else {
//					base.setWordUri(fileResult.getWordPath());
//					base.setPdfUri(fileResult.getPdfPath());
//				}
//			}

			getReportDao(base.getReportType()).save(base);// 保存报告明细
			return ResultFactory.newResultModel(EnumField.sucess, base.getId());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultFactory.newResultModel(EnumField.fail, null);
		}
	}

	@Transactional
	public ResultModel transferTo(String transferTo, int id) {
		try {
			reportInterFace.updateTransfer(transferTo, id);
			return ResultFactory.newResultModel(EnumField.sucess, null);
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
	@Transactional
	public ResultModel updateEstateData(BaseEstate base) {
		try {
			ReportInfo reportInfo = getReportInfo(base);
			reportInterFace.update(reportInfo);// 更新report_list表

			getReportDao(base.getReportType()).update(base);// 保存报告明细
			return ResultFactory.newResultModel(EnumField.sucess, base.getPdfUri());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultFactory.newResultModel(EnumField.fail, null);
		}
	}

	@Transactional
	public ResultModel updateZipUri(Map<String, String> params) {
		try {
			getReportDao(params.get("reportType")).updateUri(params);// 保存报告明细
			return ResultFactory.newResultModel(EnumField.sucess, null);
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
			BaseEstate baseEstate =getReportDao(reportType).get(id);//查询出需要删除的路径
			reportInterFace.delete(id);
			getReportDao(reportType).delete(id);
			FileUtil.deleteFold(baseEstate);
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
	 * 根据多个state查找列表
	 * 
	 * @param states
	 * @return
	 * @author lenovo
	 * @date 2019年7月26日 下午9:00:10
	 */
	public ResultModel getReportsByStates(List<String> states) {
		if (null == states || states.isEmpty()) {
			return ResultFactory.newResultModel(EnumField.nullObject, null);
		}
		try {
			List<ReportInfo> lists = reportInterFace.selectReportByStates(states);
			return ResultFactory.newResultModel(EnumField.sucess, lists);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultFactory.newResultModel(EnumField.fail, null);
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
		reportInfo.setApplicant(base.getApplicant());// 申请人
		reportInfo.setApplicationDate(new Timestamp(System.currentTimeMillis()));// 申请日期
		reportInfo.setApplicationNum(base.getAssessReportNum());// 申请编号
		reportInfo.setId(base.getId());
		reportInfo.setAssessAim(base.getAssessAim());// 估价目的
		reportInfo.setBranchOffice(base.getBranchOffice());// 分公司
		reportInfo.setCheckResult(base.getCheckResult());// 审核结果
		reportInfo.setReportType(base.getReportType());
		reportInfo.setOrderNum(base.getOrderNum());// 序号
		reportInfo.setState(base.getState() == null ? "0" : base.getState());// 审核状态
		reportInfo.setLogin(base.getLogin());// 登录用户
		reportInfo.setChecker(base.getChecker());// 申请人
		return reportInfo;
	}

	public List<ReportInfo> getAllReportsData() {
		return reportInterFace.getAllReports();
	}

//	/**
//	 * 
//	 * @param branchOffice
//	 * @param state
//	 * @return
//	 * @author lenovo
//	 * @date 2019年6月25日 上午9:17:13
//	 */
//	public ResultModel getReportsDatas(String branchOffice, String state) {
//		try {
//			List<ReportInfo> lists = reportInterFace.getReportsByCondition(branchOffice, state);
//			return ResultFactory.newResultModel(EnumField.sucess, lists);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResultFactory.newResultModel(EnumField.fail, null);
//		}
//	}

	public ResultModel getReportsByConditions(Map<String, Object> params) {
		try {
			if (null == params || params.isEmpty()) {
				params = new HashMap<String, Object>();
			}
			List<ReportInfo> lists = reportInterFace.getReportsByConditions(params);
			return ResultFactory.newResultModel(EnumField.sucess, lists);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultFactory.newResultModel(EnumField.fail, null);
		}
	}

	@Transactional
	public ResultModel updateRemarkOrStampState(Map<String, Object> params) {
		int num = reportInterFace.updateRemarkOrStampState(params);
		return ResultFactory.newResultModel(EnumField.sucess, num);
	}

	public ResultModel getCheckReportsByConditions(Map<String, Object> params) {
		try {
			if (null == params || params.isEmpty()) {
				params = new HashMap<String, Object>();
			}
			List<ReportInfo> lists = reportInterFace.getCheckReportsByConditions(params);
			return ResultFactory.newResultModel(EnumField.sucess, lists);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultFactory.newResultModel(EnumField.fail, null);
		}
	}
	public ReportInfo getReportById(int id) {
		try {
			ReportInfo report = reportInterFace.getReportById(id);
			return report;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResultModel getStaticsByConditions(Map<String, Object> params) {
		try {
			if (null == params || params.isEmpty()) {
				params = new HashMap<String, Object>();
			}
			List<StaticsModel> staticsModels = new ArrayList<StaticsModel>();
			List<ReportInfo> lists = reportInterFace.getStaticsByConditions(params);
			for (ReportInfo reportInfo : lists) {
				ResultModel model = getDetailReport(reportInfo.getId(), reportInfo.getReportType());
				staticsModels.add(new StaticsModel(reportInfo, (BaseEstate) model.getData()));
			}
			return ResultFactory.newResultModel(EnumField.sucess, staticsModels);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultFactory.newResultModel(EnumField.fail, null);
		}
	}

	@Async("asyncPoolTaskExecutor")
	public void word2Pdf(Object obj) {
		log.warn("word2Pdf线程名:{},obj={}", Thread.currentThread().getName(), JSON.toJSONString(obj));
		if (null == obj || obj.toString().isEmpty() || !(obj instanceof FileResult)) {
			return;
		}
		FileResult result = (FileResult) obj;

		// Word2PdfUtil.doc2pdf(obj.toString());
		log.warn("word2Pdf转换结果:{}", Word2PdfUtil.doc2pdf(result));
	}
}
