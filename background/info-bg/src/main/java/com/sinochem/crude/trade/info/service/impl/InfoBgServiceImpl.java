package com.sinochem.crude.trade.info.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.ColColumnMapper;
import com.sinochem.crude.trade.info.dao.InfoMapper;
import com.sinochem.crude.trade.info.dao.immt.ColColumnImmtMapper;
import com.sinochem.crude.trade.info.domain.ChannelM;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.domain.ColColumn;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.domain.InfoAttachment;
import com.sinochem.crude.trade.info.domain.InfoContent;
import com.sinochem.crude.trade.info.model.InfoVO;
import com.sinochem.crude.trade.info.service.ChannelMService;
import com.sinochem.crude.trade.info.service.ChannelSubService;
import com.sinochem.crude.trade.info.service.InfoAttachmentService;
import com.sinochem.crude.trade.info.service.InfoContentService;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.info.util.DBUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;

/**
 * 资讯服务
 * @author x
 */
@Service
public class InfoBgServiceImpl implements InfoService {
	@Autowired
	private InfoMapper infoMapper;
	@Autowired
	private ChannelMService  channelMService;
	@Autowired
	private ChannelSubService channelSubService;
	@Autowired
	private InfoAttachmentService infoAttachmentService;
	@Autowired
	private InfoContentService infoContentService;
	@Autowired
	private ColColumnMapper colColumnMapper;
	@Autowired
	private ColColumnImmtMapper colColumnImmtMapper;
	@Autowired
	private DBUtil dBUtil;
	
	private Log log = LogFactory.getLog(getClass());
	
	public InfoMapper getMapper(){
		return infoMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Info info){
		info.setBrowseCount(0); //浏览次数
		info.setShareCount(0);
		info.setCommentCount(0);
		info.setFabulousCount(0);
		info.setCollectionCount(0);
		info.setTrampleCount(0);
		 return infoMapper.insertRecord(info);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return infoMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Info  record){
    	return infoMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Info info) throws BizException{
		if ( info.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return infoMapper.updateRecordById(info);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return infoMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Info info){
		return infoMapper.updateRecords(info.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Info findByPrimaryKey(Long id){
		return  infoMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Info findByUuid(String uuid){
		return  infoMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Info> queryByEntitys(Info info){
		return  infoMapper.queryByEntitys(info);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return infoMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) infoMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return infoMapper.countRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/
	/**
	 * 更新资讯中评论，分享,收藏数据
	 */
	@Override
	public int updateInfoCount(Info info,CurrentUser user) {
		log.info("更新资讯中评论，分享,收藏数据" + info);
		info.setUpdateDate(DateTimeUtils.currentDate());
		info.setUpdateUser(user.getName());
		info.setCreateUser(user.getName());
		return infoMapper.updateInfoCount(info);
	}
	/**
	 * 公告列表
	 */
	@Override
	public List<Info> listNotice(Info info) {
		log.info("公告列表" + info);
		//List<Info> list = infoMapper.listNotice(info);
		//return list;
		return infoMapper.listNotice(info);
	}
	/**
	 * 发布公告
	 */
	@Override
	public int saveOrUpdateNotice(Info info,CurrentUser user) {
		log.info("发布公告" + info);
		int result = 0;
		if(StringUtils.isBlank(info.getUuid())){
			//新增
			info.setChannelId(-1L);
			info.setAliveFlag(Constants.ALIEVE_FLAG);
			info.setUuid(KeyGenUtils.newUuid());
			info.setCreateDate(DateTimeUtils.currentDate());
			info.setCreateUser(user.getName());
			info.setAuthor(user.getName());
			info.setReleaseId(user.getMemberId()+"");
			info.setReleaseDate(DateTimeUtils.currentDate());
			info.setStatus("30");
			info.setUuid(KeyGenUtils.newUuid());
			//result = infoMapper.insertRecord(info);
			result = this.insertRecord(info);
		}else{
			//更新
			info.setStatus("30");
			info.setUpdateDate(DateTimeUtils.currentDate());
			info.setUpdateUser(user.getName());
			result = infoMapper.updateInfoTitle(info);
		}
		return result;
	}
	
	/**
	 * 撤销公告
	 */
	@Override
	public int revokeNotice(String uuid, CurrentUser user) {
		log.info("撤销公告-->" + uuid);
		Info info = new Info();
		info.setUuid(uuid);
		info.setUpdateDate(DateTimeUtils.currentDate());
		info.setUpdateUser(user.getName());
		info.setStatus("00"); 
		return infoMapper.updateNoticeStatus(info);
	}
	
	/**
	 * 历史公告发布
	 */
	@Override
	public int pushNotice(String uuid, CurrentUser user) {
		log.info("历史公告发布 -->" + uuid);
		Info info = new Info();
		info.setUuid(uuid);
		info.setUpdateDate(DateTimeUtils.currentDate());
		info.setUpdateUser(user.getName());
		info.setReleaseId(user.getMemberId()+"");
		info.setReleaseDate(DateTimeUtils.currentDate());
		info.setStatus("30"); 
		return infoMapper.updateNoticeStatus(info);
	}
	
	/**
	 * 删除历史公告
	 */
	@Override
	public int deleteByInfo(Info info) {
		log.info("删除历史公告" + info);
		info.setUpdateDate(DateTimeUtils.currentDate());
		return infoMapper.deleteByInfo(info);
	}
	/**
	 * 保存或编辑资讯信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Result saveOrUpdateInfo(InfoVO vo,CurrentUser user) {
		log.info("保存或编辑资讯信息" + vo);
		Result res = new Result();
		Info info = new Info();
		info.setAliveFlag(Constants.ALIEVE_FLAG);
		ChannelSub channel = channelSubService.findByUuid(vo.getChannelUUId());
		info.setChannelId(channel.getId());
		info.setTitle(vo.getTitle());
		//设置置顶为0
		info.setStick("0");
		//是否显示导语
		if("1".equals(vo.getIsShowGeneral())){
			info.setGeneral(vo.getGeneral());
			info.setIsShowGeneral(vo.getIsShowGeneral());
		}else{
			info.setIsShowGeneral("0");
		}
		//是否显示来源
		if("1".equals(vo.getIsShowOrigin())){
			info.setOrigin(vo.getOrigin());
			info.setIsShowOrigin(vo.getIsShowOrigin());
		}else{
			info.setIsShowOrigin("0");
		}
		info.setTagList(vo.getTagList());
		//设置发布状态
		if("10".equals(vo.getStatus()) || "30".equals(vo.getStatus())){
			info.setStatus(vo.getStatus()); //10草稿 30已发布,00撤销
		}
		info.setArticleType("20");
		info.setProbationFlag("0");
		//显示免责 或 显示版权声明
		if("1".equals(vo.getIsShowCopyright())){
			info.setIsShowCopyright(vo.getIsShowCopyright());
			info.setIsShowDisclaimer("0");
		}else if("1".equals(vo.getIsShowDisclaimer())){
			info.setIsShowCopyright("0");
			info.setIsShowDisclaimer(vo.getIsShowDisclaimer());
		}
		//设置显示模式: 10单图,20三图 , 30自动,40广告
		if("1".equals(vo.getDisplayMode())){
			info.setDisplayMode("10");
		}else if("2".equals(vo.getDisplayMode())){
			info.setDisplayMode("20");
		}else if("0".equals(vo.getDisplayMode())){
			//显示模式为自动模式转为附件入库
			List<InfoAttachment> srcs = getImgSrcFromContentWithHtml(vo.getContentWithHtml());
			int size = srcs.size();
			switch (size) {
			case 0:
				info.setDisplayMode("30");
				break;
			case 1:
				info.setDisplayMode("10");
				break;

			case 2:
				info.setDisplayMode("10");
				srcs.remove(1);
				break;
			case 3:
				info.setDisplayMode("20");
				break;
			default:
				break;
			}
			vo.setAttachments(srcs);
		}else if("3".equals(vo.getDisplayMode())){
			info.setDisplayMode("40");
		}
		
		info.setCreateUser(user.getName());
		info.setCreateDate(DateTimeUtils.currentDate());
		info.setReleaseId(user.getMemberId()+"");
		info.setAuthor(user.getName());
		if (StringUtils.isNotBlank(vo.getReleaseDate())) {
			info.setReleaseDate(DateTimeUtils.toDate(vo.getReleaseDate(), "yyyy-MM-dd HH:mm"));
		} else {
			info.setReleaseDate(DateTimeUtils.currentDate());
		}
		info.setValidBegin(DateTimeUtils.currentDate());
		
		//附件关联 Info 表
		List<InfoAttachment> ams =  vo.getAttachments();
		if(!CollectionUtils.isEmpty(ams)){
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < ams.size(); i ++){
				sb.append(ams.get(i).getAttachmentPath()+",");
			}
			info.setExtend1(sb.toString().substring(0,sb.toString().length()- 1));
		}
		
		if(StringUtils.isBlank(vo.getUuid())){
			info.setUuid(KeyGenUtils.newUuid());
			//infoMapper.insertRecord(info);
			this.insertRecord(info);
			res.setCode(info.getUuid());
			//内容
			InfoContent infoContent = new InfoContent();
			infoContent.setAliveFlag(Constants.ALIEVE_FLAG);
			infoContent.setTex(vo.getContent());
			infoContent.setInfoId(info.getId());
			infoContent.setTexHtml(vo.getContentWithHtml());
			infoContent.setCreateDate(DateTimeUtils.currentDate());
			infoContent.setCreateUser(user.getName());
			infoContent.setUuid(KeyGenUtils.newUuid());
			infoContentService.insertRecord(infoContent);
			res.setMessage("新增资讯成功");
		}else{
			res.setMessage("编辑资讯成功");
			info.setId(getInfoIdByUuid(vo.getUuid()));
			//先删除之前 附件
			List<InfoAttachment> list = infoAttachmentService.findListByInfoId(info.getId());
			if(!CollectionUtils.isEmpty(list)){
				for(InfoAttachment item : list){
					infoAttachmentService.deleteInfoAttachment(item);
				}
			}
			infoMapper.updateRecordById(info);
			res.setCode(vo.getUuid());
			
			//内容
			InfoContent infoContent = infoContentService.findByInfoId(info.getId());
			infoContent.setTex(vo.getContent());
			infoContent.setTexHtml(vo.getContentWithHtml());
			infoContent.setUpdateDate(DateTimeUtils.currentDate());
			infoContent.setUpdateUser(user.getName());
			infoContent.setUuid(KeyGenUtils.newUuid());
		    infoContentService.updateRecordById(infoContent);
		    res.setMessage("编辑资讯成功");
		}
		//附件
		List<InfoAttachment> attachments =  vo.getAttachments();
		if(!CollectionUtils.isEmpty(attachments)){
			for(int i = 0; i < attachments.size(); i ++){
				InfoAttachment item = attachments.get(i);
				if(item != null){
					item.setUuid(KeyGenUtils.newUuid());
					item.setCreateUser(user.getName());
					item.setCreateDate(DateTimeUtils.currentDate());
					item.setPriority(i+1);
					item.setCreateUser(user.getName());
					item.setAliveFlag(Constants.ALIEVE_FLAG);
					item.setInfoId(info.getId());
					infoAttachmentService.insertRecord(item);
				}
			}
		}
		return res;
	}
	
	/**
	 * 资讯预览
	 */
	@Override
	public InfoVO findPreviewInfo(String uuid) {
		log.info("资讯预览  ---> " + uuid);
		Info info = infoMapper.findByUuid(uuid);
		InfoVO vo = new InfoVO();
		if(info != null){
			vo.setTitle(info.getTitle());
			vo.setUuid(vo.getUuid());
			InfoContent content = infoContentService.findByInfoId(info.getId());
			vo.setContent(content.getTex());
			vo.setContentWithHtml(content.getTexHtml());
			ChannelSub channel = channelSubService.findByPrimaryKey(info.getChannelId());
			vo.setChannelName(channel.getChannelName());
			vo.setReleaseDate(DateTimeUtils.toDateTimeString(info.getReleaseDate()));
			vo.setReleaseId(info.getReleaseId());
			vo.setIsShowCopyright(info.getIsShowCopyright());
			vo.setIsShowDisclaimer(info.getIsShowDisclaimer());
			return vo;
		}
		return null;
	}
	
	/**
	 * 显示模式：自动 - 提取图片url 转为 附件
	 * @return
	 */
	public  List<InfoAttachment> getImgSrcFromContentWithHtml(String htmlStr) {  
        String img = "";  
        Pattern p_image;  
        Matcher m_image;  
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";  
        List<InfoAttachment> pics = new ArrayList<InfoAttachment>();
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);  
        m_image = p_image.matcher(htmlStr);  
        
        while (m_image.find()) {  
            img = img + "," + m_image.group();  
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);  
            while (m.find()) {  
            	InfoAttachment infoAttachment = new InfoAttachment();
            	infoAttachment.setAttachmentPath(m.group(1));
            	pics.add(infoAttachment);  
            	if(pics.size() == 3){
            		break ;
            	}
            }  
            
        }  
        return pics;  
    }
	/**
	 * 编辑资讯
	 */
	@Override
	public InfoVO findInfoToEdit(String uuid) {
		log.info("编辑资讯  ---> " + uuid);
		Info info = infoMapper.findByUuid(uuid);
		InfoVO vo = new InfoVO();
		if(info != null){
			InfoContent content = infoContentService.findByInfoId(info.getId());
			ChannelSub channel = channelSubService.findByPrimaryKey(info.getChannelId());
			List<InfoAttachment> attachments = infoAttachmentService.findListByInfoId(info.getId());
			vo.setTitle(info.getTitle());
			vo.setContent(content.getTex());
			vo.setContentWithHtml(content.getTexHtml());
			vo.setChannelName(channel.getChannelName());
			vo.setReleaseDate(DateTimeUtils.toDateTimeString(info.getReleaseDate()));
			vo.setReleaseDate1(info.getReleaseDate());
			vo.setReleaseId(info.getReleaseId());
			vo.setAttachments(attachments);
			vo.setIsShowCopyright(info.getIsShowCopyright());
			vo.setIsShowOrigin(info.getIsShowOrigin());
			vo.setIsShowGeneral(info.getIsShowGeneral());
			vo.setIsShowDisclaimer(info.getIsShowDisclaimer());
			vo.setTagList(info.getTagList());
			vo.setOrigin(info.getOrigin());
			vo.setDisplayMode(info.getDisplayMode());
			vo.setGeneral(info.getGeneral());
			if(channel != null){
				vo.setChannelUUId(channel.getUuid());
				ChannelM channelM = channelMService.findByPrimaryKey(channel.getChannelMId());
				vo.setChannelMUUId(channelM.getUuid());
			}
			return vo;
		}
		return null;
	}

	/**
	 * 查询未发布资讯列表
	 */
	@Override
	public List<Map<String, Object>> ListUnreleased(Map<String, Object> map) {
		return infoMapper.ListUnreleased(map);
	}

	/**
	 * 资讯置顶
	 */
	@Override
	public boolean settingInfoTopByUUId(Info info) {
		log.info("资讯置顶 --- " + info);
		Info stick = infoMapper.findStickInfo(info.getChannelId());
		if(stick != null && !stick.getUuid().equals(info.getUuid())){
			stick.setStick("0");
			infoMapper.settingInfoTopByUUId(stick);
		}
		if("1".equals(info.getStick())){
			info.setStick("0");
		}else{
			info.setStick("1");
		}
		return infoMapper.settingInfoTopByUUId(info) == 1;
	} 
	
	/**
	 * 精选或取消精选
	 */
	public  boolean selectedByUuid(Info info) {
		if("20".equals(info.getInformationType())) {
			info.setInformationType("10"); //设置普通文章
		}else {
			info.setInformationType("20"); //设置精选文章
		}
		return this.updateRecordById(info) == 1;
	}
	
	/**
	 * 根据uuid查询对象(包括栏目内容)
	 */
	@Override
	public Map<String, Object> selectByUuid(String uuid) {
		return  infoMapper.selectByUuid(uuid);
	}
	
	@Override
	public Long getIdByUuid(String uuid){
		return infoMapper.findByUuid(uuid).getId();
	}
	
	/**
	 * 查询前台列表-不分页
	 */
	@Override
	public List<Map<String, Object>> getFrontList(Map<String, Object> map){
		return infoMapper.getFrontList(map);
	}
	
	/**
	 * 查询前台列表-不分页
	 */
	@Override
	public Page<Map<String, Object>> getFrontListPage(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) infoMapper.queryRecords(map);
	}
	
	/**
	 * 查询前一篇
	 */
	@Override
	public Map<String, Object> getPreInfo(Map map){
		return infoMapper.getPreInfo(map);
	}
	
	/**
	 * 查询后一篇
	 */
	@Override
	public Map<String, Object> getNextInfo(Map map){
		return infoMapper.getNextInfo(map);
	}
	
	/**
	 * 测试取数据
	 */
	public void getRemoteData(){
		String sql = "SELECT a.CRUDE_NAME_C, a.CRUDE_NAME_E, t.PRICING, t.PRICING_DATE FROM om_futures_price_his t,"
				+ " sys_crude a WHERE t.CRUDE_ID = a.ID AND a.IS_PRIC_BASIS = 1 ORDER BY t.PRICING_DATE DESC";
		Map<Integer, Object> conditionMap = new HashMap<>();
		//conditionMap.put(1, 5);
		//conditionMap.put(2, "e");
		List<Map<String, Object>> resultList = dBUtil.queryAll(sql,conditionMap);
		log.info(resultList);
	}
	
	public int updateBrowseCount(Long infoId){
		return infoMapper.updateBrowseCount(infoId);
	}
	/**
	 * 发布资讯
	 */
	@Override
	public boolean releaseInfo(Long id) {
		Info info = new Info();
		info.setId(id);
		info.setStatus("30"); //30 已发布
		return this.updateRecordById(info) == 1;
	}

	/**
	 * 删除资讯
	 */
	@Override
	public boolean delete(Info info) {
		info.setAliveFlag("0");
		if (info.getId() != null) {// 存在专栏id
			ColColumn colColumn = colColumnMapper.findColumnByInfoId(info.getId());
			if (colColumn != null) {// 存在专栏
				ColColumn column = new ColColumn();
				column.setId(colColumn.getId());
				column.setAliveFlag(Constants.ALIEVE_FLAG);
				if(colColumn.getArticleCount() >= 1){
					column.setArticleCount(colColumn.getArticleCount()-1);
				}
				colColumnImmtMapper.updateRecord(column);
			}
		}
		return this.updateRecordById(info) == 1;
	}

	@Override
	public Integer findMaxInfoGrabId(String typ) {
		return infoMapper.findMaxInfoGrabId(typ);
	}
	
	/**
	 * 根据uuid查找info id
	 * @param uuid
	 * @return
	 */
	private Long getInfoIdByUuid(String uuid) {
		return infoMapper.findByUuid(uuid).getId();
	}

	@Override
	public List<Map<String, Object>> findByBizId(Info info) {
		return infoMapper.findByBizId(info);
	}

	@Override
	public Page<Map<String, Object>> queryInfoListByBizId(Map<String, Object> beanToMap, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) infoMapper.queryInfoListByBizId(beanToMap);
	}
	
}

