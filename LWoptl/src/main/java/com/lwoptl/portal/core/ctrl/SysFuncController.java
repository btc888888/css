/**
 * @author yujie.li
 * @date 2018年8月17日
 */
package com.lwoptl.portal.core.ctrl;

import java.util.ArrayList;
import java.util.Collection;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.lwoptl.common.model.SysFunction;
import com.lwoptl.common.model.SysRole;
import com.lwoptl.common.routes.ControllerBind;
import com.lwoptl.common.vo.Feedback;
import com.lwoptl.common.vo.TreeNode;
import com.lwoptl.common.base.BaseController;
import com.lwoptl.common.config.WebContant;
import com.lwoptl.portal.core.service.SysFuncService;
import com.lwoptl.portal.core.service.SysRoleService;

/**
 * 功能
 * 
 * @author yujie.li
 *
 */
@ControllerBind(path="/portal/core/sysFunc")
public class SysFuncController extends BaseController {

	@Inject
	SysFuncService service;
	@Inject
	SysRoleService roleService;
	
	public void index() {
		render("index.html");
	}

	public void list() {
		Record record = new Record();
		record.set("parent_code ='"+getPara("id")+"' or id", getPara("id"));
		renderJson(service.page(getParaToInt("pageNumber", 1), getParaToInt("pageSize", 10), record));
	}

	public void add() {
		setAttr("parentId", getPara("parentId"));
		SysFunction parent = (SysFunction) service.findById(getPara("parentId"));
		if (parent == null) {
			setAttr("parentName", WebContant.projectName);
		} else {
			setAttr("parentName", parent.getFuncName());
		}
		render("add.html");
	}

	public void save() {
		SysFunction entity=getBean(SysFunction.class);
		SysFunction parent = (SysFunction) service.findById(entity.getParentCode());
		if (parent == null) {
			entity.setParentName(WebContant.projectName);
		} else {
			entity.setParentName(parent.getFuncName());
		}
		if(!service.saveEntity(entity)) {
			setException("功能编号已存在，请重新输入");
		}
		setAttr("sysFunction", entity);
		CacheKit.removeAll("funcManager");
		render("add.html");
	}

	public void edit() {
		setAttr("sysFunction", service.findById(getPara()));
		render("edit.html");
	}

	@Before(EvictInterceptor.class)
	@CacheName("userFunc")
	public void update() {
		SysFunction entity=getBean(SysFunction.class);
		SysFunction parent = (SysFunction) service.findById(entity.getParentCode());
		if (parent == null) {
			entity.setParentName(WebContant.projectName);
		} else {
			entity.setParentName(parent.getFuncName());
		}
		entity.update();
		setAttr("sysFunction", entity);
		CacheKit.removeAll("funcManager");
		render("edit.html");
	}

	@Before(EvictInterceptor.class)
	@CacheName("userFunc")
	public void delete() {
		service.deleteByIds(getIds());
		CacheKit.removeAll("funcManager");
		renderJson(Feedback.success());
	}

	/**
	 * 功能树
	 * 
	 * @author yujie.li
	 * @date 2018年10月8日
	 */
	public void tree() {
		Collection<TreeNode> nodeList = CacheKit.get("funcManager", "tree", new IDataLoader() {		
			@Override
			public Object load() {
				return service.getFunctionTree("frame");
			}
		});
				
		Collection<TreeNode> nodes = new ArrayList<TreeNode>();
		TreeNode node = new TreeNode();
		node.setId("frame");
		node.setText(WebContant.projectName);
		node.setChildren(nodeList);
		nodes.add(node);

		renderJson(nodes);
	}

	/**
	 * 角色功能树
	 *
	 * @param req
	 * @param roleCode
	 * @param g
	 * @return
	 * @author yujie.li
	 * @date 2018年3月6日上午10:21:49
	 */
	public void roleFuncTree() {
		try {
			String roleCode = getPara("roleCode");
			if (StrKit.notBlank(getPara("type"))) {
				if ("parentRoleCode".equals(getPara("type")) && !"sys".equals(getPara("roleCode"))) {
					SysRole sysRole = roleService.findByRoleCode(getPara("roleCode"));
					roleCode = sysRole.getParentId();
				}
			}
			
			Collection<TreeNode> nodeList = CacheKit.get("funcManager", roleCode);
			if(nodeList==null){				
				nodeList=service.getRoleFunctionTree(roleCode, "frame");
				CacheKit.put("funcManager", roleCode, nodeList);
			}
	
			Collection<TreeNode> nodes = new ArrayList<TreeNode>();
			TreeNode node = new TreeNode();
			node.setId("frame");
			node.setText(WebContant.projectName);
			node.setChildren(nodeList);
			nodes.add(node);
			renderJson(nodes);
		} catch (Exception e) {
			handerException(e);
			renderJson(err());
		}
	}

	@Before(EvictInterceptor.class)
	@CacheName("userFunc")
	public void isStop() {
		SysFunction entity = (SysFunction) service.findById(getPara("id"));
		entity.setIsStop(getParaToInt("state")).update();
		CacheKit.removeAll("funcManager");
		renderJson(suc());
	}
	
	/**
	 * 表单修改字段值
	 */
	@Before(EvictInterceptor.class)
	@CacheName("userFunc")
	public void updateFieldValue(){
		getModel(SysFunction.class).update();
		CacheKit.removeAll("funcManager");
		renderJson(suc("修改成功"));
	}
}
