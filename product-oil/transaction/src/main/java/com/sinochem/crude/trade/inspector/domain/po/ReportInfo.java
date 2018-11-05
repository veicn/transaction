package com.sinochem.crude.trade.inspector.domain.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private Long id;

    /**附件uuid*/
    private String uuid;

    /**提单量（吨）*/
    private BigDecimal bill_lading_ton;

    /**提单量（桶）*/
    private BigDecimal bill_lading_bucket;

    /**船检量（桶）*/
    private BigDecimal ship_inspection_bucket;

    /**提单日期*/
    private Date bill_lading_date;

    /**汽油密度 */
    private BigDecimal gasoline_density;

    /**汽油终馏点*/
    private BigDecimal gasoline_boiling_point;

    /**汽油蒸汽压*/
    private BigDecimal gasoline_vapor_tension;

    /**汽油诱导期*/
    private BigDecimal gasoline_induction_period;

    /**汽油硫含量*/
    private BigDecimal gasoline_sulfur_content;

    /**汽油含氧量*/
    private BigDecimal gasoline_oxygen_content;

    /**汽油胶质*/
    private BigDecimal gasoline_gum;

    /**汽油镏程*/
    private BigDecimal gasoline_distillation;

    /**汽油氧化安定性*/
    private BigDecimal gasoline_oxidation_stability;

    /**汽油水溶性酸或碱*/
    private BigDecimal gasoline_acid_alkali;

    /**汽油辛烷值（RON研究法）*/
    private BigDecimal gasoline_octane_number;

    /**柴油密度*/
    private BigDecimal dieseloil_density;

    /**柴油十六烷值*/
    private BigDecimal dieseloil_cetane_value;

    /**柴油闪点*/
    private BigDecimal dieseloil_flash_point;

    /**柴油铜片腐蚀*/
    private BigDecimal dieseloil_copper_corrosion;

    /**柴油闭口闪点*/
    private BigDecimal dieseloil_closing_flash;

    /**柴油酸度*/
    private BigDecimal dieseloil_acidity;

    /**柴油水分*/
    private BigDecimal dieseloil_water_cnteont;

    /**柴油运动粘度*/
    private BigDecimal dieseloil_viscosity;

    /**联系人*/
    private String linkman;

    /**联系电话*/
    private String contact_number;

    /**备注*/
    private String remark;

    /**状态（暂存0，已提交1）*/
    private String state;

    /**订单id*/
    private String deal_no;

    /**商检公司id*/
    private String inspector_id;

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

    /**商检单据编号*/
    private String billno;


    /**商检任务主键id*/
    private String insp_app_id;

    public String getInsp_app_id() {
        return insp_app_id;
    }

    public void setInsp_app_id(String insp_app_id) {
        this.insp_app_id = insp_app_id;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getBill_lading_ton() {
        return bill_lading_ton;
    }

    public void setBill_lading_ton(BigDecimal bill_lading_ton) {
        this.bill_lading_ton = bill_lading_ton;
    }

    public BigDecimal getBill_lading_bucket() {
        return bill_lading_bucket;
    }

    public void setBill_lading_bucket(BigDecimal bill_lading_bucket) {
        this.bill_lading_bucket = bill_lading_bucket;
    }

    public BigDecimal getShip_inspection_bucket() {
        return ship_inspection_bucket;
    }

    public void setShip_inspection_bucket(BigDecimal ship_inspection_bucket) {
        this.ship_inspection_bucket = ship_inspection_bucket;
    }

    public Date getBill_lading_date() {
        return bill_lading_date;
    }

    public void setBill_lading_date(Date bill_lading_date) {
        this.bill_lading_date = bill_lading_date;
    }

    public BigDecimal getGasoline_density() {
        return gasoline_density;
    }

    public void setGasoline_density(BigDecimal gasoline_density) {
        this.gasoline_density = gasoline_density;
    }

    public BigDecimal getGasoline_boiling_point() {
        return gasoline_boiling_point;
    }

    public void setGasoline_boiling_point(BigDecimal gasoline_boiling_point) {
        this.gasoline_boiling_point = gasoline_boiling_point;
    }

    public BigDecimal getGasoline_vapor_tension() {
        return gasoline_vapor_tension;
    }

    public void setGasoline_vapor_tension(BigDecimal gasoline_vapor_tension) {
        this.gasoline_vapor_tension = gasoline_vapor_tension;
    }

    public BigDecimal getGasoline_induction_period() {
        return gasoline_induction_period;
    }

    public void setGasoline_induction_period(BigDecimal gasoline_induction_period) {
        this.gasoline_induction_period = gasoline_induction_period;
    }

    public BigDecimal getGasoline_sulfur_content() {
        return gasoline_sulfur_content;
    }

    public void setGasoline_sulfur_content(BigDecimal gasoline_sulfur_content) {
        this.gasoline_sulfur_content = gasoline_sulfur_content;
    }

    public BigDecimal getGasoline_oxygen_content() {
        return gasoline_oxygen_content;
    }

    public void setGasoline_oxygen_content(BigDecimal gasoline_oxygen_content) {
        this.gasoline_oxygen_content = gasoline_oxygen_content;
    }

    public BigDecimal getGasoline_gum() {
        return gasoline_gum;
    }

    public void setGasoline_gum(BigDecimal gasoline_gum) {
        this.gasoline_gum = gasoline_gum;
    }

    public BigDecimal getGasoline_distillation() {
        return gasoline_distillation;
    }

    public void setGasoline_distillation(BigDecimal gasoline_distillation) {
        this.gasoline_distillation = gasoline_distillation;
    }

    public BigDecimal getGasoline_oxidation_stability() {
        return gasoline_oxidation_stability;
    }

    public void setGasoline_oxidation_stability(BigDecimal gasoline_oxidation_stability) {
        this.gasoline_oxidation_stability = gasoline_oxidation_stability;
    }

    public BigDecimal getGasoline_acid_alkali() {
        return gasoline_acid_alkali;
    }

    public void setGasoline_acid_alkali(BigDecimal gasoline_acid_alkali) {
        this.gasoline_acid_alkali = gasoline_acid_alkali;
    }

    public BigDecimal getGasoline_octane_number() {
        return gasoline_octane_number;
    }

    public void setGasoline_octane_number(BigDecimal gasoline_octane_number) {
        this.gasoline_octane_number = gasoline_octane_number;
    }

    public BigDecimal getDieseloil_density() {
        return dieseloil_density;
    }

    public void setDieseloil_density(BigDecimal dieseloil_density) {
        this.dieseloil_density = dieseloil_density;
    }

    public BigDecimal getDieseloil_cetane_value() {
        return dieseloil_cetane_value;
    }

    public void setDieseloil_cetane_value(BigDecimal dieseloil_cetane_value) {
        this.dieseloil_cetane_value = dieseloil_cetane_value;
    }

    public BigDecimal getDieseloil_flash_point() {
        return dieseloil_flash_point;
    }

    public void setDieseloil_flash_point(BigDecimal dieseloil_flash_point) {
        this.dieseloil_flash_point = dieseloil_flash_point;
    }

    public BigDecimal getDieseloil_copper_corrosion() {
        return dieseloil_copper_corrosion;
    }

    public void setDieseloil_copper_corrosion(BigDecimal dieseloil_copper_corrosion) {
        this.dieseloil_copper_corrosion = dieseloil_copper_corrosion;
    }

    public BigDecimal getDieseloil_closing_flash() {
        return dieseloil_closing_flash;
    }

    public void setDieseloil_closing_flash(BigDecimal dieseloil_closing_flash) {
        this.dieseloil_closing_flash = dieseloil_closing_flash;
    }

    public BigDecimal getDieseloil_acidity() {
        return dieseloil_acidity;
    }

    public void setDieseloil_acidity(BigDecimal dieseloil_acidity) {
        this.dieseloil_acidity = dieseloil_acidity;
    }

    public BigDecimal getDieseloil_water_cnteont() {
        return dieseloil_water_cnteont;
    }

    public void setDieseloil_water_cnteont(BigDecimal dieseloil_water_cnteont) {
        this.dieseloil_water_cnteont = dieseloil_water_cnteont;
    }

    public BigDecimal getDieseloil_viscosity() {
        return dieseloil_viscosity;
    }

    public void setDieseloil_viscosity(BigDecimal dieseloil_viscosity) {
        this.dieseloil_viscosity = dieseloil_viscosity;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDeal_no() {
        return deal_no;
    }

    public void setDeal_no(String deal_no) {
        this.deal_no = deal_no;
    }

    public String getInspector_id() {
        return inspector_id;
    }

    public void setInspector_id(String inspector_id) {
        this.inspector_id = inspector_id;
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

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public Map<String, Object> toMap() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",this.id);
        map.put("uuid",this.uuid);
        map.put("bill_lading_ton",this.bill_lading_ton);
        map.put("bill_lading_bucket",this.bill_lading_bucket);
        map.put("ship_inspection_bucket",this.ship_inspection_bucket);
        map.put("bill_lading_date",this.bill_lading_date);
        map.put("gasoline_density",this.gasoline_density);
        map.put("gasoline_boiling_point",this.gasoline_boiling_point);
        map.put("gasoline_vapor_tension",this.gasoline_vapor_tension);
        map.put("gasoline_induction_period",this.gasoline_induction_period);
        map.put("gasoline_sulfur_content",this.gasoline_sulfur_content);
        map.put("gasoline_oxygen_content",this.gasoline_oxygen_content);
        map.put("gasoline_gum",this.gasoline_gum);
        map.put("gasoline_distillation",this.gasoline_distillation);
        map.put("gasoline_oxidation_stability",this.gasoline_oxidation_stability);
        map.put("gasoline_acid_alkali",this.gasoline_acid_alkali);
        map.put("gasoline_octane_number",this.gasoline_octane_number);
        map.put("dieseloil_density",this.dieseloil_density);
        map.put("dieseloil_cetane_value",this.dieseloil_cetane_value);
        map.put("dieseloil_flash_point",this.dieseloil_flash_point);
        map.put("dieseloil_copper_corrosion",this.dieseloil_copper_corrosion);
        map.put("dieseloil_closing_flash",this.dieseloil_closing_flash);
        map.put("dieseloil_acidity",this.dieseloil_acidity);
        map.put("dieseloil_water_cnteont",this.dieseloil_water_cnteont);
        map.put("dieseloil_viscosity",this.dieseloil_viscosity);
        map.put("linkman",this.linkman);
        map.put("contact_number",this.contact_number);
        map.put("remark",this.remark);
        map.put("state",this.state);
        map.put("deal_no",this.deal_no);
        map.put("inspector_id",this.inspector_id);
        map.put("alive_flag",this.alive_flag);
        map.put("create_date",this.create_date);
        map.put("update_date",this.update_date);
        map.put("createUser",this.createUser);
        map.put("updateUser",this.updateUser);
        map.put("billno",this.billno);
        map.put("insp_app_id",this.insp_app_id);

        return map;
    }
}
