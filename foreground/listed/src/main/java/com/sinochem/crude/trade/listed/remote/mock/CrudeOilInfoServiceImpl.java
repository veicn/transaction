package com.sinochem.crude.trade.listed.remote.mock;

import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudeOilInfoServiceImpl implements CrudeOilInfoService {
    @Override
    public List<CrudeOilInfoVO> findCrudeOilInfos(String crudeName, Long catagoryId) {
        return null;
    }

    @Override
    public CrudeOilInfoVO findCrudeOilInfoById(Long id) {
        return null;
    }

    @Override
    public CrudeOilInfoVO findCrudeOilInfoByUUID(String uuid) {
        return null;
    }

    @Override
    public CrudeOilInfoVO findByENameAndAreaId(Long areaId) {
        return null;
    }

	@Override
	public int addCrudeOilInfo(CrudeOilInfoVO crudeOilInfoVO) {
		// TODO Auto-generated method stub
		return 0;
	}


}
