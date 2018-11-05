package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.BusinessCardApply;
import com.sinochem.crude.trade.member.domain.query.BusinessCardApplyQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessCardApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessCardApply record);

    int insertSelective(BusinessCardApply record);

    BusinessCardApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessCardApply record);

    int updateByPrimaryKey(BusinessCardApply record);

    /**
     * 根据实体查询
     * @param entity
     * @return
     */
    List<BusinessCardApply> selectByEntity(BusinessCardApply entity);

    List<BusinessCardApplyQuery> getBusinessCardApplyByMemberId(@Param("memberId") Long memberId,@Param("applyStatus") String applyStatus);

    List<BusinessCardApplyQuery> getBusinessCardApplyByApplyMemberId(@Param("applyMemberId") Long applyMemberId,@Param("applyStatus") String applyStatus);
}