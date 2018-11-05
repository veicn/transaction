package com.sinochem.crude.trade.operation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.commons.ComConstants;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.operation.dao.MemMenuSmemMapper;
import com.sinochem.crude.trade.operation.domain.MemMenuSmem;
import com.sinochem.crude.trade.operation.service.MemMemberSmemService;
import com.sinochem.crude.trade.operation.vo.AppMenuOut;
import com.sinochem.crude.trade.operation.vo.MemberMenu;
import com.sinochem.crude.trade.operation.vo.MemberMenuSave;
import com.sinochem.crude.trade.operation.vo.MenuOutPut;
import com.sinochem.crude.trade.operation.vo.MenuRank;

@Service
public class MemMemberSmemServiceImpl implements MemMemberSmemService {

	private static Log log = LogFactory.getLog(MemMemberSmemServiceImpl.class);

	@Autowired
	private MemMenuSmemMapper memMenuSmemMapper;

	/**
	 * 将用户的关注数据保存到MongoDB中和MySQL中
	 *
	 * @param memberMenuSave
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveAttentionToMySqlAndMongonDB(CurrentUser user, MemberMenuSave memberMenuSave) {

		log.info("Start saveAttention:" + memberMenuSave.getEmpId());
		String empId = "";
		if (user == null) {
			empId = memberMenuSave.getEmpId();
		} else {
			empId = ObjectUtils.toString(user.getMemberId());
		}
		//menuIds 传递的未删除 1.删除会员状态为10； 2.根据会员获取自定义40; 3.menuIds中有menuId在自定义的则
		//删除行业推荐
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("empId", memberMenuSave.getEmpId());
		map.put("menuType", ComConstants.MENU_TYPE_10);
		memMenuSmemMapper.deleteTradeRecommend(map);
		
		//删除热门推荐
		/*map.put("isPersonal", memberMenu.getIsPersonal());
		memMenuSmemMapper.deleteHotRecommend(map);*/

		// 清除用户的关注设置
		Map<String, Object> dmap = new HashMap<String, Object>();
		dmap.put("empId", memberMenuSave.getEmpId());
		//dmap.put("marketId", ComConstants.MENU_DEFINE_FLAG);
		memMenuSmemMapper.updateRecordsDefine(dmap);
		
		List<String> menuIds = memberMenuSave.getMenuIds();
		if (menuIds != null && !menuIds.isEmpty()) {
			Integer i =1;
			List<MenuRank> rankList = new ArrayList<MenuRank>();			
			for (String menuId : menuIds) {
				if(StringUtils.isNotBlank(menuId)){
					MenuRank rank = new MenuRank();
					rank.setSort(i++);
					rank.setMenuId(menuId);
					rankList.add(rank);
				}
			}
			
			for (MenuRank rank : rankList) {
				saveSubscribe(ObjectUtils.toString(memberMenuSave.getEmpId()), empId, rank.getMenuId(), rank.getSort());
			}
		}
		log.info("End saveAttention:"+memberMenuSave.getEmpId());
	

		Boolean mysqlResult = null;
		return mysqlResult;
	}
	
	private void saveSubscribe(String empId, String operateId, String menuId, Integer sort) {
    	if(StringUtils.isNotBlank(menuId)){
    		MemMenuSmem entity = new MemMenuSmem();
    		
    		entity.setId(KeyGenUtils.newUuid());
    		entity.setMenuId(menuId);
    		entity.setEmpId(empId);
    		entity.setSort(sort);
    		entity.setMenuType(ComConstants.MENU_TYPE_10);
    		entity.setCreateUser(operateId);
    		entity.setUpdateUser(entity.getCreateUser());
    		entity.setCreateDate(DateTimeUtils.currentDate());
    		entity.setUpdateDate(DateTimeUtils.currentDate());
    		
    		memMenuSmemMapper.insertRecord(entity);
    	}
		
	}

	/**
	 * 登录用户查询关注菜单
	 *
	 * @param memberMenu 用户id、是否是个人
	 * @return 用户关注菜单
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<AppMenuOut> queryMenusAll(MemberMenu memberMenu, CurrentUser user) {

		List<AppMenuOut> amo = new ArrayList<AppMenuOut>();		
		// 关注
		AppMenuOut mo10 = new AppMenuOut();
		// 热门推荐
		AppMenuOut mo20 = new AppMenuOut();
		mo10.setMenuType(ComConstants.MENU_TYPE_10);
		mo10.setTradeName(ComConstants.MENU_DES_10);
		mo20.setMenuType(ComConstants.MENU_TYPE_20);
		mo20.setTradeName(ComConstants.MENU_DES_20);
		List<MenuOutPut> mop1020 = new ArrayList<MenuOutPut>();
		
		List<String> moi1020 = Lists.transform(mop1020, new Function<MenuOutPut, String>() {
			@Override
			public String apply(MenuOutPut input) {
				// TODO Auto-generated method stub
				return input.getMenuId();
			}
		});

		// 如果用户的ID为空，只查询所有的非会员相关菜单（热门推荐、行业推荐、固定菜单）
		if(memberMenu.getEmpId() != null) {
			//查询用户关注菜单 和 自定义菜单
			searchPersonalMenus(ObjectUtils.toString(memberMenu.getEmpId()), mop1020);
		}
		mo10.setMenuOutPut(mop1020);
		amo.add(mo10);
		List<MenuOutPut> mop12 = new ArrayList<MenuOutPut>();

		//查询热门推荐
		List<String> list = new ArrayList<String>();
		list.add(ComConstants.MENU_TYPE_20);
		List<MenuOutPut> mol2030 = memMenuSmemMapper.queryPublicMenu(list, "");
		if (mol2030 != null && !mol2030.isEmpty()) {
			for (MenuOutPut menuOutPut : mol2030) {
				if (!moi1020.contains(menuOutPut.getMenuId())) {
					menuOutPut.setMenuType(ComConstants.MENU_TYPE_20);
					mop12.add(menuOutPut);
				}
			}
			mo20.setMenuOutPut(mop12);
		}

		amo.add(mo20);

		return amo;
	}
	
	/**
	 * 查询用户关注菜单 和 自定义菜单
	 *
	 * @param empId 会员ID
	 * @param mop1020
	 */
	private void searchPersonalMenus(String empId, List<MenuOutPut> mop1020) {
		// 查询用户关注菜单
		List<MenuOutPut> mol1040 = memMenuSmemMapper.queryPersonMenu(empId, "");
		if (mol1040 != null && !mol1040.isEmpty()) {
			for (MenuOutPut menuOutPut : mol1040) {
				mop1020.add(menuOutPut);
			}
		} else {
			//查询优先推荐
			List<String> list = new ArrayList<String>();
			list.add(ComConstants.MENU_TYPE_20);
			List<MenuOutPut> mol2030 = memMenuSmemMapper.queryPublicMenu(list, "1");
			for (MenuOutPut menuOutPut : mol2030) {
				mop1020.add(menuOutPut);
			}
		}
	}

}
