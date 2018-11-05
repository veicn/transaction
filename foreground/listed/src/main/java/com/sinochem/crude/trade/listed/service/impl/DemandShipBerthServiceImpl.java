package com.sinochem.crude.trade.listed.service.impl;

import com.sinochem.crude.trade.listed.dao.DemandShipBerthMapper;
import com.sinochem.crude.trade.listed.domain.DemandShipBerth;
import com.sinochem.crude.trade.listed.service.DemandShipBerthService;

import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sijliu on 15/11/2017.
 */
@Service
public class DemandShipBerthServiceImpl implements DemandShipBerthService {

    @Autowired
    DemandShipBerthMapper demandShipBerthMapper;

    @Override
    public Long saveOrUpdateShipBerth(DemandShipBerth record) {
        if (record.getId() == null) {
            demandShipBerthMapper.insertSelective(record);
        } else {
            demandShipBerthMapper.updateByPrimaryKeySelective(record);
        }
        return record.getId();
    }

    @Override
    public List<DemandShipBerth> getShipBerthByDemandId(Long demandId) throws BizException {
        return demandShipBerthMapper.selectByDemandId(demandId);
    }

	@Override
	public void copySaveShipBerthByDemandIdAndBerthIds(Long demandId, Long newDemandId, String shipBerthIds) throws BizException {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(shipBerthIds)){
			String[] shipBerthIdArr = shipBerthIds.split(",");
			List<DemandShipBerth> shipBerths = this.getShipBerthByDemandId(demandId);
			
			for(DemandShipBerth shipBerth:shipBerths){
				if(ArrayUtils.contains(shipBerthIdArr, shipBerth.getId()+"")){
					DemandShipBerth newShipBerth = new DemandShipBerth();
					BeanUtils.copyProperties(shipBerth, newShipBerth);
					newShipBerth.setDemandId(newDemandId);
					newShipBerth.setId(null);
					this.saveOrUpdateShipBerth(newShipBerth);
				}
			}
			
		}
	}
}
