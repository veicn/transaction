package com.sinochem.crude.trade.blockchain.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TDataPartnerMapper;
import com.sinochem.crude.trade.blockchain.domain.TDataPartner;
import com.sinochem.crude.trade.blockchain.service.TDataPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TDataPartnerServiceImpl implements TDataPartnerService {

    @Autowired
    private TDataPartnerMapper tDataPartnerMapper;

    @Override
    public List<TDataPartner> findAllList() {
        return tDataPartnerMapper.selectAllList();
    }

    @Override
    public List<TDataPartner> findListByRole(String role){

        return tDataPartnerMapper.selectListByRole(role);
    }

    @Override
    public TDataPartner findByQuery(TDataPartner tDataPartner) {
        return tDataPartnerMapper.findByQuery(tDataPartner);
    }
}
