package com.sinochem.crude.trade.listed.service;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.model.query.ResourceQuery;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.crude.trade.listed.model.vo.CrudeOilDemandQueryVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.DemandOrderReturnVO;
import com.sinochem.crude.trade.order.remote.UserVo;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;

import java.util.List;

/**
 * Created by sijliu on 15/11/2017.
 */
public interface DemandService {

    /**
     * 保存更新
     * @param record
     */
    public Long saveOrUpdateDemand(Demand record) throws BizException;

    /**
     * 按主键查询
     * @param id
     * @return
     */
    public Demand getDemandByKey(Long id);

    /**
     * 按编码uuid查询
     * @param uuid
     *
     * @return
     */
    public Demand getDemandByUuid(String uuid);

    /**
     * 按parent_id查询
     * @param parentId
     * @return
     */
    public List<Demand> getDemandsByParentId(Long parentId);

    /**
     * 按id查询
     * @param ids
     * @return
     */
    public List<Demand> getDemandsByIds(List<Long> ids);

    /**
     * modified by Yichen Zhao on 20180117
     * 确认实际中标
     * @param demandBiddingId
     * @param remindFlag
     * @param user
     * @return
     * @throws BizException
     */
    public String confirmActualBiddingAndRemind(Long demandBiddingId, String remindFlag, CurrentUser user) throws Exception;

    /**
     * added by Yichen Zhao on 20180117
     * 确认意向中标
     * @param demandId
     * @param user
     * @return 返回意向报价单所关联的需求单的ID
     * @throws BizException
     */
    public Long confirmIntentionBiddingAndRemind(Long demandId, CurrentUser user) throws BizException;

    /**
     * added by Yichen Zhao on 20180422
     * 根据demandId和UserVO直接生成订单
     */
    public DemandOrderReturnVO generateOrder(Long demandId, UserVo userVo) throws Exception;

    /**
     *
     * @param contrastIds
     * @return
     */
    public List<Demand> getDemandListByIds(String contrastIds);



    void copyProperties(Demand desc, Demand source);


    /**
     * 连表查询demand及相关信息
     * @param resourceQuery 查询条件
     * @param pageInfo 分页信息
     * @return
     */
    PageInfoResult queryDemandJoinTableByCondition(ResourceQuery resourceQuery, PageInfo pageInfo);
    
    /**
     * 连表查询demand及相关信息（无效的截止日期）
     * @param resourceQuery 查询条件
     * @param pageInfo 分页信息
     * @return
     */
    PageInfoResult queryInvalidDemandJoinTableByCondition(ResourceQuery resourceQuery, PageInfo pageInfo);

    /**
     * 连表查询demand 报价及相关信息
     * @param resourceQuery 查询条件
     * @param pageInfo 分页信息
     * @return
     */
    PageInfoResult queryDemandBiddingJoinTableByCondition(ResourceQuery resourceQuery, PageInfo pageInfo);

    /**
     * 获取前5条报价数据
     * @param resourceQuery
     * @return
     */
    List<DemandJoinResult> getTop5BiddingDatas(ResourceQuery resourceQuery);

    /**
     * 投标
     * @param demand    报价信息
     * @param oper  操作人
     * @param ship  运输信息
     * @param shipBerthIds 泊位id
     * @param buyer 投标方
     * @return
     */
    Long tender(Demand demand, Long oper, DemandShip ship, String shipBerthIds, DemandRelevanter buyer) throws Exception;



    /**
     *原油大厅信息条数
     * @return
     */
    Long getDemandCount();

    
    /**
     * 按原油对比数据项查询，按demandId主键查询
     * @param ids
     * @return
     */
    List<DemandJoinResult> getCrudeOilCompareByIds(List<Long> ids);

    /**
     * 查询统计我的需求单数量
     * @param resourceQuery
     * @return
     */
    Long countMyDemandNumByCondition(ResourceQuery resourceQuery);

    /**
     * 查询统计我的需求单数据或资源单数量
     * @param resourceQuery
     * @return
     */
    Long countMyDemandNumByUserEpId(ResourceQuery resourceQuery);

    /**
     * 根据条件搜索原油采购单
     * added by Yichen Zhao on 20180112
     * @param crudeOilDemandQueryVO
     * @param pageInfo
     * @return
     */
    PageInfoResult queryCrudeOilDemandByCondition(CrudeOilDemandQueryVO crudeOilDemandQueryVO, PageInfo pageInfo);



    /**
     * 获取需求信息 并验证当前用户是否有权限
     * by sijliu  20180122
     * @param demandId  需求id
     * @param epMemberId    当前登陆用户的企业memberid
     * @return
     */
    Demand getDemandByKeyAndCurrentUser(Long demandId, Long epMemberId) throws BizException;

    /**
     * 需求延长投标截止日期
     * @param demandId      需求
     * @param stopBidTime   投标截止日期
     * @return
     * @throws BizException
     */
    Result postpone(String demandId, String stopBidTime) throws BizException;
    
    /**
     * 判断当前报价单是否已结标
     * @param demandId 报价单id
     * @return 1已结标 0未结标
     */
    Integer checkStopBid(Long demandId) throws BizException;
}
