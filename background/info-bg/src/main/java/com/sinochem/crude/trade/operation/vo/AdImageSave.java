package com.sinochem.crude.trade.operation.vo;

import java.io.Serializable;

/**
 * Created by GHuang on 2017/1/12.
 */
public class AdImageSave implements Serializable{

	private static final long serialVersionUID = 1L;	
	
    private String imageId;
    private String imageUrl;
    private String imgAlias;
    private String imgId;
    private String updater;
    
    
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getImageUrl() {
		return imageUrl;
		
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImgAlias() {
		return imgAlias;
	}
	public void setImgAlias(String imgAlias) {
		this.imgAlias = imgAlias;
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

   
}
