package com.sinochem.crude.trade.shipping.constant;

/**
 * @author WGP
 * @descriptioncrude-trade-product_oil
 * @date 2018/4/12
 **/
public class WeChatInfo {

	public static final String PRO_CONFIRM_SHIP_AGREEMENT_EN_ONE = "Dear Customer: Your Charter Party (NO.{confirmationSheetCd},Product:{product},Quantity:{quantity})was confirmed, please check details.";
	public static final String PRO_CONFIRM_SHIP_AGREEMENT_EN_TWO = "Dear Customer: We are sorry to inform you that your Charter Party (NO.{confirmationSheetCd},Product:{product},Quantity：{quantity})was terminated, please check details.";

	public static final String PRO_CONFIRM_SHIP_CONFIRMATION_EN_ONE = "Dear Customer: Your Vessel Acceptance (NO.{confirmationSheetCd},Product:{product},Quantity:{quantity})was confirmed, please check details.";
	public static final String PRO_CONFIRM_SHIP_CONFIRMATION_EN_TWO = "Dear Customer: We are sorry to inform you that your Vessel Acceptance(NO.{confirmationSheetCd},Product:{product},Quantity：{quantity})was terminated, please check details.";

	public static final String PRO_CONFIRMATION_SHIP_CONFIRM_EN_ONE = "Dear Customer:  You were appionted as the shipping agent of the shipment (Vessel's name: {vesselname},Product:{product},Quantity:{quantity},Loading range:{laycanStrat}——{laycanEnd})，please  check details.";
	public static final String PRO_CONFIRMATION_SHIP_CONFIRM_EN_TWO = "Dear Customer: The quantity of order{orderNumber}(Product:{product},Quantity:{quantity}) is confirmed, please send DI.";

	public static final String PRO_CREATE_SHIP_AGREEMENT_EN = "Dear Customer: {buyer} uploaded a Charter Party (NO.:{agreementCd},Product:{product}, Quantity:{quantity}), please check details.";

	public static final String PRO_CREATE_SHIP_CONFIRMATION_EN = "Dear Customer:{enterprise}:uploaded a Vessel's name:{vesselname}(NO.{confirmationSheetCd},Product:{product},Quantity:{quantity}), please check details.";

	public static final String PRO_CREATE_SHIP_CONFIRMATION_NOCONFIRM_EN_ONE = "Dear Customer: {merchantNm} uploaded a Vessel's name Acceptance(NO.:{confirmationCD},Product:{product}, Quantity:{quantity}), please check details.";
	public static final String PRO_CREATE_SHIP_CONFIRMATION_NOCONFIRM_EN_TWO = "Dear Customer: You were appionted as the shipping agent of the shipment (Vessel's name:{vesselname},{product}, Quantity:{quantity},Loading range:{laycan})，please  check details.";
	public static final String PRO_CREATE_SHIP_CONFIRMATION_NOCONFIRM_EN_THREE = "Dear Customer: The quantity of Order:{orderNumber}(Product:{product}, Quantity:{quantity}) was confirmed, please send DI.";
	public static final String PRO_CREATE_SHIP_CONFIRMATION_NOCONFIRM_EN_FOUR = "Dear Customer: The DI of Order:{orderNumber}(Product:{product}, Quantity:{quantity}) was confirmed, please prepare documents for CIQ and CHINA CUSTOMS.";

	public static final String PRO_PRESENT_NOR_EN= "Dear Customer: Your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product},Quantity:{quantity}) tendered NOR at {nor}, please check details.";

	public static final String PRO_SHIP_COMPLETED_EN = "Dear Customer: The yoyage of your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product}, Quantity:{quantity}) was completed, please check details.";

	public static final String PRO_UPDATE_LOADING_PROGRESS_EN = "Dear Customer: Your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product}, Quantity:{quantity})  has new loading progress information, please check details.";

	public static final String PRO_UPDATE_PORT_INSPECTION_EN = "Dear Customer: Your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product}, Quantity:{quantity})  was checked by CIQ and Independent Inspection , Please check details.";

	public static final String PRO_UPDATE_SHIP_VOYAGE_EN = "Dear Customer: Your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product}, Quantity:{quantity})has new voyage tracking information, please check details.";

	public static final String PRO_COMMENCED_LOADING_EN = "[mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product}, Quantity:{quantity}) completed loading at {completedLoading}, please check details.";
	public static final String PRO_ALL_LINES_CAST_OFF_EN = "[mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product}, Quantity:{quantity}) sailed at {sailed}, please check details.";
	public static final String PRO_APPOINT_DEPA_EN = "[mycrudeoil.com] Dear Customer: You were appionted as the shipping agent of the shipment (Vessel's name:{vesselname},Product:{product},Quantity:{quantity},Loading laycan:{laycan})，please check details. <a href=\"{hyperlink}\">Please click the following website to check.</a>";
	public static final String PRO_CARGO_DOCUMENTS_ON_BOARD_EN_ONE = "[mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product}, Quantity:{quantity}) was on board , please check details.";
	public static final String PRO_CARGO_DOCUMENTS_ON_BOARD_EN_TWO= "[mycrudeoil.com]Dear Customer:Your orderId:{orderNumber}(Product:{product}, Quantity:{quantity}) was loaded，please issue bill of lading.";
	public static final String PRO_ALL_FAST_EN_ONE = "[mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product}, Quantity:{quantity}) was all fast at:{allFast} , please check details.";
	public static final String PRO_ALL_FAST_EN_TWO = "[mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product}, Quantity:{quantity}) will be loading，please confirm the draft bill of lading.";
	public static final String PRO_COMPLETED_LOADING_EN = "[mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product}, Quantity:{quantity}) commenced loading at {commencedLoading}, please check details.";
	public static final String PRO_CREATE_SHIP_DEMANDS_EN = "[mycrudeoil.com]Dear Customer: {buyer} uploaded a Charter Party (NO.:{demandsCd},Product:{product}, Quantity:{quantity}), please check details. <a href=\"{hyperlink}\">Please click the following website to check.</a>";
	public static final String PRO_DISPONENT_OWNER_UPDATE_STOWAGE_PLAN_EN = "[mycrudeoil.com]Dear Customer:The DI of Your order:{orderNumber} (Product:{product},Quantity:{quantity}) was updated, please check details.";
	public static final String PRO_DISPONENT_OWNER_UPDATE_TRANSIT_EN = "[mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product}, Quantity:{quantity}) has new voyage tracking information, please check details.";
	public static final String PRO_DISPONENT_OWNER_UPDATE_UNLOADING_EN = "[mycrudeoil.com]Dear Customer: Your shipment (Shipment NO.{confirmationSheetCd} ,Vessel's name:{vesselname},Product:${product},Quantity:{quantity}) was discharged, please check details.";
	public static final String PRO_MERCHANT_UPDATE_TRANSIT_EN = "[mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:{confirmationSheetCd}, Vessel's name:{vesselname}, Product:{product}, Quantity:{quantity}) has new voyage tracking information, please check details.";
	public static final String PRO_MERCHANT_UPDATE_UNLOADING_EN = "[mycrudeoil.com]Dear Customer: Your shipment (Shipment NO.{confirmationSheetCd} ,Vessel's name:{vesselname},Product:${product},Quantity:{quantity}) was discharged, please check details.";
	public static final String PRO_UPDATE_PLAN_EN = "[mycrudeoil.com]Dear Customer:The DI of Your order:{orderNumber} (Product:{product},Quantity:{quantity}) was updated, please check details.";

}
