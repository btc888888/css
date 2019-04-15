package com.lwoptl.portal.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.lwoptl.common.base.service.BaseService;
import com.lwoptl.common.model.DataDictionary;
import com.lwoptl.common.vo.TreeNode;

/**
 * 数据字典
 * @author yujie.li
 *
 */
public class DataDictionaryService extends BaseService {

	private DataDictionary dao=new DataDictionary().dao();

	/* (non-Javadoc)
	 * @see com.lwoptl.common.base.service.BaseService#getDao()
	 */
	@Override
	public Model<?> getDao() {
		return dao;
	}

	/**
	 * 数据字典树
	 * @return
	 */
	public Collection<TreeNode> getDictionaryTree() {
		List<Record> list = queryAllList("order by order_num asc");
		Collection<TreeNode> nodes = new ArrayList<TreeNode>();
		for (Record record : list) {
			TreeNode node = new TreeNode();		
			node.setId(record.getStr("code"));
			node.setText(record.getStr("name"));
			Collection<TreeNode> children =new ArrayList<TreeNode>();
			node.setChildren(children);
			nodes.add(node);
		}
		return nodes;
	}
	
}
