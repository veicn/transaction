package com.sinochem.crude.trade.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.sinochem.it.b2b.common.CommonUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.lang.CharEncoding;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageHttpUtil {
    public static final String MAIL_TPL_URL = "/mail/sendMailWithTemplate.json";
    public static final String SMS_URL = "/sms/sendMsg.json";
    public static final String MESSAGE_URL = "/message/push.json";
    public static final String APP_URL = "/app/push.json";
    private static final String MESSAGE_SALT = "6f9b9af3cd6e8b8a73c2cdced37fe9f59226e27d";


    public static String sendMessage(HttpClient httpClient, String url, List<NameValuePair> params) throws BizException {
        String result = "200";
        try {
            //POST的URL
            HttpPost httppost = new HttpPost(url);
            //建立HttpPost对象
            //添加参数
            httppost.setEntity(new UrlEncodedFormEntity(params, CharEncoding.UTF_8));
            //设置编码
            HttpResponse response = httpClient.execute(httppost);
            //发送Post,并返回一个HttpResponse对象
            if (response.getStatusLine().getStatusCode() == 200) {//如果状态码为200,就是正常返回
                result = EntityUtils.toString(response.getEntity());
                return result;
            } else {
                throw new BizException("http请求异常:" + response.getStatusLine().getStatusCode());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new BizException("字符编码不支持！");
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizException("数据读写失败！");
        }
    }

    /**
     * 组装邮件参数
     *
     * @param tplName  模板名称
     * @param subject  邮件主题
     * @param model    (此参数为map ， key 必须包含userName（用户名） ， platform（平台名） ，email（用户邮箱），activationUrl（激活链接）)
     * @param receiver 收件人
     * @return
     */
    public static List<NameValuePair> generateTplMailParams(String tplName, String subject, String receiver, Map<String, Object> model) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("tplName", tplName));
        param.add(new BasicNameValuePair("receiver", receiver));
        param.add(new BasicNameValuePair("memberId", CommonUtils.PLANTFORM_MEMBER_ID + ""));
        param.add(new BasicNameValuePair("subject", subject));
        param.add(new BasicNameValuePair("model", JSONObject.toJSONString(model)));
        //签名(签名顺序：memberId, receiver, model, subject, tplName)，若参数为空则不参加签名
        Object[] params = new Object[]{CommonUtils.PLANTFORM_MEMBER_ID + "", receiver, JSONObject.toJSONString(model), subject, tplName};
        String sign = Digest.digestWithMD5(params, MESSAGE_SALT);
        param.add(new BasicNameValuePair("sign", sign));
        return param;
    }

    /**
     * 组装短信参数
     *
     * @param tplName  模板
     * @param mobileNo 手机号
     * @param model    模板参数
     * @return
     */
    public static List<NameValuePair> generateSmsParams(String tplName,String mobileNo, Map<String, Object> model) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("memberId", CommonUtils.PLANTFORM_MEMBER_ID + ""));
        param.add(new BasicNameValuePair("strMobileNo", mobileNo));
        param.add(new BasicNameValuePair("tplName", tplName));
        param.add(new BasicNameValuePair("model", JSONObject.toJSONString(model)));
        //签名(签名顺序：model, tplName, strContent, strMobileNo, memberId)，若参数为空则不参加签名
        Object[] params = new Object[]{JSONObject.toJSONString(model), tplName, mobileNo, CommonUtils.PLANTFORM_MEMBER_ID + ""};
        String sign = Digest.digestWithMD5(params, MESSAGE_SALT);
        param.add(new BasicNameValuePair("sign", sign));
        return param;
    }

    /**
     * 组装站内信参数
     *
     * @param tplName 模板
     * @param toId    发送给谁
     * @param level   消息等级 （1是通知等级，2是提示等级，3站内信等级）
     * @param title   标题
     * @param model   模板参数
     * @return
     */
    public static List<NameValuePair> generateMessageParams( String tplName, String toId, String level,String title,Map<String, Object> model) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("fromId", CommonUtils.PLANTFORM_MEMBER_ID + ""));
        param.add(new BasicNameValuePair("createUser", CommonUtils.PLANTFORM_MEMBER_ID + ""));
        param.add(new BasicNameValuePair("toId", toId));
        param.add(new BasicNameValuePair("level", level));//消息等级（1是通知等级，2是提示等级，3站内信等级）
        param.add(new BasicNameValuePair("tplName", tplName));
        param.add(new BasicNameValuePair("title", title));
        param.add(new BasicNameValuePair("model", JSONObject.toJSONString(model)));
        //签名(签名顺序：tplName,model,attribute, isAjaxCallback, callback, onceRead, toId, content,title, createUser, effectTime, level, fromId)，若参数为空则不参加签名
        Object[] params = new Object[]{tplName,JSONObject.toJSONString(model), toId, title,CommonUtils.PLANTFORM_MEMBER_ID + "",level, CommonUtils.PLANTFORM_MEMBER_ID + ""};
        String sign = Digest.digestWithMD5(params, MESSAGE_SALT);
        param.add(new BasicNameValuePair("sign", sign));
        return param;
    }

    /**
     * 组装APP推送参数
     *
     * @param tplName 模板
     * @param toId    发送给谁
     * @param model   模板参数
     * @return
     */
    public static List<NameValuePair> generateAPPParams( String tplName, String toId,Map<String, Object> model) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("platform", "0"));//接收方平台(0:all,1:android,2:ios,3:winphone)支持数组。例：”1”或[“1”,”2”]
        param.add(new BasicNameValuePair("alias", toId));//接收方别名，可为数组。例：”a”或[“a”,”b”]
        param.add(new BasicNameValuePair("notifyMode", "2"));
        param.add(new BasicNameValuePair("tplName", tplName));
        param.add(new BasicNameValuePair("contentType", "text"));
        param.add(new BasicNameValuePair("model", JSONObject.toJSONString(model)));
        //签名(签名顺序：delayTime, notifyMode, contentType, extras, content, title, alias, tagMode, tags, platform)，若参数为空则不参加签名
        Object[] params = new Object[]{JSONObject.toJSONString(model),tplName,0L,"2","text","1",new String[]{toId},new Integer[]{0}};
        String sign = Digest.digestWithMD5(params, MESSAGE_SALT);
        param.add(new BasicNameValuePair("sign", sign));
        return param;
    }

}
