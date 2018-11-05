package com.sinochem.crude.trade.info.remote;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.eyeieye.melody.util.DateUtil;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.domain.InfoInterface;
import com.sinochem.crude.trade.info.service.InfoInterfaceService;

@Service
public class InfoServiceImpl implements InfoService{
	
	@Autowired
	private InfoInterfaceService infoInterfaceService;	
	
	private static final Log log = LogFactory.getLog(InfoServiceImpl.class);
	
	/**
	 * 处理来自外部系统的资讯信息
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public void saveInfoInterface(InfoInterfaceVo vo) throws Exception{
		
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = objectMapper.readValue(vo.getBizContent(), Map.class);
		log.info("业务参数map--->" + map);
		
		String externalId = (String)map.get("id");		
		@SuppressWarnings("unchecked")
		List<String> tags = (List<String>)map.get("infoTag");
		StringBuilder infoTag = new StringBuilder();
		for(int i=0; i<tags.size(); i++ ){
			if(i==tags.size()-1)
				infoTag.append(tags.get(i));
			else{
				infoTag.append(tags.get(i));
				infoTag.append(",");
			}
		}
		
		InfoInterface infoInterface = BeanConvertUtils.mapToBean(map, InfoInterface.class);
		infoInterface.setExternalId(externalId);
		infoInterface.setInfoTag(infoTag.toString());
		infoInterface.setAuthor((String)map.get("infoAuthor"));
		infoInterface.setEditTime(DateUtil.strToDate((String)map.get("editDate"), "yyyy-MM-dd HH:mm:ss"));
		infoInterface.setExternalSystem(vo.getSysCode());
		infoInterface.setInterfaceDate(new Date());
		infoInterface.setStatus("00");
		infoInterface.setAliveFlag("1");
		
		InfoInterface param = new InfoInterface();
		param.setExternalId(infoInterface.getExternalId());
		param.setExternalSystem(infoInterface.getExternalSystem());
		param.setAliveFlag("1");
		if(CollectionUtils.isNotEmpty(infoInterfaceService.queryByEntitys(param))){
			infoInterface.setId(infoInterfaceService.queryByEntitys(param).get(0).getId());
			infoInterfaceService.updateRecordById(infoInterface);
			log.info("更新记录");
		}else{
			infoInterface.setId(null);
			infoInterface.setUuid(UUID.randomUUID().toString().replace("-", ""));
			infoInterfaceService.insertRecord(infoInterface);
			log.info("新增记录");
		}
		
	}

}
