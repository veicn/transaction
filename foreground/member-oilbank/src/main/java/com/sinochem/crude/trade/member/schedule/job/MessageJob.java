package com.sinochem.crude.trade.member.schedule.job;

import com.sinochem.crude.trade.member.service.MessageLogService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 推送 定时任务
 */
public class MessageJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
            MessageLogService messageLogService = (MessageLogService) jobDataMap.get("messageLogService");
            Long messageLogId = (Long) jobDataMap.get("messageLogId");
            messageLogService.asyncBatchPush(messageLogId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
        }
    }
}
