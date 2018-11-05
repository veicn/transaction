package com.sinochem.crude.trade.shipping.remote.impl;

import com.sinochem.crude.trade.shipping.dao.ConfirmationSheetMapper;
import com.sinochem.crude.trade.shipping.dao.VoyageStartMapper;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.VoyageStart;
import com.sinochem.crude.trade.shipping.remote.VoyageStartRemoteService;
import com.sinochem.crude.trade.shipping.vo.VoyageStartRemoteVO;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WGP
 * @descriptioncrude-trade
 * @date 2018/5/4
 **/
@Service("voyageStartRemoteServiceImpl")
public class VoyageStartRemoteServiceImpl implements VoyageStartRemoteService {

    private final Logger logger = LoggerFactory.getLogger(AgreementRemoteServiceImpl.class);


    @Autowired
    private ConfirmationSheetMapper confirmationSheetMapper;

    @Autowired
    private VoyageStartMapper voyageStartMapper;


    /**
     * 根据订单ID更新配载计划的文件
     * @param voyageStartRemoteVO
     * @return 成功（1）失败（0） 异常（2）
     */
    @Override
    public Integer updateVoyageStartFileByOrderId(VoyageStartRemoteVO voyageStartRemoteVO)throws BizException {
        if(voyageStartRemoteVO == null || voyageStartRemoteVO.getOrderId()==null){
            throw new BizException("根据订单ID更新配载计划的文件参数为空");
        }
        ConfirmationSheet confirmationSheet = new  ConfirmationSheet();
        confirmationSheet.setOrderId(voyageStartRemoteVO.getOrderId());
        List<ConfirmationSheet> list = confirmationSheetMapper.queryByEntitys(confirmationSheet);
        if(list != null && list.size()>0){
            try{
                confirmationSheet = list.get(0);
                VoyageStart voyageStart = new VoyageStart();
                voyageStart.setShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                String accessory = voyageStartRemoteVO.getAccessory();
                if(accessory != null){
                    voyageStart.setAccessory(voyageStartRemoteVO.getAccessory());
                    voyageStart.setAccessoryFileNm(voyageStartRemoteVO.getAccessoryFileNm());
                }else{
                    voyageStart.setAccessory("");
                    voyageStart.setAccessoryFileNm("");
                }
                int flag = voyageStartMapper.updateFileByOrderId(voyageStart);
                return flag>0?Integer.valueOf(1):Integer.valueOf(0);
            }catch (Exception e){
                logger.error("配载计划更新单据失败！",e);
                return  Integer.valueOf(2);
            }
        }else{
            return Integer.valueOf(0);
        }

    }

    @Override
    public Integer updateVoyageStartDiByOrderId(VoyageStartRemoteVO voyageStartRemoteVO) throws BizException {
        ConfirmationSheet confirmationSheet = new  ConfirmationSheet();
        confirmationSheet.setOrderId(voyageStartRemoteVO.getOrderId());
        List<ConfirmationSheet> list = confirmationSheetMapper.queryByEntitys(confirmationSheet);
        if(list != null && list.size()>0  ){
            try{
                confirmationSheet = list.get(0);
                VoyageStart voyageStart = new VoyageStart();
                voyageStart.setShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                String diFileUrl = voyageStartRemoteVO.getDi();
                if(diFileUrl != null){
                    voyageStart.setDi(diFileUrl);
                    voyageStart.setDiFileNm(voyageStartRemoteVO.getDiFileNm());
                }else{
                    voyageStart.setDiFileNm("");
                    voyageStart.setDi("");
                }
                int flag = voyageStartMapper.updateDiFileByOrderId(voyageStart);
                return flag>0?Integer.valueOf(1):Integer.valueOf(0);
            }catch (Exception e){
                logger.error("配载计划更新单据失败！",e);
                return  Integer.valueOf(2);
            }
        }else{
            return Integer.valueOf(0);
        }

    }


}
