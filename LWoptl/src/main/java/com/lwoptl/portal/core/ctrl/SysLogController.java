package com.lwoptl.portal.core.ctrl;

import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Record;
import com.lwoptl.common.base.BaseController;
import com.lwoptl.common.routes.ControllerBind;
import com.lwoptl.portal.core.service.SysLogService;

/**
 * 系统日志
 * @author yujie.li
 *
 */
@ControllerBind(path="/portal/core/sysLog")
public class SysLogController extends BaseController {

	@Inject
	SysLogService service;
	public void index(){
		render("index.html");
	}
	
	public void list(){
		Record record = new Record();
		record.set("user_code", getPara("userCode"));
		record.set("method_name", getPara("methodName"));
		record.set("create_time", getPara("createTime"));
		record.set("remark", getPara("remark"));
		renderJson(service.queryForList(getParaToInt("pageNumber",1), getParaToInt("pageSize",10),record,"order by create_time desc"));
	}
	
	public void detail(){
		setAttr("sysLog",service.findById(getPara()));
		render("detail.html");
	}
	
	public void delete(){
		service.deleteByIds(getIds());
		renderJson(suc());
	}
}
