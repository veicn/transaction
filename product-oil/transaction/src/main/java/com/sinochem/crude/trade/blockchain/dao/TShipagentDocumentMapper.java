package com.sinochem.crude.trade.blockchain.dao;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.domain.TShipagentDocument;
import com.sinochem.crude.trade.shipagent.model.query.TShipagentDocumentQuery;

import java.util.List;

public interface TShipagentDocumentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TShipagentDocument record);

    int insertSelective(TShipagentDocument record);

    TShipagentDocument selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TShipagentDocument record);

    int updateByPrimaryKey(TShipagentDocument record);

    /**
     * 查询列表
     * @param document
     * @return
     */
    Page<TShipagentDocument> selectByParameter(TShipagentDocument document);


    /**
     * 查询列表
     * @param document
     * @return
     */
    Page<TShipagentDocument> selectDocumentList(TShipagentDocumentQuery document);


    /**
     * 更新url
     * @param document
     */
    public void updateSofUrl(TShipagentDocument document);

    /**
     * 更新url
     * @param document
     */
    public void updateBlUrl(TShipagentDocument document);
}