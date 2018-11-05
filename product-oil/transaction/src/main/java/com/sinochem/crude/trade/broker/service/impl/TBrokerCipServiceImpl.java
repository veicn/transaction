package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TBrokerCipMapper;
import com.sinochem.crude.trade.blockchain.domain.TBrokerCip;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/29 15:26
 * @Version: [v1.0]
 */
@Service
public class TBrokerCipServiceImpl implements TBrokerCipService {
    @Autowired
    private TBrokerCipMapper tBrokerCipMapper;

    @Override
    public List<TBrokerCip> selectByUuid(String uuid) {
        return tBrokerCipMapper.selectByUuid(uuid);
    }

    @Override
    public int insertList(List<TBrokerCip> list) {
        if(list!=null && list.size()>0) {
            return tBrokerCipMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int deleteByUuid(String uuid) {
        return tBrokerCipMapper.deleteByUuid(uuid);
    }

    @Override
    public int updateByUuid(List<TBrokerCip> list) {
        if(list!=null && list.size()>0) {
            tBrokerCipMapper.deleteByUuid(list.get(0).getDeclarationuuid());
            return tBrokerCipMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int updateByUuid(TBrokerVO tBrokerVO) {
        if(tBrokerVO!=null){
            if(tBrokerVO.gettBrokerCips()!=null&&tBrokerVO.gettBrokerCips().size()>0){
                return updateByUuid(tBrokerVO.gettBrokerCips());
            }
            else {
                return deleteByUuid(tBrokerVO.getUuid());
            }
        }
        return 0;
    }
}
