package com.sinochem.crude.trade.shipagent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.blockchain.dao.TShipagentBillladingMapper;
import com.sinochem.crude.trade.blockchain.dao.TShipagentDocumentMapper;
import com.sinochem.crude.trade.blockchain.dao.TShipagentSofMapper;
import com.sinochem.crude.trade.blockchain.domain.TCommonAttachments;
import com.sinochem.crude.trade.blockchain.domain.TShipagentBillLoading;
import com.sinochem.crude.trade.blockchain.domain.TShipagentDocument;
import com.sinochem.crude.trade.blockchain.domain.TShipagentSof;
import com.sinochem.crude.trade.blockchain.service.TCommonAttachmentsService;
import com.sinochem.crude.trade.shipagent.constant.Constants;
import com.sinochem.crude.trade.shipagent.model.query.TShipagentDocumentQuery;
import com.sinochem.crude.trade.shipagent.service.DocumentService;
import com.sinochem.crude.trade.shipagent.service.SofService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author admin1
 * @date 2018/9/19
 */
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {


	@Autowired
	private TShipagentDocumentMapper tShipagentDocumentMapper;

	@Autowired
	private TCommonAttachmentsService tCommonAttachmentsService;

	@Autowired
	private TShipagentSofMapper tShipagentSofMapper;

	@Autowired
	private TShipagentBillladingMapper tShipagentBillladingMapper;


	@Override
	public Page<TShipagentDocument> list(TShipagentDocumentQuery query) throws Exception{
		PageHelper.startPage(query.getPageNum() , query.getPageSize() /*, "d.create_date DESC"*/);
		return tShipagentDocumentMapper.selectDocumentList(query);
	}


	@Override
	public TShipagentDocument get(Long id) {
		return tShipagentDocumentMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TShipagentDocument> get(TShipagentDocument queryDocument) {
		return tShipagentDocumentMapper.selectByParameter(queryDocument);
	}

	@Override
	public int updateByPrimaryKeySelective(TShipagentDocument document) {
		return tShipagentDocumentMapper.updateByPrimaryKeySelective(document);
	}

	@Override
	public void udateDocumentUrl(TCommonAttachments attachments) throws BizException{

		if(attachments == null || StringUtils.isBlank(attachments.getBusinessUuid())){
			throw new BizException("删除附件不存在");
		}
		List<TCommonAttachments> attachmentsList = tCommonAttachmentsService.selectByBusinessUuid(attachments.getBusinessUuid());

		//单证更新
		TShipagentDocument queryDocument = new TShipagentDocument();

		// sof 更新
		if(TCommonAttachments.FILETYPE_SOF.equals(attachments.getFileType())){
			final String sofUrl = groupUrl(attachmentsList);
			TShipagentSof sof = tShipagentSofMapper.selectByUuid(attachments.getBusinessUuid());
			queryDocument.setSofId(sof.getId());
			queryDocument.setAliveFlag(Constants.ALIVE_FLAG_YES);
			List<TShipagentDocument> documentList = tShipagentDocumentMapper.selectByParameter(queryDocument);

			if(documentList != null && documentList.size() == 1){
				TShipagentDocument document = documentList.get(0);
				document.setSofUrl(sofUrl);
				tShipagentDocumentMapper.updateSofUrl(document);
			}
		}

		// bl更新
		if(TCommonAttachments.FILETYPE_BL.equals(attachments.getFileType())){
			final String blUrl = groupUrl(attachmentsList);
			TShipagentBillLoading billLoading = tShipagentBillladingMapper.selectByUuid(attachments.getBusinessUuid());
			queryDocument.setBlId(billLoading.getId());
			queryDocument.setAliveFlag(Constants.ALIVE_FLAG_YES);
			List<TShipagentDocument> documentList = tShipagentDocumentMapper.selectByParameter(queryDocument);

			if(documentList != null && documentList.size() == 1){
				TShipagentDocument document = documentList.get(0);
				document.setBlUrl(blUrl);
				tShipagentDocumentMapper.updateBlUrl(document);
			}
		}
	}


	private String groupUrl(List<TCommonAttachments> attachmentsList){
		String url = "";
		if(attachmentsList != null && attachmentsList.size() > 0){
			for(TCommonAttachments a : attachmentsList){
				url = url + ";" + a.getPath();
			}
		}
		return url;
	}
}
