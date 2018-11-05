package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TBrokerGoodsMapper;
import com.sinochem.crude.trade.blockchain.domain.TBrokerGoods;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/10/15 18:05
 * @Version: [v1.0]
 */
@Service
public class TBrokerGoodsServiceImpl implements TBrokerGoodsService {
    @Autowired
    private TBrokerGoodsMapper tBrokerGoodsMapper;

    @Override
    public List<TBrokerGoods> selectByUuid(String uuid) {
        return tBrokerGoodsMapper.selectByUuid(uuid);
    }

    @Override
    public int deleteByUuid(String uuid) {
        return tBrokerGoodsMapper.deleteByUuid(uuid);
    }

    @Override
    public int insertList(List<TBrokerGoods> list) {
        if(list!=null && list.size()>0) {
            return tBrokerGoodsMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int updateByUuid(List<TBrokerGoods> list) {
        if(list!=null && list.size()>0){
            tBrokerGoodsMapper.deleteByUuid(list.get(0).getDeclarationuuid());
            return tBrokerGoodsMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int updateByUuid(TBrokerVO tBrokerVO) {
        if(tBrokerVO!=null){
            if(tBrokerVO.gettBrokerGoods()!=null&&tBrokerVO.gettBrokerGoods().size()>0){
                return updateByUuid(tBrokerVO.gettBrokerGoods());
            }
            else {
                return deleteByUuid(tBrokerVO.getUuid());
            }
        }
        return 0;
    }
}
