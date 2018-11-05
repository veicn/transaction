package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
/**
 * 专栏查询
 * @author x
 */
public class ColColumnQuery extends QueryBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id; //专栏id
	private Integer pageNum;
	private Integer pageSize;
	
	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		return map;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
   			this.pageSize = 0;
   		}
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	

}
