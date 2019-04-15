
package com.lwoptl.portal.echart.vo;

import java.util.Arrays;

/**
 * 图表参数对象
 * @author yujie.li
 * @date 2018-03-02
 */
public class Chart {
	/** id **/
	private String id;
	/** 标题 **/
	private String title;
	/** 副标题 **/
	private String subtitle;
	/** x轴属性 **/
	private String xAxis;
	/** y轴属性 **/
	private String yAxis;
	/** 图表类型，bar,line,pie **/
	private String chartType;
	/** x轴名称 **/
    private String xName;
    /** y轴名称 **/
    private String yName;
    /** 实例名称 **/
    private String seriesName;
    /** 单位 **/
    private String tooltipText;
	/** 数据查询sql **/
    private String sql;
    /** sql参数 **/
    private Object[] params;
    
	public Chart() {
	}

	/**
	 * 构造基础参数
	 * @param title
	 * @param subtitle
	 * @param xAxis
	 * @param yAxis
	 * @param chartType
	 * @param xName
	 * @param yName
	 * @param seriesName
	 * @param tooltipText
	 * @param sql
	 * @param params
	 */
	public Chart(String title, String subtitle, String xAxis, String yAxis, String chartType, String xName,
			String yName, String seriesName, String tooltipText, String sql, Object[] params) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.chartType = chartType;
		this.xName = xName;
		this.yName = yName;
		this.seriesName = seriesName;
		this.tooltipText = tooltipText;
		this.sql = sql;
		this.params = params;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getxAxis() {
		return xAxis;
	}

	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}

	public String getyAxis() {
		return yAxis;
	}

	public void setyAxis(String yAxis) {
		this.yAxis = yAxis;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public String getxName() {
		return xName;
	}

	public void setxName(String xName) {
		this.xName = xName;
	}

	public String getyName() {
		return yName;
	}

	public void setyName(String yName) {
		this.yName = yName;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getTooltipText() {
		return tooltipText;
	}

	public void setTooltipText(String tooltipText) {
		this.tooltipText = tooltipText;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "Chart [id=" + id + ", title=" + title + ", subtitle=" + subtitle 
				+ ", xAxis=" + xAxis + ", yAxis=" + yAxis + ", chartType=" + chartType 
				+ ", xName=" + xName + ", yName=" + yName + ", seriesName=" + seriesName
				+ ", tooltipText=" + tooltipText + ", sql=" + sql + ", params=" + Arrays.toString(params) + "]";
	}

		
}