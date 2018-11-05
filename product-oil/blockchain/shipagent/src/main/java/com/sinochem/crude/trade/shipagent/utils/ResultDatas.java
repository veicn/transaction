package com.sinochem.crude.trade.shipagent.utils;

public class ResultDatas<T> extends Result {
	private static final long serialVersionUID = 1L;

	private T datas;

	public T getDatas() {
		return datas;
	}

	public void setDatas(T datas) {
		this.datas = datas;
	}

}
