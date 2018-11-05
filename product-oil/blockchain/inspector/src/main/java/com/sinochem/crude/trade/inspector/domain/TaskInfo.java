package com.sinochem.crude.trade.inspector.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TaskInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private Long id;

    /**订单id,关联t_contract_sheet中serial_number*/
    private String deal_no;

    /**deal_uuid*/
    private String deal_uuid;

    /**被委托商检企业id */
    private Long enterprise_id;

    /**被委托商检企业名称 */
    private String enterprise_name;

    /**委托方企业id*/
    private Long appoint_enterprise_id;

    /**委托企业名称，目前是：中化泉州石化有限公司 */
    private String appoint_enterprise_name;

    /**委托商检状态：1未完成、2已完成*/
    private String status;

    /**油种*/
    private String oilseed;

    /**采样时间，界面格式：2018/08/08 12:00*/
    private Date sampling_date;

    /**罐号*/
    private BigDecimal tank_num;

    /**装期，界面格式：2018/08/08*/
    private Date laytime;

    /**是否有效(1有效0无效)*/
    private String alive_flag;

    /**创建时间*/
    private Date create_date;

    /**修改时间*/
    private Date update_date;


    /**创建人*/
    private Long createUser;

    /**修改人*/
    private Long updateUser;

    /**商检报告编号*/
    private Long insp_pre_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeal_no() {
        return deal_no;
    }

    public void setDeal_no(String deal_no) {
        this.deal_no = deal_no;
    }

    public String getDeal_uuid() {
        return deal_uuid;
    }

    public void setDeal_uuid(String deal_uuid) {
        this.deal_uuid = deal_uuid;
    }

    public Long getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(Long enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public Long getAppoint_enterprise_id() {
        return appoint_enterprise_id;
    }

    public void setAppoint_enterprise_id(Long appoint_enterprise_id) {
        this.appoint_enterprise_id = appoint_enterprise_id;
    }

    public String getAppoint_enterprise_name() {
        return appoint_enterprise_name;
    }

    public void setAppoint_enterprise_name(String appoint_enterprise_name) {
        this.appoint_enterprise_name = appoint_enterprise_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOilseed() {
        return oilseed;
    }

    public void setOilseed(String oilseed) {
        this.oilseed = oilseed;
    }

    public Date getSampling_date() {
        return sampling_date;
    }

    public void setSampling_date(Date sampling_date) {
        this.sampling_date = sampling_date;
    }

    public BigDecimal getTank_num() {
        return tank_num;
    }

    public void setTank_num(BigDecimal tank_num) {
        this.tank_num = tank_num;
    }

    public Date getLaytime() {
        return laytime;
    }

    public void setLaytime(Date laytime) {
        this.laytime = laytime;
    }

    public String getAlive_flag() {
        return alive_flag;
    }

    public void setAlive_flag(String alive_flag) {
        this.alive_flag = alive_flag;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Long getInsp_pre_id() {
        return insp_pre_id;
    }

    public void setInsp_pre_id(Long insp_pre_id) {
        this.insp_pre_id = insp_pre_id;
    }

    public Map<String, Object> toMap() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",this.id);
        map.put("deal_no",this.deal_no);
        map.put("deal_uuid",this.deal_uuid);
        map.put("enterprise_id",this.enterprise_id);
        map.put("enterprise_name",this.enterprise_name);
        map.put("appoint_enterprise_id",this.appoint_enterprise_id);
        map.put("appoint_enterprise_name",this.appoint_enterprise_name);
        map.put("status",this.status);
        map.put("oilseed",this.oilseed);
        map.put("sampling_date",this.sampling_date);
        map.put("tank_num",this.tank_num);
        map.put("laytime",this.laytime);
        map.put("alive_flag",this.alive_flag);
        map.put("create_date",this.create_date);
        map.put("update_date",this.update_date);
        map.put("createUser",this.createUser);
        map.put("updateUser",this.updateUser);
        map.put("insp_pre_id",this.insp_pre_id);

        return map;
    }
}
