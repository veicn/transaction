package com.sinochem.crude.trade.member.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 文件信息VO
 * @author hexinfang
 *
 */
public class FileInfoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 901519818495065569L;

	/**
	 * 文件路径
	 */
	private String path;
	
	/**
	 * 文件后缀
	 */
	private String suffix;
	
	/**
	 * 文件大小
	 */
	private String size;

	/**
	 * 文件原名
	 */
	private String originalName;	
	
	public String getPath() {
		return path;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getSize() {
		return size;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getOriginalName() {return originalName;}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}





}
