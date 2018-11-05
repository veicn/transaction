package com.sinochem.crude.trade.listed.service;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.listed.domain.TradingChain;
import com.sinochem.crude.trade.listed.model.form.TradeChainForm;
import com.sinochem.crude.trade.listed.model.query.TradeChainQuery;
import com.sinochem.crude.trade.listed.model.vo.tradingChainVo.EnterpriseNameByTradingChainVO;
import com.sinochem.crude.trade.listed.model.vo.tradingChainVo.TradingChainDetailsVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;

import java.util.List;

/**
 * 贸易链service层
 * Made By WangTing
 */
public interface TradingChainService {

    /**
     * 根据uuid获取贸易链
     */
    public TradingChain selectByUuid(CurrentUser user, String uuid);

    /**
     * 贸易链保存
     *
     */
    public TradingChain saveTradingChain(CurrentUser user, TradeChainForm tradeChainForm) throws BizException;

    /**
     * 贸易链修改
     */
    public TradingChain updateTradingChain(CurrentUser user, TradeChainForm tradeChainForm,String uuid) throws BizException;


    /**
     *贸易链状态改变(提交、撤回、确认、驳回)
     */
    public void alterTradingChainStatus(CurrentUser user,String uuid,Integer status) throws BizException;

    /**
     *通过条件查询贸易链列表(分页)
     */
    public PageInfoResult getTradingChainList(CurrentUser user, TradeChainQuery tradeChainQuery, PageInfo pageInfo) throws BizException;


    /**
     *获取贸易链详情
     */
    public TradingChainDetailsVO getTradingChainDetails(CurrentUser user, String uuid) throws BizException;

    /**
     * 根据当前登录用户查询出所有与该用户参与贸易链的企业名称
     */
    public List<EnterpriseNameByTradingChainVO> selectEnterpriseNameByTradingChain(CurrentUser user) throws BizException;


    /**
     * 贸易链确认
     */
    public void confirmTradingChain(CurrentUser user,String uuid,String reason) throws BizException;

    /**
     * 贸易链驳回
     */
    public void rejectTradingChain(CurrentUser user,String uuid,String reason) throws BizException;

    /**
     * 通过demandId获取贸易链
     */
    public List<TradingChain> selectByDemandId (Long demandId) throws BizException;


}
