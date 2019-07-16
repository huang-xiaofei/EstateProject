/**
 * 
 */
package com.estate.frontier.model.factory;

import com.estate.frontier.model.constant.EnumField;

/**
 * @author lenovo
 * @data 2019年7月5日 下午3:01:13
 */
public class ResultFactory {

	public static ResultModel newResultModel(EnumField e, Object data) {
		return new ResultModel(e.getKey(), e.getValue(), data);
	}
}
