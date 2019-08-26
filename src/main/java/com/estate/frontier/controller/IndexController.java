/**
 * 
 */
package com.estate.frontier.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.estate.frontier.model.base.BaseEstate;
import com.estate.frontier.model.constant.EnumField;
import com.estate.frontier.model.constant.NormalConstant;
import com.estate.frontier.model.factory.ResultFactory;
import com.estate.frontier.model.factory.ResultModel;
import com.estate.frontier.service.IndexService;
import com.estate.frontier.util.FileUtil;
import com.estate.frontier.util.FileUtil.FileResult;

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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveRptOrFile", method = RequestMethod.POST)
	@ApiOperation(value = "上传文件和保存报告信息", notes = "先保存报告信息，在上传文件")
	@ResponseBody
	public String saveRptOrFile(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		if (null == files || files.isEmpty()) {
			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.fail, "无文件！"));
		}
		if (files.size() > 2) {
			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.fail, "文件数超过两个！"));
		}
		for (MultipartFile multipartFile : files) {
			log.info("saveRptOrFile->收到file:{}", multipartFile.getOriginalFilename());
		}
		// MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
		String json = request.getParameter("data");
		log.info("saveRptOrFile->收到json:{}", json);

		BaseEstate base = getBaseEstate(json);
		if (null == base) {
			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.nullObject, null));
		}
		ResultModel result = indexService.saveEstateData(base, files);
		log.info("files:"+JSON.toJSONString(result));
		wordToPdf((List<FileResult>) result.getData());
		System.out.println("------------------------------------------------");
		return JSON.toJSONString(result);
	}
	@RequestMapping(value = "/saveRpt", method = RequestMethod.POST)
	@ApiOperation(value = "保存报告信息", notes = "保存报告信息")
	@ResponseBody
	public String saveRpt(@RequestBody String json) {
		log.info("saveRpt收到数据:{}",json);
		BaseEstate base = getBaseEstate(json);
		if (null == base) {
			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.nullObject, null));
		}
		ResultModel result = indexService.saveEstateData(base, null);
		
		return JSON.toJSONString(result);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ApiOperation(value = "上传文件和保存报告信息", notes = "先保存报告信息，在上传文件")
	@ResponseBody
	public String Test(@RequestBody MultipartFile file, @RequestParam String json) {
		// log.info("saveRptOrFile->收到file:{},收到json:{}" + file.getOriginalFilename(),
		// json);
		BaseEstate base = getBaseEstate(json);
		if (null == base) {
			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.nullObject, null));
		}
		ResultModel result = indexService.saveEstateData(base, Arrays.asList(file));
		wordToPdf((List<FileResult>) result.getData());
		System.out.println("------------------------------------------------");
		return JSON.toJSONString(result);
	}
//
//	//@RequestMapping(value = "/updateRptOrFile", method = RequestMethod.POST)
//	@ApiOperation(value = "更新报告和文件信息", notes = "先更新报告信息，在上传文件")
//	@ResponseBody
//	@Deprecated
//	public String updateRptOrFile(HttpServletRequest request) {
//		MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
//		String json = request.getParameter("data");
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

	@RequestMapping(value = "/transferTo", method = RequestMethod.GET)
	@ApiOperation(value = "转让给其他人盖章", notes = "transferTo(转给的用户名),id(报告id)")
	@ResponseBody
	public String transferTo(@RequestParam String transferTo, @RequestParam int id) {
		ResultModel result = indexService.transferTo(transferTo, id);
		return JSON.toJSONString(result);
	}

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
	
	@RequestMapping(value = "/getReports", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取报告列表", notes = "不传参数则获取所有报告信息")
	public String getReportsByConditions(@RequestBody Map<String, Object> params) {
		log.info("获取的参数为={}", JSON.toJSONString(params));
		ResultModel result = indexService.getReportsByConditions(params);
		return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
	}

	@RequestMapping(value = "/getCheckReports", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取报告列表", notes = "不传参数则获取所有报告信息")
	public String getCheckReportsByConditions(@RequestBody Map<String, Object> params) {
		log.info("获取的参数为={}", JSON.toJSONString(params));
		ResultModel result = indexService.getCheckReportsByConditions(params);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/getReportsDetail", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取详细报告", notes = "获取详细报告")
	public String getDetailReport(@RequestParam int id, @RequestParam String reportType) {
		log.info("执行getDetailReports,id={},reportType={}", id, reportType);
		ResultModel result = indexService.getDetailReport(id, reportType);
		return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
	}
	@RequestMapping(value = "/getStaticsList", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取统计列表", notes = "{branchOffice:,reportType:,assessAim:,valueTime}")
	public String getStaticsList(@RequestBody Map<String, Object> params) {
		log.info("获取的参数为={}", JSON.toJSONString(params));
		ResultModel result = indexService.getStaticsByConditions(params);
		return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
	}
	@RequestMapping(value = "/getReportsByStates", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "根据多个state查询列表", notes = "前端传入格式:[1,2,3]")
	public String getReportsByStates(@RequestParam(value = "states[]") List<String> states) {
		log.info("getReportsByStates接收到的数据为:{}", states);
		ResultModel result = indexService.getReportsByStates(states);
		return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
	}

	@RequestMapping(value = "/updateRemarkOrStampState", method = RequestMethod.POST)
	@ResponseBody
	public String updateRemarkOrStampState(@RequestBody Map<String, Object> params) {
		ResultModel result = indexService.updateRemarkOrStampState(params);
		return JSON.toJSONString(result);
	}

	/**************** 先上传在保存 ****************************/

	public static void main(String[] args) throws Exception {
		String json = "{\"reportType\":\"1\",\"projectName\":\"\",\"assessReportNum\":\"\",\"assessStartTime\":\"2019-05-06\",\"assessEndTime\":\"2019-05-06\",\"assessObject\":\"\",\"valueTime\":\"2019-05-06\",\"assessAim\":[\"出让\",\"转让\"],\"assessMethod\":\"\",\"valueType\":\"\",\"buildingArea\":\"\",\"floorArea\":\"\",\"assessUnitPrice\":\"\",\"assessTotalPrice\":\"\",\"client\":\"\",\"firstReporter\":\"\",\"firstReporterRgNum\":\"\",\"partReporter1\":\"\",\"partReporter1RgNum\":\"\",\"partReporter2\":\"\",\"partReporter2RgNum\":\"\",\"serviceSource\":\"\",\"branchOffice\":\"\",\"serviceCharge\":\"\",\"checker\":\"\",\"assessOrg\":\"\"}";
		JSONObject obj = JSON.parseObject(json);
		BaseEstate base = getBaseEstate(json);
		System.out.println(base);
	}

	@RequestMapping(value = "/updateRpt", method = RequestMethod.POST)
	@ApiOperation(value = "更新报告信息", notes = "只更新报告信息")
	@ResponseBody
	public String updateRpt(@RequestBody String json) {
		System.out.println(json);
		BaseEstate base = getBaseEstate(json);
		if (null == base) {
			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.nullObject, null));
		}

		ResultModel result = indexService.updateEstateData(base);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/updateUri", method = RequestMethod.POST)
	@ApiOperation(value = "更新上传zip文件的uri", notes = "字段{reportType:1,id:22,zipUri:'D://test/a.zip',pdfUri:'',wordUri:''}")
	@ResponseBody
	public ResultModel updateUri(@RequestBody Map<String, String> params) {
		return indexService.updateZipUri(params);
	}

	@RequestMapping(value = "/upLoad", method = RequestMethod.POST, consumes = "multipart/form-data", produces = "text/plain;charset=UTF-8")
	@ApiOperation(value = "上传文件", notes = "上传文件")
	@ResponseBody
	public String upLoad(@RequestParam("file") List<MultipartFile> file) {
		for (MultipartFile multipartFile : file) {
			log.info("upLoad："+file.size());
			log.info("upLoad："+multipartFile.getOriginalFilename());
		}
		if (file == null || file.isEmpty()) {
			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.fail, "无文件传入！"));
		}
		List<FileResult> results = null;
		try {
			results = FileUtil.upLoad(file);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultFactory.newResultModel(EnumField.fail, "上传文件异常！"));
		}
		wordToPdf(results);
		return JSON.toJSONString(ResultFactory.newResultModel(EnumField.sucess, results));
	}

	/**
	 * @param results
	 * @author lenovo
	 * @date 2019年8月11日 下午12:46:56
	 */
	private void wordToPdf(List<FileResult> results) {
		if(null ==results) {
			return;
		}
		for (FileResult r : results) {
			if(r.getWordPath().endsWith(".pdf")) {
				continue;
			}
			if (!FileUtil.isZip(r.getWordPath())) {
				indexService.word2Pdf(r);// 异步执行word转为pdf
			}
		}
	}

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
