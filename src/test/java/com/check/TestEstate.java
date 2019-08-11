/**
 * 
 */
package com.check;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.estate.frontier.mapper.LandInterface;
import com.estate.frontier.model.LandInfo;
import com.estate.frontier.model.RealEstateInfo;
import com.estate.frontier.model.constant.NormalConstant;
import com.estate.frontier.service.IndexService;

/**
 * @author lenovo
 * @data 2019年6月21日 下午4:16:12
 */
@RunWith(SpringRunner.class)
@PropertySource({ "classpath:application.yml" })
@WebAppConfiguration
@SpringBootTest
public class TestEstate {
	@Autowired
	private LandInterface landInterface;
	@Autowired
	private IndexService indexService;

	@Test
	public void testSave() {
		RealEstateInfo landInfo = new RealEstateInfo();
		// LandInfo landInfo = new LandInfo();
		landInfo.setReportType(NormalConstant.REAL_ESTATE);
		landInfo.setProjectName("房地产三期评估");
		landInfo.setAssessReportNum("(江苏) 天征XXX估第00000012");
		landInfo.setAssessStartTime(new Date());
		landInfo.setAssessEndTime(new Date());
		landInfo.setValueTime(new Date());
		landInfo.setAssessObject("房地产一期评估");
		//landInfo.setAssessAim("出让");
		landInfo.setAssessMethod("1-比较法");
		// landInfo.setLandSite("江苏省南京市江宁区苏源大道11号");
		// landInfo.setLandArea(93.5);
		// landInfo.setLandAmount(12.5);
		landInfo.setAssessUnitPrice(25000);
		landInfo.setAssessTotalPrice(93.5 * 25000);
		landInfo.setClient("张三");
		// landInfo.setClientEmail("1790944194@qq.com");
		// landInfo.setClientPhone("17749527530");
		landInfo.setFirstReporter("李四");
		landInfo.setFirstReporterRgNum("李四-00000013");
		landInfo.setPartReporter1("王五");
		landInfo.setPartReporter1RgNum("王五-00000013");
		landInfo.setPartReporter2("赵柳");
		landInfo.setPartReporter2RgNum("赵柳-00000013");
		landInfo.setServiceSource("NB-江苏-拉勾网");
		// landInfo.setServiceCharge(13569.56);
		landInfo.setBranchOffice("拉钩网-房地产分公司");
		landInfo.setChecker("张益达");
		landInfo.setAssessOrg("江苏) 天征评估机构");
		indexService.saveEstateData(landInfo, null);
	}

	@Test
	public void testUpdate() {
		LandInfo landInfo = new LandInfo();
		landInfo.setId(13);
		landInfo.setReportType(NormalConstant.REAL_ESTATE);
		landInfo.setProjectName("房地产二期评估");
		landInfo.setAssessReportNum("(江苏) 天征XXX估第00000010");
		landInfo.setAssessStartTime(new Date());
		landInfo.setAssessEndTime(new Date());
		landInfo.setValueTime(new Date());
		landInfo.setAssessObject("房地产二期评估");
		//landInfo.setAssessAim("出让");
		landInfo.setAssessMethod("1-比较法");
		landInfo.setLandSite("江苏省南京市江宁区苏源大道11号");
		landInfo.setLandArea(93.5);
		landInfo.setLandAmount(12.5);
		landInfo.setAssessUnitPrice(25000);
		landInfo.setAssessTotalPrice(93.5 * 25000);
		landInfo.setClient("张三");
		landInfo.setClientEmail("1790944194@qq.com");
		landInfo.setClientPhone("17749527530");
		landInfo.setFirstReporter("李四");
		landInfo.setFirstReporterRgNum("李四-00000013");
		landInfo.setPartReporter1("王五");
		landInfo.setPartReporter1RgNum("王五-00000013");
		landInfo.setPartReporter2("赵柳");
		landInfo.setPartReporter2RgNum("赵柳-00000013");
		landInfo.setServiceSource("NB-江苏-拉勾网");
		landInfo.setServiceCharge(13569.56);
		landInfo.setBranchOffice("珍爱网-房地产分公司");
		landInfo.setChecker("张益达");
		landInfo.setAssessOrg("江苏) 天征评估机构");
		indexService.updateEstateData(landInfo, null);
	}

	@Test
	public void testGet() {
		System.out.println(indexService.getDetailReport(13, NormalConstant.REAL_ESTATE));
	}

	@Test
	public void testDelete() {
		System.out.println(indexService.deleteEstateData(14, NormalConstant.REAL_ESTATE));
	}
}
