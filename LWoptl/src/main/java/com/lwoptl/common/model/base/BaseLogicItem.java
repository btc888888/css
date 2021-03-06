package com.lwoptl.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseLogicItem<M extends BaseLogicItem<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setParentId(java.lang.String parentId) {
		set("parent_id", parentId);
		return (M)this;
	}
	
	public java.lang.String getParentId() {
		return getStr("parent_id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setCreateBy(java.lang.String createBy) {
		set("create_by", createBy);
		return (M)this;
	}
	
	public java.lang.String getCreateBy() {
		return getStr("create_by");
	}

	public M setCreateDae(java.util.Date createDae) {
		set("create_dae", createDae);
		return (M)this;
	}
	
	public java.util.Date getCreateDae() {
		return get("create_dae");
	}

	public M setUrl(java.lang.String url) {
		set("url", url);
		return (M)this;
	}
	
	public java.lang.String getUrl() {
		return getStr("url");
	}

}
