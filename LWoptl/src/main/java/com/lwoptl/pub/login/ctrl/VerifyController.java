/**
 * @author yujie.li
 * @date 2018年10月15日
 */
package com.lwoptl.pub.login.ctrl;

import com.jfinal.core.Controller;
import com.lwoptl.common.kit.VerifyCodeKit;
import com.lwoptl.common.routes.ControllerBind;

/**
 * 验证码
 * 
 * @author yujie.li
 *
 */
@ControllerBind(path="/pub/verify")
public class VerifyController extends Controller {

	public void index() {
		VerifyCodeKit.createImage(getResponse(), 2);
		getSession().setAttribute("verifyCode", VerifyCodeKit.getVerityCode(2));
		renderNull();
	}
}
