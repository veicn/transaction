package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TBrokerOtherpackinfoMapper;
import com.sinochem.crude.trade.blockchain.domain.TBrokerOtherpackinfo;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 15:14
 * @Version: [v1.0]
 */
@Service
public class TBrokerOtherpackinfoServiceImpl implements TBrokerOtherpackinfoService{
    @Autowired
    private TBrokerOtherpackinfoMapper tBrokerOtherpackinfoMapper;

    @Override
    public List<TBrokerOtherpackinfo> selectByUuid(String uuid) {
        return tBrokerOtherpackinfoMapper.selectByUuid(uuid);
    }

    @Override
    public int insertList(List<TBrokerOtherpackinfo> list) {
        if(list!=null && list.size()>0) {
            return tBrokerOtherpackinfoMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int deleteByUuid(String uuid) {
        return tBrokerOtherpackinfoMapper.deleteByUuid(uuid);
    }

    @Override
    public int updateByUuid(List<TBrokerOtherpackinfo> list) {
        if(list!=null && list.size()>0){
            tBrokerOtherpackinfoMapper.deleteByUuid(list.get(0).getDeclarationuuid());
            return tBrokerOtherpackinfoMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int updateByUuid(TBrokerVO tBrokerVO) {
        if(tBrokerVO!=null){
            if(tBrokerVO.gettBrokerOtherpackinfo()!=null&&tBrokerVO.gettBrokerOtherpackinfo().size()>0){
                return updateByUuid(tBrokerVO.gettBrokerOtherpackinfo());
            }
            else {
                return deleteByUuid(tBrokerVO.getUuid());
            }
        }
        return 0;
    }
}
