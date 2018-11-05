package com.sinochem.crude.trade.shipping.enums;

/**
 * @author WGP
 * @descriptioncrude-trade-product_oil
 * @date 2018/4/10
 **/
public enum NotificationEnums {

    //新增租船需求提醒
    PRO_CREATE_SHIP_DEMANDS_EN("pro_create_ship_demands_en", "新增租船需求提醒", "New demand for chartering ships"),
    
    //二船成提交租船协议提醒
    PRO_PRO_CREATE_SHIP_AGREEMENT_EN("pro_create_ship_agreement_en", "二船东提交租船协议提醒", "The chartering agreement is a reminder"),
    
    //租船协议确认/中止提醒
    PRO_CONFIRM_SHIP_AGREEMENT_EN("pro_confirm_ship_agreement_en", "租船协议确认/中止提醒", "The chartering agreement is confirmed/discontinue"),

    //生成租船确认单提醒(不需要确认)
    PRO_CREATE_SHIP_CONFIRMATION_NOCONFIRM_EN("pro_create_ship_confirmation_noconfirm_en", "生成租船确认单提醒", "Generate chartering confirmation note."),
    
    //生成租船确认单提醒
    PRO_CREATE_SHIP_CONFIRMATION_EN("pro_create_ship_confirmation_en", "生成租船确认单提醒", "Generate chartering confirmation note."),

    //租船确认单确认提醒
    PRO_CONFIRMATION_SHIP_CONFIRM_EN("pro_confirmation_ship_confirm_en", "租船确认单确认提醒", "Chartering order confirmation note"),

    //租船确认单确认/中止提醒
    PRO_CONFIRM_SHIP_CONFIRMATION_EN("pro_confirm_ship_confirmation_en", "租船确认单确认/中止提醒", "Chartering order confirmation/stop reminder."),
    
    //二船东更新配载计划提醒
    PRO_DISPONENT_OWNER_UPDATE_STOWAGE_PLAN_EN("pro_disponent_owner_update_stowage_plan_en", "二船东更新配载计划提醒", "Update the stowage plan reminder."),
    
    //贸易商更新配载计划提醒
    PRO_UPDATE_PLAN_EN("pro_update_plan_en", "贸易商更新配载计划提醒", "Update the stowage plan reminder."),
    
    //船代更新船程信息提醒
    PRO_SHIPPING_AGENT_UPDATE_SHIP_PLAN_EN("pro_shipping_agent_update_ship_plan_en", "船代更新船程信息提醒", "Update shipping information reminder."),

    //维护装港递交NOR提醒
    PRO_PRESENT_NOR_EN("pro_present_nor_en", "维护装港递交NOR提醒", "The maintenance loading port is sent NOR reminded."),

    //维护装港靠泊提醒
    PRO_ALL_FASE_EN("pro_all_fast_en", "维护装港靠泊提醒", "Maintenance of loading port."),

    //维护装港“商检联检”提醒
    PRO_UPDATE_PORT_INSPECTION_EN("pro_update_port_inspection_en", "维护装港商检联检提醒", "To maintain the inspection and inspection of port inspection."),

    //维护装港开始装货提醒
    PRO_COMMENCED_LOADING_EN("pro_commenced_loading_en", "维护装港开始装货提醒", "Maintenance loading port to start loading reminder."),

    //维护装港“更新装货进度”提醒
    PRO_UPDATE_LOADING_PROGRESS_EN("pro_update_loading_progress_en", "维护装港更新装货进度提醒", "Update loading progress reminder."),

    //维护装港装货完毕提醒
    PRO_COMPLETED_LOADING_EN("pro_completed_loading_en", "维护装港装货完毕提醒", "Reminder of loading and unloading of loading port."),

    //维护装港“商检文件到船”提醒
    PRO_CARGO_DOCUMENTS_ON_BOARD_EN("pro_cargo_documents_on_board_en", "维护装港商检文件到船提醒", "Maintain port inspection documents to ship reminder."),

    //维护装港船舶离泊提醒
    PRO_ALL_LINES_CAST_OFF_EN("pro_all_lines_cast_off_en", "维护装港船舶离泊提醒", "Maintenance of ship's departure reminder."),

    //二船东更新在途信息提醒
    PRO_DISPONENT_OWNER_UPDATE_TRANSIT_EN("pro_disponent_owner_update_transit_en", "二船东更新在途信息提醒", "Update the in-transit information alert."),

    //贸易商更新在途信息提醒
    PRO_MERCHANT_UPDATE_TRANSIT_EN("pro_merchant_update_transit_en", "贸易商更新在途信息提醒", "Update the in-transit information alert."),

    //二船东更新卸港信息提醒
    PRO_DISPONENT_OWNER_UPDATE_UNLOADING_EN("pro_disponent_owner_update_unloading_en", "二船东更新卸港信息提醒", "Update the unloading information reminder."),

    //贸易商更新卸港信息提醒
    PRO_MERCHANT_UPDATE_UNLOADING_EN("pro_merchant_update_unloading_en", "贸易商更新卸港信息提醒", "Update the unloading information reminder."),

    //贸易商确认航次结束提醒
    PRO_SHIP_COMPLETED_EN("pro_ship_completed_en", "贸易商确认航次结束提醒", "Confirm that the voyage is over.");


    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 中文标题
     */
    private String zhTitle;

    /**
     * 英文标题
     */
    private String enTitle;

    NotificationEnums(String templateName, String zhTitle, String enTitle) {
        this.templateName = templateName;
        this.zhTitle = zhTitle;
        this.enTitle = enTitle;
    }

    /** getters */
    public String getZhTitle() {
        return zhTitle;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public String getTemplateName() {
        return templateName;
    }


}
