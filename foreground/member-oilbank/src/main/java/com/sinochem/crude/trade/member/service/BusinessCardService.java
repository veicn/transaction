package com.sinochem.crude.trade.member.service;

import com.sinochem.crude.trade.member.domain.BusinessCards;
import com.sinochem.crude.trade.member.domain.query.BusinessCardApplyQuery;
import com.sinochem.crude.trade.member.domain.query.BusinessCardQuery;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;

/**
 * Created by AlterEgo on 2018/5/4.
 */
public interface BusinessCardService {

    /**
     * 根据memberId获取名片信息
     * @param memberId
     * @return
     */
    BusinessCards getInfoByMemberId(Long memberId);

    /**
     * 编辑名片（不存在时，新增）
     * @param memberId
     * @param businessCard
     * @throws BizException
     */
    void updateBusinessCard(Long memberId, BusinessCards businessCard)throws BizException;

    /**
     * 发送名片申请
     * @param memberId
     * @param applyMemberId
     * @throws BizException
     */
    void sendBusinessCard(Long memberId, Long applyMemberId)throws BizException;

    /**
     *  根据申请人查询名片申请列表
     * @param memberId
     * @param applyStatus 申请状态
     * @return
     */
    List<BusinessCardApplyQuery> getBusinessCardApplyByMemberId(Long memberId,String applyStatus);

    /**
     * 根据被申请人查询名片申请列表
     * @param memberId
     * @param applyStatus 申请状态
     * @return
     */
    List<BusinessCardApplyQuery> getBusinessCardApplyByApplyMemberId(Long applyMemberId,String applyStatus);

    /**
     *  @param memberId 会员id
     * @param sendApplyMemberId 发送名片申请的会员id
     * @param applyStatus 申请状态（1：同意，2：忽略）
     */
    void BusinessCardApplyConfirm(Long memberId, Long sendApplyMemberId, String applyStatus)throws BizException;

    /**
     * 删除收藏
     * @param memberId
     * @param collectMemberId
     */
    void deleteCollectBusinessCard(Long memberId,Long collectMemberId);

    /**
     * 搜索名片列表
     * @param queryParam
     * @param queryParam.memberId ，传入该参数，只会搜索改会员id收藏（保存）的名片
     * @return
     */
    List<BusinessCards> getBusinessCardByQuery(BusinessCardQuery queryParam);

}
