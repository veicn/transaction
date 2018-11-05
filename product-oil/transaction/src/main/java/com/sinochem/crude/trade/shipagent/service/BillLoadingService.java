package com.sinochem.crude.trade.shipagent.service;

import com.sinochem.crude.trade.blockchain.domain.TShipagentAppoint;
import com.sinochem.crude.trade.blockchain.domain.TShipagentBillLoading;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.crude.trade.transaction.model.vo.FileInfoVO;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.Map;

/**
 *
 * @author admin1
 * @date 2018/9/14
 */
public interface BillLoadingService {


	public TShipagentBillLoading get(Long id) throws BizException;


//	public ResultDatas delete(Long id);


	public ResultData create(TShipagentBillLoading billlading);


	public ResultData save(TShipagentBillLoading billLoading)throws Exception;


	public ResultData commit(TShipagentBillLoading billLoading)throws Exception;


	public void updateTaskStatus(TShipagentAppoint appoint);

	public TShipagentBillLoading selectByUuid(String uuid);

	public  ResultData uploadFile(FileInfoVO fileInfoVO)throws Exception;
}
