package com.sinochem.crude.trade.wechat.service;


import com.sinochem.crude.trade.wechat.domain.WechatSendmsgLogs;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;

import java.util.List;

public interface WechatSendmsgLogService {
    int insertSelective(WechatSendmsgLogs record);
    List<WechatSendmsgLogs> SendMsgList(List<WechatSendmsgLogs> logslist,HttpConnectionManager httpClientManager);
    List<WechatSendmsgLogs> ForkJoinSendMsgList(List<WechatSendmsgLogs> logslist,HttpConnectionManager httpClientManager);
    List<WechatSendmsgLogs> selectall(int start, int end);
}
