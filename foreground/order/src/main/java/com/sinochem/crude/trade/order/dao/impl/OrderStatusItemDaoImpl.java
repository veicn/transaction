package com.sinochem.crude.trade.order.dao.impl;

import java.net.URL;
import java.util.*;

import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.order.status.domain.OrderBizTypeConfig;
import com.sinochem.it.b2b.order.status.domain.OrderStatus;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinochem.it.b2b.order.status.dao.OrderStatusItemDao;
import com.sinochem.it.b2b.order.status.domain.OrderStatusItem;

@Component
public class OrderStatusItemDaoImpl implements OrderStatusItemDao ,InitializingBean {
	@Value("${order.biz.type.config.file:conf/config.xml}")
	String configFile;

	@Override
	public List<OrderStatusItem> getAllItems() {
		return orderStatusItems;
	}

	static List<OrderStatusItem> orderStatusItems = new ArrayList<>();

	@Override
	public OrderStatusItem getItem(String itemCode){
		for(OrderStatusItem orderStatusItem : orderStatusItems){
			if(itemCode.equals(orderStatusItem.getCode())){
				return orderStatusItem;
			}
		}
		return null;
	}

	@Override
	public OrderStatusItem.Entity getEntity(String itemCode, int value){
		OrderStatusItem item = getItem(itemCode);
		if(item == null){
			return null;
		}
		for(OrderStatusItem.Entity entity: item.getEntities()){
			if(value == entity.getValue() ){
				return entity;
			}
		}
		return null;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		//读取文件数据
		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource(configFile);
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(url.getFile());
		} catch (DocumentException e) {
			throw new BizException(e);
		}
		Element root = doc.getRootElement();

		//获取orderStatus的config
		for (Iterator i = root.element("order_status_config").elementIterator("order_status_item"); i.hasNext(); ) {
			Element orderStatusEl = (Element) i.next();
			OrderStatusItem orderStatus = new OrderStatusItem();
			orderStatus.setCode(orderStatusEl.attributeValue("code"));
			orderStatus.setDescribe(orderStatusEl.attributeValue("describe"));
			orderStatus.setDescribeEn(orderStatusEl.attributeValue("describe_en"));
			orderStatus.setStartNo(Integer.valueOf(orderStatusEl.attributeValue("start_no")));
			orderStatus.setEndNo(Integer.valueOf(orderStatusEl.attributeValue("end_no")));

			//获取内部的entities
			List<OrderStatusItem.Entity> list = new ArrayList<>();
			for(Iterator el = orderStatusEl.elementIterator("entity"); el.hasNext(); ){
				Element itemEl = (Element) el.next();
				OrderStatusItem.Entity entity = orderStatus.new Entity();
				entity.setValue(Integer.valueOf(itemEl.attributeValue("value") ) );
				entity.setDescribe(itemEl.attributeValue("describe"));
				entity.setDescribeEn(itemEl.attributeValue("describe_en"));
				list.add(entity);
			}
			orderStatus.setEntities(list);
			//添加到缓存
			orderStatusItems.add(orderStatus);
		}

	}
}
