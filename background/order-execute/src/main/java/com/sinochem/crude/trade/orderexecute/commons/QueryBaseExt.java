package com.sinochem.crude.trade.orderexecute.commons;

import com.sinochem.crude.trade.common.QueryBase;

/**
 * 为了兼容分页参数pageNum,等同于currentPage
 * @author me
 *
 */
public abstract class QueryBaseExt extends QueryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7731933936668401582L;

	private Integer pageNum;
	private static final Integer defaultFriatPage = 10;
	
	public Integer getPageNum() {
		if(pageNum == null) {
			pageNum = defaultFriatPage;
		}
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		if ((pageNum == null) || (pageNum.intValue() <= 0)) {
			this.pageNum = defaultFriatPage;
		} else {
			this.pageNum = pageNum;
		}
		super.setCurrentPage(pageNum);
	}

	/**
	 * @param current
	 * The currentPage to set.
	 */ 
	public void setCurrentPage(Integer cPage) {
		setPageNum(cPage);
	}

}
