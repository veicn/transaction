package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.ColColumnMapper;
import com.sinochem.crude.trade.info.dao.ColSubscribeMapper;
import com.sinochem.crude.trade.info.dao.CollectionMapper;
import com.sinochem.crude.trade.info.dao.FabulousMapper;
import com.sinochem.crude.trade.info.dao.InfoContentMapper;
import com.sinochem.crude.trade.info.dao.InfoMapper;
import com.sinochem.crude.trade.info.dao.immt.ColColumnImmtMapper;
import com.sinochem.crude.trade.info.domain.ColColumn;
import com.sinochem.crude.trade.info.domain.ColSubscribe;
import com.sinochem.crude.trade.info.domain.Collection;
import com.sinochem.crude.trade.info.domain.Fabulous;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.domain.InfoContent;
import com.sinochem.crude.trade.info.model.ColSubscribeVO;
import com.sinochem.crude.trade.info.model.ColumnDetailVO;
import com.sinochem.crude.trade.info.model.ColumnVO;
import com.sinochem.crude.trade.info.model.FabulousVO;
import com.sinochem.crude.trade.info.service.ColColumnService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class ColColumnServiceImpl implements ColColumnService {
	@Autowired
	private ColColumnMapper colColumnMapper;
	
	@Autowired
	private ColColumnImmtMapper colColumnImmtMapper;
	
	@Autowired
	private InfoMapper infoMapper;
	
	@Autowired
	private InfoContentMapper contentMapper;
	
	@Autowired
	private FabulousMapper fabulousMapper;
	
	@Autowired
	private CollectionMapper collectionMapper;
	
	@Autowired
	private ColSubscribeMapper colSubscribeMapper;
	
	public ColColumnMapper getMapper(){
		return colColumnMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<ColColumn> queryByEntitys(ColColumn colcolumn){
		return colColumnImmtMapper.queryByEntitys(colcolumn);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public ColColumn findByPrimaryKey(String id){
		return colColumnImmtMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(ColColumn colcolumn) throws Exception {
		if ( colcolumn.getId() == null  ) {
			throw new Exception("id 为空，不能修改 ");
		}
		colColumnImmtMapper.updateRecord(colcolumn);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(String id  , String deleteUser) throws Exception {
		if (  id == null ) {
			throw new Exception("id 为空，不能删除 ");
		}
		colColumnImmtMapper.deleteRecordByKey(id , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(ColColumn colcolumn,CurrentUser user){
		colcolumn.setId(KeyGenUtils.newUuid());
		colcolumn.setAliveFlag(Constants.ALIEVE_FLAG);
		colcolumn.setCreateDate(DateTimeUtils.currentDate());
		colcolumn.setCreateUser(user.getMemberId()+"");
		colColumnImmtMapper.insertRecord(colcolumn);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return colColumnMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) colColumnMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return colColumnMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map) throws Exception {
		colColumnMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) throws Exception {
		colColumnMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 新增栏目文章
	 * 
	 */
	@Override
	public Result addColumnInfo(ColumnVO vo, CurrentUser user) {
		Result result = new Result();
		result.setMessage("新增专栏文章成功");
		
		ColColumn colColumn = colColumnImmtMapper.findByPrimaryKey(vo.getBizId());
		if(colColumn == null){
			result.setFail("未找到对应的专栏");
			return result;
		}
		Info info = new Info();
		info.setTitle(vo.getTitle());
		info.setBizId(vo.getBizId());
		info.setExtend1(vo.getExtend1());
		info.setAuthor(user.getName());
		info.setReleaseDate(DateTimeUtils.currentDate());
		info.setStatus(Constants.INFO_ARTICLE_STATUS_YFB);
		info.setArticleType(Constants.ARTICLE_TYPE_ZL);
		info.setAliveFlag(Constants.ALIEVE_FLAG);
		info.setCreateDate(DateTimeUtils.currentDate());
		info.setUpdateDate(DateTimeUtils.currentDate());
		info.setCreateUser(user.getName());
		info.setUpdateUser(user.getName());
		
		if(StringUtils.isBlank(vo.getUuid())){
			info.setUuid(KeyGenUtils.newUuid());
			if(infoMapper.insertRecord(info) != 1){
				result.setFail("保存专栏文章失败");
				return result;
			}else{
				InfoContent content = new InfoContent();
				Info findByUuid = infoMapper.findByUuid(info.getUuid());
				if(findByUuid == null){
					result.setFail("文章不存在");
					return result;
				}else{
						content.setInfoId(findByUuid.getId());
						content.setUuid(KeyGenUtils.newUuid());
						content.setTex(vo.getTex());
						content.setTexHtml(vo.getTexHtml());
						content.setAliveFlag(Constants.ALIEVE_FLAG);
						content.setCreateDate(DateTimeUtils.currentDate());
						content.setUpdateDate(DateTimeUtils.currentDate());
						content.setCreateUser(user.getName());
						content.setUpdateUser(user.getName());   
						
						if(contentMapper.insertRecord(content) != 1){
							result.setFail("文章内容保存失败");
							return result;
						}else{
							if(colColumn.getArticleCount() == null){
								colColumn.setArticleCount(1);
							}else{
								colColumn.setArticleCount(colColumn.getArticleCount()+1);
							}
							colColumnImmtMapper.updateRecord(colColumn);
							result.setMessage("更新专栏文章成功");
							result.setCode(findByUuid.getUuid());
						}
					}
					
				}
		}else{
			Info byUuid = infoMapper.findByUuid(vo.getUuid());
			info.setId(byUuid.getId());
			info.setUuid(vo.getUuid());
			if(infoMapper.updateRecordById(info)!= 1){
				result.setFail("保存专栏文章失败");
				return result;
			}else{
				InfoContent content = new InfoContent();
				Info findByUuid = infoMapper.findByUuid(vo.getUuid());
				if(findByUuid == null){
					result.setFail("文章不存在");
					return result;
				}else{
						InfoContent infoContent = contentMapper.findByInfoId(byUuid.getId());
						content.setInfoId(byUuid.getId());
						content.setId(infoContent.getId());
						content.setUuid(infoContent.getUuid());
						content.setTex(vo.getTex());
						content.setTexHtml(vo.getTexHtml());
						content.setAliveFlag(Constants.ALIEVE_FLAG);
						content.setCreateDate(DateTimeUtils.currentDate());
						content.setUpdateDate(DateTimeUtils.currentDate());
						content.setCreateUser(user.getName());
						content.setUpdateUser(user.getName());   
						
						if(contentMapper.updateRecordById(content) != 1){
							result.setFail("文章内容保存失败");
							return result;
						}else{
							if(colColumn.getArticleCount() == null){
								colColumn.setArticleCount(1);
							}else{
								colColumn.setArticleCount(colColumn.getArticleCount()+1);
							}
							colColumnImmtMapper.updateRecord(colColumn);
							result.setMessage("更新专栏文章成功");
							result.setCode(byUuid.getUuid());
						}
					}
					
				}
		}
		
		return result;
	}

	/*文章详情查看*/
	@Override
	public Map<String, Object> getInfoByUuid(String infoUuid) {
		return infoMapper.getInfoByUuid(infoUuid);
	}
	
	/*保存预览专栏文章*/
	@Override
	public Result saveColumnInfo(ColumnVO vo, CurrentUser user) {
		Result result = new Result();
		result.setMessage("保存专栏文章成功");
		
		ColColumn colColumn = colColumnImmtMapper.findByPrimaryKey(vo.getBizId());
		if(colColumn == null){
			result.setFail("未找到对应的专栏");
			return result;
		}
		Info info = new Info();
		info.setTitle(vo.getTitle());
		info.setBizId(vo.getBizId());
		info.setExtend1(vo.getExtend1());
		info.setAuthor(user.getName());
		info.setReleaseDate(DateTimeUtils.currentDate());
		info.setStatus(Constants.INFO_ARTICLE_STATUS_DTJ);
		info.setArticleType(Constants.ARTICLE_TYPE_ZL);
		info.setAliveFlag(Constants.ALIEVE_FLAG);
		info.setCreateDate(DateTimeUtils.currentDate());
		info.setUpdateDate(DateTimeUtils.currentDate());
		info.setCreateUser(user.getName());
		info.setUpdateUser(user.getName());
		
		if(StringUtils.isEmpty(vo.getUuid())){
			info.setUuid(KeyGenUtils.newUuid());
			if(infoMapper.insertRecord(info) != 1){
				result.setFail("保存专栏文章失败");
				return result;
			}else{
				InfoContent content = new InfoContent();
				Info findByUuid = infoMapper.findByUuid(info.getUuid());
				if(findByUuid == null){
					result.setFail("文章不存在");
					return result;
				}else{
						content.setInfoId(findByUuid.getId());
						content.setUuid(KeyGenUtils.newUuid());
						content.setTex(vo.getTex());
						content.setTexHtml(vo.getTexHtml());
						content.setAliveFlag(Constants.ALIEVE_FLAG);
						content.setCreateDate(DateTimeUtils.currentDate());
						content.setUpdateDate(DateTimeUtils.currentDate());
						content.setCreateUser(user.getName());
						content.setUpdateUser(user.getName());   
						
						if(contentMapper.insertRecord(content) != 1){
							result.setFail("文章内容保存失败");
							return result;
						}else{
							if(colColumn.getArticleCount() == null){
								colColumn.setArticleCount(1);
							}else{
								colColumn.setArticleCount(colColumn.getArticleCount()+1);
							}
							colColumnImmtMapper.updateRecord(colColumn);
							result.setMessage("更新专栏文章成功");
							result.setCode(findByUuid.getUuid());
						}
					}
					
				}
			}else{
				Info byUuid = infoMapper.findByUuid(vo.getUuid());
				info.setId(byUuid.getId());
				info.setUuid(vo.getUuid());
				if(infoMapper.updateRecordById(info)!= 1){
					result.setFail("保存专栏文章失败");
					return result;
				}else{
					InfoContent content = new InfoContent();
					Info findByUuid = infoMapper.findByUuid(vo.getUuid());
					if(findByUuid == null){
						result.setFail("文章不存在");
						return result;
					}else{
							InfoContent infoContent = contentMapper.findByInfoId(byUuid.getId());
							content.setInfoId(byUuid.getId());
							content.setId(infoContent.getId());
							content.setUuid(infoContent.getUuid());
							content.setTex(vo.getTex());
							content.setTexHtml(vo.getTexHtml());
							content.setAliveFlag(Constants.ALIEVE_FLAG);
							content.setCreateDate(DateTimeUtils.currentDate());
							content.setUpdateDate(DateTimeUtils.currentDate());
							content.setCreateUser(user.getName());
							content.setUpdateUser(user.getName());   
							
							if(contentMapper.updateRecordById(content) != 1){
								result.setFail("文章内容保存失败");
								return result;
							}else{
								if(colColumn.getArticleCount() == null){
									colColumn.setArticleCount(1);
								}else{
									colColumn.setArticleCount(colColumn.getArticleCount()+1);
								}
								colColumnImmtMapper.updateRecord(colColumn);
								result.setMessage("更新专栏文章成功");
								result.setCode(byUuid.getUuid());
							}
						}
						
					}
			}
		
		return result;
	}
	
	/*热门文章列表*/
	@Override
	public List<Map<String, Object>> hotInfoList() {
		return infoMapper.hotInfoList();
	}
	
	/*更新浏览次数*/
	@Override
	public Integer updateInfo(ColumnVO columnVO) {
		Info info = new Info();
		info.setAliveFlag(Constants.ALIEVE_FLAG);
		if(columnVO.getBrowseCount() == null){
			info.setBrowseCount(1);
		}else{
			info.setBrowseCount(columnVO.getBrowseCount()+1);
		}
		info.setUuid(columnVO.getUuid());
		return infoMapper.updateInfo(BeanConvertUtils.beanToMap(info));
	}
	
	@Override
	public Page<Map<String, Object>> queryColumnList(Map<String, Object> beanToMap, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return colColumnMapper.queryColumnList(beanToMap);
	}

	@Override
	public ColumnDetailVO findById(Map<String, Object> map) {
		return colColumnMapper.findById(map);
	}

	@Override
	public void updateColumnById(String columnId,String type) {
		ColColumn data = colColumnImmtMapper.findByPrimaryKey(columnId);
		ColColumn colColumn = new ColColumn();
		if(StringUtils.equals(type, "1")){
			if(data.getSubscribeCount() == null){
				colColumn.setSubscribeCount(1);
			}else{
				colColumn.setSubscribeCount(data.getSubscribeCount()+1);
			}
		}else{
			if(data.getSubscribeCount() == null || data.getSubscribeCount()==0){
				colColumn.setSubscribeCount(0);
			}else{
				colColumn.setSubscribeCount(data.getSubscribeCount()-1);
			}
		}
		colColumn.setId(columnId);
		colColumnMapper.updateColumnSubscribeCount(BeanConvertUtils.beanToMap(colColumn));
	}
	
	@Override
	public List<ColSubscribe> findSubscribeById(String id) {
		return colSubscribeMapper.findByColId(id);
	}
	
	@Override
	public List<Fabulous> findFabulousByInfoId(FabulousVO fabulousVO) {
		return fabulousMapper.findFabulousByInfoId(fabulousVO);
	}
	
	@Override
	public List<Collection> findCollectionByInfoId(Collection collection) {
		return collectionMapper.findCollectionByInfoId(collection);
	}
	
	@Override
	public List<ColSubscribe> findSubscribeByColumnId(ColSubscribeVO colSubscribeVO) {
		return colSubscribeMapper.findSubscribeByColumnId(colSubscribeVO);
	}
	
}
