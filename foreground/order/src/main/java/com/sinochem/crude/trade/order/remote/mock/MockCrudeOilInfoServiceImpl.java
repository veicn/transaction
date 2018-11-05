package com.sinochem.crude.trade.order.remote.mock;

import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/12/13
 */
@Service
public class MockCrudeOilInfoServiceImpl implements CrudeOilInfoService {

    /**
     * 查询原油信息
     *
     * @param crudeName  原油名称，模糊查询，支持中英文
     * @param catagoryId 原油种类id
     * @return
     */
    @Override
    public List<CrudeOilInfoVO> findCrudeOilInfos(String crudeName, Long catagoryId) {
        return null;
    }

    /**
     * 查询原油信息ById
     *
     * @param id
     * @return
     */
    @Override
    public CrudeOilInfoVO findCrudeOilInfoById(Long id) {
        return null;
    }

    /**
     * 查询原油信息ByUUID
     *
     * @param uuid
     * @return
     */
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


    /**
     * 根基英文名称和agrinid查询地区
     * @param EName
     * @param areaId
     * @return
     */


}
