package com.sinochem.crude.trade.xxl;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;

import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.HttpUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.common.utils.StringUtils;
import com.sinochem.crude.trade.commons.JKXContants;
import com.sinochem.crude.trade.commons.JKXContants.JKX_NEW_TYPE;
import com.sinochem.crude.trade.commons.JKXLayoutContants;
import com.sinochem.crude.trade.commons.JKXLayoutContants.JKX_LAYOUT_TYPE;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.domain.InfoContent;
import com.sinochem.crude.trade.info.service.ChannelSubService;
import com.sinochem.crude.trade.info.service.InfoContentService;
import com.sinochem.crude.trade.info.service.InfoService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import jodd.util.StringUtil;
import net.sf.json.JSONObject;
@Component
@JobHander("dataFeed")
public class DataFeedHandler extends  IJobHandler{
	@Autowired
	private InfoContentService infoContentService;
	@Autowired
	private InfoService infoService;
	@Value("${dataFeed.newsids.url}")  
	private String newsidsUrl;  
	@Value("${dataFeed.newsc.url}")  
	private String newscUrl;
	@Value("${dataFeed.layout.url}")  
	private String layoutUrl;
	@Autowired
	private ChannelSubService channelSubService;
	
    private static final Log log = LogFactory.getLog(DataFeedHandler.class);

	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		log.error("DataFeedHandler  start ---->");
		this.LayoutInfo();
		this.info();
		log.error("DataFeedHandler  end ---->");
		return ReturnT.SUCCESS;
	}
	/*
	 * 普通资讯获取
	 * */
	private void info() throws Exception {
		List<String> type = getJkxNewTypes();
		for (String typ : type) {
			String langVer = getJKXLangVer(typ);
			StringBuilder sb=new StringBuilder();
			Integer maxId= infoService.findMaxInfoGrabId(typ);
			//typ前面加C*取中文加E*取英文资讯
			if(StringUtil.equals(langVer, "zh")){
				sb.append("C*");
				sb.append(typ);
			}else if(StringUtil.equals(langVer, "en")){
				sb.append("E*");
				sb.append(typ);
			}
			StringBuilder url=new StringBuilder(newsidsUrl);
			url.append(URLEncoder.encode(sb.toString(), "UTF-8"));
			if(maxId!=null){
				url.append("&id=");
				url.append(maxId);
			}
			String sendGet = HttpUtils.newscGet(url.toString(), "GB2312");
			log.info("id集合str---- "+sendGet);
			JSONObject fromObject = null;
			try {
				fromObject = JSONObject.fromObject(sendGet);
			} catch (Exception e) {
				log.error("id集合str转json失败 ----" + typ,e);
				continue;
			}
			if(fromObject==null){
				continue;
			}
			try {
				List<String> ids = (List<String>)fromObject.get("id_array");
				for (String id : ids) {
					StringBuilder newsUrl = new StringBuilder(newscUrl);
					if(existInfo(id,typ)) {
						continue;	
					}
					newsUrl.append(id);
					String newscGet = HttpUtils.newscGet(newsUrl.toString(), "GB2312");
					log.info(newscGet);
					if(StringUtils.indexOf(newscGet, "newsc")<0){
						log.error("newsc标签不存在>");
						continue;
					}
					newscGet = newscGet.replace("&", "&amp;");
					try {
						Document doc = Jsoup.parseBodyFragment(newscGet);
						try {
							saveInfo(doc,typ,id,langVer);
						}catch(Exception e) {
							log.error("插入失败 key " + typ,e);
							continue;
						}
					} catch (Exception e) {
						log.error("解析失败 key " + typ,e);
					}
				}
			} catch (Exception e) {
				log.error("fromObject ----" + fromObject.toString());
				continue;
			}
			
		}
	}
	private void LayoutInfo(){
		List<String> type = getJkxLayoutTypes();
		for (String typ : type) {
			String jkxType = getLayoutJkxType(typ);
			StringBuilder url=new StringBuilder(layoutUrl);
			try {
				url.append(URLEncoder.encode(typ, "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			String sendGet = HttpUtils.newscGet(url.toString(), "GB2312");
			log.info(sendGet);
			if(StringUtils.indexOf(sendGet, "pagec")<0){
				log.error("pagec标签不存在>");
				continue;
			}
			sendGet = sendGet.replace("&", "&amp;");
			if(null != sendGet && !"".equals(sendGet)){  
			    if(sendGet.indexOf("<") != -1 && sendGet.lastIndexOf(">") != -1 && sendGet.lastIndexOf(">") > sendGet.indexOf("<"))  
			    	sendGet = sendGet.substring(sendGet.indexOf("<"), sendGet.lastIndexOf(">") + 1);  
			} 
			try {
				Document doc = Jsoup.parseBodyFragment(sendGet);
				try {
					saveLayoutInfo(doc,typ,jkxType);
				}catch(Exception e) {
					log.error("插入失败 key " + typ,e);
					continue;
				}
			} catch (Exception e) {
				log.error("解析失败 key " + typ,e);
			}
		}
	}
	//入库
	private void saveInfo(Document doc,String typ,String id,String langVer) throws Exception {
		String title = doc.getElementsByTag("t").get(0).text();
		StringBuilder sb=new StringBuilder();
		sb.append(doc.getElementsByTag("date").get(0).text()+doc.getElementsByTag("time").get(0).text());
		Info info = new Info();
		try {
			info.setReleaseDate(DateTimeUtils.toDate(sb.toString(), "yyyyMMddHH:mm"));
		} catch (Exception e) {
			log.error("日期转换失败--"+sb,e);
			return;
		}
		info.setExtend2(typ);
		info.setTitle(title);
		info.setAliveFlag(Constants.ALIEVE_FLAG);
		if(title.indexOf("成品油") > 0) {
			info.setChannelId((channelSubService.findByName("CPYYB").getId()));
		}else {
			info.setChannelId(channelSubService.findByName(getChannelCodeByCode(typ)).getId());
		} 
		if(StringUtil.equals(langVer, "en")){
			info.setOrigin("Jcache");
			info.setAuthor("Jcache");
		}else{
			info.setOrigin("金凯讯");
			info.setAuthor("金凯讯");
		}
		info.setExtend3(getTypeTimeByCode(typ));
		info.setInfoGrabId(id);
		info.setCreateDate(DateTimeUtils.currentDate());
		info.setValidBegin(DateTimeUtils.currentDate());
		//设置置顶为0
		info.setStick("0");
		info.setUuid(KeyGenUtils.newUuid());
		info.setStatus("30");
		info.setArticleType("20");
		info.setIsShowDisclaimer("1");// 免责申明
		info.setExtend10(langVer);
		infoService.insertRecord(info);
		InfoContent infoContent = new InfoContent();
		infoContent.setAliveFlag(Constants.ALIEVE_FLAG);
		infoContent.setTex(doc.getElementsByTag("c").get(0).text());
		infoContent.setInfoId(info.getId());
		infoContent.setTexHtml(doc.getElementsByTag("c").get(0).html());
		infoContent.setCreateDate(DateTimeUtils.currentDate());
		infoContent.setUuid(KeyGenUtils.newUuid());
		infoContentService.insertRecord(infoContent);
	}
	//入库
	private void saveLayoutInfo(Document doc,String typ,String jkxType) throws Exception {
		Element c = doc.getElementsByTag("c").get(0);
		String langVer = getLayoutLangVer(typ);
		Info info = new Info();
		info.setInfoGrabId(typ);
		List<Info> queryByEntitys = infoService.queryByEntitys(info);
		InfoContent infoContent = new InfoContent();
		if(CollectionUtils.isNotEmpty(queryByEntitys)) {
			infoContent.setInfoId(queryByEntitys.get(0).getId());
			List<InfoContent> contens = infoContentService.queryByEntitys(infoContent);
			if(CollectionUtils.isNotEmpty(contens)){
				InfoContent editContent = contens.get(0);
				editContent.setTex(c.text());
				editContent.setTexHtml(c.html());
				infoContentService.updateRecordById(editContent);
			}
		}else{
			info.setExtend2(typ);
			info.setTitle(jkxType);
			info.setAliveFlag(Constants.ALIEVE_FLAG);
			info.setChannelId(channelSubService.findByName(getLayoutChannelCodeByCode(typ)).getId());
			info.setExtend3(getLayoutTypeTimeByCode(typ));
			info.setCreateDate(DateTimeUtils.currentDate());
			info.setReleaseDate(DateTimeUtils.currentDate());
			info.setValidBegin(DateTimeUtils.currentDate());
			//设置置顶为0
			info.setStick("0");
			info.setUuid(KeyGenUtils.newUuid());
			info.setStatus("30");
			info.setArticleType("20");
			if(StringUtil.equals(langVer, "en")){
				info.setOrigin("Jcache");
				info.setAuthor("Jcache");
			}else{
				info.setOrigin("金凯讯");
				info.setAuthor("金凯讯");
			}
			info.setIsShowDisclaimer("1");// 免责申明
			info.setExtend10(langVer);
			infoService.insertRecord(info);
			infoContent.setAliveFlag(Constants.ALIEVE_FLAG);
			infoContent.setTex(c.text());
			infoContent.setInfoId(info.getId());
			infoContent.setTexHtml(c.html());
			infoContent.setCreateDate(DateTimeUtils.currentDate());
			infoContent.setUuid(KeyGenUtils.newUuid());
			infoContentService.insertRecord(infoContent);
		}
	}
	//判断是否存在
	private boolean existInfo(String id,String typ) {
		Info info = new Info();
		info.setInfoGrabId(id);
		info.setExtend2(typ);
		return !CollectionUtils.isEmpty(infoService.queryByEntitys(info));
	}

	private List<String> getJkxNewTypes(){
		JKX_NEW_TYPE[] jkx = JKXContants.JKX_NEW_TYPE.values();
		List<String> list = new ArrayList<>();
		for(int i = 0; i < jkx.length; i ++) {
			list.add(jkx[i].getJkxCode());
		}
		return list;
	}
	
	private String getChannelCodeByCode(String jkxCode) {
		for(JKX_NEW_TYPE item : JKXContants.JKX_NEW_TYPE.values()) {
			if(item.getJkxCode() == jkxCode) {
				return item.getChannelCode();
			}
		}
		return null;
	}
	
	private String getTypeTimeByCode(String jkxCode) {
		for(JKX_NEW_TYPE item : JKXContants.JKX_NEW_TYPE.values()) {
			if(item.getJkxCode() == jkxCode) {
				return item.getTypeTime();
			}
		}
		return null;
	}
	private String getJkxType(String jkxCode) {
		for(JKX_NEW_TYPE item : JKXContants.JKX_NEW_TYPE.values()) {
			if(item.getJkxCode() == jkxCode) {
				return item.getJkxType();
			}
		}
		return null;
	}
	private String getJKXLangVer(String jkxCode) {
		for(JKX_NEW_TYPE item : JKXContants.JKX_NEW_TYPE.values()) {
			if(item.getJkxCode() == jkxCode) {
				return item.getLangVer();
			}
		}
		return null;
	}
	private List<String> getJkxLayoutTypes(){
		JKX_LAYOUT_TYPE[] jkx = JKXLayoutContants.JKX_LAYOUT_TYPE.values();
		List<String> list = new ArrayList<>();
		for(int i = 0; i < jkx.length; i ++) {
			list.add(jkx[i].getJkxCode());
		}
		return list;
	}
	private String getLayoutJkxType(String jkxCode) {
		for(JKX_LAYOUT_TYPE item : JKXLayoutContants.JKX_LAYOUT_TYPE.values()) {
			if(item.getJkxCode() == jkxCode) {
				return item.getJkxType();
			}
		}
		return null;
	}
	private String getLayoutLangVer(String jkxCode) {
		for(JKX_LAYOUT_TYPE item : JKXLayoutContants.JKX_LAYOUT_TYPE.values()) {
			if(item.getJkxCode() == jkxCode) {
				return item.getLangVer();
			}
		}
		return null;
	}
	private String getLayoutChannelCodeByCode(String jkxCode) {
		for(JKX_LAYOUT_TYPE item : JKXLayoutContants.JKX_LAYOUT_TYPE.values()) {
			if(item.getJkxCode() == jkxCode) {
				return item.getChannelCode();
			}
		}
		return null;
	}
	
	private String getLayoutTypeTimeByCode(String jkxCode) {
		for(JKX_LAYOUT_TYPE item : JKXLayoutContants.JKX_LAYOUT_TYPE.values()) {
			if(item.getJkxCode() == jkxCode) {
				return item.getTypeTime();
			}
		}
		return null;
	}
}
