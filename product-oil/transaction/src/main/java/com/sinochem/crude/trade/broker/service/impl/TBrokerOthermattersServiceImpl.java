package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TBrokerOthermattersMapper;
import com.sinochem.crude.trade.blockchain.domain.TBrokerOthermatters;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 15:12
 * @Version: [v1.0]
 */
@Service
public class TBrokerOthermattersServiceImpl implements TBrokerOthermattersService{

    @Autowired
    private TBrokerOthermattersMapper tBrokerOthermattersMapper;
    @Override
    public List<TBrokerOthermatters> selectByUuid(String uuid) {
        return tBrokerOthermattersMapper.selectByUuid(uuid);
    }

    @Override
    public int insertList(List<TBrokerOthermatters> list) {
        if(list!=null && list.size()>0) {
            return tBrokerOthermattersMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int deleteByUuid(String uuid) {
        return tBrokerOthermattersMapper.deleteByUuid(uuid);
    }

    @Override
    public int updateByUuid(List<TBrokerOthermatters> list) {
        if(list!=null && list.size()>0){
            tBrokerOthermattersMapper.deleteByUuid(list.get(0).getDeclarationuuid());
            return tBrokerOthermattersMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int updateByUuid(TBrokerVO tBrokerVO) {
        if(tBrokerVO!=null){
            if(tBrokerVO.gettBrokerOthermatters()!=null&&tBrokerVO.gettBrokerOthermatters().size()>0){
                return updateByUuid(tBrokerVO.gettBrokerOthermatters());
            }
            else {
                return deleteByUuid(tBrokerVO.getUuid());
            }
        }
        return 0;
    }
}
