package com.sinochem.crude.trade.order.dao.impl;

import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.order.status.dao.OrderBizTypeConfigDao;
import com.sinochem.it.b2b.order.status.domain.OrderBizTypeConfig;
import com.sinochem.it.b2b.order.status.domain.OrderStatusItem;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;

@Component
public class OrderBizTypeConfigDaoImpl implements OrderBizTypeConfigDao,InitializingBean{
	@Value("${order.biz.type.config.file:conf/config.xml}")
	String configFile;
	public OrderBizTypeConfigDaoImpl() {
	}

	public OrderBizTypeConfigDaoImpl(String configFile) {
		this.configFile = configFile ;
	}

	@Override
	public List<OrderBizTypeConfig> getAllConfigs() {
		List<OrderBizTypeConfig> list  = new ArrayList<>();
		for(List<OrderBizTypeConfig> orderBizTypeConfigList : orderBizTypeConfigs.values()){
			list.addAll(orderBizTypeConfigList);
		}


		return list;
	}


	static Map<String,List<OrderBizTypeConfig>> orderBizTypeConfigs = new HashMap<>();

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



		//获取bizType的config
		for (Iterator bizTypeIterator = root.element("order_biz_type_config").elementIterator("entity"); bizTypeIterator.hasNext(); ) {
			Element bizTypeElement= (Element) bizTypeIterator.next();
			List<OrderBizTypeConfig> orderBizTypeConfigList = new ArrayList<>();

			String code = bizTypeElement.attributeValue("code");
			String describe = bizTypeElement.attributeValue("describe");
			for (Iterator entityIterator = bizTypeElement.elementIterator("item"); entityIterator.hasNext(); ) {

				Element entityEl = (Element) entityIterator.next();
				OrderBizTypeConfig orderBizTypeConfig = new OrderBizTypeConfig();
				orderBizTypeConfig.setCode(code);
				orderBizTypeConfig.setDescribe(describe);
				orderBizTypeConfig.setFallback("true".equals(entityEl.attributeValue("fallback")));
				orderBizTypeConfig.setItemCode(entityEl.attributeValue("code"));
				orderBizTypeConfig.setSkip("true".equals(entityEl.attributeValue("skip")));

				orderBizTypeConfigList.add(orderBizTypeConfig);
			}
			orderBizTypeConfigs.put(bizTypeElement.attributeValue("code"),orderBizTypeConfigList);
		}
	}
}
