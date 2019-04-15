package com.lwoptl.logical.service;

import java.util.ArrayList;
import java.util.Collection;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lwoptl.common.base.service.BaseService;
import com.lwoptl.common.model.LogicForm;
import com.lwoptl.common.model.LogicItem;

/**
*@author yujie.li
 * 2019年2月28日
 *TODO
 */
public class LogicalService extends BaseService {
    private  LogicItem dao = new LogicItem().dao();
    private LogicForm dao2 = new LogicForm().dao();
	/* (non-Javadoc)
	 * @see com.lwoptl.common.base.service.BaseService#getDao()
	 */
	@Override
	public Model<?> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
    public boolean addFunList(LogicItem logicItem ) {
    	LogicItem logicItem2 = dao.findFirst("select  CONCAT(\"F\",LPAD(MAX(substr(id,2))+1,3,0))  id FROM  logic_item a");
    	logicItem.setId( logicItem2.getId());
    	return logicItem.save();
    }
    public boolean addLgFrom(LogicForm logicForm ) {
    	LogicForm logicForm2 =   dao2.findFirst("SELECT  CONCAT(\"-C\",LPAD(IFNULL(MAX(substr(id,7)),'0') + 1,3,0)) id  FROM logic_form a WHERE a.item_id = ?",logicForm.getItemId());
    	logicForm.setId(logicForm.getItemId() + logicForm2.getId());
    	return logicForm.save();
    }
    
    public boolean updateSaveLgFrom(LogicForm logicForm ) {
    	return logicForm.update();
    }
    public boolean deleteLogicForm(LogicForm logicForm ) {
    	return logicForm.delete();
    }
    
    public      Collection<JSONObject> getFunlist(String parId) {
    	Collection<LogicItem> list = dao.find("select * from logic_item where parent_id = ?", parId);
    	 JSONObject jsonObject = new JSONObject();
    	 Collection<JSONObject> l1 = new ArrayList<>();
    		for (LogicItem logicItem : list) {
    			jsonObject = new JSONObject();
     			jsonObject.put("id", logicItem.getId());
     			jsonObject.put("name", logicItem.getName());
     			Collection<JSONObject> list2 = getFunlist(logicItem.getId());
     			if(list2.size()>0) {
     				jsonObject.put("children", list2);
     			}
     			l1.add(jsonObject);
    		}
 		return l1;

    }
    public      Collection<LogicItem> getChildFunlist(String parId) {
    	Collection<LogicItem> list = dao.find("select * from logic_item where parent_id = ?", parId);
    	 return   list;
    	//return dao.find("select * from demo1 where username ");
    	
    }
    
    public      Page<Record> getFunDetial(String id, int page,int limit) {
	   	 Page<Record> list = Db.paginate(page, limit, "SELECT  * ", "FROM logic_form WHERE item_id = ? order by create_date desc",id);
	   	 return   list;
	   }
    public  LogicForm  findFormById(String id) {
    	return dao2.findById(id);
    }
    
}
