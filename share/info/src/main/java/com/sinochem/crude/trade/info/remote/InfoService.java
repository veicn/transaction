package com.sinochem.crude.trade.info.remote;

public interface InfoService {

	void saveInfoInterface(InfoInterfaceVo vo) throws Exception;

	/**
	 * 同步自外部系统的会员表信息
	 * @throws Exception
	 */
	void synMemmberInterface(MemMemberVo vo) throws Exception;
}
