package com.sinochem.crude.trade.shipping.helper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.helper.HttpConnectionHelper;
import com.sinochem.crude.trade.shipping.constant.ExternalApi;
import com.sinochem.crude.trade.shipping.model.vo.EnterpriseVO;
import com.sinochem.crude.trade.shipping.model.vo.MemberInfoVO;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author WGP
 * @descriptioncrude-trade-product_oil
 * @date 2018/4/23
 **/

@Component
public class MemberInfoHelper {

    private final Logger LOGGER = LoggerFactory.getLogger(MemberInfoHelper.class);

    @Autowired
    private HttpConnectionManager httpConnectionManager;

    @Autowired
    private HttpConnectionHelper httpConnectionHelper;

    @Autowired
    private URLBroker memberServer;

    @Autowired
    private URLBroker systemServer;

    public MemberInfoVO memberInfo(Long memberId) {
        MemberInfoVO vo = new MemberInfoVO();
        vo.setMemberId(memberId);

        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient closeableHttpClient = httpConnectionManager.getHttpClient();

        String requestUrl = systemServer.get(ExternalApi.QUERY_MEMBER_INFO +"?memberId="+ memberId).toString();
        HttpGet httpGet = new HttpGet(requestUrl);

        try {
            httpResponse = closeableHttpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseString = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);
            JSONObject responseJson = JSONObject.parseObject(responseString.toString());

            LOGGER.info("根据memberId获取手机邮箱：" + responseString);

            JSONObject dataJsonArray = responseJson.getJSONObject("datas");
            if(dataJsonArray != null){
                vo.setEmail(dataJsonArray.getString("email"));
                vo.setMobile(dataJsonArray.getString("mobile"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return vo;
    }

    /**
     * 根据epMemberId获取企业信息
     */
    public EnterpriseVO getEnterpriseByEpMemberId(Long epMemberId) {

        String paramString = new StringBuilder("memberId=").append(epMemberId).toString();
        String url = memberServer.get(ExternalApi.QUERY_ENTERPRISE_INFO).toString();

        try {
            JSONObject responseJson = httpConnectionHelper.httpGet(paramString, url);
            if (responseJson == null) {
                throw new RuntimeException("网络错误");
            }

            Integer status = responseJson.getInteger(ExternalApi.API_STATUS);
            if (!(ExternalApi.API_STATUS_SUCCESS == status)) {
                throw new RuntimeException("请求失败");
            }

            JSONObject dataJson = responseJson.getJSONObject(ExternalApi.API_DATAS);
            EnterpriseVO enterpriseVO = new EnterpriseVO();

            enterpriseVO.setId(dataJson.getLong("id"));
            enterpriseVO.setMemberId(dataJson.getLong("memberId"));
            enterpriseVO.setCres(dataJson.getString("cres"));
            enterpriseVO.setName(dataJson.getString("name"));

            return enterpriseVO;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据企业名称（是name不是username）获取企业信息
     */
    public EnterpriseVO getEnterpriseByName(String name) {

        if (StringUtil.isEmpty(name)) {
            return null;
        }

        String url = memberServer.get(ExternalApi.QUERY_ALL_ENTERPRISES).toString();

        try {
            JSONObject responseJson = httpConnectionHelper.httpGet(null, url);

            if (responseJson == null) {
                throw new RuntimeException("网络错误");
            }

            Integer status = responseJson.getInteger(ExternalApi.API_STATUS);
            if (!status.equals(ExternalApi.API_STATUS_SUCCESS)) {
                throw new RuntimeException("请求失败");
            }

            JSONArray dataJsonArray = responseJson.getJSONArray(ExternalApi.API_DATAS);
            for (int i = 0; i < dataJsonArray.size(); i++) {
                JSONObject dataJson = dataJsonArray.getJSONObject(i);
                String enterpriseName = dataJson.getString("name");

                if (name.equals(enterpriseName)) {
                    EnterpriseVO enterpriseVO = new EnterpriseVO();

                    enterpriseVO.setId(dataJson.getLong("id"));
                    enterpriseVO.setName(name);
                    enterpriseVO.setCres(dataJson.getString("cres"));
                    enterpriseVO.setMemberId(dataJson.getLong("memberId"));

                    return enterpriseVO;
                }
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
