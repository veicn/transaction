package com.sinochem.crude.trade.shipagent.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TShipagentSofDetailMapper;
import com.sinochem.crude.trade.blockchain.domain.TShipagentSofDetail;
import com.sinochem.crude.trade.shipagent.constant.Constants;
import com.sinochem.crude.trade.shipagent.service.SofDetailService;
import com.sinochem.crude.trade.shipagent.utils.Result;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author songhaiqiang
 * @date 2018/9/7
 */
@Service("sofDetailService")
public class SofDetailServiceImpl implements SofDetailService {

	@Autowired
	private TShipagentSofDetailMapper tShipagentSofDetailMapper;

	/**
	 * 批量插入
	 * @param list
	 * @return
	 * @throws BizException
	 */
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	@Override
	public ResultData<Integer> batchInsert(List<TShipagentSofDetail> list) throws BizException{
		ResultData resultDatas = new ResultData();
		int updateNum = tShipagentSofDetailMapper.bachInsert(list);
		if(updateNum < list.size()){
			throw new BizException("插入失败");
		}
		resultDatas.setData(updateNum);
		return resultDatas;
	}


	/**
	 * 根据主键更新详情
	 * @param detail
	 * @return
	 */
	@Override
	public ResultData updateById(TShipagentSofDetail detail){
		ResultData resultDatas = new ResultData();
		if(detail == null || detail.getId()==null){
			resultDatas.setMessage("更新失败,详情主键不能为空");
			resultDatas.setStatus(Result.ERROR);
			return resultDatas;
		}
		int updateNum = tShipagentSofDetailMapper.updateByPrimaryKeySelective(detail);
		resultDatas.setData(updateNum);
		return resultDatas;
	}

	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	@Override
	public ResultData<Integer> deleteById(Long id){
		ResultData<Integer> resultDatas = new ResultData<>();
		TShipagentSofDetail detail = new TShipagentSofDetail();
		detail.setId(id);
		detail.setAliveFlag(Constants.ALIVE_FLAG_NO);
		int updateNum = tShipagentSofDetailMapper.updateByPrimaryKeySelective(detail);
		resultDatas.setData(updateNum);
		return resultDatas;
	}
}
