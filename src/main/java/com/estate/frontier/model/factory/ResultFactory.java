/**
 * 
 */
package com.estate.frontier.model.factory;

import java.util.List;

import com.estate.frontier.model.constant.EnumField;

/**
 * @author lenovo
 * @data 2019年7月5日 下午3:01:13
 */
public class ResultFactory {

	public static ResultModel newResultModel(EnumField e, Object data) {
		if(data instanceof List) {
			List<?> list =(List<?>) data;
			return new ResultModel(e.getKey(), e.getValue(), data,list.size());
		}
		return new ResultModel(e.getKey(), e.getValue(), data);
	}
}
