package com.sinochem.crude.trade.transport.model;

import java.io.Serializable;

/**
 * 文件信息VO
 * @author
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
	 * 文件名称
	 */
	private String name;
	
	/**
	 * 文件大小（KB）
	 */
	private String size;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
