package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TBrokerProductspecificationMapper;
import com.sinochem.crude.trade.blockchain.domain.TBrokerDangerinfo;
import com.sinochem.crude.trade.blockchain.domain.TBrokerProductspecification;
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
public class TBrokerProductspecificationServiceImpl implements TBrokerProductspecificationService {
    @Autowired
    private TBrokerProductspecificationMapper mapper;
    @Override
    public List<TBrokerProductspecification> selectByUuid(String uuid) {
        return mapper.selectByUuid(uuid);
    }

//    @Override
//    public int insertList(List<TBrokerProductspecification> list) {
//        return 0;
//    }

    @Override
    public int deleteByUuid(String uuid) {
        return mapper.deleteByUuid(uuid);
    }

    @Override
    public int updateByUuid(List<TBrokerProductspecification> list) {

        if(list!=null && list.size()>0){
            mapper.deleteByUuid(list.get(0).getDeclarationuuid());
            return mapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int insertSelective(TBrokerProductspecification record) {
        if(record==null)
            return 0;
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(TBrokerProductspecification record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByUuid(TBrokerVO tBrokerVO) {
        if(tBrokerVO!=null){
            if(tBrokerVO.gettBrokerProductspecification()!=null){
                return updateByUuid(tBrokerVO.gettBrokerProductspecification());
            }
            else {
                return deleteByUuid(tBrokerVO.getUuid());
            }
        }
        return 0;
    }

    @Override
    public int insertList(List<TBrokerProductspecification> list) {
        if(list!=null && list.size()>0) {
            return mapper.insertList(list);
        }
        return 0;
    }

}
