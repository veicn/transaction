package com.sinochem.crude.trade.member.service.impl;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.dao.MessageLogMapper;
import com.sinochem.crude.trade.member.dao.MessageLogMemberMapper;
import com.sinochem.crude.trade.member.domain.Member;
import com.sinochem.crude.trade.member.domain.MessageLog;
import com.sinochem.crude.trade.member.domain.MessageLogMember;
import com.sinochem.crude.trade.member.enums.EnumMessageLogMethod;
import com.sinochem.crude.trade.member.enums.EnumMessageLogType;
import com.sinochem.crude.trade.member.schedule.job.MessageJob;
import com.sinochem.crude.trade.member.schedule.manager.ScheduleManager;
import com.sinochem.crude.trade.member.service.MessageLogService;
import com.sinochem.crude.trade.member.service.MessageService;
import com.sinochem.crude.trade.member.service.PersonService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageUtils;
import com.sinochem.it.b2b.common.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MessageLogServiceImpl implements MessageLogService {
    private static Logger logger = LoggerFactory.getLogger(MessageLogServiceImpl.class);
    @Autowired
    private MessageLogMapper messageLogMapper;

    @Autowired
    private MessageLogMemberMapper messageLogMemberMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PersonService personService;

    private static final String CRON_DATE_FORMAT = "ss mm HH dd MM ? yyyy";

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ScheduleManager scheduleManager;

    @Override
    public List<MessageLog> getLogList(MessageLog messageLog, boolean needDetail) {
        List<MessageLog> logList = this.messageLogMapper.getLogList(messageLog);
        if (needDetail) {
            for (MessageLog log : logList) {
                log.setMessageLogMembers(messageLogMemberMapper.getLogMemberListByMessageId(log.getId()));
            }
        }
        return logList;
    }

    @Override
    public List<MessageLog> getLogList(MessageLog messageLog, PageInfo pageInfo) {
        PageUtils.page(pageInfo);
        return this.getLogList(messageLog, false);

    }


    @Override
    @Transactional
    public void add(MessageLog messageLog, MessageLogMember messageLogMember, Long memberId, String credentials, String roles, String toId, String delayTime, String periodTime) throws BizException {
        validate(messageLog, messageLogMember);
        List<Member> targetMembers = this.getReceiveTarget(messageLogMember.getMemberId(), credentials, roles, toId);

        // 立即发送设置已发送， 否则设置未发送
        if (EnumMessageLogMethod.NOW.getCode().equals(messageLog.getMethod())) {
            messageLog.setIsSend(Boolean.TRUE);
            messageLog.setCronDesc(EnumMessageLogMethod.NOW.getDesc());
        } else {
            messageLog.setIsSend(Boolean.FALSE);

            String cron = null;
            String cronDesc = null;
            if (EnumMessageLogMethod.DELAY.getCode().equals(messageLog.getMethod())) {//定时发送
                if (StringUtils.isBlank(delayTime)) throw new BizException("定时发送时间不可为空!");
                Date delayTimeDate = com.sinochem.it.b2b.common.utils.DateUtil.getDateTime(delayTime);
                cron = com.sinochem.it.b2b.common.utils.DateUtil.format(delayTimeDate, CRON_DATE_FORMAT);
                cronDesc = EnumMessageLogMethod.DELAY.getDesc() + delayTime;
            } else if (EnumMessageLogMethod.PERIOD.getCode().equals(messageLog.getMethod())) {
                if (StringUtils.isBlank(periodTime)) throw new BizException("定时发送周期不可为空!");
                cron = periodTime;
                cronDesc = EnumMessageLogMethod.PERIOD.getDesc() + periodTime;
            }
            messageLog.setCron(cron);
            messageLog.setCronDesc(cronDesc);
        }
        this.save(memberId, messageLog, targetMembers);

        if (!EnumMessageLogMethod.NOW.getCode().equals(messageLog.getMethod())) {
            this.addPushTask(messageLog.getId());
        } else {
            this.asyncBatchPush(messageLog.getId());
        }
    }

    @Override
    public MessageLog getLogDetail(Long logId) {
        if (logId == null) return null;
        MessageLog messageLog = this.messageLogMapper.selectByPrimaryKey(logId);
        messageLog.setMessageLogMembers(this.messageLogMemberMapper.getLogMemberListByMessageId(logId));
        return messageLog;
    }

    /**
     * 异步循环 邮件、短信、推送
     *
     * @param messageLogId
     */
    @Override
    public void asyncBatchPush(Long messageLogId) throws BizException {
        if (messageLogId == null) throw new BizException("messageLogId不能为空");
        final MessageLog messageLog = this.getLogDetail(messageLogId);
        for (final MessageLogMember messageLogMember : messageLog.getMessageLogMembers()) {
            final Member member = this.personService.getMemberById(messageLogMember.getMemberId());
            if (member == null) {
                messageLogMember.setSuccess(Boolean.FALSE);
                messageLogMember.setResponse("会员查询为空!");
                this.messageLogMemberMapper.updateByPrimaryKeySelective(messageLogMember);
            }

            Thread pushThread = new Thread() {
                @Override
                public void run() {
                    send(messageLog, messageLogMember);
                    logger.info("消息推送返回：" + messageLogMember.getResponse());
                    messageLogMemberMapper.updateByPrimaryKeySelective(messageLogMember);
                }
            };
            this.taskExecutor.execute(pushThread);

        }
        messageLog.setIsSend(Boolean.TRUE);
        messageLog.setUpdateTime(DateUtil.now());
        this.messageLogMapper.updateByPrimaryKeySelective(messageLog);

    }

    @Override
    public void addPushTask(Long messageLogId) throws BizException {
        if (messageLogId == null) throw new BizException("添加推送任务失败，messageLogId为空");
        MessageLog messageLog = this.getLogDetail(messageLogId);

        HashMap<String, Object> jobParam = new HashMap<String, Object>();
        jobParam.put("messageLogId", messageLog.getId());
        jobParam.put("messageLogService", this);
        this.scheduleManager.addJob(MessageJob.class, messageLog.getCron(), jobParam,
                ScheduleManager.JOB_NAME_KEY + MessageJob.class.getSimpleName() + messageLog.getId(),
                ScheduleManager.JOB_GROUP_NAME_KEY + MessageJob.class.getSimpleName() + messageLog.getId(),
                ScheduleManager.TRIGGER_NAME_KEY + MessageJob.class.getSimpleName() + messageLog.getId(),
                ScheduleManager.TRIGGER_GROUP_NAME_KEY + MessageJob.class.getSimpleName() + messageLog.getId());
    }

    /**
     * 重新发送。
     * 如果messageLogId 不为空， 直接所有receiver重新发送
     * 如果messagelogId 为空， 只重新发送单条记录
     *
     * @param memberId
     * @param messageLogId
     * @param messageLogMemberId
     * @throws BizException
     */
    @Override
    public void reSend(Long memberId, Long messageLogId, Long messageLogMemberId) throws BizException {
        if (messageLogId == null && messageLogMemberId == null)
            throw new BizException("messageLogId和messageLogMemberId不可都为空！");
        if (messageLogId != null) {
            this.asyncBatchPush(messageLogId);
        } else {
            MessageLogMember messageLogMember = this.messageLogMemberMapper.selectByPrimaryKey(messageLogMemberId);
            MessageLog messageLog = this.messageLogMapper.selectByPrimaryKey(messageLogMember.getMessageLogId());
            send(messageLog, messageLogMember);
            this.messageLogMemberMapper.updateByPrimaryKeySelective(messageLogMember);
            if (Boolean.FALSE == messageLogMember.isSuccess()) throw new BizException(messageLogMember.getResponse());
        }

    }

    private void send(MessageLog messageLog, MessageLogMember messageLogMember) {
        Member member = this.personService.getMemberById(messageLogMember.getMemberId());
        String res = "";
        boolean isSuc = true;
        if (EnumMessageLogType.MAIL.getCode().equals(messageLog.getType())) {
            if (StringUtils.isBlank(member.getEmail())) {
                res = "邮箱为空!";
                isSuc = false;
            } else {
                try {
                    res = messageService.sendMail(messageLog.getCreater(), member.getEmail(), messageLog.getContent(), messageLog.getTitle());
                } catch (BizException be) {
                    res = be.getMessage();
                    isSuc = false;
                }
            }
        } else if (EnumMessageLogType.SMS.getCode().equals(messageLog.getType())) {
            if (StringUtils.isBlank(member.getMobile())) {
                res = "手机号为空!";
            } else {
                try {
//                                res = messageService.sendSms(messageLog.getCreater(), messageLog.getContent(), member.getMobile().toString());
                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put("content", messageLog.getContent());
                    res = messageService.sendSms(messageLog.getCreater(), member.getMobile(), "plain_text", model);
                } catch (BizException be) {
                    res = be.getMessage();
                    isSuc = false;
                }
            }
        } else if (EnumMessageLogType.PUSH.getCode().equals(messageLog.getType())) {
            try {
                res = messageService.sendPush(messageLog.getCreater(), 3, messageLog.getTitle(), messageLog.getContent(), member.getId());
            } catch (BizException be) {
                res = be.getMessage();
                isSuc = false;
            }
        }
        messageLogMember.setResponse(res);
        messageLogMember.setSuccess(isSuc);
        messageLogMember.setCreateTime(DateUtil.now());
    }

    /**
     * 保存操作
     *
     * @param memberId
     * @param messageLog
     * @param targetMembers
     * @throws BizException
     */
    private void save(Long memberId, MessageLog messageLog, List<Member> targetMembers) throws BizException {
        if (memberId == null) throw new BizException("创建人不可为空！");
        if (messageLog == null || StringUtils.isBlank(messageLog.getType())) throw new BizException("推送类型不可为空！");
        Date now = com.sinochem.it.b2b.common.utils.DateUtil.now();

        messageLog.setCreater(memberId);
        messageLog.setUpdater(memberId);
        messageLog.setCreateTime(now);
        messageLog.setUpdateTime(now);
        this.messageLogMapper.insert(messageLog);
        List<MessageLogMember> messageLogMemberList = new ArrayList<MessageLogMember>();
        for (Member member : targetMembers) {
            MessageLogMember tempMessageLogMember = new MessageLogMember(messageLog.getId(), member.getId());
            messageLogMemberList.add(tempMessageLogMember);
        }
        this.messageLogMemberMapper.batchInsert(messageLogMemberList);

//        this.asyncBatchPush(memberId, targetMembers, messageLog.getType(), messageLog);
    }


    private List<Member> getReceiveTarget(Long memberId, String credentials, String roles, String toId) throws
            BizException {
        List<Member> memberList = null;
        Member memberQuery = new Member();
        if ("-1".equals(toId)) { //推送全体账号
            memberList = this.personService.selectByPrimary(memberQuery);
        } else if (!memberId.equals(-1L)) { //推送单个账号
            memberQuery.setId(memberId);
            memberList = this.personService.selectByPrimary(memberQuery);
        } else if (!"-1".equals(credentials)) { //推送企业资质下所有账号
            memberList = this.personService.selectMembersByCredential(credentials);
        } else if (!"-1".equals(roles)) { //推送角色下所有账号
            memberList = this.personService.selectMembersByRole(roles);
        } else {
            throw new BizException("无法识别的推送账号！");
        }
        if (CollectionUtils.isEmpty(memberList)) throw new BizException("选择推送对象不存在！");
        return memberList;
    }


    private void validate(MessageLog messageLog, MessageLogMember messageLogMember) throws BizException {
        String errorMsg = null;
        if (StringUtils.isBlank(messageLog.getType())) errorMsg = "请选择推送方式！";
        else if (StringUtils.isBlank(messageLog.getContent())) errorMsg = "请填写推送内容！";
        if (!StringUtils.equals(messageLog.getType(), EnumMessageLogType.SMS.getCode()) && StringUtils.isBlank(messageLog.getTitle()))
            throw new BizException("请填写标题！");
        else if (messageLogMember.getMemberId() == null) errorMsg = "请选择推送对象！";
        if (StringUtils.isNotBlank(errorMsg)) throw new BizException(errorMsg);

    }
}

