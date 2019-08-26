/**
 * 
 */
package com.estate.frontier.model.constant;

/**
 * @author lenovo
 * @data 2019年6月25日 下午3:53:54
 */
public enum EnumField {

	upLoadFail("400", "上传文件失败"), saveEstateFail("401", "保存报告信息失败"), nullObject("500", "传入数据为空或格式不对"),
	sucess("200", "execute sucess"), fail("500", "execute failed");
	private String key;
	private String value;

	/**
	 * @param key
	 * @param value
	 */
	private EnumField(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
