package com.lwoptl.portal.core.service;

import com.jfinal.plugin.activerecord.Model;
import com.lwoptl.common.base.service.BaseService;
import com.lwoptl.common.model.DataDictionaryValue;

/**
 * 字典数据值
 * @author yujie.li
 *
 */
public class DataDictionaryValueService extends BaseService {

	private DataDictionaryValue dao=new DataDictionaryValue().dao();

	/* (non-Javadoc)
	 * @see com.lwoptl.common.base.service.BaseService#getDao()
	 */
	@Override
	public Model<?> getDao() {
		return dao;
	}

}
