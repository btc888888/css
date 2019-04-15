package com.lwoptl.common.vo;

import java.util.Collection;

import com.jfinal.core.JFinal;

public class FunNode {
	private static String id;
	private String icon;
	private String url;
	private Integer levelNo;// 是否菜单
	private String FontIcon;// 字体图标
	private static Collection<FunNode> children;
	private String pid;

	public static String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public static String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	private static String name;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public String getHref() {
		return JFinal.me().getContextPath() + url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static Collection<FunNode> getChildren() {
		return children;
	}

	public void setChildren(Collection<FunNode> children) {
		this.children = children;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getLevelNo() {
		return levelNo;
	}

	public String getFontIcon() {
		return FontIcon;
	}

	public void setLevelNo(Integer levelNo) {
		this.levelNo = levelNo;
	}

	public void setFontIcon(String fontIcon) {
		FontIcon = fontIcon;
	}

}
