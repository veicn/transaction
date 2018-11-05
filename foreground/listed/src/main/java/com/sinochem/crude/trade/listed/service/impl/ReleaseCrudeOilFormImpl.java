package com.sinochem.crude.trade.listed.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.listed.dao.ReleaseCrudeOilFormMapper;
import com.sinochem.crude.trade.listed.enums.CrudeOilQuantityType;
import com.sinochem.crude.trade.listed.enums.CrudeOilDemandOrderProptertyType;
import com.sinochem.crude.trade.listed.enums.DataQueryOrderType;
import com.sinochem.crude.trade.listed.model.query.CrudeOilDemandQuery;
import com.sinochem.crude.trade.listed.model.vo.CrudeOilDemandQueryVO;
import com.sinochem.crude.trade.listed.model.vo.ReleaseCrudeOilVO;
import com.sinochem.crude.trade.listed.service.ReleaseCrudeOilFormService;

import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.page.PageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ReleaseCrudeOilFormImpl implements ReleaseCrudeOilFormService{

    @Autowired(required = false)
    private ReleaseCrudeOilFormMapper releaseCrudeOilFormMapper;

    @Autowired
    CrudeOilInfoService crudeOilInfoService;


    @Override
    public PageInfoResult<ReleaseCrudeOilVO> getReleaseCrudeOilFormList(PageInfo pageInfo, CrudeOilDemandQueryVO crudeOilDemandQueryVO) {
        CrudeOilDemandQuery crudeOilDemandQuery = new CrudeOilDemandQuery();
        crudeOilDemandQuery.setEpMemberId(crudeOilDemandQueryVO.getEpMemberId());
        if(!StringUtils.isEmpty(crudeOilDemandQueryVO.getPurchaseType())){
            crudeOilDemandQuery.setPurchaseType(crudeOilDemandQueryVO.getPurchaseType());
        }
        if(!StringUtils.isEmpty(crudeOilDemandQueryVO.getCrudePlace()) ){
            crudeOilDemandQuery.setCrudePlace(crudeOilDemandQueryVO.getCrudePlace());
        }

        String oilNumber = crudeOilDemandQueryVO.getOilNumber();
        if (!StringUtil.isEmpty(oilNumber)) {
            if (CrudeOilQuantityType.BELOW_FIVE_HANDRED_THOUSAND.getCode().equals(oilNumber)) {
                crudeOilDemandQuery.setOilNumberMin(CrudeOilQuantityType.BELOW_FIVE_HANDRED_THOUSAND.getMin());
                crudeOilDemandQuery.setOilNumberMax(CrudeOilQuantityType.BELOW_FIVE_HANDRED_THOUSAND.getMax());
            } else if (CrudeOilQuantityType.FIVE_TO_TEN_HANDRED_THOUSAND.getCode().equals(oilNumber)) {
                crudeOilDemandQuery.setOilNumberMin(CrudeOilQuantityType.FIVE_TO_TEN_HANDRED_THOUSAND.getMin());
                crudeOilDemandQuery.setOilNumberMax(CrudeOilQuantityType.FIVE_TO_TEN_HANDRED_THOUSAND.getMax());
            } else if (CrudeOilQuantityType.TEN_TO_TWENTY_HANDRED_THOUSAND.getCode().equals(oilNumber)) {
                crudeOilDemandQuery.setOilNumberMin(CrudeOilQuantityType.TEN_TO_TWENTY_HANDRED_THOUSAND.getMin());
                crudeOilDemandQuery.setOilNumberMax(CrudeOilQuantityType.TEN_TO_TWENTY_HANDRED_THOUSAND.getMax());
            } else if (CrudeOilQuantityType.ABOVE_TWENTY_HANDRED_THOUSAND.getCode().equals(oilNumber)) {
                crudeOilDemandQuery.setOilNumberMin(CrudeOilQuantityType.ABOVE_TWENTY_HANDRED_THOUSAND.getMin());
                crudeOilDemandQuery.setOilNumberMax(CrudeOilQuantityType.ABOVE_TWENTY_HANDRED_THOUSAND.getMax());
            }
        }
        if (StringUtils.isNotEmpty(crudeOilDemandQueryVO.getOilType())) {
            CrudeOilInfoVO coivo = crudeOilInfoService.findCrudeOilInfoById(Long.valueOf(crudeOilDemandQueryVO.getOilType()));
            if (coivo == null ) {
                crudeOilDemandQuery.setOilType(null);
            } else {
                crudeOilDemandQuery.setOilType(coivo.getCrudeNameE());
            }
        }

        //默认按照发布时期降序排序
        String desc = DataQueryOrderType.DESC.getName();
        String keys = CrudeOilDemandOrderProptertyType.PUB_DATE.getName();

        if(crudeOilDemandQueryVO.getOrderByKeys() != null){
            if(crudeOilDemandQueryVO.getSort() != null){
                if(crudeOilDemandQueryVO.getSort().equals(DataQueryOrderType.ASC.getCode())){
                    desc = DataQueryOrderType.ASC.getName();
                }
            }

            String orderByKeys = crudeOilDemandQueryVO.getOrderByKeys();
            if (CrudeOilDemandOrderProptertyType.NUM.getCode().equals(orderByKeys)) {
                keys = CrudeOilDemandOrderProptertyType.NUM.getName();
            } else if (CrudeOilDemandOrderProptertyType.PUB_DATE.getCode().equals(orderByKeys)) {
                keys = CrudeOilDemandOrderProptertyType.PUB_DATE.getName();
            } else if (CrudeOilDemandOrderProptertyType.DISCHARGE_START_TIME.getCode().equals(orderByKeys)) {
                keys = CrudeOilDemandOrderProptertyType.DISCHARGE_START_TIME.getName();
            }
        }
        crudeOilDemandQuery.setOrderByKeys(keys + " " + desc);

        PageUtils.page(pageInfo);
        List<ReleaseCrudeOilVO> list = releaseCrudeOilFormMapper.getReleaseCrudeOilFormList(crudeOilDemandQuery);
        return new PageInfoResult(list);
    }
}
