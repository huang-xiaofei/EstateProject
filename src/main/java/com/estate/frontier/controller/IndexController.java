/**
 * 
 */
package com.estate.frontier.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.estate.frontier.model.base.BaseEstate;
import com.estate.frontier.model.constant.EnumField;
import com.estate.frontier.model.constant.NormalConstant;
import com.estate.frontier.model.factory.ResultFactory;
import com.estate.frontier.model.factory.ResultModel;
import com.estate.frontier.service.IndexService;
import com.estate.frontier.util.FileUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 主q
 * 
 * @author lenovo
 * @data 2019年6月25日 上午9:03:20
 */
@RestController
@RequestMapping("/index")
@Api(value = "indexController")
public class IndexController {
	private static final Logger log = LogManager.getLogger(IndexController.class);
	@Autowired
	private IndexService indexService;

	@RequestMapping(value = "/saveRptOrFile", method = RequestMethod.POST)
	@ApiOperation(value = "上传文件和保存报告信息", notes = "先保存报告信息，在上传文件")
	@ResponseBody
	public String saveRptOrFile(HttpServletRequest request) {
		MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
		String json = request.getParameter("data");
		log.info("saveRptOrFile->收到file:{},收到json:{}" + file.getOriginalFilename(), json);
		BaseEstate base = getBaseEstate(json);
		if (null == base) {
			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.nullObject, null));
		}
		ResultModel result = indexService.saveEstateData(base, file);
		indexService.word2Pdf(result.getData());
		System.out.println("------------------------------------------------");
		return JSON.toJSONString(result);
	}

//	@RequestMapping(value = "/test", method = RequestMethod.POST)
//	@ApiOperation(value = "上传文件和保存报告信息", notes = "先保存报告信息，在上传文件")
//	@ResponseBody
//	public String Test(@RequestBody MultipartFile file, @RequestParam String json) {
//		log.info("saveRptOrFile->收到file:{},收到json:{}" + file.getOriginalFilename(), json);
//		BaseEstate base = getBaseEstate(json);
//		if (null == base) {
//			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.nullObject, null));
//		}
//		ResultModel result = indexService.saveEstateData(base, file);
//		indexService.word2Pdf(result.getData());
//		System.out.println("------------------------------------------------");
//		return JSON.toJSONString(result);
//	}

//	@RequestMapping(value = "/testUpdate", method = RequestMethod.POST)
//	@ApiOperation(value = "更新报告和文件信息", notes = "先更新报告信息，在上传文件")
//	@ResponseBody
//	public String testUpdate(@RequestBody(required = false) MultipartFile file,
//			@RequestParam(required = false) String json) {
//		log.info("updateRptOrFile->收到id:{},json:{}", json);
//		BaseEstate base = getBaseEstate(json);
//		if (null == base) {
//			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.nullObject, null));
//		}
//		ResultModel result = indexService.updateEstateData(base, file);
//
//		indexService.word2Pdf(result.getData());// 异步执行word转为pdf
//		System.out.println("------------------------------------------------");
//		return JSON.toJSONString(result);
//	}

	@RequestMapping(value = "/updateRptOrFile", method = RequestMethod.POST)
	@ApiOperation(value = "更新报告和文件信息", notes = "先更新报告信息，在上传文件")
	@ResponseBody
	public String updateRptOrFile(HttpServletRequest request) {
		MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
		String json = request.getParameter("data");
		log.info("updateRptOrFile->收到id:{},json:{}", json);
		BaseEstate base = getBaseEstate(json);
		if (null == base) {
			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.nullObject, null));
		}
		ResultModel result = indexService.updateEstateData(base, file);

		indexService.word2Pdf(result.getData());// 异步执行word转为pdf
		System.out.println("------------------------------------------------");
		return JSON.toJSONString(result);
	}

//	@RequestMapping(value = "/updateRpt", method = RequestMethod.POST)
//	@ApiOperation(value = "更新报告信息", notes = "只更新报告信息")
//	@ResponseBody
//	public String updateRpt(@RequestBody String json) {
//		System.out.println(json);
//		BaseEstate base = getBaseEstate(json);
//		if (null == base) {
//			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.nullObject, null));
//		}
//
//		ResultModel result = indexService.updateEstateData(base, null);
//		return JSON.toJSONString(result);
//	}

//	@RequestMapping(value = "/preSubmit", method = RequestMethod.POST)
//	@ApiOperation(value = "提交审核时更新报告状态", notes = "提交审核时更新报告状态")
//	@ResponseBody
//	public String preSubmit(@RequestParam String id) {
//		ResultModel result = indexService.updateRptState(NormalConstant.PRE_SUBMIT_STATE, id);
//		return JSON.toJSONString(result);
//	}

	@RequestMapping(value = "/updateRptState", method = RequestMethod.POST)
	@ApiOperation(value = "更新报告状态", notes = "state值说明:{un_check =0 未审核,pre_check=1 准备提交审核,checked=2 已审核,check_pass=3 审核通过,check_reject=4 审核拒绝}")
	@ResponseBody
	public String updateRptState(@RequestParam String id, @RequestParam String state) {
		ResultModel result = indexService.updateRptState(state, id);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ApiOperation(value = "删除文件和报告信息", notes = "删除报告列表和报告详细信息，在删除所有已上传的文件")
	@ResponseBody
	public String deleteEstate(@RequestParam int id, @RequestParam String reportType) {
		log.info("执行deleteEstate,id={},reportType={}", id, reportType);
		ResultModel result = indexService.deleteEstateData(id, reportType);
		log.info("删除结果:{}", result.getState());
		return JSON.toJSONString(result);
	}

	/************************* get *************************/
	@RequestMapping(value = "/getReports", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取报告列表", notes = "不传参数则获取所有报告信息")
	public String getReports(@RequestParam(required = false) String branchOffice,
			@RequestParam(required = false) String state) {
		log.info("执行getReports,branchOffice={},state={}", branchOffice, state);
		ResultModel result = indexService.getReportsDatas(branchOffice, state);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/getReportsDetail", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取详细报告", notes = "获取详细报告")
	public String getDetailReport(@RequestParam int id, @RequestParam String reportType) {
		log.info("执行getDetailReports,id={},reportType={}", id, reportType);
		ResultModel result = indexService.getDetailReport(id, reportType);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/getCheckList", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取审核列表", notes = "获取审核列表")
	public String getCheckList() {
		ResultModel result = indexService.getReportsDatas(null, NormalConstant.pre_check);
		return JSON.toJSONString(result);
	}

	/**************** 先上传在保存 ****************************/

	public static void main(String[] args) throws Exception {
		String json = "{\"reportType\":\"1\",\"projectName\":\"\",\"assessReportNum\":\"\",\"assessStartTime\":\"2019-05-06\",\"assessEndTime\":\"2019-05-06\",\"assessObject\":\"\",\"valueTime\":\"2019-05-06\",\"assessAim\":\"\",\"assessMethod\":\"\",\"valueType\":\"\",\"buildingArea\":\"\",\"floorArea\":\"\",\"assessUnitPrice\":\"\",\"assessTotalPrice\":\"\",\"client\":\"\",\"firstReporter\":\"\",\"firstReporterRgNum\":\"\",\"partReporter1\":\"\",\"partReporter1RgNum\":\"\",\"partReporter2\":\"\",\"partReporter2RgNum\":\"\",\"serviceSource\":\"\",\"branchOffice\":\"\",\"serviceCharge\":\"\",\"checker\":\"\",\"assessOrg\":\"\"}";
		JSONObject obj = JSON.parseObject(json);
		BaseEstate base = getBaseEstate(json);
		System.out.println(base);
	}

	@RequestMapping(value = "/upLoad/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "上传文件", notes = "上传文件")
	@ResponseBody
	public String upLoad(@RequestParam("file") MultipartFile file, @PathVariable("id") String id) {
		log.info("执行upLoad,文件名:{},id:{}", file.getOriginalFilename() == null ? "null" : file.getOriginalFilename(), id);
		String upURI = "";
		if (null != file && !file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			fileName = FileUtil.getFileName(fileName);
			System.out.println("文件名字为：：：" + fileName);
			upURI = id + FileUtil.FILE_SEPARATOR + fileName;
			try {
				FileUtil.upLoadFile(file, upURI);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info("返回前端的uri:{}", upURI);
		return upURI;
	}
//
//	@RequestMapping(value = "/upLoad2/{id}", method = RequestMethod.POST)
//	@ApiOperation(value = "上传文件", notes = "上传文件")
//	@ResponseBody
//	public String upLoad2(HttpServletRequest request, @PathVariable String id) {
//		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
//		MultipartFile file = req.getFile("file");
//		log.info("执行upLoad,文件名:{},id:{}", file.getOriginalFilename() == null ? "null" : file.getOriginalFilename(), id);
//		String upURI = "";
//		if (!file.isEmpty()) {
//			String fileName = file.getOriginalFilename();
//			fileName = FileUtil.getFileName(fileName);
//			System.out.println("文件名字为：：：" + fileName);
//			upURI = id + FileUtil.FILE_SEPARATOR + fileName;
//			try {
//				FileUtil.upLoadFile(file, upURI);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		log.info("返回前端的uri:{}", upURI);
//		return upURI;
//	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	@ApiImplicitParam(paramType = "query", name = "uri", value = "上传文件的uri", required = true, dataType = "String")
	public String downLoad(HttpServletResponse response, String uri) {
		log.info("执行downLoad,请求下载的uri:{}", uri);
		String path = System.getProperty("user.dir") + "/upLoad/" + uri;
		File file = new File(path);
		String filename = file.getName();
		if (file.exists()) { // 判断文件父目录是否存在
			// response.setContentType("application/octet-stream");//
			// response.setHeader("content-type", "application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;fileName=" + filename);// 设置文件名
			FileInputStream fis = null; // 文件输入流
			BufferedInputStream bis = null;
			OutputStream os = null; // 输出流
			try {
				os = response.getOutputStream();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				IOUtils.copy(bis, os);
				System.out.println("----------file download" + filename);
			} catch (Exception e) {
				e.printStackTrace();
				return JSON.toJSONString(ResultFactory.newResultModel(EnumField.fail, null));
			} finally {
				try {
					bis.close();
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param json
	 * @return
	 * @author lenovo
	 * @date 2019年6月25日 上午9:37:04
	 */
	private static BaseEstate getBaseEstate(String json) {
		BaseEstate baseEstate = null;
		try {
			JSONObject obj = JSON.parseObject(json);
			String reportType = obj.getString("reportType");
			baseEstate = (BaseEstate) JSON.parseObject(json, NormalConstant.REPORT_TYPE_CLASS.get(reportType));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("反序列化BaseEstate失败:" + e.getMessage());
			return null;
		}
		return baseEstate;
	}
}
