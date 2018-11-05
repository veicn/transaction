package com.sinochem.crude.trade.shipagent.service;

import com.sinochem.crude.trade.blockchain.domain.TShipagentSofDetail;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author admin1
 * @date 2018/9/14
 */
public interface SofDetailService {


	public ResultData<Integer> batchInsert(List<TShipagentSofDetail> list) throws BizException ;

	public ResultData updateById(TShipagentSofDetail detail);

	public ResultData<Integer> deleteById(Long id);


}
