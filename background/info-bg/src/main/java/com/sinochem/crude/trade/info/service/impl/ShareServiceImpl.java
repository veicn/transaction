package com.sinochem.crude.trade.info.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.dao.ShareMapper;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.domain.Share;
import com.sinochem.crude.trade.info.model.ShareVO;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.info.service.ShareService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class ShareServiceImpl implements ShareService {
	@Autowired
	private ShareMapper shareMapper;
	@Autowired
	private InfoService infoService;
	
	public ShareMapper getMapper(){
		return shareMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Share share){
		share.setCreateUser(share.getCreateUser());
		share.setUpdateUser(share.getUpdateUser());
		share.setCreateDate(DateTimeUtils.currentDate());
		share.setUpdateDate(DateTimeUtils.currentDate());
		 return shareMapper.insertRecord(share);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return shareMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Share  record){
    	return shareMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Share share) throws BizException{
		if ( share.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return shareMapper.updateRecordById(share);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return shareMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Share share){
		return shareMapper.updateRecords(share.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Share findByPrimaryKey(Long id){
		return  shareMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Share findByUuid(String uuid){
		return  shareMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Share> queryByEntitys(Share share){
		return  shareMapper.queryByEntitys(share);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return shareMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) shareMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return shareMapper.countRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/

	/**
	 * 资讯分享
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Result shareInfo(String infoUUId, String sharePlatform, CurrentUser user) throws BizException {
		Result res = new Result();
		Share cmsShareIn = new Share();
		cmsShareIn.setUuid(KeyGenUtils.newUuid());
		Info info = infoService.findByUuid(infoUUId);
		cmsShareIn.setInformationId(info.getId());
		cmsShareIn.setSharePlatform(sharePlatform);
		cmsShareIn.setCreateUser(user.getName());
		cmsShareIn.setUpdateUser(user.getName());
		int r1 = this.insertRecord(cmsShareIn);

		//在资讯表中更新分享资讯数
		if (info.getShareCount()==null || info.getShareCount()==0) {
			info.setShareCount(1);
		}else{
			info.setShareCount(info.getShareCount() + 1);
		}
		int r2 = infoService.updateInfoCount(info,user);
		if(r1 +r2 == 0){
			res.setMessage("分享失败");
		}else {
			res.setMessage("分享成功");
		}
		return res;
	}

	/**
	 * 分享列表查询
	 */
	@Override
	public ResultDatas<List<ShareVO>> queryList(ShareVO vo) throws BizException {
		ResultDatas<List<ShareVO>> res = new ResultDatas<>();
		List<ShareVO> shareVOList = new ArrayList<>();
		Share share = new Share();
		BeanUtils.copyProperties(vo,share);
		List<Share> list = shareMapper.queryShareList(share);
		for (Share item : list){
			ShareVO vo1 = new ShareVO();
			BeanUtils.copyProperties(item,vo1);
			shareVOList.add(vo1);
		}
		res.setDatas(shareVOList);
		return res;
	}
}
