package com.lwoptl.portal.logical.ctrl;

import java.util.Collection;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.lwoptl.common.base.BaseController;
import com.lwoptl.common.model.LogicForm;
import com.lwoptl.common.model.LogicItem;
import com.lwoptl.common.model.LogicTimeline;
import com.lwoptl.common.routes.ControllerBind;
import com.lwoptl.common.util.Layui;
import com.lwoptl.common.visit.Visitor;
import com.lwoptl.common.visit.VisitorUtil;
import com.lwoptl.portal.logical.service.LogicalService;

/**
*@author yujie.li
 * 2019年3月5日
 *TODO
 */
@ControllerBind(path="/portal/rmaImport")
public class rmaImportController extends BaseController {
	@Inject LogicalService logicalService;
	@Inject Layui  layui;
	public void index() {
		render("index.html");
	}
	public void searchList() {
		String Center = getPara("Center");   
		String SerialNo = getPara("SerialNo");   
		String RMANo = getPara("RMANo");   
		String Status = getPara("Status");   
		String sflag = getPara("sflag");   
		Page<Record> list =new Page<>();
		if("Y".equals(sflag)) {
			 list  =	logicalService.getImportDetail(getParaToInt("page", 1),getParaToInt("  ", 10),Center,SerialNo,RMANo,Status);
			 renderJson(layui.getLayuiMap(list));
		}else {
			renderJson(suc("暂无数据"));
		}
	}


}
