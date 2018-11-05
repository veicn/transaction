package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TBrokerBusinessmattersMapper;
import com.sinochem.crude.trade.blockchain.domain.TBrokerBusinessmatters;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 15:04
 * @Version: [v1.0]
 */
@Service
public class TBrokerBusinessmattersServiceImpl implements TBrokerBusinessmattersService {
    @Autowired
    private TBrokerBusinessmattersMapper tBrokerBusinessmattersMapper;
    @Override
    public List<TBrokerBusinessmatters> selectByUuid(String uuid) {
        return tBrokerBusinessmattersMapper.selectByUuid(uuid);
    }

    @Override
    public int insertList(List<TBrokerBusinessmatters> list) {
        if(list!=null && list.size()>0) {
            return tBrokerBusinessmattersMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int deleteByUuid(String uuid) {
        return tBrokerBusinessmattersMapper.deleteByUuid(uuid);
    }

    @Override
    public int updateByUuid(List<TBrokerBusinessmatters> list) {
        if(list!=null && list.size()>0) {
            tBrokerBusinessmattersMapper.deleteByUuid(list.get(0).getDeclarationuuid());
            return tBrokerBusinessmattersMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int updateByUuid(TBrokerVO tBrokerVO) {
        if(tBrokerVO!=null){
            if(tBrokerVO.gettBrokerBusinessmatters()!=null&&tBrokerVO.gettBrokerBusinessmatters().size()>0){
                return updateByUuid(tBrokerVO.gettBrokerBusinessmatters());
            }
            else {
                return deleteByUuid(tBrokerVO.getUuid());
            }
        }
        return 0;
    }
}
