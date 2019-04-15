/**
 * @author yujie.li
 * @date 2018年8月17日
 */
package com.lwoptl.portal.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.lwoptl.common.base.service.BaseService;
import com.lwoptl.common.kit.Md5Kit;
import com.lwoptl.common.model.SysUser;
import com.lwoptl.common.vo.Grid;

/**
 * 用户
 * 
 * @author yujie.li
 *
 */
public class SysUserService extends BaseService {

	private SysUser dao = new SysUser().dao();
	
	/* (non-Javadoc)
	 * @see com.lwoptl.common.base.service.BaseService#getDao()
	 */
	@Override
	public Model<?> getDao() {
		return dao;
	}
	
	public Grid page(int pageNumber, int pageSize, Record record) {
		Record rd = new Record();
		rd.set("a.user_code like '%" + record.getStr("userName") + "%' or a.user_name", record.getStr("userName"));
		rd.set("a.org_id", record.getStr("orgId"));
		rd.set("a.sex", record.getStr("sex"));
		String sql=Db.getSql("core.getUserList");
		return queryForList(sql,pageNumber, pageSize, rd, "group by a.id");
	}

	/**
	 * 部门下拉选择数据
	 * [{value:id,text:orgname}]
	 * @return
	 */
	public List<Record> queryOrgIdAndNameRecord(){
		return queryForList("select id 'value',org_name 'text' from sys_org");
	}
	
	public SysUser findByUserCode(String userCode) {
		return dao.findById(userCode);
	}

	public boolean saveEntity(SysUser entity) {
		if(isExist(entity.getUserCode())) {
			return false;
		}
		entity.setPasswd(Md5Kit.md5(entity.getPasswd()));
		return entity.save();
	}

	@Override
	public boolean deleteById(String id) {
		if(!"admin".equals(id)&&!"superadmin".equals(id)) {		
			return dao.deleteById(id);
		}
		return false;
	}

	@Override
	public void deleteByIds(List<String> ids) {
		Object[][] paras=new Object[ids.size()][1];
		for(int i=0;i<ids.size();i++) {
			if(!"superadmin".equals(ids.get(i))&&!"admin".equals(ids.get(i))){
				paras[i][0]=ids.get(i);				
			}
		}
		String sql="delete from sys_user where id=?";
		Db.batch(sql, paras, 50);
	}
	
	public boolean isExist(String userCode) {
		if (findByUserCode(userCode)!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 默认密码:用户编号+123
	 * @param ids
	 * @author yujie.li
	 * @date 2018年12月5日
	 */
	public void resetPassword(List<String> ids) {
		for(String id:ids) {
			SysUser entity=(SysUser) findById(id);
			entity.setPasswd(Md5Kit.md5(id+"123"));
			entity.update();
		}
	}
	
	public boolean updatePassword(String userCode,String newPassword,String oldPassword){
		SysUser entity=findByUserCode(userCode);
		if(!entity.getPasswd().equals(Md5Kit.md5(oldPassword))){
			return false;
		}
		entity.setPasswd(Md5Kit.md5(newPassword));
		return entity.update();
	}
	
	/**
	 * 获取用户功能权限map集合
	 * @param userCode
	 * @return
	 */
	public Map<String, Boolean> getUserFuncMap(String userCode) {
		List<Record> list;
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		String sql = Db.getSql("core.getUserFuncMap");
		list = Db.find(sql, userCode);
		if (list != null) {
			for (Record record : list) {
				map.put(record.getStr("function_id"), true);
			}
		}

		return map;
	}


}
