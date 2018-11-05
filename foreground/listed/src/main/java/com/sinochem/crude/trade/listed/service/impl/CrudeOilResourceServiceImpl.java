package com.sinochem.crude.trade.listed.service.impl;

import java.util.List;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.domain.CrudeOil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.listed.dao.CrudeOilResourceMapper;
import com.sinochem.crude.trade.listed.service.CrudeOilResourceService;

/**
 * Created by sijliu on 23/11/2017.
 */
@Service
@Transactional
public class CrudeOilResourceServiceImpl implements CrudeOilResourceService {

    @Autowired(required = false)
    CrudeOilResourceMapper crudeOilResourceMapper;
    @Autowired
    private CrudeOilInfoService crudeOilInfoService;

    @Override
    public int saveOrUpdateObj(CrudeOil record) {
        int num = 1;
        if (record.getId() == null) {
            crudeOilResourceMapper.insertSelective(record);
        } else {
            crudeOilResourceMapper.updateByPrimaryKeySelective(record);
        }
        return num;
    }

    @Override
    public List<CrudeOil> getCrudeListDemandId(Long demandId) {
        List<CrudeOil> crudeOilList = crudeOilResourceMapper.getCrudeOilListByDemandId(demandId);
        for (CrudeOil cor: crudeOilList) {
            if(cor.getOrigin() != null) {
                CrudeOilInfoVO crudeOilInfoVO = crudeOilInfoService.findByENameAndAreaId(cor.getOrigin());
                if(crudeOilInfoVO != null)
                cor.setAreaString(VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh",crudeOilInfoVO.getOriginNameC()},new String[]{"en",crudeOilInfoVO.getOriginNameE()}}));
            }
        }
        return crudeOilList;
    }

    @Override
    public void deleteByDemandId(Long demandId) {
        crudeOilResourceMapper.deleteByDemandId(demandId);
    }
}
