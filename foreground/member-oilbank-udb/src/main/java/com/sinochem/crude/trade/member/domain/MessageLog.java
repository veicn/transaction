package com.sinochem.crude.trade.member.domain;

import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

public class MessageLog {
    private Long id;

    private String type;

    private String title;

    private String method;

    private String cron;

    private String cronDesc;

    private Boolean isSend;

    private boolean delFlg;

    private Date createTime;

    private Long creater;

    private Date updateTime;

    private Long updater;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron == null ? null : cron.trim();
    }

    public String getCronDesc() {
        return cronDesc;
    }

    public void setCronDesc(String cronDesc) {
        this.cronDesc = cronDesc == null ? null : cronDesc.trim();
    }

    public Boolean getIsSend() {
        return isSend;
    }

    public void setIsSend(Boolean isSend) {
        this.isSend = isSend;
    }

    public boolean getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(boolean delFlg) {
        this.delFlg = delFlg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public String getContent() {
        String value = StringUtils.isBlank(content) ? content : content.replace("&lt;", "<").replace("&gt;", ">");
        return value;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


    private List<MessageLogMember> messageLogMembers;

    public List<MessageLogMember> getMessageLogMembers() {
        return messageLogMembers;
    }

    public void setMessageLogMembers(List<MessageLogMember> messageLogMembers) {
        this.messageLogMembers = messageLogMembers;
    }
}