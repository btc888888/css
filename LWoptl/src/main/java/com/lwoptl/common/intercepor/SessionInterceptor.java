package com.lwoptl.common.intercepor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Inject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.lwoptl.common.visit.Visitor;
import com.lwoptl.common.visit.VisitorUtil;
import com.lwoptl.portal.core.service.SysRoleFuncService;

/**
 * 登陆session、权限认证
 * 
 * @author yujie.li
 * @date 2018-07-13
 */
public class SessionInterceptor implements Interceptor {

	@Inject
	SysRoleFuncService sysRoleFuncService;
	
	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		String actionKey = inv.getActionKey();
		HttpSession session = controller.getSession();
		Visitor vs = VisitorUtil.getVisitor(session);
		
		//需要身份认证的地址
		if (actionKey.startsWith("/portal")||actionKey.equals("/")) {
			if (vs == null) {
				//地址参数
				String para=controller.getPara()==null?"":"/"+controller.getPara();
				Map<String,String[]> paramMap=controller.getParaMap();
				boolean first=true;
				for(String key:paramMap.keySet()){
					para+=first?"?":"&";					
					para+=key+"="+paramMap.get(key)[0];
					first=false;
				}				
				//参数转码
				try {
					para=URLEncoder.encode(para, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				//重新登录，携带重定向地址
				controller.redirect("/pub/login?returnUrl="+actionKey+para);
				return;
			}
			
			//验证用户权限
			if(sysRoleFuncService.getSysPermissions().get(actionKey)!=null
					&&sysRoleFuncService.getUserPermissions(vs.getCode()).get(actionKey)==null) {								
				controller.getResponse().setStatus(403);
				controller.renderError(403);
				return;
			}
			
		}
		
		//用于页面按钮权限控制
		controller.setAttr("vs", vs);
		inv.invoke();
	}

}
