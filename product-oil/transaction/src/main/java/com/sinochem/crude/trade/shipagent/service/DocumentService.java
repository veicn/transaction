package com.sinochem.crude.trade.shipagent.service;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.domain.TCommonAttachments;
import com.sinochem.crude.trade.blockchain.domain.TShipagentDocument;
import com.sinochem.crude.trade.shipagent.model.query.TShipagentDocumentQuery;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by admin1 on 2018/9/19.
 */
public interface DocumentService {


	/**
	 *
	 * 单证列表详情
	 * @param query
	 * @return
	 */
	public Page<TShipagentDocument> list(TShipagentDocumentQuery query) throws Exception;


	/**
	 * 单证明细
	 * @param id
	 * @return
	 */
	public TShipagentDocument get(Long id);

	/**
	 * sasdas
	 * @param queryDocument
	 * @return
	 */
	public List<TShipagentDocument> get(TShipagentDocument queryDocument) ;



	public int updateByPrimaryKeySelective(TShipagentDocument document);


	public void udateDocumentUrl(TCommonAttachments attachments)throws BizException;
}
