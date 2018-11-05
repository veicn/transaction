package com.sinochem.crude.trade.operation.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class OpeAdImageVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 图片描述 */
    private String imageDes;

    public String getImageDes() {
        return imageDes;
    }

    public void setImageDes(String imageDes) {
        this.imageDes = imageDes;
    }
    
    /**图片id*/
    private String imageId;  
    
    /**类型代码*/
    private String typeCode;  
    
    /**排序代码*/
    private String sortCode;  
    
    /**图片路径*/
    private String imageUrl;  
    
    /**页面代码（备用）*/
    private String pageCode;  
    
    /**市场代码*/
    private String marketId;  
    
    /**语言*/
    private String langVer;
    
    /**点击图片之后跳转路径*/
    private String gotoUrl;
    
    /**广告位id*/
   	private String adSetId;  
   	
   	/**状态10:上架 20:下架 轮播图为null*/
   	private String status;
   	
   	/**关键字*/
   	private String keyWord;

    public String getGotoUrl() {
        return gotoUrl;
    }

    public void setGotoUrl(String gotoUrl) {
        this.gotoUrl = gotoUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getLangVer() {
        return langVer;
    }

    public void setLangVer(String langVer) {
        this.langVer = langVer;
    }

	public String getAdSetId() {
		return adSetId;
	}

	public void setAdSetId(String adSetId) {
		this.adSetId = adSetId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
	/**分页对象*/
	@JsonUnwrapped
	private SimplePageInfo pageInfo;

	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	

}
