package com.sinochem.crude.trade.common.base;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.utils.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 关联数据库的实体的VO的基类
 * @author Yichen Zhao
 * date: 20180224
 */
public abstract class BasePersistVO<T extends BasePO> implements Serializable{

    protected String id;

    protected String uuid;

    protected String gmtCreated;

    protected String gmtModified;

    protected String userCreated;

    protected String userModified;

    protected String aliveFlag;

    protected String version;

    public BasePersistVO() { }

    /**
     * 使用构造函数获得VO
     */
    public BasePersistVO(T domain) {
        convertToVO(domain);

        Long id = domain.getId();
        if (id != null) {
            this.id = id.toString();
        }

        String uuid = domain.getUuid();
        if (!StringUtil.isEmpty(uuid)) {
            this.uuid = uuid;
        }

        Date gmtCreated = domain.getGmtCreated();
        if (gmtCreated != null) {
            this.gmtCreated = DateUtil.formatDateTime(gmtCreated);
        }

        Date gmtModified = domain.getGmtModified();
        if (gmtModified != null) {
            this.gmtModified = DateUtil.formatDateTime(gmtModified);
        }

        Long userCreated = domain.getUserCreated();
        if (userCreated != null) {
            this.userCreated = userCreated.toString();
        }

        Long userModified = domain.getUserModified();
        if (userModified != null) {
            this.userModified = userModified.toString();
        }

        Integer aliveFlag = domain.getAliveFlag();
        if (aliveFlag != null) {
            this.aliveFlag = aliveFlag.toString();
        }

        Long version = domain.getVersion();
        if (version != null) {
            this.version = version.toString();
        }
    }

    /**
     * 使用该方法获得领域对象
     */
    public T getDomain() {
        T domain = this.convertToDomain();

        if (!StringUtil.isEmpty(id)) {
            domain.setId(Long.parseLong(id));
        }

        if (!StringUtil.isEmpty(uuid)) {
            domain.setUuid(uuid);
        }

        if (!StringUtil.isEmpty(gmtCreated)) {
            domain.setGmtCreated(DateUtil.getDateTime(gmtCreated));
        }

        if (!StringUtil.isEmpty(gmtModified)) {
            domain.setGmtModified(DateUtil.getDateTime(gmtModified));
        }

        if (!StringUtil.isEmpty(userCreated)) {
            domain.setUserCreated(Long.parseLong(userCreated));
        }

        if (!StringUtil.isEmpty(userModified)) {
            domain.setUserModified(Long.parseLong(userModified));
        }

        if (!StringUtil.isEmpty(aliveFlag)) {
            domain.setAliveFlag(Integer.parseInt(aliveFlag));
        }

        if (!StringUtil.isEmpty(version)) {
            domain.setVersion(Long.parseLong(version));
        }

        return domain;
    }

    /**
     * 将领域类转化为当前VO对象
     */
    protected abstract void convertToVO(T domain);

    /**
     * 将当前VO对象转化为领域类
     */
    protected abstract T convertToDomain();

    /** getters and setters */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(String gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserModified() {
        return userModified;
    }

    public void setUserModified(String userModified) {
        this.userModified = userModified;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
