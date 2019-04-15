package com.lwoptl.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseFileUploaded<M extends BaseFileUploaded<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
		return (M)this;
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public M setFileName(java.lang.String fileName) {
		set("file_name", fileName);
		return (M)this;
	}
	
	public java.lang.String getFileName() {
		return getStr("file_name");
	}

	public M setFileSize(java.lang.Long fileSize) {
		set("file_size", fileSize);
		return (M)this;
	}
	
	public java.lang.Long getFileSize() {
		return getLong("file_size");
	}

	public M setSavePath(java.lang.String savePath) {
		set("save_path", savePath);
		return (M)this;
	}
	
	public java.lang.String getSavePath() {
		return getStr("save_path");
	}

	public M setObjectId(java.lang.String objectId) {
		set("objectId", objectId);
		return (M)this;
	}
	
	public java.lang.String getObjectId() {
		return getStr("objectId");
	}

	public M setUrl(java.lang.String url) {
		set("url", url);
		return (M)this;
	}
	
	public java.lang.String getUrl() {
		return getStr("url");
	}

	public M setTa(java.lang.String ta) {
		set("ta", ta);
		return (M)this;
	}
	
	public java.lang.String getTa() {
		return getStr("ta");
	}

}
