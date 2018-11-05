package com.sinochem.crude.trade.listed.domain;

public class DemandImages {

	private Long id;

	/**
	 * 需求id
	 */
	private Long demandId;

	/**
	 * 图片id列表
	 */
	private String images;

	public DemandImages(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images == null ? null : images.trim();
	}

}
