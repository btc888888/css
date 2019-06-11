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
@ControllerBind(path="/portal/logical")
public class LogicalController extends BaseController {
	@Inject LogicalService logicalService;
	@Inject Layui  layui;
	public void index() {
		render("index.html");
	}
	/*
	 * 获取左侧功能清单List
	 */
	public void getFunList() {
		Collection<JSONObject> list  =	logicalService.getFunlist("");
		System.out.println(list.size() + "asdadasd");
		renderJson(list);
	}
	/*
	 * 获取右侧安段说明清单并分页
	 */
	public void showList() {
		String id = getPara("id");   
		String funName = getPara("funName");   
		setAttr("funName", funName);
		Page<Record> list  =	logicalService.getFunDetial(id, getParaToInt("page", 1),getParaToInt("limit", 10));
		renderJson(layui.getLayuiMap(list));
	}
	/*
	 *测试连接第二个数据库
	 */
	public void ServiceDetail() {
		Page<Record> list  =	logicalService.getServiceDetail(getParaToInt("page", 1),getParaToInt("limit", 10));
		renderJson(layui.getLayuiMap(list));
	}
	/*
	 * 点击新增跳转到add.html
	 */
	public void add() {
		String pid = getPara("pid");
		String name = getPara("name");
	    setAttr("pid", pid);
	    setAttr("funName", name);
		render("add.html");
	}
	/*
	 * 删除选择的字段功能
	 */
	public void deleteCols() {
		String  deleteId = getPara("data[0][id]");
		LogicForm  logicForm = new LogicForm();
		logicForm.setId(deleteId);
		renderJson(logicalService.deleteLogicForm(logicForm));
	}
	/*
	 * 删除当前行
	 */
	public void deleteCols2() {
		LogicForm  logicForm =  new LogicForm();
		logicForm.setId(getPara("id"));
		renderJson(logicalService.deleteLogicForm(logicForm));
	}
	/*
	 * 编辑字段明细
	 */
	public void upateForm() {
		String id = getPara("id");
		String funName = getPara("funname");
		LogicForm  logicForm =  logicalService.findFormById(id);
	    setAttr("funName", funName);
	    setAttr("pid", logicForm.getItemId());
	    setAttr("logicForm", logicForm);
	    render("add.html");
	}
	/*
	 * 保存新增字段
	 */
	public void save() {
		LogicForm  logicForm = getModel(LogicForm.class,"",true);
		Visitor vs = VisitorUtil.getVisitor(getSession());
		logicForm.setCreateBy(vs.getCode() + "/" +  vs.getName());
		logicalService.addLgFrom(logicForm);
		setAttr("id",logicForm.getItemId());
		setAttr("name",getPara("name"));
		renderJson();
	}
	
	/*
	 * 保存需求时间线明细
	 */
	public void saveResume() {
		LogicTimeline  logicTimeline  = new LogicTimeline();
		Visitor vs = VisitorUtil.getVisitor(getSession());
		logicTimeline.setTlCreator(vs.getCode() + "/" +  vs.getName());
		logicTimeline.setItemId(getPara("reqId"));
		logicTimeline.setTlContent(getPara("tlContent"));
		if(logicalService.addResume(logicTimeline))
	    setAttr("msg","简历更新成功!");
		renderJson();
	}
	
	/*
	 * 保存修改后的字段明细
	 */
	public void updateSave() {
		LogicForm  logicForm = getModel(LogicForm.class,"",true);
		Visitor vs = VisitorUtil.getVisitor(getSession());
		logicForm.setCreateBy(vs.getCode() + "/" +  vs.getName());
		if(logicalService.updateSaveLgFrom(logicForm))
		setAttr("msg","保存成功!");
		setAttr("id",logicForm.getItemId());
		setAttr("name",getPara("name"));
		
		renderJson();
	}
	/*
	 * 获取Timeline 清单
	 */
	public void tlLoading() {
		String reqId = getPara("reqId");
		renderJson(logicalService.getTimelineList(reqId));
	}
	/*
	 * 新增左侧功能清单
	 */
	public void addFunList() {
		String pid = getPara("pid");
		String name = getPara("name");
		LogicItem logicItem = getBean(LogicItem.class);
		logicItem.setParentId(pid);
		logicItem.setName(name);
		if(logicalService.addFunList(logicItem))
	    setAttr("msg","添加成功!");
		//Collection<JSONObject> list  =	logicalService.getFunlist(pid);
		renderJson();
	}
	/*
	 * 上传文件
	 */
	public void uploadFile() {
		List<UploadFile> uploadList = getFiles();
		String pid = getPara("pid");
		List<String> url = saveFiles(uploadList, pid);		
		renderJson(Ret.ok("url", url));	
	}
	/*
	 * 加载文件清单
	 */
	public void loadFilelist() {
		String reqId = getPara("reqId");
		renderJson(logicalService.getFileList(reqId));
	}

}
