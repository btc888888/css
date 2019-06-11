package com.lwoptl.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseLogicTimeline<M extends BaseLogicTimeline<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setItemId(java.lang.String itemId) {
		set("item_id", itemId);
		return (M)this;
	}
	
	public java.lang.String getItemId() {
		return getStr("item_id");
	}

	public M setTlTime(java.util.Date tlTime) {
		set("tl_time", tlTime);
		return (M)this;
	}
	
	public java.util.Date getTlTime() {
		return get("tl_time");
	}

	public M setTlContent(java.lang.String tlContent) {
		set("tl_content", tlContent);
		return (M)this;
	}
	
	public java.lang.String getTlContent() {
		return getStr("tl_content");
	}

	public M setTlCreator(java.lang.String tlCreator) {
		set("tl_creator", tlCreator);
		return (M)this;
	}
	
	public java.lang.String getTlCreator() {
		return getStr("tl_creator");
	}

}
