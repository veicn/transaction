package com.sinochem.crude.trade.shipagent.service;

import com.sinochem.crude.trade.blockchain.domain.TShipagentAppoint;
import com.sinochem.crude.trade.blockchain.domain.TShipagentDocument;
import com.sinochem.crude.trade.blockchain.domain.TShipagentSof;
import com.sinochem.crude.trade.blockchain.utils.BlockChainUtil;
import com.sinochem.crude.trade.shipagent.model.vo.TShipagentSofVo;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.crude.trade.transaction.model.vo.FileInfoVO;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 *
 * @author admin1
 * @date 2018/9/14
 */
public interface SofService {



	public TShipagentSofVo getSofAndDetail(long id) throws BizException;


	public TShipagentSof get(long id) throws Exception;


	public ResultData delete(Long id)throws Exception;

	/**
	 * 暂存sof
	 * @param sof
	 * @return
	 * @throws BizException
	 */
	public ResultData save(TShipagentSof sof)throws Exception;

	/**
	 * 提交SOF
	 * @param sof
	 * @return
	 * @throws BizException
	 */
	public ResultData commit(TShipagentSof sof)throws Exception;

	/**
	 * 更新任务状态
	 * @param appoint
	 */
	public void updateTaskStatus(TShipagentAppoint appoint);


	/**
	 *  根据uuid查找
	 * @param uuid
	 * @return
	 */
	public TShipagentSof selectByUuid(String uuid);


	/**
	 *
	 * @param fileInfoVO
	 * @return
	 */
	public ResultData uploadFile(FileInfoVO fileInfoVO)throws Exception;
}
