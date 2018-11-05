package com.sinochem.crude.trade.member.schedule.bean;

import com.sinochem.crude.trade.member.domain.MessageLog;
import com.sinochem.crude.trade.member.service.MessageLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目启动时读取未发送的记录添加到定时任务中
 */
@Component
public class InitScheduleBean implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitScheduleBean.class);

    @Autowired
    private MessageLogService messageLogService;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("member init ：将MessageLog未发送记录添加到定时任务");
        try {
            MessageLog messageLog = new MessageLog();
            messageLog.setIsSend(Boolean.FALSE);
            List<MessageLog> messageLogList = this.messageLogService.getLogList(messageLog, true);
            for (MessageLog tempMessageLog : messageLogList) {
                this.messageLogService.addPushTask(tempMessageLog.getId());
            }
        }catch (Exception e){
            LOGGER.error("member init ：定时任务添加失败: "+e.toString());
        }
    }
}
