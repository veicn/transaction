package com.sinochem.crude.trade.shipping.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinochem.crude.trade.shipping.domain.po.MessageSheet;
import com.sinochem.crude.trade.shipping.helper.NotificationHelper;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.ExternalApi;
import com.sinochem.crude.trade.shipping.dao.AgreementMapper;
import com.sinochem.crude.trade.shipping.dao.ConfirmationSheetMapper;
import com.sinochem.crude.trade.shipping.dao.TransitLoadingMapper;
import com.sinochem.crude.trade.shipping.domain.Agreement;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.TransitLoading;
import com.sinochem.crude.trade.shipping.model.query.TransitLoadingQuery;
import com.sinochem.crude.trade.shipping.service.SimplePageInfo;
import com.sinochem.crude.trade.shipping.service.TransitLoadingService;
import com.sinochem.it.b2b.common.exception.BizException;

@Service
public class TransitLoadingServiceImpl implements TransitLoadingService {


    @Value("${id.quanzhou}")
    private Long producterId;
    private final Logger logger = LoggerFactory.getLogger(TransitLoadingServiceImpl.class);
    @Autowired
    private TransitLoadingMapper transitLoadingMapper;

    @Autowired
    private ConfirmationSheetMapper confirmationSheetMapper; //确认单mapper

    @Autowired
    private AgreementMapper agreementMapper; //需求单mapper


    @Autowired
    private NotificationHelper notificationHelper;

    public TransitLoadingMapper getMapper() {
        return transitLoadingMapper;
    }

    /**
     * 新增
     */
    @Override
    public int insertRecord(TransitLoading transitloading) {
        return transitLoadingMapper.insertRecord(transitloading);
    }

    /**
     * 根据主键物理删除, 慎用！！！
     */
    @Override
    public int deleteById(Long transitLoadingId) throws BizException {
        if (transitLoadingId == null) {
            throw new BizException("id 为空，不能修改 ");
        }
        return transitLoadingMapper.deleteById(transitLoadingId);
    }

    /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(TransitLoading record) {
        return transitLoadingMapper.deleteRecords(record);
    }

    /**
     * 根据主键-修改对象
     */
    @Override
    public int updateRecordById(TransitLoading transitLoading) throws BizException {
        if (transitLoading.getTransitLoadingId() == null) {
            throw new BizException("transitLoadingId 为空，不能修改 ");
        }
        return transitLoadingMapper.updateRecordById(transitLoading);
    }

    /**
     * 根据uuid-修改对象
     */
    @Override
    public int updateRecordByUuid(TransitLoading transitLoading) throws BizException {
        if (transitLoading.getUuid() == null) {
            throw new BizException("uuid为空，不能修改 ");
        }
        return transitLoadingMapper.updateRecordByUuid(transitLoading);
    }

    /**
     * 根据条件-批量修改数据(危险慎用)
     */
    @Override
    public int updateRecords(Map<String, Object> map) {
        return transitLoadingMapper.updateRecords(map);
    }

    /**
     * 根据条件-批量修改数据(危险慎用)
     */
    @Override
    public int updateRecords(TransitLoading transitLoading) {
        return transitLoadingMapper.updateRecords(transitLoading.toMap());
    }


    /**
     * 根据主键-查询对象
     */
    @Override
    public TransitLoading findByPrimaryKey(Long transitLoadingId) {
        return transitLoadingMapper.findByPrimaryKey(transitLoadingId);
    }

    /**
     * 根据uuid查询对象
     */
    @Override
    public TransitLoading findByUuid(String uuid) {
        return transitLoadingMapper.findByUuid(uuid);
    }

    /**
     * 根据对象-查询对象列表
     */
    @Override
    public List<TransitLoading> queryByEntitys(TransitLoading transitLoading) {
        return transitLoadingMapper.queryByEntitys(transitLoading);
    }

    /**
     * 根据条件-查询全部
     */
    @Override
    public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
        return transitLoadingMapper.queryRecords(map);
    }

    /**
     * 根据条件-分页查询
     */
    @Override
    public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
        return (Page<Map<String, Object>>) transitLoadingMapper.queryRecords(map);
    }

    /**
     * 根据条件-查询记录条数
     */
    @Override
    public int countRecords(Map<String, Object> map) {
        return transitLoadingMapper.countRecords(map);
    }

    //**************************以下方法为开发者补充*********************************/

    /**
     * 查询列表
     *
     * @param domain
     * @return
     */
    @Override
    public List<TransitLoading> queryRecordsList(TransitLoadingQuery domain
    ) {
        List<TransitLoading> queryRecordsList = transitLoadingMapper.queryRecordsList(domain);

        return queryRecordsList;
    }

    /**
     * 航程在途中信息逻辑删除
     */
    @Override
    public Integer transitLocigDelete(TransitLoading domain) throws BizException {
        domain.setAliveFlag(Constants.DELE_FLAG);
        return transitLoadingMapper.transitLocigDelete(domain);
    }

    /**
     * 航程在途中新增
     *
     * @throws BizException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertTransit(List<TransitLoading> listdomain,
                                 CurrentUser currentUser, String conuuid) throws BizException {
        if (currentUser == null || StringUtil.isBlank(conuuid)) {
            throw new BizException("The User's Information Is Empty,Insert Faild");
        }
        //查询确认单id
        ConfirmationSheet findByUuid = confirmationSheetMapper.findByUuid(conuuid);
        if (null == findByUuid) {
            throw new BizException("The Confirmation table Is Empty,Faild");
        }
        Integer count = transitLoadingMapper.findCountByconfirmationSheetId(findByUuid.getConfirmationSheetId());
        if(count >0 ){
            transitLoadingMapper.deleteByConfiId(findByUuid.getConfirmationSheetId());
        }

        Integer insertTransit = null;
        for (int i = 0; i < listdomain.size(); i++) {
            listdomain.get(i).setUuid(KeyGenUtils.newUuid());
            listdomain.get(i).setAliveFlag(Constants.SAVE_FLAG);
            listdomain.get(i).setCreateUser(currentUser.getMemberId());
            listdomain.get(i).setUpdateUser(currentUser.getMemberId());
            listdomain.get(i).setShipConfirmationSheetId(findByUuid.getConfirmationSheetId());

            insertTransit = transitLoadingMapper.insertTransit(listdomain.get(i));

        }
        //船代更新船程信息，就发送消息
        if (insertTransit > 0) {
            try {
                MessageSheet messageSheet = new MessageSheet();
//                {confirmationSheetCd，vesselname，product,quantity}
                messageSheet.setConfirmationSheetCd(findByUuid.getConfirmationSheetCd());
                messageSheet.setProduct(findByUuid.getProduct());
                messageSheet.setQuantity(findByUuid.getQuantity());
                messageSheet.setVesselName(findByUuid.getVesselName());
                Long[] memberIds = new Long[]{
                        findByUuid.getBuyerId(),
                        findByUuid.getSellerId(),
                        this.producterId,
                        null
                };
                Agreement agreement = agreementMapper.findByPrimaryKey(findByUuid.getShipAgreementId());
                if (agreement != null) {
                    memberIds[3] = agreement.getEpMemberId();
                }
                String[] urls = new String[]{
                        Constants.BUYER_CONFIRMATION_URL,
                        Constants.SELLER_CHARTER_PARTY_URL,
                        Constants.SELLER_CHARTER_PARTY_URL,
                        Constants.AGENT_CONFIRMATION_URL
                };
                int index =0;
                for (Long memberId : memberIds) {
                    if (memberId != null) {
                        messageSheet.setMemberId(memberId);
                        messageSheet.setHyperlink(urls[index]);
                        notificationHelper.sendProShippingAgentUpdateShipPlan(messageSheet);
                    }
                    index++;
                }
            } catch (Exception e) {
                logger.error("船代更新航程信息发送消息失败", e);
            }
        }
        String confirmatstatus = findByUuid.getStatus();
        Integer valueOf = Integer.valueOf(confirmatstatus);
        //判断是否已经装港完毕 如已装港完成 不让修改
        if (valueOf < 40) {//TODO
            ConfirmationSheet confirmationSheet = new ConfirmationSheet();
            //修改为航次开始
            confirmationSheet.setStatus(ExternalApi.CONFIRM_30);
            confirmationSheet.setConfirmationSheetId(findByUuid.getConfirmationSheetId());
            confirmationSheet.setUpdateUser(currentUser.getMemberId());
            confirmationSheetMapper.updateStatusByconfirmationId(confirmationSheet);
            // Integer res = transitLoadingMapper.insertTransit(domain);
            Agreement agreement = new Agreement();
            agreement.setAgreementId(findByUuid.getShipAgreementId());
            agreement.setStatus(ExternalApi.AGREEMENT_30);
            agreement.setUpdateUser(currentUser.getMemberId());
            agreementMapper.updateStatusByAgreementId(agreement);
        }

        return insertTransit;
    }

    //查看航程信息
    @Override
    public List<TransitLoading> findByShipConfirmationSheetId(Long confirmationSheetId) {
        return transitLoadingMapper.findByShipConfirmationSheetId(confirmationSheetId);
    }

    /**
     * 航程在途中新增  微信端
     *
     * @return
     * @throws BizException
     */
    @Override
    public Integer saveTransitLoading(TransitLoading domain, CurrentUser currentUser) throws BizException {
//		if (currentUser == null) {
//			throw new BizException("用户为空，不能新增 ");
//		}
        if (StringUtil.isEmpty(domain.getUuid())) {
            domain.setUuid(KeyGenUtils.newUuid());
            domain.setAliveFlag(Constants.SAVE_FLAG);
//			domain.setCreateUser(currentUser.getMemberId());
//			domain.setUpdateUser(currentUser.getMemberId());
            return transitLoadingMapper.insertRecord(domain);
        } else {
            //domain.setUpdateUser(currentUser.getMemberId());
            return transitLoadingMapper.updateRecordByUuid(domain);
        }

    }
//
//    /**
//     * 根据船舶的imo值获取mmsi的值 - 武刚鹏
//     * @param imo
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public String findShipTrackList(String imo) throws Exception {
//        CloseableHttpClient httpClient = null;
//        CloseableHttpResponse response = null;
//        String mmsi = null;
//        try {
//            httpClient = HttpClients.createDefault();
//            HttpGet httpGet = new HttpGet(this.SHIP_TRACK_IMO_API + imo);
//            response = httpClient.execute(httpGet);
//            if (response == null) {
//                HttpEntity entity = response.getEntity();
//                String entityStr = EntityUtils.toString(entity);
////            {"status":0,"code":null,"message":"成功","params":null,"pageNum":null,"pageSize":null,"pageCount":null,"total":null,"langVer":null,"datas":{"code":"0","message":"操作成功","data":[{"beam":"","cubic":"","mmsi":"240593000","sog":"0.10","lon":"109.13985","imo":"9315367","dest":"YANGPU,CN","callSign":"","vesselId":"2018033100000067","vesselSizeCn":"VLCC","eta":"1523541600000","draught":"20.0","receivedTime":"1523868774000","draft":"","sdwt":"318683","lat":"19.794216","vesselName":"Spyros","vesselTypeCn":"","loa":"","hullType":"","build":"2007","hog":"25.0","status_cn":"靠泊","status_en":"Moored","seaArea":"南海"}]}}
//
//                if(entityStr!= null){
//                    //解析第一层对象
//                    JSONObject obj = JSON.parseObject(entityStr);
//                    if(obj!=null){
//                        JSONObject obj2 = obj.getJSONObject("datas");
//                        if(obj2!=null){
//                            // 解析第二层对象，返回数组
//                            JSONArray obj3 = obj2.getJSONArray("data");
//                            if(obj3!=null){
//                                //取数组第一个元素，获取mmsi键值对应的value
//                                JSONObject o = obj3.getJSONObject(0);
//                                mmsi = o.getString("mmsi");
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (NullPointerException e) {
//            logger.error("获取原油服务imo错误",e);
//        } catch (IOException e) {
//            logger.error("获取原油服务imo错误",e);
//        } catch (Exception e) {
//            logger.error("获取原油的链接错误",e);
//        }
//        finally {
//            if(httpClient!=null){
//                httpClient.close();
//            }
//            if(response!=null){
//                response.close();
//            }
//            return mmsi;
//        }
//    }

}
