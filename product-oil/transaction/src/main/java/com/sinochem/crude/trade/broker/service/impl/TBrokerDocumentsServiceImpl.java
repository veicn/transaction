package com.sinochem.crude.trade.broker.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TBrokerDocumentsMapper;
import com.sinochem.crude.trade.blockchain.domain.TBrokerDangerinfo;
import com.sinochem.crude.trade.blockchain.domain.TBrokerDocuments;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 15:10
 * @Version: [v1.0]
 */
@Service
public class TBrokerDocumentsServiceImpl implements TBrokerDocumentsService{
    @Autowired
    private TBrokerDocumentsMapper tBrokerDocumentsMapper;

    @Override
    public List<TBrokerDocuments> selectByUuid(String uuid) {
        return tBrokerDocumentsMapper.selectByUuid(uuid);
    }

    @Override
    public int deleteByUuid(String uuid) {
        return tBrokerDocumentsMapper.deleteByUuid(uuid);
    }

    @Override
    public int insertList(List<TBrokerDocuments> list) {
        if(list!=null && list.size()>0) {
            return tBrokerDocumentsMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int updateByUuid(List<TBrokerDocuments> list) {
        if(list!=null && list.size()>0){
            tBrokerDocumentsMapper.deleteByUuid(list.get(0).getDeclarationuuid());
           return tBrokerDocumentsMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int updateByUuid(TBrokerVO tBrokerVO) {
        if(tBrokerVO!=null){
            if(tBrokerVO.gettBrokerDocuments()!=null&&tBrokerVO.gettBrokerDocuments().size()>0){
                return updateByUuid(tBrokerVO.gettBrokerDocuments());
            }
            else {
                return deleteByUuid(tBrokerVO.getUuid());
            }
        }
        return 0;
    }
}
