package com.lwoptl.demo.service;

import java.awt.List;

import com.alibaba.druid.sql.dialect.db2.visitor.DB2ASTVisitor;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lwoptl.common.base.service.BaseService;
import com.lwoptl.common.model.Demo1;

/**
*@author yujie.li
 * 2019年2月28日
 *TODO
 */
public class DemoService extends BaseService {
    private  Demo1 dao = new Demo1().dao();
	/* (non-Javadoc)
	 * @see com.lwoptl.common.base.service.BaseService#getDao()
	 */
	@Override
	public Model<?> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
    public boolean save(Demo1 demo ) {
    	return demo.save();
    }
    
    public      Page<Demo1> getDemolist(int page,int limit) {
    	 Page<Demo1> list = dao.paginate(page, limit, "select *  ", "from demo1");
    	 return   list;
    	//return dao.find("select * from demo1 where username ");
    	
    }
    public      Page<Record> getDemolist2(int page,int limit) {
    	//String sql ="SELECT * FROM sys_log a LEFT JOIN sys_function b on  a.url = b.link_page";
   	 Page<Record> list = Db.paginate(page, limit, "select *  ", "FROM sys_log a LEFT JOIN sys_function b on  a.url = b.link_page where a.ip =?","127.0.0.1");
   	 return   list;
   	//return dao.find("select * from demo1 where username ");
   	
   }
}
