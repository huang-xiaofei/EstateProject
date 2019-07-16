/**
 * 
 */
package com.check.json;

import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * @author lenovo
 * @data 2019年6月29日 下午3:21:11
 */
public class TestFJson {

	public static void main(String[] args) {
		Son son = new Son();
		son.setId(1);
		son.setAge("2");
		son.setName("xx");
		son.setSonId(2);
		String json = JSON.toJSONString(son);
		System.out.println(json);
		Map map = JSON.parseObject(json, Map.class);
		System.out.println(map);
	}
}
