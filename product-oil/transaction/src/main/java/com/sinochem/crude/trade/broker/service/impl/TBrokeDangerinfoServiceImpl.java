package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TBrokerDangerinfoMapper;
import com.sinochem.crude.trade.blockchain.dao.TBrokerDeclarationMapper;
import com.sinochem.crude.trade.blockchain.domain.TBrokerDangerinfo;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 15:08
 * @Version: [v1.0]
 */
@Service
public class TBrokeDangerinfoServiceImpl implements TBrokeDangerinfoService{
    @Autowired
    private TBrokerDangerinfoMapper tBrokerDangerinfoMapper;
    @Override
    public TBrokerDangerinfo selectByUuid(String uuid) {
        return tBrokerDangerinfoMapper.selectByUuid(uuid);
    }

    @Override
    public int insertSelective(TBrokerDangerinfo record) {
        if(record==null)
            return 0;
        return tBrokerDangerinfoMapper.insertSelective(record);
    }

    @Override
    public int deleteByUuid(String uuid) {
        return tBrokerDangerinfoMapper.deleteByUuid(uuid);
    }

    @Override
    public int updateByUuid(TBrokerDangerinfo record) {
        if(record!=null){
            tBrokerDangerinfoMapper.deleteByUuid(record.getDeclarationuuid());
          return   tBrokerDangerinfoMapper.insertSelective(record);
        }
        return 0;
    }

    @Override
    public int updateByUuid(TBrokerVO tBrokerVO) {
        if(tBrokerVO!=null){
            if(tBrokerVO.gettBrokerDangerinfo()!=null){
                return updateByUuid(tBrokerVO.gettBrokerDangerinfo());
            }
            else {
                return deleteByUuid(tBrokerVO.getUuid());
            }
        }
        return 0;
    }
}
