package com.lwoptl.portal;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.common.ConcurrentReaderHashMap;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Ret;
import com.jfinal.upload.UploadFile;
import com.lwoptl.common.model.FileUploaded;
import com.lwoptl.common.routes.ControllerBind;
import com.lwoptl.common.base.BaseController;
import com.lwoptl.common.config.WebContant;

/**
 * 附件、公共方法类
 * 特别说明:该类的的viewPath="/"
 * @author yujie.li
 *
 */
@ControllerBind(path="/portal")
public class PortalController extends BaseController {

	/**
	 * 
	 * go后面带视图目录参数 如：/portal/go/common/upload/upload，<br/>
	 * 将会访问/common/upload/upload.html文件
	 * 
	 * @author yujie.li
	 * @date 2018年8月2日
	 */
	public void go() {
		render(getAttr("view") + ".html");
	}

	/**
	 * 上传页面
	 * 
	 * @author yujie.li
	 * @date 2018年8月13日
	 */
	public void toUpload() {
		String objectId = getPara();
		if (objectId == null) {
			objectId = getVisitor().getCode();
		}
		setAttr("objectId", objectId);
		render("common/upload/upload.html");
	}

	/**
	 * 上传文件,可多附件上传
	 * 
	 * @author yujie.li
	 * @date 2018年8月1日
	 */
	public void upload() {
		List<UploadFile> uploadList = getFiles();
		String objectId = getPara() == null ? getPara("objectId") : getPara();
		List<String> url = saveFiles(uploadList, objectId);		
		renderJson(Ret.ok("url", url));	
	}

	/**
	 * 下载文件
	 * 
	 * @params url格式：18080615/18080615025800002
	 * @author yujie.li
	 * @date 2018年8月1日
	 */
	public void download() {
		String url = getAttr("url", "");
		FileUploaded fu;
		if (url.contains("/")) {
			fu = getFileUploaded(getAttr("url"));
		} else {
			fu = getFileUploadedByObjectId(getAttr("url"));
		}

		renderFile(new File(fu.getSavePath()), fu.getFileName());
	}

	/**
	 * 删除文件
	 * 
	 * @params url格式：18080615/18080615025800002
	 * @author yujie.li
	 * @date 2018年8月1日
	 */
	public void delete() {
		String url = getAttr("url", "");
		if (url.contains("/")) {
			deleteFileByUrl(url);
		} else {
			deleteFileByObjectId(url);
		}
		renderJson(Ret.ok());
	}

	/**
	 * 显示上传文件列表
	 * 
	 * @author yujie.li
	 * @date 2018年8月13日
	 */
	public void getFileList() {
		String objectId = getPara(0,getVisitor().getCode());
		setAttr("fileList", getFileUploadListByObjectId(objectId));
		setAttr("objectId", getPara());
		//render("common/upload/fileList.html");
		render("common/upload/test2.html");
	}
	
	public void getFileList2() {
		String objectId = getPara(0,getVisitor().getCode());
//		setAttr("fileList", getFileUploadListByObjectId(objectId));
		List<FileUploaded> list =getFileUploadListByObjectId(objectId);
		Map<String, Object>  map =  new ConcurrentReaderHashMap();
		map.put("code", 0);
		map.put("msg", "null");
		map.put("count", 3);
		map.put("data", list);		
		renderJson(map);
	}

	/**
	 * 下载模板文件
	 * 
	 * @params
	 * @author yujie.li
	 * @date 2018年8月2日
	 */
	public void temp() {
		renderFile(new File(PathKit.getWebRootPath() + "/" + WebContant.baseDownloadPath + getAttr("url")));
	}

}
