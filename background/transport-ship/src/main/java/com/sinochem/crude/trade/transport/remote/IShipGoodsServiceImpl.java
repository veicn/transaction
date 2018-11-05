package com.sinochem.crude.trade.transport.remote;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.service.DisburdenService;
import com.sinochem.crude.trade.transport.service.ShipmentService;

/**
 * @ClassName: IShipGoodsServiceImpl
 * @Description: 货物装卸港信息维护
 * @author gyy
 * @date 2018年2月24日
 * @version V1.0
 */
@Service(value="iShipGoodsService")
public class IShipGoodsServiceImpl implements IShipGoodsService {
	Log log = LogFactory.getLog(IShipGoodsServiceImpl.class);

	@Autowired
	private ShipmentService shipmentService;
	
	@Autowired
	private DisburdenService disburdenService;

	@Override
	public void saveLoadGoods(List<LoadGoodsVO> list , String orderCode,Long memberId) {
		try {
			if (list == null || list.isEmpty()){
				throw new TransportException(TransportException.TYPE.ITSH047);
			}
			if (StringUtils.isNullOrEmpty(orderCode)){
				throw new TransportException(TransportException.TYPE.ITSH106);
			}
			shipmentService.saveLoadGoods(list,orderCode,memberId);
			
		} catch (BizException e) {
			log.error("保存货物装港信息失败",e);
			throw new TransportException(e.getCode(),e.getMessage());
		} catch (Exception e) {
			log.error("保存货物装港信息系统异常",e);
			throw new TransportException(TransportException.TYPE.ITSH552);
		}
	}

	@Override
	public void saveUnloadGoods(List<UnloadGoodsVO> list , String orderCode,Long memberId) {
		try {
			if (list == null || list.isEmpty()){
				throw new TransportException(TransportException.TYPE.ITSH085);
			}
			if (StringUtils.isNullOrEmpty(orderCode)){
				throw new TransportException(TransportException.TYPE.ITSH106);
			}
			disburdenService.saveUnloadGoods(list,orderCode,memberId);
			
		} catch (BizException e) {
			log.error("保存货物卸港信息失败",e);
			throw new TransportException(e.getCode(),e.getMessage());
		} catch (Exception e) {
			log.error("保存货物卸港信息系统异常",e);
			throw new TransportException(TransportException.TYPE.ITSH552);
		}
	}
}
