package com.lwoptl.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseDataDictionary<M extends BaseDataDictionary<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setCode(java.lang.String code) {
		set("code", code);
		return (M)this;
	}
	
	public java.lang.String getCode() {
		return getStr("code");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

	public M setOrderNum(java.lang.Integer orderNum) {
		set("order_num", orderNum);
		return (M)this;
	}
	
	public java.lang.Integer getOrderNum() {
		return getInt("order_num");
	}

}