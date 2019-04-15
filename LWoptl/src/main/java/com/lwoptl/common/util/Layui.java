package com.lwoptl.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.apache.xmlbeans.impl.common.ConcurrentReaderHashMap;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.lwoptl.common.model.Demo1;
import com.lwoptl.common.model.LogicItem;
import com.lwoptl.common.vo.FunNode;
import com.lwoptl.common.vo.TreeNode;

/**
*@author yujie.li
 * 2019年3月1日
 *TODO
 */
public class Layui {
	public Map<String, Object>  getLayuiMap(List<T>list,int page,int pageNumber){
		@SuppressWarnings("unchecked")
		Map<String, Object>  map =  new ConcurrentReaderHashMap();
		map.put("code", 0);
		map.put("msg", "null");
		map.put("count", list.size());
		map.put("page",page);
		map.put("limit",pageNumber);
		map.put("data", list);	
		return map;
	}

	/**
	 * @param list
	 * @param paraToInt
	 * @param paraToInt2
	 * @return
	 */
	public Map<String, Object> getLayuiMap(Page<?> page) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		Map<String, Object>  map =  new ConcurrentReaderHashMap();
		map.put("code", 0);
		map.put("msg", "null");
		map.put("count", page.getTotalRow());
		map.put("page",page.getPageNumber());
		map.put("limit",page.getPageSize());
		map.put("data", page.getList());	
		return map;
	}
	
	public List<JSONObject> jsonObjects(Collection<FunNode> collection) {
		JSONObject jsonObject = new JSONObject();
		List<JSONObject> l1 = new ArrayList<>();
		for (FunNode funNode : collection) {
			jsonObject = new JSONObject();
			jsonObject.put("id", FunNode.getId());
			jsonObject.put("name", FunNode.getName());
		//	jsonObject.put("href", FunNode.getAttributes().get("href"));
			if (FunNode.getChildren() != null && FunNode.getChildren().size() > 0) {
				List<JSONObject> lss = jsonObjects(FunNode.getChildren());
				jsonObject.put("children", lss);
			}
			l1.add(jsonObject);
		}
		return l1;
	}

	public List<JSONObject> jsonObjects2(Collection<LogicItem> collection) {
		JSONObject jsonObject = new JSONObject();
		List<JSONObject> l1 = new ArrayList<>();
		for (LogicItem logicItem : collection) {
			jsonObject = new JSONObject();
			jsonObject.put("id", logicItem.getId());
			jsonObject.put("name", logicItem.getName());
			
			l1.add(jsonObject);
		}
		return l1;
	}
	
	
}
