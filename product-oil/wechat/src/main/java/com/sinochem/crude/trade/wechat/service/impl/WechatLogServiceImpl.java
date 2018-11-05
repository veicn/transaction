package com.sinochem.crude.trade.wechat.service.impl;

import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.wechat.dao.WechatLogsMapper;
import com.sinochem.crude.trade.wechat.domain.WechatLogs;
import com.sinochem.crude.trade.wechat.service.WechatLogService;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WechatLogServiceImpl implements WechatLogService {
    @Autowired
    private WechatLogsMapper wechatLogsMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());
    public void addlog(WechatLogs wechatLogs)
    {

        try {

            wechatLogs.setTime(new Date());
            wechatLogsMapper.insertSelective(wechatLogs);
        }
        catch (Exception e) {
             e.printStackTrace();
            logger.error("获取个人信息error:" + e);
        }
    }

    @Override
    public List<WechatLogs> selectall(int start, int end) {
        List<WechatLogs> list=wechatLogsMapper.selectall(start,end);
        for(WechatLogs item:list){
            String formatDate = DateUtil.formatDateTime(item.getTime());
            item.setStrTime(formatDate);
        }
        return list;
    }
}
