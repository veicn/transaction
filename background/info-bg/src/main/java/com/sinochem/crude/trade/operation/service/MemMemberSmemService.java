package com.sinochem.crude.trade.operation.service;

import java.util.List;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.operation.vo.AppMenuOut;
import com.sinochem.crude.trade.operation.vo.MemberMenu;
import com.sinochem.crude.trade.operation.vo.MemberMenuSave;

public interface MemMemberSmemService {

    /**
     * 保存用户关注到MySQL和MongoDB
     *
     * @param memberMenuSave
     * @return
     */
    public Boolean saveAttentionToMySqlAndMongonDB(CurrentUser user, MemberMenuSave memberMenuSave);

	/**
	 * 获取个人所有菜单
	 * @param memberMenu
	 * @return
	 */
	public List<AppMenuOut> queryMenusAll(MemberMenu memberMenu, CurrentUser user);

}
