package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TCommonAttachments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCommonAttachmentsMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByBusinessUuid(@Param("businessUuid")String businessUuid,@Param("fileType")String fileType);

    int insert(TCommonAttachments record);

    int insertList(List<TCommonAttachments> list);

    int insertSelective(TCommonAttachments record);

    TCommonAttachments selectByPrimaryKey(Long id);

    List<TCommonAttachments> selectByBusinessUuid(String businessUuid);

    int updateByPrimaryKeySelective(TCommonAttachments record);

    int updateByPrimaryKey(TCommonAttachments record);

    List<TCommonAttachments> selectByParameter(TCommonAttachments attachments);

    List<TCommonAttachments> selectByBusinessId(String id);

    String[] selectTradeAnnex(String deal_no);
}