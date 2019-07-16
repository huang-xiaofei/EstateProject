/**
 * 
 */
package com.estate.frontier.mapper.base;

import com.estate.frontier.model.base.BaseEstate;

/**
 * @author lenovo
 * @data 2019年7月5日 下午5:20:50
 */
public interface BaseInterface {
	public void save(BaseEstate assessInfo);

	public void update(BaseEstate assessInfo);

	public void delete(int id);

	public BaseEstate get(int id);
}
