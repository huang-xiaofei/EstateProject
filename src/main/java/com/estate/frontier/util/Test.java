/**
 * 
 */
package com.estate.frontier.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.estate.frontier.model.constant.EnumField;
import com.estate.frontier.model.factory.ResultModel;

/**
 * @author lenovo
 * @data 2019年6月26日 上午9:13:41
 */
public class Test {
	private static final Logger log = LogManager.getLogger(Test.class);

	/**
	 * @param args
	 * @author lenovo
	 * @date 2019年6月26日 上午9:13:41
	 */
	public static void main(String[] args) {
		log.info(JSON
				.toJSONString(new ResultModel(EnumField.nullObject.getKey(), EnumField.nullObject.getValue(), null)));
		;

	}

}
