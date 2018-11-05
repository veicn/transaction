package com.sinochem.crude.trade.info.job;

/**
 * 远程数据
 * @author x
 */
public interface RemoteJobService {

	/**
	 * ###取资讯信息(共3042条)  t_cms_info, t_cms_info_content
		SELECT
			t.content_id AS infoId,
			t2.channel_name AS channelName,
			t.title AS title,
			t3.txt AS infoConent,
			t.author AS author,
			t.origin AS infoSource,
			t.origin_url AS infoSourceUrl,
			t.release_date AS editDate
		FROM
			jc_content_ext t,
			jc_content_channel t1,
			jc_channel_ext t2,
			jc_content_txt t3
		WHERE
			t.content_id = t1.content_id
		AND t1.channel_id = t2.channel_id
		AND t3.content_id = t.content_id
		AND t2.channel_name IN (
			'油价与相关指数走势',   ##油价与相关指数
			'航运市场信息', ##最新成交点数
			'实货市场信息', ##原油实货成交信息
			'实货动态',
			'原油市场'
		)
		ORDER BY
			t.content_id DESC;
	 */
	public void saveInfos(Integer day);
	
	/**
	 * ###取航运点数(共1891条)-t_crude_ship_point
		SELECT
			t.release_date as releaseDate,
		  t.ws as point,
		  t.frt_b as bucketFreight,
		  t.remarks as remarks,
		  'VLCC' as shipType, 
		  a.NAME_E as dispatchPort,
			b.NAME_E as dischargePort,
		  c.NAME_C as as dileveryRegion
		FROM
			gm_ws_his t,
			sys_port a,
			sys_port b,
		sys_origin_area c
		WHERE
			t.port_shipmert_id = a.ID
		AND t.port_discharge_id = b.id
		and t.origin_area_id = c.ID
		ORDER BY
			t.release_date DESC;
			
			RELEASE_DATE + DILIVERY_REGION,   为逻辑主键，如果这2项一致说明是同一条数据；
			
	 */
	public void saveShipPoints(Integer day);
	
	/**
	 * ####指数(实时报价)32538条数据 - t_crude_futures_price
		SELECT
			a.CRUDE_NAME_C,
			a.CRUDE_NAME_E,
			t.PRICING,
			t.PRICING_DATE,
			t.NOTE
		FROM
			om_futures_price_his t,
			sys_crude a
		WHERE
			t.CRUDE_ID = a.ID
		###AND a.IS_PRIC_BASIS = 1
	 */
	public void saveFutures(Integer day);
	
	/**
	 * ###官价(共1242条数据) —— t_crude_base_price
		SELECT
			b.CRUDE_NAME_C,
			b.CRUDE_NAME_E,
			b.IS_PRIC_BASIS,
			b.PRIC_MODE,
			a.PRICING,
			a.PRICING_DATE,
			a.NOTE
		FROM
			om_pricing_basis_his a,
			sys_crude b
		WHERE
			a.CRUDE_ID = b.ID
		order by a.PRICING_DATE desc;
	 */
	public void saveCrudeBasePrices(Integer day);
	
	/**
	 * ####贴水(28450条数据)  ---t_crude_agio
		SELECT
			t1.CRUDE_NAME_C,
			t1.CRUDE_NAME_E,
			t.AGIO,
			t.PRICING_DATE,
			t.NOTE,
			t.LOADING_MONTH,
			t.transport_clause,
			t.`change`,
			t.xiaoyi
		FROM
			om_stock_agio_his t,
			sys_crude t1
		WHERE
			t.CRUDE_ID = t1.ID;
	 */
	public void saveAgios(Integer day);
}
