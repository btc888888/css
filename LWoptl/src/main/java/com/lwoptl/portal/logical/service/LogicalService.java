package com.lwoptl.portal.logical.service;

import java.util.ArrayList;
import java.util.Collection;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.lwoptl.common.base.service.BaseService;
import com.lwoptl.common.kit.IdKit;
import com.lwoptl.common.model.FileUploaded;
import com.lwoptl.common.model.LogicForm;
import com.lwoptl.common.model.LogicItem;
import com.lwoptl.common.model.LogicTimeline;

/**
*@author yujie.li
 * 2019年2月28日
 *TODO
 */
public class LogicalService extends BaseService {
    private  LogicItem dao = new LogicItem().dao();
    private LogicForm dao2 = new LogicForm().dao();
    private LogicTimeline tldao = new LogicTimeline().dao();
    private FileUploaded fileDao = new FileUploaded();
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
    
    public boolean addResume(LogicTimeline lt) {
    	lt.setId(IdKit.createUUID());
    	return lt.save();
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
    }
    public      Collection<LogicTimeline> getTimelineList(String itemId) {
    	Collection<LogicTimeline> list = tldao.find("SELECT *  FROM logic_timeline WHERE item_id =?  ORDER BY tl_time DESC", itemId);
    	return   list;
    }
    
    public      Collection<FileUploaded> getFileList(String itemId) {
    	Collection<FileUploaded> list = fileDao.find("SELECT *  FROM file_uploaded WHERE objectId =?  ORDER BY create_time DESC", itemId);
    	return   list;
    }
    
    public      Page<Record> getFunDetial(String id, int page,int limit) {
	   	 Page<Record> list = Db.paginate(page, limit, "SELECT  * ", "FROM logic_form WHERE item_id = ? order by create_date desc",id);
	   	 return   list;
	   }
    
    public      Page<Record> getServiceDetail( int page,int limit) {
    	Page<Record> list = Db.use("oracle").paginate(page, limit, "SELECT  * "," from ES_REPAIR_ITEM_TBL");
		return list;
    }
    
    public  LogicForm  findFormById(String id) {
    	return dao2.findById(id);
    }
	/**
	 * @param paraToInt
	 * @param paraToInt2
	 * @param center
	 * @param serialNo
	 * @param rMANo
	 * @param status
	 * @return
	 */
	public Page<Record> getImportDetail(Integer paraToInt, Integer paraToInt2, String center, String serialNo,String rMANo, String status) {
		Kv conds = Kv.by("center",center).set("serialNo",serialNo).set("rMANo",rMANo).set("status",status);
		SqlPara sp = Db.getSqlPara("import.searchImportList",conds);
		Page<Record> list = Db.use("oracle").paginate(paraToInt, paraToInt2, sp);
    	return list;
	}
    
}
