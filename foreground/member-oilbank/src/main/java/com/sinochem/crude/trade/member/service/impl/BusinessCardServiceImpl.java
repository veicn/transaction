package com.sinochem.crude.trade.member.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.dao.BusinessCardApplyMapper;
import com.sinochem.crude.trade.member.dao.BusinessCardCollectMapper;
import com.sinochem.crude.trade.member.dao.BusinessCardsMapper;
import com.sinochem.crude.trade.member.domain.BusinessCardApply;
import com.sinochem.crude.trade.member.domain.BusinessCardCollect;
import com.sinochem.crude.trade.member.domain.BusinessCards;
import com.sinochem.crude.trade.member.domain.query.BusinessCardApplyQuery;
import com.sinochem.crude.trade.member.domain.query.BusinessCardQuery;
import com.sinochem.crude.trade.member.model.BusinessCardApplyStatusEnum;
import com.sinochem.crude.trade.member.service.BusinessCardService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Created by AlterEgo on 2018/5/4.
 */
@Service
public class BusinessCardServiceImpl implements BusinessCardService {

    @Autowired
    private BusinessCardsMapper businessCardsMapper;

    @Autowired
    private BusinessCardApplyMapper businessCardApplyMapper;

    @Autowired
    private BusinessCardCollectMapper businessCardCollectMapper;

    @Override
    public BusinessCards getInfoByMemberId(Long memberId) {
        if(memberId==null){
            return null;
        }
        BusinessCards param = new BusinessCards();
        param.setMemberId(memberId);
        List<BusinessCards> list = businessCardsMapper.selectByEntity(param);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else {
            return list.get(0);
        }
    }

    @Override
    public void updateBusinessCard(Long memberId, BusinessCards businessCard) throws BizException {
        // TODO 错误提示中英文为实现
        if(memberId==null){
            throw new BizException("会员id不能为空！");
        }
        if(businessCard==null){
            throw new BizException("名片信息不能为空！");
        }
        BusinessCards businessCardOld = this.getInfoByMemberId(memberId);
        if(businessCardOld==null){
            //不存在名片，新增名片
            businessCard.setMemberId(memberId);
            businessCard.setDelFlg(false);
            businessCard.setCreateTime(new Date());
            businessCard.setUpdateTime(new Date());
            businessCard.setUpdater(memberId);
            businessCard.setCreater(memberId);
            businessCardsMapper.insertSelective(businessCard);
        }else {
            //名片存在，修改名片
            businessCardOld.setNickName(businessCard.getNickName());
            businessCardOld.setNickNameEn(businessCard.getNickNameEn());
            businessCardOld.setOrgName(businessCard.getOrgName());
            businessCardOld.setOrgNameEn(businessCard.getOrgNameEn());
            businessCardOld.setProfessionalName(businessCard.getProfessionalName());
            businessCardOld.setProfessionalNameEn(businessCard.getProfessionalNameEn());
            businessCardOld.setPhone(businessCard.getPhone());
            businessCardOld.setEmail(businessCard.getEmail());
            businessCardOld.setUpdater(memberId);
            businessCardOld.setUpdateTime(new Date());
            businessCardsMapper.updateByPrimaryKeySelective(businessCardOld);
        }
    }

    @Transactional
    @Override
    public void sendBusinessCard(Long memberId, Long applyMemberId) throws BizException {
        // TODO 错误提示中英文为实现
        BusinessCards businessCard = this.getInfoByMemberId(memberId);
        BusinessCards businessCardApply = this.getInfoByMemberId(applyMemberId);
        if(businessCard==null){
            throw new BizException("未找到自己的名片！");
        }
        if(businessCardApply==null){
            throw new BizException("未找到对方的名片！");
        }
        BusinessCardApply businessCardApplyParam = new BusinessCardApply();
        businessCardApplyParam.setMemberId(memberId);
        businessCardApplyParam.setApplyMemberId(applyMemberId);
//        businessCardApplyParam.setApplyStatus(String.valueOf(BusinessCardApplyStatusEnum.APPLYING.getCode()));
        List<BusinessCardApply> list = businessCardApplyMapper.selectByEntity(businessCardApplyParam);
        if(!CollectionUtils.isEmpty(list)){
            BusinessCardApply businessCardApply1 = list.get(0);
            if(String.valueOf(BusinessCardApplyStatusEnum.APPLYING.getCode()).equals(businessCardApply1.getApplyStatus())){
//                throw new BizException("请勿重新发送申请");
                return;
            }else if(String.valueOf(BusinessCardApplyStatusEnum.AGREE.getCode()).equals(businessCardApply1.getApplyStatus())){
                throw new BizException("已收藏对方名片！");
            }
        }
        BusinessCardApply businessCardApplyNew = new BusinessCardApply();
        businessCardApplyNew.setMemberId(memberId);
        businessCardApplyNew.setApplyMemberId(applyMemberId);
        businessCardApplyNew.setCreater(memberId);
        businessCardApplyNew.setCreateTime(new Date());
        businessCardApplyNew.setUpdater(memberId);
        businessCardApplyNew.setUpdateTime(new Date());
        businessCardApplyMapper.insertSelective(businessCardApplyNew);
        //收藏对方名片
//        BusinessCardCollect businessCardCollect = new BusinessCardCollect();
//        businessCardCollect.setMemberId(memberId);
//        businessCardCollect.setCollectMemberId(applyMemberId);
//        businessCardCollect.setCreateTime(new Date());
//        businessCardCollect.setCreater(memberId);
//        businessCardCollect.setUpdater(memberId);
//        businessCardCollect.setUpdateTime(new Date());
//        businessCardCollectMapper.insertSelective(businessCardCollect);
    }

    @Override
    public List<BusinessCardApplyQuery> getBusinessCardApplyByMemberId(Long memberId,String applyStatus) {
        return businessCardApplyMapper.getBusinessCardApplyByMemberId(memberId,applyStatus);
    }

    @Override
    public List<BusinessCardApplyQuery> getBusinessCardApplyByApplyMemberId(Long applyMemberId,String applyStatus) {
        return businessCardApplyMapper.getBusinessCardApplyByApplyMemberId(applyMemberId,applyStatus);
    }

    @Transactional
    @Override
    public void BusinessCardApplyConfirm(Long memberId, Long sendApplyMemberId, String applyStatus) throws BizException {
        if(StringUtil.isEmpty(applyStatus)||!(applyStatus.equals("1")||applyStatus.equals("2"))){
            throw new BizException("申请状态只能是“1”或“2”");
        }
        //是否需要验证两个会员id的正确性？

        BusinessCardApply businessCardApplyParam = new BusinessCardApply();
        businessCardApplyParam.setMemberId(sendApplyMemberId);//发送名片申请的会员id
        businessCardApplyParam.setApplyMemberId(memberId);//被申请的会员id
        businessCardApplyParam.setApplyStatus(String.valueOf(BusinessCardApplyStatusEnum.APPLYING.getCode()));
        List<BusinessCardApply> businessCardApplyList = businessCardApplyMapper.selectByEntity(businessCardApplyParam);
        if(CollectionUtils.isEmpty(businessCardApplyList)){
            throw new BizException("未找到申请名片记录！");
        }
        // “同意”还是“忽略”，被“申请人”都要保存对方的名片
        this.collectBusinessCard(memberId,sendApplyMemberId);
        if(applyStatus.equals("1")){
            //同意
            this.collectBusinessCard(sendApplyMemberId,memberId);
        }
        //修改状态
        BusinessCardApply businessCardApply = businessCardApplyList.get(0);
        businessCardApply.setApplyStatus(applyStatus);
        businessCardApply.setUpdater(memberId);
        businessCardApply.setUpdateTime(new Date());
        businessCardApplyMapper.updateByPrimaryKeySelective(businessCardApply);
    }

    /**
     * 收藏（保存）名片
     * @param memberId 会员id
     * @param collectMemberId 被收藏的会员id
     */
    private void collectBusinessCard(Long memberId,Long collectMemberId){
        BusinessCardCollect businessCardCollectParam = new BusinessCardCollect();
        businessCardCollectParam.setMemberId(memberId);
        businessCardCollectParam.setCollectMemberId(collectMemberId);
        List<BusinessCardCollect> businessCardCollectList = businessCardCollectMapper.selectByEntity(businessCardCollectParam);
        //是否已收藏
        if(CollectionUtils.isEmpty(businessCardCollectList)){
            //保存对方的名片
            BusinessCardCollect businessCardCollect = new BusinessCardCollect();
            businessCardCollect.setMemberId(memberId);
            businessCardCollect.setCollectMemberId(collectMemberId);
            businessCardCollect.setCreateTime(new Date());
            businessCardCollect.setCreater(memberId);
            businessCardCollect.setUpdateTime(new Date());
            businessCardCollect.setUpdater(memberId);
            businessCardCollectMapper.insertSelective(businessCardCollect);
        }
    }

    /**
     * 删除收藏的名片
     * @param memberId 会员id
     * @param collectMemberId 被收藏的会员id
     */
    public void deleteCollectBusinessCard(Long memberId,Long collectMemberId){
        BusinessCardCollect businessCardCollectParam = new BusinessCardCollect();
        businessCardCollectParam.setMemberId(memberId);
        businessCardCollectParam.setCollectMemberId(collectMemberId);
        List<BusinessCardCollect> businessCardCollectList = businessCardCollectMapper.selectByEntity(businessCardCollectParam);
        if(businessCardCollectList != null && businessCardCollectList.size() > 0){
            businessCardCollectMapper.deleteByPrimaryKey(businessCardCollectList.get(0).getId());
        }
    }

    @Override
    public List<BusinessCards> getBusinessCardByQuery(BusinessCardQuery queryParam) {
        return businessCardsMapper.getBusinessCardByQuery(queryParam);
    }


}
