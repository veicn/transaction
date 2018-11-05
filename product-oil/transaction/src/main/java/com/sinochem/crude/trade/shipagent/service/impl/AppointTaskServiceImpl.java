package com.sinochem.crude.trade.shipagent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.blockchain.dao.TCommonAttachmentsMapper;
import com.sinochem.crude.trade.blockchain.dao.TShipagentAppointMapper;
import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.service.TCommonAttachmentsService;
import com.sinochem.crude.trade.broker.model.vo.TBrokerAppointQueryVO;
import com.sinochem.crude.trade.shipagent.constant.Constants;
import com.sinochem.crude.trade.shipagent.service.AppointTaskService;
import com.sinochem.crude.trade.shipagent.service.BillLoadingService;
import com.sinochem.crude.trade.shipagent.service.DocumentService;
import com.sinochem.crude.trade.shipagent.service.SofService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author songhaiqiang
 * @date 2018/9/6
 */
@Service("appointTaskService")
public class AppointTaskServiceImpl implements AppointTaskService{

    @Autowired
    TShipagentAppointMapper tShipagentAppointMapper;

    @Autowired
	private SofService sofService;

    @Autowired
	private DocumentService documentService;

    @Autowired
	private BillLoadingService billLoadingService;

    @Autowired
	private TCommonAttachmentsService tCommonAttachmentsService;

	/**
	 * 任务列表查询
	 * @param tBrokerAppoint
	 * @return
	 */
	@Override
	public Page<TShipagentAppoint> getTBrokerAppointPage(TBrokerAppointQueryVO tBrokerAppoint){
		PageHelper.startPage(tBrokerAppoint.getPageNum(), tBrokerAppoint.getPageSize() , "create_date desc");
		Page<TShipagentAppoint> contractSheetList = tShipagentAppointMapper.getTBrokerAppointPage(tBrokerAppoint);
		return contractSheetList;
	}

	@Override
	public TShipagentAppoint selectByPrimaryKey(Long id){
		return tShipagentAppointMapper.selectByPrimaryKey(id);
	}


	@Override
	public void insert(TShipagentAppoint tShipagentAppoint){

        tShipagentAppointMapper.insert(tShipagentAppoint);

    }

	@Override
    public void updateByDealNo(TShipagentAppoint tShipagentAppoint){

        tShipagentAppointMapper.updateByDealNo(tShipagentAppoint);

    }

    @Override
    public TShipagentAppoint getTShipagentAppointByDealNo(String dealNo) {
        return tShipagentAppointMapper.selectByDealNo(dealNo);
    }

	@Override
	public List<TShipagentAppoint> selectByCondition(TShipagentAppoint queryAppoint) {
		return tShipagentAppointMapper.selectByCondition(queryAppoint);
	}

	@Override
	public void updateStatusIfFileNotExist(TCommonAttachments attachments) throws BizException{
		if(attachments == null || attachments.getBusinessUuid() == null){
			throw new BizException("删除出错");
		}
		final String businessUuid = attachments.getBusinessUuid();
		final String fileType = attachments.getFileType();

		List<TCommonAttachments> attachmentsList = tCommonAttachmentsService.selectByBusinessUuid(businessUuid);

		if(attachmentsList == null || attachmentsList.size() < 1) {
			//查询关联是否存在附件
			if (TCommonAttachments.FILETYPE_SOF.equals(fileType)) {
				TShipagentAppoint appoint = this.selectBySofUuid(businessUuid);
				this.updateStatus(appoint);
			}

			if (TCommonAttachments.FILETYPE_BL.equals(fileType)) {
				TShipagentAppoint appoint = this.selectByBlUuid(businessUuid);
				this.updateStatus(appoint);
			}
		}
	}


	private void updateStatus(TShipagentAppoint appoint){
		if (appoint == null) {
			return;
		}
		TShipagentAppoint updateAppoint = new TShipagentAppoint();
		updateAppoint.setStatus(TShipagentAppoint.STATUS_ACCEPTED);
		updateAppoint.setId(appoint.getId());
		tShipagentAppointMapper.updateByPrimaryKeySelective(updateAppoint);
	}

	private TShipagentAppoint selectByBlUuid(String businessUuid) {
		TShipagentBillLoading billLoading = billLoadingService.selectByUuid(businessUuid);
		if(billLoading== null){
			return null;
		}
		TShipagentDocument queryDocument = new TShipagentDocument();
		queryDocument.setAliveFlag(Constants.ALIVE_FLAG_YES);
		queryDocument.setBlId(billLoading.getId());
		List<TShipagentDocument> documentList = documentService.get(queryDocument);
		if(documentList == null || documentList.size() < 1){
			return null;
		}
		final TShipagentDocument document = documentList.get(0);
		return tShipagentAppointMapper.selectByDealNo(document.getDealNo());

	}

	private TShipagentAppoint selectBySofUuid(String businessUuid) {
		TShipagentSof sof = sofService.selectByUuid(businessUuid);
		if(sof == null){
			return null;
		}
		TShipagentDocument queryDocument = new TShipagentDocument();
		queryDocument.setAliveFlag(Constants.ALIVE_FLAG_YES);
		queryDocument.setSofId(sof.getId());
		List<TShipagentDocument> documentList = documentService.get(queryDocument);
		if(documentList == null || documentList.size() < 1){
			return null;
		}
		final TShipagentDocument document = documentList.get(0);
		return tShipagentAppointMapper.selectByDealNo(document.getDealNo());
	}
}
