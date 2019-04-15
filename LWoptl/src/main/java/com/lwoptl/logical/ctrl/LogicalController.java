package com.lwoptl.logical.ctrl;

import java.util.Collection;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lwoptl.common.base.BaseController;
import com.lwoptl.common.model.LogicForm;
import com.lwoptl.common.model.LogicItem;
import com.lwoptl.common.model.SysOrg;
import com.lwoptl.common.routes.ControllerBind;
import com.lwoptl.common.util.Layui;
import com.lwoptl.common.visit.Visitor;
import com.lwoptl.common.visit.VisitorUtil;
import com.lwoptl.logical.service.LogicalService;

/**
*@author yujie.li
 * 2019年3月5日
 *TODO
 */
@ControllerBind(path="/logical")
public class LogicalController extends BaseController {
	@Inject LogicalService logicalService;
	@Inject Layui  layui;
	public void index() {
		render("index.html");
	}
	public void getFunList() {
		Collection<JSONObject> list  =	logicalService.getFunlist("");
		System.out.println(list.size() + "asdadasd");
		renderJson(list);
	}
	public void showList() {
		String id = getPara("id");   
		Page<Record> list  =	logicalService.getFunDetial(id, getParaToInt("page", 1),getParaToInt("limit", 10));
		renderJson(layui.getLayuiMap(list));
	}
	public void add() {
		String pid = getPara("pid");
		String name = getPara("name");
	    setAttr("pid", pid);
	    setAttr("funName", name);
		render("add.html");
	}
	public void deleteCols() {
		String  deleteId = getPara("data[0][id]");
		LogicForm  logicForm = new LogicForm();
		logicForm.setId(deleteId);
		logicalService.deleteLogicForm(logicForm);
		renderJson();
	}
	public void upateForm() {
		String id = getPara("id");
		String funName = getPara("funname");
		LogicForm  logicForm =  logicalService.findFormById(id);
	    setAttr("funName", funName);
	    setAttr("pid", logicForm.getItemId());
	    setAttr("logicForm", logicForm);
	    render("add.html");
	}
	
	public void save() {
		LogicForm  logicForm = getModel(LogicForm.class,"",true);
		Visitor vs = VisitorUtil.getVisitor(getSession());
		logicForm.setCreateBy(vs.getCode() + "/" +  vs.getName());
		logicalService.addLgFrom(logicForm);
		setAttr("id",logicForm.getItemId());
		setAttr("name",getPara("name"));
		renderJson();
	}
	
	public void updateSave() {
		LogicForm  logicForm = getModel(LogicForm.class,"",true);
		Visitor vs = VisitorUtil.getVisitor(getSession());
		logicForm.setCreateBy(vs.getCode() + "/" +  vs.getName());
		logicalService.updateSaveLgFrom(logicForm);
		setAttr("id",logicForm.getItemId());
		setAttr("name",getPara("name"));
		renderJson();
	}
	
	public void addFunList() {
		String pid = getPara("pid");
		String name = getPara("name");
		LogicItem logicItem = getBean(LogicItem.class);
		logicItem.setParentId(pid);
		logicItem.setName(name);
		logicalService.addFunList(logicItem);
		Collection<JSONObject> list  =	logicalService.getFunlist(pid);
		System.out.println(list.size() + "asdadasd");
		renderJson(list);
	}

}
