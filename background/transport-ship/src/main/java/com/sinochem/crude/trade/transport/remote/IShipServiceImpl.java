package com.sinochem.crude.trade.transport.remote;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.ProductoilShip;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.service.PalletService;
import com.sinochem.crude.trade.transport.service.ProductoilShipService;
import com.sinochem.crude.trade.transport.service.SysShipService;

/**
 * @ClassName: IShipServiceImpl
 * @author gyy
 * @date 2018年2月24日
 * @version V1.0
 */
@Service(value="iShipService")
public class IShipServiceImpl implements IShipService {
	Log log = LogFactory.getLog(IShipServiceImpl.class);

	@Autowired
	private PalletService palletService;
	
	@Autowired
	private SysShipService shipService;
	
	@Autowired
	private ProductoilShipService productoilShipService;
	
	@Override
	public void cancelOrder(String orderCode,Long memberId) {
		try {
			if (StringUtils.isNullOrEmpty(orderCode)){
				throw new TransportException(TransportException.TYPE.ITSH106);
			}
			palletService.cancelOrder(orderCode,memberId);
			
		} catch (BizException e) {
			log.error("订单取消船务操作失败",e);
			throw new TransportException(e.getCode(),e.getMessage());
		} catch (Exception e) {
			log.error("订单取消船务系统异常",e);
			throw new TransportException(TransportException.TYPE.ITSH552);
		}
		
	}

	@Override
	public String findImoByShipName(String name) {
		String imo ="";
		try {
			if (!StringUtils.isNullOrEmpty(name)){
				SysShip ship = new SysShip();
				ship.setName(name);
				List<SysShip> list = shipService.queryByEntitys(ship);
				if (!list.isEmpty()){
					imo = list.get(0).getImo();
				}
			}
		} catch (BizException e) {
			log.error("查询imo号失败",e);
			throw new TransportException(e.getCode(),e.getMessage());
		} catch (Exception e) {
			log.error("查询imo号系统异常",e);
			throw new TransportException(TransportException.TYPE.ITSH552);
		}
		return imo;
		
	}

	@Override
	public List<SysShipNameAndId> findSysShipNameList() {
		List<SysShipNameAndId>  list = new ArrayList<>();
		
		try {
			// 查询平台船舶信息列表
			List<Map<String,Object>> sysShipList= productoilShipService.findSysShipNameList();
			list = BeanConvertUtils.mapToBeanInList(sysShipList, SysShipNameAndId.class);
			
		} catch (BizException e) {
			log.error("查询船舶失败",e);
			throw new TransportException(e.getCode(),e.getMessage());
		} catch (Exception e) {
			log.error("查询船舶系统异常",e);
			throw new TransportException(TransportException.TYPE.ITSH552);
		}
		return list;
	}

	@Override
	public SysShipVO findShipById(Long shipId) {
		SysShipVO ship = new SysShipVO();
		try {
			if (shipId != null){
			 ProductoilShip sysShip = productoilShipService.findByPrimaryKey(shipId);
				if (sysShip != null){
					BeanUtils.copyProperties(sysShip, ship);
				}
			}
		} catch (BizException e) {
			log.error("查询船舶失败",e);
			throw new TransportException(e.getCode(),e.getMessage());
		} catch (Exception e) {
			log.error("查询船舶系统异常",e);
			throw new TransportException(TransportException.TYPE.ITSH552);
		}
		return ship;
	}

	@Override
	public void insertProductoilShip(SysShipVO vo) {

		try {
			if ( StringUtils.isNullOrEmpty(vo.getName())) {
				throw new TransportException(TransportException.TYPE.ITSH203);
			}
			if (StringUtils.isNullOrEmpty(vo.getImo())) {
				throw new TransportException(TransportException.TYPE.ITSH202);
			}
			productoilShipService.insertProductoilShip(vo);
		} catch (BizException e) {
			log.error("查询新增船舶失败",e);
			throw new TransportException(e.getCode(),e.getMessage());
		} catch (Exception e) {
			log.error("查询新增船舶系统异常",e);
			throw new TransportException(TransportException.TYPE.ITSH552);
		}
		
	}
}
