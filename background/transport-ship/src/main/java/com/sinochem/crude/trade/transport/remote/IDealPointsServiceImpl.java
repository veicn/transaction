package com.sinochem.crude.trade.transport.remote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.DealPoints;
import com.sinochem.crude.trade.transport.model.DealPointsVO;
import com.sinochem.crude.trade.transport.service.DealPointsService;

/**
 * @ClassName: Controller
 * @Description: dubbo服务实现类
 * @author Wh
 * @date 2017年12月5日
 * @version V1.0
 */
@Service(value="iDealPointsService")
public class IDealPointsServiceImpl implements IDealPointsService {
	Log log = LogFactory.getLog(IDealPointsServiceImpl.class);

	@Autowired
	private DealPointsService dealPointsService;
	
	/**
	 * 新增成交点数（WS点）
	 */
	@Override
	public void saveDealPoints(IDealPointsVO dp) {
		try {
			//检查dp对象
			if(dp == null){
				throw new TransportException(TransportException.TYPE.ITSH319);
			}
			//检查地区名
			if(StringUtils.isNullOrEmpty(dp.getRegion())){
				throw new TransportException(TransportException.TYPE.ITSH321);
			}
			//检查船型
			if(StringUtils.isNullOrEmpty(dp.getType())){
				throw new TransportException(TransportException.TYPE.ITSH322);
			}
			//检查价格
			if(dp.getPrice() == null){
				throw new TransportException(TransportException.TYPE.ITSH323);
			}
			//检查名称
			if(StringUtils.isNullOrEmpty(dp.getName())){
				throw new TransportException(TransportException.TYPE.ITSH324);
			}
			//检查预估单桶运费
			if(dp.getSingleFare() == null){
				throw new TransportException(TransportException.TYPE.ITSH326);
			}
			//预估参考路线
			if(StringUtils.isNullOrEmpty(dp.getRefRoute())){
				throw new TransportException(TransportException.TYPE.ITSH327);
			}
			//检查日期
			if(dp.getDate() == null){
				throw new TransportException(TransportException.TYPE.ITSH325);
			}
			DealPointsVO dp2 = BeanConvertUtils.beanToBean(dp, DealPointsVO.class);
			//查重
			DealPoints dealPoints = dealPointsService.checkDp(dp2);
			if(dealPoints == null){
				dealPointsService.saveDealPoints2(BeanConvertUtils.beanToBean(dp2, DealPoints.class));					
			}else{
				throw new TransportException(TransportException.TYPE.ITSH328);
			}
		}catch (BizException e) {
			log.error("saveDealPoints error", e);
			throw new TransportException(e.getCode(),e.getMessage());
		} catch (Exception e) {
			log.error("saveDealPoints error", e);
			throw new TransportException(TransportException.TYPE.ITSH329);
		}
	}
}
