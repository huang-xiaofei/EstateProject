/**
 * 
 */
package com.estate.frontier.model.factory;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * @author lenovo
 * @data 2019年6月25日 下午8:52:44
 */
@JSONType(orders = { "code", "state", "data" })
public class ResultModel {

	private String code;
	private String state;
	private Object data;
	private Integer count;
	public ResultModel() {

	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @param reportInfo
	 * @param code
	 * @param state
	 */
	public ResultModel(String code, String state, Object data) {
		super();
		this.code = code;
		this.state = state;
		this.data = data;
	}
	public ResultModel(String code, String state, Object data,int count) {
		super();
		this.code = code;
		this.state = state;
		this.data = data;
		this.count=count;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
