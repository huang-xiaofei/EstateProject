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
import com.estate.frontier.model.AssetsInfo;
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
public class TestAssets {
	@Autowired
	private LandInterface landInterface;
	@Autowired
	private IndexService indexService;

	@Test
	public void testSave() {
		AssetsInfo assetsInfo = new AssetsInfo();
		assetsInfo.setReportType(NormalConstant.ASSETS_ESTATE);
		assetsInfo.setProjectName("房地产一期评估");
		assetsInfo.setAssessReportNum("(江苏) 天征XXX估第00000013");
		assetsInfo.setAssessStartTime(new Date());
		assetsInfo.setAssessEndTime(new Date());
		assetsInfo.setValueTime(new Date());
		assetsInfo.setAssessObject("房地产一期评估");
		//assetsInfo.setAssessAim("出让");
		assetsInfo.setAssessMethod("1-比较法");
		assetsInfo.setAssessedUnitName("天征XXX估第00000013");
		assetsInfo.setIsStateAssets(true);
		assetsInfo.setIsPrivateAsset(false);
		assetsInfo.setValueType("1-了解价值");
		assetsInfo.setAssessResult(230.99);
		assetsInfo.setAssessDate(new Date());
		assetsInfo.setActualFee(444444);
		assetsInfo.setAssetsReportDate(new Date());
		assetsInfo.setAssetsFee(1111);
		assetsInfo.setNoAssetsFee(false);
		assetsInfo.setDebtFee(222222);
		assetsInfo.setNoAssetsFee(false);
		assetsInfo.setNoNetAssets(true);
		indexService.saveEstateData(assetsInfo, null);
	}

	@Test
	public void testUpdate() {
		AssetsInfo assetsInfo = new AssetsInfo();
		assetsInfo.setId(17);
		assetsInfo.setReportType(NormalConstant.ASSETS_ESTATE);
		assetsInfo.setProjectName("房地产二期评估");
		assetsInfo.setAssessReportNum("(江苏) 天征XXX估第00000013");
		assetsInfo.setAssessStartTime(new Date());
		assetsInfo.setAssessEndTime(new Date());
		assetsInfo.setValueTime(new Date());
		assetsInfo.setAssessObject("房地产一期评估");
		//assetsInfo.setAssessAim("出让");
		assetsInfo.setAssessMethod("1-排除法");
		assetsInfo.setAssessedUnitName("天征XXX估第00000013");
		assetsInfo.setIsStateAssets(true);
		assetsInfo.setIsPrivateAsset(false);
		assetsInfo.setValueType("1-了解价值");
		assetsInfo.setAssessResult(230.99);
		assetsInfo.setAssessDate(new Date());
		assetsInfo.setActualFee(444444);
		assetsInfo.setAssetsReportDate(new Date());
		assetsInfo.setAssetsFee(1111);
		assetsInfo.setNoAssetsFee(false);
		assetsInfo.setDebtFee(222222);
		assetsInfo.setNoAssetsFee(false);
		assetsInfo.setNoNetAssets(true);
		assetsInfo.setBranchOffice("迪迪分公司");
		indexService.updateEstateData(assetsInfo, null);
	}

	@Test
	public void testGet() {
		System.out.println(indexService.getDetailReport(46, NormalConstant.ASSETS_ESTATE));
	}

	@Test
	public void testDelete() {
		System.out.println(indexService.deleteEstateData(46, NormalConstant.ASSETS_ESTATE));
	}
}
