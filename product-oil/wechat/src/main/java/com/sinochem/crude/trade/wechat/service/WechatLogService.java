package com.sinochem.crude.trade.wechat.service;

import com.sinochem.crude.trade.wechat.domain.WechatLogs;

import java.util.List;


public interface WechatLogService {
    void addlog(WechatLogs wechatLogs);

    List<WechatLogs> selectall(int start, int end);
}
