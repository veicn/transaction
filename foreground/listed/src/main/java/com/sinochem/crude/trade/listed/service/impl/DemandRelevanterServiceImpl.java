package com.sinochem.crude.trade.listed.service.impl;

import com.sinochem.crude.trade.listed.dao.DemandRelevanterMapper;
import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.enums.DemandRelevanterType;
import com.sinochem.crude.trade.listed.service.DemandRelevanterService;
import com.sinochem.crude.trade.member.user.CurrentUser;

import java.util.List;

import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sijliu on 15/11/2017.
 */
@Service
public class DemandRelevanterServiceImpl implements DemandRelevanterService {

    @Autowired(required = false)
    DemandRelevanterMapper demandRelevanterMapper;

    @Override
    public Long saveOrUpdateRelevanter(DemandRelevanter record) {
        if (record.getId() == null) {
            demandRelevanterMapper.insertSelective(record);
        } else {
            demandRelevanterMapper.updateByPrimaryKeySelective(record);
        }
        return record.getId();
    }

    @Override
    public DemandRelevanter getDemandRelevanterByKey(Long id) {
        return demandRelevanterMapper.selectByPrimaryKey(id);
    }

	@Override
	public List<DemandRelevanter> getDemandRelevantersByDemandId(Long id) {
		// TODO Auto-generated method stub
		return demandRelevanterMapper.getDemandRelevantersByDemandId(id);
	}

    /**
     * 根据demangId 和type 查询联系人信息
     * @param demandId
     * @param i
     * @return
     */
    @Override
    public DemandRelevanter getDemandRelevanterByDemandIdAndTypeOne(Long demandId, String i) {
        return demandRelevanterMapper.getDemandRelevanterByDemandIdAndTypeOne(demandId,i);
    }

    /**
     * 根据demangId 和type 查询联系人信息
     * @param demandId
     * @param j
     * @return
     */
    @Override
    public DemandRelevanter getDemandRelevanterByDemandIdAndTypeTwo(Long demandId, String j) throws BizException {
        return demandRelevanterMapper.getDemandRelevanterByDemandIdAndTypeTwo(demandId,j);
    }

	@Override
	public void copySaveRelevanterByDemandId(Long demandId, Long newDemandId, DemandRelevanter buyer) {
		List<DemandRelevanter> relevanters =  demandRelevanterMapper.getDemandRelevantersByDemandId(demandId);
		for(DemandRelevanter relevanter:relevanters){
			DemandRelevanter newRelevanter = new DemandRelevanter();
			BeanUtils.copyProperties(relevanter, newRelevanter);

			newRelevanter.setDemandId(newDemandId);
			newRelevanter.setId(null);
			this.saveOrUpdateRelevanter(newRelevanter);
		}
		buyer.setDemandId(newDemandId);
		buyer.setType(DemandRelevanterType.BUYER.getCode());
		this.saveOrUpdateRelevanter(buyer);
	}



}



