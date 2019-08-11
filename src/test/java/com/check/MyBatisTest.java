package com.check;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.estate.frontier.mapper.RealEstateInterface;
import com.estate.frontier.mapper.ReportInterFace;
import com.estate.frontier.model.RealEstateInfo;
import com.estate.frontier.model.ReportInfo;
import com.estate.frontier.service.IndexService;

@RunWith(SpringRunner.class)
@PropertySource({ "classpath:application.yml" })
@WebAppConfiguration
@SpringBootTest
public class MyBatisTest {

	@Autowired
	private RealEstateInterface realEstateInterface;

	@Autowired
	private ReportInterFace reportInterFace;
	@Autowired
	private IndexService indexService;

	@Test
	public void test() {
		RealEstateInfo realEstateInfo = new RealEstateInfo();
		// realEstateInfo.setId(1);
		realEstateInfo.setProjectName("���ز����۱���");
		realEstateInfo.setAssessEndTime(new Date());
		realEstateInfo.setAssessAim("����");
		realEstateInfo.setAssessMethod("1-�ų���");
		realEstateInfo.setAssessObject("���ز�");
		realEstateInfo.setAssessOrg("����ST");
		realEstateInfo.setAssessTotalPrice(34.54);
		realEstateInfo.setReportType("���ز����۱���");
		// System.out.println(realEstateInterface.checkProjectName("���ز����۱���3"));
		indexService.saveEstateData(realEstateInfo, null);
	}

	@Test
	public void testGetReport() {
		List<ReportInfo> list = reportInterFace.getAllReports();
		System.out.println(JSON.toJSONString(list));
	}

	@Test
	public void testDeleteReport() {
		reportInterFace.delete(1);
	}

	@Test
	public void testSaveReport() throws Exception {
		ReportInfo info = new ReportInfo();
		info.setApplicant("����");
		//info.setApplicationDate(new Date());
		info.setApplicationNum("001005");
		info.setAssessAim("����Ͷ��");
		info.setBranchOffice("����");
		info.setCheckResult("δͨ��");
		info.setReportType("���ز����۱���");
		info.setOrderNum("003");
		info.setState("δ���");
		System.out.println(reportInterFace.save(info));
		System.out.println(info.getId());
		if ("���ز����۱���".equals(info.getReportType())) {
			RealEstateInfo realEstateInfo = new RealEstateInfo();
			realEstateInfo.setId(info.getId());
			realEstateInfo.setProjectName("���ز����۱���");
			realEstateInfo.setAssessEndTime(new Date());
			realEstateInfo.setAssessAim("����");
			realEstateInfo.setAssessMethod("1-�ų���");
			realEstateInfo.setAssessObject("���ز�");
			realEstateInfo.setAssessOrg("����ST");
			realEstateInfo.setAssessTotalPrice(34.54);
			realEstateInterface.save(realEstateInfo);
		}
	}
}
