package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
/**
 * 评论查询
 * @author x
 */
public class CommentQuery extends QueryBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String infoUuid; //资讯uuid
	private Integer pageNum;
	private Integer pageSize;
	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<>();
		map.put("infoUuid", infoUuid);
		return map;
	}
	public String getInfoUuid() {
		return infoUuid;
	}
	public void setInfoUuid(String infoUuid) {
		this.infoUuid = infoUuid;
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
