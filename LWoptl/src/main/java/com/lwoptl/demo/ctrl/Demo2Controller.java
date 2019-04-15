package com.lwoptl.demo.ctrl;

import com.jfinal.kit.Ret;
import com.lwoptl.common.base.BaseController;
import com.lwoptl.common.model.Demo1;
import com.lwoptl.common.routes.ControllerBind;

/**
*@author yujie.li
 * 2019年2月28日
 *TODO
 */
@ControllerBind(path="/demo2")
public class Demo2Controller extends BaseController {
	public void index() {
		render("demo2.html");
	}
	
	public void save() {
		//方法一：通过getPara("para_name")获取值。
		String date = getPara("date");
		/*方法二：通过Model绑定取值。 说明：如果 ajax 传递的数据前有 modelName 前缀的话，通过getModel(xx.calss)
		*如果 ajax 传递的数据没有 modelName 前缀的话，只需要在传入第二个参数为空字符串即可拿到值，如本例。
		*关于Model的自动生成可运行类：Table2ModelGenerator.java
		*如果前端所有值都在Model中set方法，直接用Demo1 demo1Model = getModel(Demo1.class,"");
		*如果前端有些值非Model属性， getModel(Demo1.class,""，true);其他的用getPara
		*/		
		Demo1 demo1Model = getModel(Demo1.class,"",true);
		String modules = getPara("modules");
		System.out.println("emai  is ------->"+demo1Model.getDate() );
		
		System.out.println("date is ------->"+date);
		System.out.println("modules is ------->"+modules);
		renderJson(Ret.ok());
	}
}
