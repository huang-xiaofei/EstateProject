/**
 * 
 */
package com.check;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.estate.frontier.mapper.LandInterface;
import com.estate.frontier.model.AssessInfo;
import com.estate.frontier.service.IndexService;

/**
 * @author lenovo
 * @data 2019年6月21日 下午4:16:12
 */
@RunWith(SpringRunner.class)
@PropertySource({ "classpath:application.yml" })
@WebAppConfiguration
@SpringBootTest
public class TestAssess {
	@Autowired
	private LandInterface landInterface;
	@Autowired
	private IndexService indexService;

	@Test
	public void testSave() {
		AssessInfo assessInfo = new AssessInfo();
		assessInfo.setReportType("4");
		assessInfo.setServiceSource("抢来的");
		assessInfo.setBranchOffice("好来网");
		assessInfo.setApplicationNum("xx12333");
		assessInfo.setChecker("郑老大");
		assessInfo.setAssessOrg("评估有限公司");
		assessInfo.setAssessAim("出售");
		indexService.saveEstateData(assessInfo, null);
	}

	@Test
	public void testUpdate() {
		AssessInfo assessInfo = new AssessInfo();
		assessInfo.setId(43);
		assessInfo.setReportType("4");
		assessInfo.setServiceSource("买来的");
		assessInfo.setBranchOffice("好来网");
		assessInfo.setApplicationNum("9999999");
		assessInfo.setChecker("张。。");
		assessInfo.setAssessOrg("评估有限公司");
		assessInfo.setAssessAim("出售");
		indexService.updateEstateData(assessInfo, null);
	}

	@Test
	public void testGet() {
		System.out.println(JSON.toJSON(indexService.getDetailReport(43, "4")));
	}

	@Test
	public void testDelete() {
		System.out.println(indexService.deleteEstateData(43, "4"));
	}
}
