package com.sinochem.crude.trade.member.schedule.manager;

import com.sinochem.it.b2b.common.exception.BizException;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class ScheduleManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleManager.class);

    public static final String JOB_NAME_KEY = "JOB_NAME_KEY";
    public static final String JOB_GROUP_NAME_KEY = "JOB_GROUP_NAME_KEY";
    public static final String TRIGGER_NAME_KEY = "TRIGGER_NAME_KEY";
    public static final String TRIGGER_GROUP_NAME_KEY = "TRIGGER_GROUP_NAME_KEY";

    @Autowired
    private SchedulerFactory schedulerFactory;

    /**
     * @param jobClass 任务
     * @param cron     不用多说 (ノへ￣、)
     * @param param    jobClass 执行任务时需要的参数
     * @Description: 添加一个定时任务
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void addJob(Class jobClass, String cron, HashMap<String, Object> param, String jobName, String jobGroupName, String triggerName, String triggerGroupName) throws BizException {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            // 任务名，任务组，任务执行类
            JobBuilder jobBuilder = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName);
            //添加参数
            JobDataMap jobDataMap = new JobDataMap(param);
            jobBuilder.setJobData(jobDataMap);
            JobDetail jobDetail = jobBuilder.build();

            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            sched.scheduleJob(jobDetail, trigger);

            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (ObjectAlreadyExistsException oe) {
//            oe.printStackTrace();
            LOGGER.warn("定时任务已存在!");
            throw new BizException("定时任务已存在,请勿重复添加!");
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
            throw new BizException("cron表达式不正确！");
        }catch (SchedulerException se) {
//            e.printStackTrace();
            LOGGER.error(se.toString());
            throw new BizException("定时任务触发时间错误！");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
            throw new BizException("定时任务添加失败！");
        }
    }

    /**
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @Description: 移除一个任务
     */
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) throws BizException {
        try {
            Scheduler sched = schedulerFactory.getScheduler();

            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

            sched.pauseTrigger(triggerKey);// 停止触发器
            sched.unscheduleJob(triggerKey);// 移除触发器
            sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
//            throw new RuntimeException(e);
        }
    }
}
