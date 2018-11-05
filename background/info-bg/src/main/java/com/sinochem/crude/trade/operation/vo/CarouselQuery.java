package com.sinochem.crude.trade.operation.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GHuang on 2017/1/12.
 */
public class CarouselQuery implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String imageDes;
    private String typeCode;
    private String pageCode;

    private Integer pageNum;
    private Integer pageSize;
    
	public String getImageDes() {
		return imageDes;
	}

	public void setImageDes(String imageDes) {
		this.imageDes = imageDes;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public Integer getPageNum() {
		if(this.pageNum==null){
   			this.pageNum = 1;
   		}
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
    public Integer getPageSize() {
    	if(this.pageSize==null){
    		this.pageSize = 10;
    	}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("imageDes", this.imageDes);
        map.put("typeCode", this.typeCode);
        map.put("pageCode", this.pageCode);
            
        return map;
    }
}
