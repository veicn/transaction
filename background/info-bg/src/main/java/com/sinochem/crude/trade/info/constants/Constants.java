package com.sinochem.crude.trade.info.constants;

/**
 * Created by x on 2017/11/7.
 */
public class Constants {
    /**逻辑删除默认[新增]*/
    public static final String ALIEVE_FLAG = "1";

    /*显示模式:10单图模式,20三图模式,30随机模式*/
    public static final String DISPLAY_MODE_DT = "10";
    public static final String DISPLAY_MODE_ST = "20";
    public static final String DISPLAY_MODE_SJ = "30";

    /*文章类型 10:专栏文章 20:普通文章 30:专题文章*/
    public static final String ARTICLE_TYPE_ZL = "10";
    public static final String ARTICLE_TYPE_PT = "20";
    public static final String ARTICLE_TYPE_ZT = "30";

    /*资讯状态 00- 已撤销 10-待提交 20-待审核 29-审核驳回 30-已发布*/
    public static final String INFO_ARTICLE_STATUS_YCX = "00";
    public static final String INFO_ARTICLE_STATUS_DTJ = "10";
    public static final String INFO_ARTICLE_STATUS_DSH = "20";
    public static final String INFO_ARTICLE_STATUS_SHBH = "29";
    public static final String INFO_ARTICLE_STATUS_YFB = "30";

    /*试读标记 1:试读 0:其他*/
    public static final String PROBATION_FLAG_SD = "1";
    public static final String PROBATION_FLAG_QT = "0";

    /*打赏标记 1:允许打赏 0:不可打赏*/
    public static final String REWARD_FLAG_YX = "1";
    public static final String REWARD_FLAG_BK = "0";

    /*语言类型（ZH：中文，EN：英文）*/
    public static final String LANG_VER_ZH = "ZH-CN";
    public static final String LANG_VER_EN = "EN-HK";

    /*显示导语 1:显示 0:不显示*/
    public static final String IS_SHOW_GENERAL_XS = "1";
    public static final String IS_SHOW_GENERAL_BXS = "0";

    /*显示来源 1:显示 0:不显示*/
    public static final String IS_SHOW_ORIGIN_XS = "1";
    public static final String IS_SHOW_ORIGIN_BXS = "0";

    /*显示版权声明 1:显示 0:不显示*/
    public static final String IS_SHOW_COPYRIGHT_XS = "1";
    public static final String IS_SHOW_COPYRIGHT_BXS = "0";

    /*显示免责声明 1:显示 0:不显示*/
    public static final String IS_SHOW_DISCLAIMER_XS = "1";
    public static final String IS_SHOW_DISCLAIMER_BXS = "0";

    /*显示作者 1:显示 0:不显示*/
    public static final String IS_SHOW_AUTHOR_XS = "1";
    public static final String IS_SHOW_AUTHOR_BXS = "0";

    /*资讯范围(0:平台 1:企业)*/
    public static final String SCOPE_PT = "0";
    public static final String SCOPE_QY = "1";
    
    /**
	 * 外部接口数据处理状态： 00：未处理，01：已使用，02：已弃用
	 */
	public static final String STATUS_00 = "00";
	
	/**
	 * 外部接口数据处理状态： 00：未处理，01：已使用，02：已弃用
	 */
	public static final String STATUS_01 = "01";
	
	/**
	 * 外部接口数据处理状态： 00：未处理，01：已使用，02：已弃用
	 */
	public static final String STATUS_02 = "02";
	
	/**
	 * 接口返回相关
	 */
	public static final String CODE_10 = "10";
	
	public static final String CODE_20 = "20";
	
	public static final String MSG_10 = "接口调用成功";
	
	public static final String MSG_20 = "系统错误";
	
	public static final String SUB_CODE_10 = "10";
	
	public static final String SUB_CODE_20 = "20";
	
	public static final String SUB_MSG_10 = "业务处理成功";
	
	public static final String SUB_MSG_20 = "业务处理失败";
	
	/**
	 * 有效标志： 1-有效， 0-无效
	 */
	public static final String ALIVE_FLAG_0 = "0";
	
	/**
	 * 有效标志： 1-有效， 0-无效
	 */
	public static final String ALIVE_FLAG_1 = "1";

	/**频道值集查询*/
    public static final String CHANNEL_LIST = "CHANEL_CODE_LIST";
    
    /**
     * priceMode的对应关系
		1	OUTRIGHT
		2	OMAN/DUBAI
		3	DUBAI
		4	OMAN
		5	DTD BRENT
		6	ICE BRENT
		7	复合计价
		8	OSP
     */
    public enum PRICEMODE{
    	mode1("1", "OUTRIGHT"),
    	mode2("2","OMAN/DUBAI"),
    	mode3("3","DUBAI"),
    	mode4("4","OMAN"),
    	mode5("5","DTD BRENT"),
    	mode6("6","ICE BRENT"),
    	mode7("7","复合计价"),
    	mode8("8","OSP");
    	PRICEMODE(String priceType,String name){
    		this.priceType = priceType;
    		this.name = name;
    	}
    	private String priceType;
    	private String name;
    	
		public String getPriceType() {
			return priceType;
		}
		public String getName() {
			return name;
		}
    }
    /**
     * 主频道类型(ZX: 资讯；SSBK: 石油百科)
     * @author x
     */
    public enum channel_m_desc_enum{
    	channel_m_desc_ZX("ZX", "资讯"),
    	channel_m_desc_SSBK("SSBK","石油百科"),
    	channel_m_desc_SDZL("SDZL","山东专栏"),
    	channel_m_desc_HELP("HELP","帮助中心"),
    	channel_m_desc_STAT("STAT","平台声明"),
    	channel_m_desc_CPY("CPY","成品油");
    	channel_m_desc_enum(String channel_m_desc_key, String channel_m_desc_value){
    		this.channel_m_desc_key = channel_m_desc_key;
    		this.channel_m_desc_value = channel_m_desc_value;
    	};
    	private String channel_m_desc_key;
    	private String channel_m_desc_value;
    	
		public String getChannel_m_desc_key() {
			return channel_m_desc_key;
		}
		public String getChannel_m_desc_value() {
			return channel_m_desc_value;
		}
    }
}
