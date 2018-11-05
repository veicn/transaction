package com.sinochem.crude.trade.info.query;


import com.sinochem.crude.trade.common.SimplePageInfo;

/**
 * 
 * @ClassName: FrontInfoVo
 * @Description: 前台资讯查询对象
 *
 */
public class FrontInfoVo extends SimplePageInfo{

	private static final long serialVersionUID = 1L;
	
	private String channelMCode;   //一级分类编码

	private String channelName;		//二级分类名称
	
	private String channelCode;// 二级分类代码
	
	private String searchText;
	
	private String stick;  //置顶
	
	private String channelMDesc;	//类别（资讯-ZX,石油百科-SSBK,山东专栏-SDZL，帮助中心-HELP, STAT-平台声明）
	
	private String channelMName;   //一级分类名称
	
	private String extend3; //资讯发布频率: day(每日一份) month(每月一份) week(每周一份) halfMonth(半月一份) untime(不定时)
	
	private String title;//资讯标题
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChannelMCode() {
		return channelMCode;
	}

	public void setChannelMCode(String channelMCode) {
		this.channelMCode = channelMCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getStick() {
		return stick;
	}

	public void setStick(String stick) {
		this.stick = stick;
	}

	public String getChannelMDesc() {
		return channelMDesc;
	}

	public void setChannelMDesc(String channelMDesc) {
		this.channelMDesc = channelMDesc;
	}

	public String getChannelMName() {
		return channelMName;
	}

	public void setChannelMName(String channelMName) {
		this.channelMName = channelMName;
	}

	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}
	
}
