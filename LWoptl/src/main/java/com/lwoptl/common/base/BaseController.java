package com.lwoptl.common.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Aop;
import com.jfinal.core.Controller;
import com.jfinal.core.NotAction;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.lwoptl.common.base.service.FileService;
import com.lwoptl.common.kit.IdKit;
import com.lwoptl.common.model.FileUploaded;
import com.lwoptl.common.visit.Visitor;
import com.lwoptl.common.visit.VisitorUtil;
import com.lwoptl.common.vo.Feedback;

public class BaseController extends Controller {
	private static final Logger LOG = Logger.getLogger(BaseController.class);

	FileService fileService =Aop.get(FileService.class);

	/** 访问者信息 **/
	@NotAction
	public Visitor getVisitor(){
		return  VisitorUtil.getVisitor(getSession());
	}
	
	/**
	 * 捕获异常
	 * 
	 * @param e
	 * @author yujie.li
	 * @date 2018年10月15日
	 */
	@NotAction
	public void handerException(Exception e) {
		LOG.info(e.getMessage(), e);
		e.printStackTrace();
	}
	
	/**
	 * 记录日志信息
	 * @param message
	 * @author yujie.li
	 * @date 2018年12月11日
	 */
	@NotAction
	public void logInfo(String message) {
		LOG.info(message);
	}
	
	/**
	 * 输出异常信息到页面
	 * @param message
	 * @author yujie.li
	 * @date 2018年12月4日
	 */
	@NotAction
	public void setException(String message) {
		setAttr("e", new Exception(message));
	}
	
	/**
	 * 输出提示信息到页面
	 * @param message
	 */
	@NotAction
	public void setMsg(String message){
		setAttr("msg",message);
	}
	
	/**
	 * 创建32位字符串
	 * 
	 * @return
	 * @author yujie.li
	 * @date 2018年8月1日
	 */
	@NotAction
	public String createUUID() {
		return IdKit.createUUID();
	}
	
	/**
	 * {
	 *   "code": "success",
	 *   "success": true,
	 *   "error": false,
	 *   "msg": "成功"
	 *  }
	 * @return
	 */
	@NotAction
	public Feedback suc(){
		return Feedback.success("成功");
	}
	
	/**
	 * {
	 *   "code": "success",
	 *   "success": true,
	 *   "error": false,
	 *   "msg": ""
	 *  }
	 * @param msg 提示信息
	 * @return
	 */
	@NotAction
	public Feedback suc(String msg){
		return Feedback.success(msg);
	}
	/**
	 * {
	 *   "code": "error",
	 *   "success": false,
	 *   "error": true,
	 *   "msg": "失败"
	 *  }
	 * @return
	 */
	@NotAction
	public Feedback err(){
		return Feedback.error("失败");
	}
	
	/**
	 * {
	 *   "code": "error",
	 *   "success": false,
	 *   "error": true,
	 *   "msg": ""
	 *  }
	 * @param msg 提示信息
	 * @return
	 */
	@NotAction
	public Feedback err(String msg){
		return Feedback.error(msg);
	}
	
	/**
	 * 获取数组变量ids
	 * 
	 * @return
	 * @author yujie.li
	 * @date 2018年8月1日
	 */
	@NotAction
	public List<String> getIds() {
		return getArray("ids");
	}

	/**
	 * 获取数组变量
	 * 
	 * @param arrayName
	 * @return
	 * @author yujie.li
	 * @date 2018年9月17日
	 */
	@NotAction
	public List<String> getArray(String arrayName) {		
		List<String> ids = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			if (getPara(arrayName + "[" + i + "]")== null) {
				break;
			} 
			ids.add(getPara(arrayName + "[" + i + "]"));
		}
		return ids;
	}

	/**
	 * 保存文件记录
	 * 
	 * @return url格式：180801/1808010001004
	 * 
	 * @author yujie.li
	 * @date 2018年8月1日
	 */
	@NotAction
	public String saveFile(UploadFile uploadFile) {
		return fileService.saveFile(uploadFile);
	}
	@NotAction
	public String saveFile(UploadFile uploadFile, String objectId) {
		return fileService.saveFile(uploadFile, objectId);
	}
	@NotAction
	public List<String> saveFiles(List<UploadFile> list) {
		return fileService.saveFiles(list);
	}
	@NotAction
	public List<String> saveFiles(List<UploadFile> list, String objectId) {
		return fileService.saveFiles(list,objectId);
	}

	/**
	 * 通过url获取文件记录对象
	 * 
	 * @param url
	 *            格式：180801/1808010001004
	 * @return
	 * @author yujie.li
	 * @date 2018年8月1日
	 */
	@NotAction
	public FileUploaded getFileUploaded(String url) {
		return fileService.queryFileUploadedByUrl(url);
	}
	@NotAction
	public FileUploaded getFileUploadedByObjectId(String objectId) {
		return fileService.queryFileUploadedByObjectId(objectId);
	}
	@NotAction
	public List<FileUploaded> getFileUploadListByObjectId(String objectId) {
		return fileService.queryFileUploadedListByObjectId(objectId);
	}

	/**
	 * 删除文件
	 * 
	 * @param url
	 *            格式：180801/1808010001004
	 * @return
	 * @author yujie.li
	 * @date 2018年8月1日
	 */
	@NotAction
	public void deleteFileByUrl(String url) {
		fileService.deleteFile(url);
	}
	@NotAction
	public void deleteFileByUrls(List<String> urls) {
		fileService.deleteFiles(urls);
	}
	@NotAction
	public void deleteFileByModel(FileUploaded entity) {
		fileService.delete(entity);
	}
	@NotAction
	public void deleteFileByObjectId(String objectId) {
		fileService.deleteFileByObjectId(objectId);
	}

	/**
	 * 导入数据
	 * 
	 * @param uf
	 * @param sql
	 *            insert into game_theme (id, state, title) values(?,?,?)
	 * @return
	 * @author yujie.li
	 * @date 2018年9月14日
	 */
	@NotAction
	public Boolean importExcel(UploadFile uf, String sql) {
		boolean b = fileService.importExcel(uf, sql);
		fileService.deleteFile(uf);
		return b;
	}

	/**
	 * 导出数据.xlsx
	 * 
	 * @param title
	 *            String[] title={"姓名","性别","年龄",...}
	 * @param fileName
	 * @param sql
	 *            select name as '0',sex as '1',age as '2' from user
	 * @author yujie.li
	 * @date 2018年8月14日
	 */
	@NotAction
	public void exportExcel(String[] title, String fileName, String sql) {
		fileService.exportExcelxlsx(getResponse(), title, fileName, sql);
	}
	@NotAction
	public void exportExcel(String[] title, String sql) {
		fileService.exportExcelxlsx(getResponse(), title, null, sql);
	}
	@NotAction
	public void exportExcel(String[] title, String fileName, String sql, Object... paras) {
		fileService.exportExcelxlsx(getResponse(), title, fileName, sql, paras);
	}
	@NotAction
	public void exportExcel(String[] title, String sql, Object... paras) {
		fileService.exportExcelxlsx(getResponse(), title, null, sql, paras);
	}
	@NotAction
	public void exportExcel(String[] title, String fileName, List<Record> list) {
		fileService.exportExcelxlsx(getResponse(), title, fileName, list);
	}
	@NotAction
	public void exportExcel(String[] title, List<Record> list) {
		fileService.exportExcelxlsx(getResponse(), title, null, list);
	}
}
