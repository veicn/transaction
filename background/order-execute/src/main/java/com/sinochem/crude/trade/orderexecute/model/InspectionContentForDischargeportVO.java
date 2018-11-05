package com.sinochem.crude.trade.orderexecute.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 商检详情信息（卸港）
 * @author me
 *
 */
public class InspectionContentForDischargeportVO {
	@Expose //没有此注解的属性不会被序列化 
	@SerializedName("a1") //序列化后的名称 
	private String voyageSummary_vef;
	
	@Expose
	@SerializedName("a2")
	private String voyageSummary_blDate;
	
	@Expose
	@SerializedName("a3")
	private String voyageSummary_joblocation;
	
	@Expose
	@SerializedName("a4")
	private String voyageSummary_operator;
	
	@Expose
	@SerializedName("a5")
	private String voyageSummary_attachmentsOfSurveyorReports;
	
	@Expose
	@SerializedName("a6")
	private String voyageSummary_surveyorAppointment;
	
	@Expose
	@SerializedName("a7")
	private String voyageSummary_stowagePlan;
	
	@Expose
	@SerializedName("a8")
	private String voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_method;
	
	@Expose
	@SerializedName("a9")
	private String voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_bbl;
	
	@Expose
	@SerializedName("a10")
	private String voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_mt;
	
	@Expose
	@SerializedName("a11")
	private String voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_method;
	
	@Expose
	@SerializedName("a12")
	private String voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_bbl;
	
	@Expose
	@SerializedName("a13")
	private String voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_mt;
	
	@Expose
	@SerializedName("a14")
	private String voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_method;
	
	@Expose
	@SerializedName("a15")
	private String voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl;
	
	@Expose
	@SerializedName("a16")
	private String voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_mt;
	
	@Expose
	@SerializedName("a17")
	private String voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_method;
	
	@Expose
	@SerializedName("a18")
	private String voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_bbl;
	
	@Expose
	@SerializedName("a19")
	private String voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_mt;
	
	@Expose
	@SerializedName("b1")
	
	private String quantityResults_method;
	
	@Expose
	@SerializedName("b2")
	private String quantityResults_density15C;
	
	@Expose
	@SerializedName("b3")
	private String quantityResults_api;
	
	@Expose
	@SerializedName("b4")
	private String quantityResults_temp;
	
	@Expose
	@SerializedName("b5")
	private String quantityResults_water;
	
	@Expose
	@SerializedName("b6")
	private String quantityResults_sediment;
	
	@Expose
	@SerializedName("b7")
	private String quantityResults_nominatedShoreTanks;
	
	@Expose
	@SerializedName("b8")
	private String quantityResults_BLTCV_BBL60F;
	
	@Expose
	@SerializedName("b9")
	private String quantityResults_BLTCV_M3at15C;
	
	@Expose
	@SerializedName("b10")
	private String quantityResults_BLTCV_Mtinair;
	
	@Expose
	@SerializedName("b11")
	private String quantityResults_BLTCV_MtTonnesVac;
	
	@Expose
	@SerializedName("b12")
	private String quantityResults_BLGCV_BBL60F;
	
	@Expose
	@SerializedName("b13")
	private String quantityResults_BLGCV_M3at15C;
	
	@Expose
	@SerializedName("b14")
	private String quantityResults_BLGCV_Mtinair;
	
	@Expose
	@SerializedName("b15")
	private String quantityResults_BLGCV_MtTonnesVac;
	
	@Expose
	@SerializedName("b16")
	private String quantityResults_BLNCV_BBL60F;
	
	@Expose
	@SerializedName("b17")
	private String quantityResults_BLNCV_M3at15C;
	
	@Expose
	@SerializedName("b18")
	private String quantityResults_BLNCV_Mtinair;
	
	@Expose
	@SerializedName("b19")
	private String quantityResults_BLNCV_MtTonnesVac;
	
	@Expose
	@SerializedName("b20")
	private String quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_BBL60F;
	
	@Expose
	@SerializedName("b21")
	private String quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_M3at15C;
	
	@Expose
	@SerializedName("b22")
	private String quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_Mtinair;
	
	@Expose
	@SerializedName("b23")
	private String quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_MtTonnesVac;
	
	@Expose
	@SerializedName("b24")
	private String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_BBL60F;
	
	@Expose
	@SerializedName("b25")
	private String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_M3at15C;
	
	@Expose
	@SerializedName("b26")
	private String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_Mtinair;
	
	@Expose
	@SerializedName("b27")
	private String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_MtTonnesVac;
	
	@Expose
	@SerializedName("b28")
	private String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_BBL60F;
	
	@Expose
	@SerializedName("b29")
	private String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_M3at15C;
	
	@Expose
	@SerializedName("b30")
	private String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_Mtinair;
	
	@Expose
	@SerializedName("b31")
	private String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_MtTonnesVac;
	
	@Expose
	@SerializedName("b32")
	private String quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_BBL60F;
	
	@Expose
	@SerializedName("b33")
	private String quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_M3at15C;
	
	@Expose
	@SerializedName("b34")
	private String quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_Mtinair;
	
	@Expose
	@SerializedName("b35")
	private String quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_MtTonnesVac;
	
	@Expose
	@SerializedName("b36")
	private String quantityResults_dischargeVolumesSummary_ROBOilTotal_BBL60F;
	
	@Expose
	@SerializedName("b37")
	private String quantityResults_dischargeVolumesSummary_ROBOilTotal_M3at15C;
	
	@Expose
	@SerializedName("b38")
	private String quantityResults_dischargeVolumesSummary_ROBOilTotal_Mtinair;
	
	@Expose
	@SerializedName("b39")
	private String quantityResults_dischargeVolumesSummary_ROBOilTotal_MtTonnesVac;
	
	@Expose
	@SerializedName("b40")
	private String quantityResults_dischargeVolumesSummary_ROBliquid_BBL60F;
	
	@Expose
	@SerializedName("b41")
	private String quantityResults_dischargeVolumesSummary_ROBliquid_M3at15C;
	
	@Expose
	@SerializedName("b42")
	private String quantityResults_dischargeVolumesSummary_ROBliquid_Mtinair;
	
	@Expose
	@SerializedName("b43")
	private String quantityResults_dischargeVolumesSummary_ROBliquid_MtTonnesVac;
	
	@Expose
	@SerializedName("b44")
	private String quantityResults_dischargeVolumesSummary_ROBNonLiquid_BBL60F;
	
	@Expose
	@SerializedName("b45")
	private String quantityResults_dischargeVolumesSummary_ROBNonLiquid_M3at15C;
	
	@Expose
	@SerializedName("b46")
	private String quantityResults_dischargeVolumesSummary_ROBNonLiquid_Mtinair;
	
	@Expose
	@SerializedName("b47")
	private String quantityResults_dischargeVolumesSummary_ROBNonLiquid_MtTonnesVac;
	
	@Expose
	@SerializedName("b48")
	private String quantityResults_dischargeVolumesSummary_ROBFreeWater_BBL60F;
	
	@Expose
	@SerializedName("b49")
	private String quantityResults_dischargeVolumesSummary_ROBFreeWater_M3at15C;
	
	@Expose
	@SerializedName("b50")
	private String quantityResults_dischargeVolumesSummary_ROBFreeWater_Mtinair;
	
	@Expose
	@SerializedName("b51")
	private String quantityResults_dischargeVolumesSummary_ROBFreeWater_MtTonnesVac;
	
	@Expose
	@SerializedName("b52")
	private String quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_BBL60F;
	
	@Expose
	@SerializedName("b53")
	private String quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_M3at15C;
	
	@Expose
	@SerializedName("b54")
	private String quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_Mtinair;
	
	@Expose
	@SerializedName("b55")
	private String quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_MtTonnesVac;
	
	@Expose
	@SerializedName("b56")
	private String quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_BBL60F;
	
	@Expose
	@SerializedName("b57")
	private String quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_M3at15C;
	
	@Expose
	@SerializedName("b58")
	private String quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_Mtinair;
	
	@Expose
	@SerializedName("b59")
	private String quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_MtTonnesVac;
	
	@Expose
	@SerializedName("b60")
	private String quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_BBL60F;
	
	@Expose
	@SerializedName("b61")
	private String quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_M3at15C;
	
	@Expose
	@SerializedName("b62")
	private String quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_Mtinair;
	
	@Expose
	@SerializedName("b63")
	private String quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_MtTonnesVac;
	
	@Expose
	@SerializedName("b64")
	private String quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_BBL60F;
	
	@Expose
	@SerializedName("b65")
	private String quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_M3at15C;
	
	@Expose
	@SerializedName("b66")
	private String quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_Mtinair;
	
	@Expose
	@SerializedName("b67")
	private String quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_MtTonnesVac;
	
	@Expose
	@SerializedName("b68")
	private String quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_BBL60F;
	
	@Expose
	@SerializedName("b69")
	private String quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_M3at15C;
	
	@Expose
	@SerializedName("b70")
	private String quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_Mtinair;
	
	@Expose
	@SerializedName("b71")
	private String quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_MtTonnesVac;
	
	@Expose
	@SerializedName("b72")
	private String quantityResults_lossGainSummaryGross_BLVSshipArrival_bbl;
	
	@Expose
	@SerializedName("b73")
	private String quantityResults_lossGainSummaryGross_BLVSshipArrival_percent;
	
	@Expose
	@SerializedName("b74")
	private String quantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_bbl;
	
	@Expose
	@SerializedName("b75")
	private String quantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_percent;
	
	@Expose
	@SerializedName("b76")
	private String quantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_bbl;
	
	@Expose
	@SerializedName("b77")
	private String quantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_percent;
	
	@Expose
	@SerializedName("b78")
	private String quantityResults_lossGainSummaryGross_BLVSshoreTankReceived_bbl;
	
	@Expose
	@SerializedName("b79")
	private String quantityResults_lossGainSummaryGross_BLVSshoreTankReceived_percent;
	
	@Expose
	@SerializedName("b80")
	private String quantityResults_lossGainSummaryNet_BLVSshipArrival_bbl;
	
	@Expose
	@SerializedName("b81")
	private String quantityResults_lossGainSummaryNet_BLVSshipArrival_percent;
	
	@Expose
	@SerializedName("b82")
	private String quantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_bbl;
	
	@Expose
	@SerializedName("b83")
	private String quantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_percent;
	
	@Expose
	@SerializedName("b84")
	private String quantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_bbl;
	
	@Expose
	@SerializedName("b85")
	private String quantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_percent;
	
	@Expose
	@SerializedName("b86")
	private String quantityResults_lossGainSummaryNet_BLVSshoreTankReceived_bbl;
	
	@Expose
	@SerializedName("b87")
	private String quantityResults_lossGainSummaryNet_BLVSshoreTankReceived_percent;
	
	@Expose
	@SerializedName("c1")
	
	private String qualityTest_sampleLocation;
	
	@Expose
	@SerializedName("c2")
	private String qualityTest_shipTankComposite;
	
	@Expose
	@SerializedName("c3")
	private String qualityTest_testItem_APIat60F_methodUsed;
	
	@Expose
	@SerializedName("c4")
	private String qualityTest_testItem_APIat60F_uom;
	
	@Expose
	@SerializedName("c5")
	private String qualityTest_testItem_APIat60F_limit;
	
	@Expose
	@SerializedName("c6")
	private String qualityTest_testItem_APIat60F_result;
	
	@Expose
	@SerializedName("c7")
	private String qualityTest_testItem_APIat60F_commentsOrRemark;
	
	@Expose
	@SerializedName("c8")
	private String qualityTest_testItem_densityAt15C_methodUsed;
	
	@Expose
	@SerializedName("c9")
	private String qualityTest_testItem_densityAt15C_uom;
	
	@Expose
	@SerializedName("c10")
	private String qualityTest_testItem_densityAt15C_limit;
	
	@Expose
	@SerializedName("c11")
	private String qualityTest_testItem_densityAt15C_result;
	
	@Expose
	@SerializedName("c12")
	private String qualityTest_testItem_densityAt15C_commentsOrRemark;
	
	@Expose
	@SerializedName("c13")
	private String qualityTest_testItem_waterContentInCrudeOilByDistillation_methodUsed;
	
	@Expose
	@SerializedName("c14")
	private String qualityTest_testItem_waterContentInCrudeOilByDistillation_uom;
	
	@Expose
	@SerializedName("c15")
	private String qualityTest_testItem_waterContentInCrudeOilByDistillation_limit;
	
	@Expose
	@SerializedName("c16")
	private String qualityTest_testItem_waterContentInCrudeOilByDistillation_result;
	
	@Expose
	@SerializedName("c17")
	private String qualityTest_testItem_waterContentInCrudeOilByDistillation_commentsOrRemark;
	
	@Expose
	@SerializedName("c18")
	private String qualityTest_testItem_kinematicViscosityAt50C_methodUsed;
	
	@Expose
	@SerializedName("c19")
	private String qualityTest_testItem_kinematicViscosityAt50C_uom;
	
	@Expose
	@SerializedName("c20")
	private String qualityTest_testItem_kinematicViscosityAt50C_limit;
	
	@Expose
	@SerializedName("c21")
	private String qualityTest_testItem_kinematicViscosityAt50C_result;
	
	@Expose
	@SerializedName("c22")
	private String qualityTest_testItem_kinematicViscosityAt50C_commentsOrRemark;
	
	@Expose
	@SerializedName("c23")
	private String qualityTest_testItem_flashPoint_methodUsed;
	
	@Expose
	@SerializedName("c24")
	private String qualityTest_testItem_flashPoint_uom;
	
	@Expose
	@SerializedName("c25")
	private String qualityTest_testItem_flashPoint_limit;
	
	@Expose
	@SerializedName("c26")
	private String qualityTest_testItem_flashPoint_result;
	
	@Expose
	@SerializedName("c27")
	private String qualityTest_testItem_flashPoint_commentsOrRemark;
	
	@Expose
	@SerializedName("c28")
	private String qualityTest_testItem_pourPoint_methodUsed;
	
	@Expose
	@SerializedName("c29")
	private String qualityTest_testItem_pourPoint_uom;
	
	@Expose
	@SerializedName("c30")
	private String qualityTest_testItem_pourPoint_limit;
	
	@Expose
	@SerializedName("c31")
	private String qualityTest_testItem_pourPoint_result;
	
	@Expose
	@SerializedName("c32")
	private String qualityTest_testItem_pourPoint_commentsOrRemark;
	
	@Expose
	@SerializedName("c33")
	private String qualityTest_testItem_sulphur_methodUsed;
	
	@Expose
	@SerializedName("c34")
	private String qualityTest_testItem_sulphur_uom;
	
	@Expose
	@SerializedName("c35")
	private String qualityTest_testItem_sulphur_limit;
	
	@Expose
	@SerializedName("c36")
	private String qualityTest_testItem_sulphur_result;
	
	@Expose
	@SerializedName("c37")
	private String qualityTest_testItem_sulphur_commentsOrRemark;
	
	@Expose
	@SerializedName("c38")
	private String qualityTest_testItem_carbonResidue_methodUsed;
	
	@Expose
	@SerializedName("c39")
	private String qualityTest_testItem_carbonResidue_uom;
	
	@Expose
	@SerializedName("c40")
	private String qualityTest_testItem_carbonResidue_limit;
	
	@Expose
	@SerializedName("c41")
	private String qualityTest_testItem_carbonResidue_result;
	
	@Expose
	@SerializedName("c42")
	private String qualityTest_testItem_carbonResidue_commentsOrRemark;
	
	@Expose
	@SerializedName("c43")
	private String qualityTest_testItem_saltContent_methodUsed;
	
	@Expose
	@SerializedName("c44")
	private String qualityTest_testItem_saltContent_uom;
	
	@Expose
	@SerializedName("c45")
	private String qualityTest_testItem_saltContent_limit;
	
	@Expose
	@SerializedName("c46")
	private String qualityTest_testItem_saltContent_result;
	
	@Expose
	@SerializedName("c47")
	private String qualityTest_testItem_saltContent_commentsOrRemark;
	
	@Expose
	@SerializedName("c48")
	private String qualityTest_testItem_ash_methodUsed;
	
	@Expose
	@SerializedName("c49")
	private String qualityTest_testItem_ash_uom;
	
	@Expose
	@SerializedName("c50")
	private String qualityTest_testItem_ash_limit;
	
	@Expose
	@SerializedName("c51")
	private String qualityTest_testItem_ash_result;
	
	@Expose
	@SerializedName("c52")
	private String qualityTest_testItem_ash_commentsOrRemark;
	
	@Expose
	@SerializedName("c53")
	private String qualityTest_testItem_vanadium_methodUsed;
	
	@Expose
	@SerializedName("c54")
	private String qualityTest_testItem_vanadium_uom;
	
	@Expose
	@SerializedName("c55")
	private String qualityTest_testItem_vanadium_limit;
	
	@Expose
	@SerializedName("c56")
	private String qualityTest_testItem_vanadium_result;
	
	@Expose
	@SerializedName("c57")
	private String qualityTest_testItem_vanadium_commentsOrRemark;
	
	@Expose
	@SerializedName("c58")
	private String qualityTest_testItem_alumimSil_methodUsed;
	
	@Expose
	@SerializedName("c59")
	private String qualityTest_testItem_alumimSil_uom;
	
	@Expose
	@SerializedName("c60")
	private String qualityTest_testItem_alumimSil_limit;
	
	@Expose
	@SerializedName("c61")
	private String qualityTest_testItem_alumimSil_result;
	
	@Expose
	@SerializedName("c62")
	private String qualityTest_testItem_alumimSil_commentsOrRemark;
	
	@Expose
	@SerializedName("c63")
	private String qualityTest_testItem_acidNumber_methodUsed;
	
	@Expose
	@SerializedName("c64")
	private String qualityTest_testItem_acidNumber_uom;
	
	@Expose
	@SerializedName("c65")
	private String qualityTest_testItem_acidNumber_limit;
	
	@Expose
	@SerializedName("c66")
	private String qualityTest_testItem_acidNumber_result;
	
	@Expose
	@SerializedName("c67")
	private String qualityTest_testItem_acidNumber_commentsOrRemark;
	
	@Expose
	@SerializedName("d1")
	private String timeLog_event_endOfSeaPassage_dateOrtime;
	
	@Expose
	@SerializedName("d2")
	private String timeLog_event_endOfSeaPassage_comment;
	
	@Expose
	@SerializedName("d3")
	private String timeLog_event_norTendered_dateOrtime;
	
	@Expose
	@SerializedName("d4")
	private String timeLog_event_norTendered_comment;
	
	@Expose
	@SerializedName("d5")
	private String timeLog_event_seaPilotOnBoard_dateOrtime;
	
	@Expose
	@SerializedName("d6")
	private String timeLog_event_seaPilotOnBoard_comment;
	
	@Expose
	@SerializedName("d7")
	private String timeLog_event_firstRopeAshore_dateOrtime;
	
	@Expose
	@SerializedName("d8")
	private String timeLog_event_firstRopeAshore_comment;
	
	@Expose
	@SerializedName("d9")
	private String timeLog_event_allFast_dateOrtime;
	
	@Expose
	@SerializedName("d10")
	private String timeLog_event_allFast_comment;
	
	@Expose
	@SerializedName("d11")
	private String timeLog_event_norReceived_dateOrtime;
	
	@Expose
	@SerializedName("d12")
	private String timeLog_event_norReceived_comment;
	
	@Expose
	@SerializedName("d13")
	private String timeLog_event_commencedLoad_dateOrtime;
	
	@Expose
	@SerializedName("d14")
	private String timeLog_event_commencedLoad_comment;
	
	@Expose
	@SerializedName("d15")
	private String timeLog_event_completedLoad_dateOrtime;
	
	@Expose
	@SerializedName("d16")
	private String timeLog_event_completedLoad_comment;
	
	@Expose
	@SerializedName("d17")
	private String timeLog_event_tanksInspectedVesselSailed_dateOrtime;
	
	@Expose
	@SerializedName("d18")
	private String timeLog_event_tanksInspectedVesselSailed_comment;
	
	@Expose
	@SerializedName("d19")
	private String qualityTest_shoreTankComposite;
	
	public String getVoyageSummary_vef() {
		return voyageSummary_vef;
	}
	public void setVoyageSummary_vef(String voyageSummary_vef) {
		this.voyageSummary_vef = voyageSummary_vef;
	}
	public String getVoyageSummary_blDate() {
		return voyageSummary_blDate;
	}
	public void setVoyageSummary_blDate(String voyageSummary_blDate) {
		this.voyageSummary_blDate = voyageSummary_blDate;
	}
	public String getVoyageSummary_joblocation() {
		return voyageSummary_joblocation;
	}
	public void setVoyageSummary_joblocation(String voyageSummary_joblocation) {
		this.voyageSummary_joblocation = voyageSummary_joblocation;
	}
	public String getVoyageSummary_operator() {
		return voyageSummary_operator;
	}
	public void setVoyageSummary_operator(String voyageSummary_operator) {
		this.voyageSummary_operator = voyageSummary_operator;
	}
	public String getVoyageSummary_attachmentsOfSurveyorReports() {
		return voyageSummary_attachmentsOfSurveyorReports;
	}
	public void setVoyageSummary_attachmentsOfSurveyorReports(String voyageSummary_attachmentsOfSurveyorReports) {
		this.voyageSummary_attachmentsOfSurveyorReports = voyageSummary_attachmentsOfSurveyorReports;
	}
	public String getVoyageSummary_surveyorAppointment() {
		return voyageSummary_surveyorAppointment;
	}
	public void setVoyageSummary_surveyorAppointment(String voyageSummary_surveyorAppointment) {
		this.voyageSummary_surveyorAppointment = voyageSummary_surveyorAppointment;
	}
	public String getVoyageSummary_stowagePlan() {
		return voyageSummary_stowagePlan;
	}
	public void setVoyageSummary_stowagePlan(String voyageSummary_stowagePlan) {
		this.voyageSummary_stowagePlan = voyageSummary_stowagePlan;
	}
	public String getVoyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_method() {
		return voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_method;
	}
	public void setVoyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_method(
			String voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_method) {
		this.voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_method = voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_method;
	}
	public String getVoyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_bbl() {
		return voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_bbl;
	}
	public void setVoyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_bbl(
			String voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_bbl) {
		this.voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_bbl = voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_bbl;
	}
	public String getVoyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_mt() {
		return voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_mt;
	}
	public void setVoyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_mt(
			String voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_mt) {
		this.voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_mt = voyageSummary_dischargeVolumesSummary_freeWaterBeforeDischarging_mt;
	}
	public String getVoyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_method() {
		return voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_method;
	}
	public void setVoyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_method(
			String voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_method) {
		this.voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_method = voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_method;
	}
	public String getVoyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_bbl() {
		return voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_bbl;
	}
	public void setVoyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_bbl(
			String voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_bbl) {
		this.voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_bbl = voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_bbl;
	}
	public String getVoyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_mt() {
		return voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_mt;
	}
	public void setVoyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_mt(
			String voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_mt) {
		this.voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_mt = voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolume_mt;
	}
	public String getVoyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_method() {
		return voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_method;
	}
	public void setVoyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_method(
			String voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_method) {
		this.voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_method = voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_method;
	}
	public String getVoyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl() {
		return voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl;
	}
	public void setVoyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl(
			String voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl) {
		this.voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl = voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl;
	}
	public String getVoyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_mt() {
		return voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_mt;
	}
	public void setVoyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_mt(
			String voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_mt) {
		this.voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_mt = voyageSummary_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_mt;
	}
	public String getVoyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_method() {
		return voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_method;
	}
	public void setVoyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_method(
			String voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_method) {
		this.voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_method = voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_method;
	}
	public String getVoyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_bbl() {
		return voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_bbl;
	}
	public void setVoyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_bbl(
			String voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_bbl) {
		this.voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_bbl = voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_bbl;
	}
	public String getVoyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_mt() {
		return voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_mt;
	}
	public void setVoyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_mt(
			String voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_mt) {
		this.voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_mt = voyageSummary_dischargeVolumesSummary_shipNetArrivalVolumeVefApplied_mt;
	}
	public String getQuantityResults_method() {
		return quantityResults_method;
	}
	public void setQuantityResults_method(String quantityResults_method) {
		this.quantityResults_method = quantityResults_method;
	}
	public String getQuantityResults_density15C() {
		return quantityResults_density15C;
	}
	public void setQuantityResults_density15C(String quantityResults_density15C) {
		this.quantityResults_density15C = quantityResults_density15C;
	}
	public String getQuantityResults_api() {
		return quantityResults_api;
	}
	public void setQuantityResults_api(String quantityResults_api) {
		this.quantityResults_api = quantityResults_api;
	}
	public String getQuantityResults_temp() {
		return quantityResults_temp;
	}
	public void setQuantityResults_temp(String quantityResults_temp) {
		this.quantityResults_temp = quantityResults_temp;
	}
	public String getQuantityResults_water() {
		return quantityResults_water;
	}
	public void setQuantityResults_water(String quantityResults_water) {
		this.quantityResults_water = quantityResults_water;
	}
	public String getQuantityResults_sediment() {
		return quantityResults_sediment;
	}
	public void setQuantityResults_sediment(String quantityResults_sediment) {
		this.quantityResults_sediment = quantityResults_sediment;
	}
	public String getQuantityResults_nominatedShoreTanks() {
		return quantityResults_nominatedShoreTanks;
	}
	public void setQuantityResults_nominatedShoreTanks(String quantityResults_nominatedShoreTanks) {
		this.quantityResults_nominatedShoreTanks = quantityResults_nominatedShoreTanks;
	}
	public String getQuantityResults_BLTCV_BBL60F() {
		return quantityResults_BLTCV_BBL60F;
	}
	public void setQuantityResults_BLTCV_BBL60F(String quantityResults_BLTCV_BBL60F) {
		this.quantityResults_BLTCV_BBL60F = quantityResults_BLTCV_BBL60F;
	}
	public String getQuantityResults_BLTCV_M3at15C() {
		return quantityResults_BLTCV_M3at15C;
	}
	public void setQuantityResults_BLTCV_M3at15C(String quantityResults_BLTCV_M3at15C) {
		this.quantityResults_BLTCV_M3at15C = quantityResults_BLTCV_M3at15C;
	}
	public String getQuantityResults_BLTCV_Mtinair() {
		return quantityResults_BLTCV_Mtinair;
	}
	public void setQuantityResults_BLTCV_Mtinair(String quantityResults_BLTCV_Mtinair) {
		this.quantityResults_BLTCV_Mtinair = quantityResults_BLTCV_Mtinair;
	}
	public String getQuantityResults_BLTCV_MtTonnesVac() {
		return quantityResults_BLTCV_MtTonnesVac;
	}
	public void setQuantityResults_BLTCV_MtTonnesVac(String quantityResults_BLTCV_MtTonnesVac) {
		this.quantityResults_BLTCV_MtTonnesVac = quantityResults_BLTCV_MtTonnesVac;
	}
	public String getQuantityResults_BLGCV_BBL60F() {
		return quantityResults_BLGCV_BBL60F;
	}
	public void setQuantityResults_BLGCV_BBL60F(String quantityResults_BLGCV_BBL60F) {
		this.quantityResults_BLGCV_BBL60F = quantityResults_BLGCV_BBL60F;
	}
	public String getQuantityResults_BLGCV_M3at15C() {
		return quantityResults_BLGCV_M3at15C;
	}
	public void setQuantityResults_BLGCV_M3at15C(String quantityResults_BLGCV_M3at15C) {
		this.quantityResults_BLGCV_M3at15C = quantityResults_BLGCV_M3at15C;
	}
	public String getQuantityResults_BLGCV_Mtinair() {
		return quantityResults_BLGCV_Mtinair;
	}
	public void setQuantityResults_BLGCV_Mtinair(String quantityResults_BLGCV_Mtinair) {
		this.quantityResults_BLGCV_Mtinair = quantityResults_BLGCV_Mtinair;
	}
	public String getQuantityResults_BLGCV_MtTonnesVac() {
		return quantityResults_BLGCV_MtTonnesVac;
	}
	public void setQuantityResults_BLGCV_MtTonnesVac(String quantityResults_BLGCV_MtTonnesVac) {
		this.quantityResults_BLGCV_MtTonnesVac = quantityResults_BLGCV_MtTonnesVac;
	}
	public String getQuantityResults_BLNCV_BBL60F() {
		return quantityResults_BLNCV_BBL60F;
	}
	public void setQuantityResults_BLNCV_BBL60F(String quantityResults_BLNCV_BBL60F) {
		this.quantityResults_BLNCV_BBL60F = quantityResults_BLNCV_BBL60F;
	}
	public String getQuantityResults_BLNCV_M3at15C() {
		return quantityResults_BLNCV_M3at15C;
	}
	public void setQuantityResults_BLNCV_M3at15C(String quantityResults_BLNCV_M3at15C) {
		this.quantityResults_BLNCV_M3at15C = quantityResults_BLNCV_M3at15C;
	}
	public String getQuantityResults_BLNCV_Mtinair() {
		return quantityResults_BLNCV_Mtinair;
	}
	public void setQuantityResults_BLNCV_Mtinair(String quantityResults_BLNCV_Mtinair) {
		this.quantityResults_BLNCV_Mtinair = quantityResults_BLNCV_Mtinair;
	}
	public String getQuantityResults_BLNCV_MtTonnesVac() {
		return quantityResults_BLNCV_MtTonnesVac;
	}
	public void setQuantityResults_BLNCV_MtTonnesVac(String quantityResults_BLNCV_MtTonnesVac) {
		this.quantityResults_BLNCV_MtTonnesVac = quantityResults_BLNCV_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_BBL60F() {
		return quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_BBL60F(
			String quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_BBL60F = quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_M3at15C() {
		return quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_M3at15C(
			String quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_M3at15C = quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_Mtinair() {
		return quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_Mtinair(
			String quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_Mtinair = quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_MtTonnesVac = quantityResults_dischargeVolumesSummary_freeWaterBeforeDischarging_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_BBL60F() {
		return quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_BBL60F(
			String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_BBL60F = quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_M3at15C() {
		return quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_M3at15C(
			String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_M3at15C = quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_Mtinair() {
		return quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_Mtinair(
			String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_Mtinair = quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_MtTonnesVac = quantityResults_dischargeVolumesSummary_shipTotalArrivalVolume_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_BBL60F() {
		return quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_BBL60F(
			String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_BBL60F = quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_M3at15C() {
		return quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_M3at15C(
			String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_M3at15C = quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_Mtinair() {
		return quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_Mtinair(
			String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_Mtinair = quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_MtTonnesVac = quantityResults_dischargeVolumesSummary_shipTotalArrivalVolumeVefApplied_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipNetArrivalVolume_BBL60F() {
		return quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipNetArrivalVolume_BBL60F(
			String quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_BBL60F = quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipNetArrivalVolume_M3at15C() {
		return quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipNetArrivalVolume_M3at15C(
			String quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_M3at15C = quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipNetArrivalVolume_Mtinair() {
		return quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipNetArrivalVolume_Mtinair(
			String quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_Mtinair = quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipNetArrivalVolume_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipNetArrivalVolume_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_MtTonnesVac = quantityResults_dischargeVolumesSummary_shipNetArrivalVolume_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBOilTotal_BBL60F() {
		return quantityResults_dischargeVolumesSummary_ROBOilTotal_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBOilTotal_BBL60F(
			String quantityResults_dischargeVolumesSummary_ROBOilTotal_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_ROBOilTotal_BBL60F = quantityResults_dischargeVolumesSummary_ROBOilTotal_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBOilTotal_M3at15C() {
		return quantityResults_dischargeVolumesSummary_ROBOilTotal_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBOilTotal_M3at15C(
			String quantityResults_dischargeVolumesSummary_ROBOilTotal_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_ROBOilTotal_M3at15C = quantityResults_dischargeVolumesSummary_ROBOilTotal_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBOilTotal_Mtinair() {
		return quantityResults_dischargeVolumesSummary_ROBOilTotal_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBOilTotal_Mtinair(
			String quantityResults_dischargeVolumesSummary_ROBOilTotal_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_ROBOilTotal_Mtinair = quantityResults_dischargeVolumesSummary_ROBOilTotal_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBOilTotal_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_ROBOilTotal_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBOilTotal_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_ROBOilTotal_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_ROBOilTotal_MtTonnesVac = quantityResults_dischargeVolumesSummary_ROBOilTotal_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBliquid_BBL60F() {
		return quantityResults_dischargeVolumesSummary_ROBliquid_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBliquid_BBL60F(
			String quantityResults_dischargeVolumesSummary_ROBliquid_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_ROBliquid_BBL60F = quantityResults_dischargeVolumesSummary_ROBliquid_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBliquid_M3at15C() {
		return quantityResults_dischargeVolumesSummary_ROBliquid_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBliquid_M3at15C(
			String quantityResults_dischargeVolumesSummary_ROBliquid_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_ROBliquid_M3at15C = quantityResults_dischargeVolumesSummary_ROBliquid_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBliquid_Mtinair() {
		return quantityResults_dischargeVolumesSummary_ROBliquid_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBliquid_Mtinair(
			String quantityResults_dischargeVolumesSummary_ROBliquid_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_ROBliquid_Mtinair = quantityResults_dischargeVolumesSummary_ROBliquid_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBliquid_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_ROBliquid_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBliquid_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_ROBliquid_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_ROBliquid_MtTonnesVac = quantityResults_dischargeVolumesSummary_ROBliquid_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBNonLiquid_BBL60F() {
		return quantityResults_dischargeVolumesSummary_ROBNonLiquid_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBNonLiquid_BBL60F(
			String quantityResults_dischargeVolumesSummary_ROBNonLiquid_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_ROBNonLiquid_BBL60F = quantityResults_dischargeVolumesSummary_ROBNonLiquid_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBNonLiquid_M3at15C() {
		return quantityResults_dischargeVolumesSummary_ROBNonLiquid_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBNonLiquid_M3at15C(
			String quantityResults_dischargeVolumesSummary_ROBNonLiquid_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_ROBNonLiquid_M3at15C = quantityResults_dischargeVolumesSummary_ROBNonLiquid_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBNonLiquid_Mtinair() {
		return quantityResults_dischargeVolumesSummary_ROBNonLiquid_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBNonLiquid_Mtinair(
			String quantityResults_dischargeVolumesSummary_ROBNonLiquid_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_ROBNonLiquid_Mtinair = quantityResults_dischargeVolumesSummary_ROBNonLiquid_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBNonLiquid_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_ROBNonLiquid_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBNonLiquid_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_ROBNonLiquid_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_ROBNonLiquid_MtTonnesVac = quantityResults_dischargeVolumesSummary_ROBNonLiquid_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBFreeWater_BBL60F() {
		return quantityResults_dischargeVolumesSummary_ROBFreeWater_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBFreeWater_BBL60F(
			String quantityResults_dischargeVolumesSummary_ROBFreeWater_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_ROBFreeWater_BBL60F = quantityResults_dischargeVolumesSummary_ROBFreeWater_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBFreeWater_M3at15C() {
		return quantityResults_dischargeVolumesSummary_ROBFreeWater_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBFreeWater_M3at15C(
			String quantityResults_dischargeVolumesSummary_ROBFreeWater_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_ROBFreeWater_M3at15C = quantityResults_dischargeVolumesSummary_ROBFreeWater_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBFreeWater_Mtinair() {
		return quantityResults_dischargeVolumesSummary_ROBFreeWater_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBFreeWater_Mtinair(
			String quantityResults_dischargeVolumesSummary_ROBFreeWater_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_ROBFreeWater_Mtinair = quantityResults_dischargeVolumesSummary_ROBFreeWater_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_ROBFreeWater_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_ROBFreeWater_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_ROBFreeWater_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_ROBFreeWater_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_ROBFreeWater_MtTonnesVac = quantityResults_dischargeVolumesSummary_ROBFreeWater_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipDischargedQtyGross_BBL60F() {
		return quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipDischargedQtyGross_BBL60F(
			String quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_BBL60F = quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipDischargedQtyGross_M3at15C() {
		return quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipDischargedQtyGross_M3at15C(
			String quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_M3at15C = quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipDischargedQtyGross_Mtinair() {
		return quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipDischargedQtyGross_Mtinair(
			String quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_Mtinair = quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipDischargedQtyGross_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipDischargedQtyGross_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_MtTonnesVac = quantityResults_dischargeVolumesSummary_shipDischargedQtyGross_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipDischargedQtyNet_BBL60F() {
		return quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipDischargedQtyNet_BBL60F(
			String quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_BBL60F = quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipDischargedQtyNet_M3at15C() {
		return quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipDischargedQtyNet_M3at15C(
			String quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_M3at15C = quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipDischargedQtyNet_Mtinair() {
		return quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipDischargedQtyNet_Mtinair(
			String quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_Mtinair = quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_shipDischargedQtyNet_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_shipDischargedQtyNet_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_MtTonnesVac = quantityResults_dischargeVolumesSummary_shipDischargedQtyNet_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_BBL60F() {
		return quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_BBL60F(
			String quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_BBL60F = quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_M3at15C() {
		return quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_M3at15C(
			String quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_M3at15C = quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_Mtinair() {
		return quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_Mtinair(
			String quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_Mtinair = quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_MtTonnesVac = quantityResults_dischargeVolumesSummary_shoreReceivedQtyGross_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_BBL60F() {
		return quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_BBL60F(
			String quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_BBL60F = quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_M3at15C() {
		return quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_M3at15C(
			String quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_M3at15C = quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_Mtinair() {
		return quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_Mtinair(
			String quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_Mtinair = quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_MtTonnesVac = quantityResults_dischargeVolumesSummary_shoreReceivedQtyNet_MtTonnesVac;
	}
	public String getQuantityResults_dischargeVolumesSummary_freeWaterInShoreTank_BBL60F() {
		return quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_BBL60F;
	}
	public void setQuantityResults_dischargeVolumesSummary_freeWaterInShoreTank_BBL60F(
			String quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_BBL60F) {
		this.quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_BBL60F = quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_BBL60F;
	}
	public String getQuantityResults_dischargeVolumesSummary_freeWaterInShoreTank_M3at15C() {
		return quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_M3at15C;
	}
	public void setQuantityResults_dischargeVolumesSummary_freeWaterInShoreTank_M3at15C(
			String quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_M3at15C) {
		this.quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_M3at15C = quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_M3at15C;
	}
	public String getQuantityResults_dischargeVolumesSummary_freeWaterInShoreTank_Mtinair() {
		return quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_Mtinair;
	}
	public void setQuantityResults_dischargeVolumesSummary_freeWaterInShoreTank_Mtinair(
			String quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_Mtinair) {
		this.quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_Mtinair = quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_Mtinair;
	}
	public String getQuantityResults_dischargeVolumesSummary_freeWaterInShoreTank_MtTonnesVac() {
		return quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_MtTonnesVac;
	}
	public void setQuantityResults_dischargeVolumesSummary_freeWaterInShoreTank_MtTonnesVac(
			String quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_MtTonnesVac) {
		this.quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_MtTonnesVac = quantityResults_dischargeVolumesSummary_freeWaterInShoreTank_MtTonnesVac;
	}
	public String getQuantityResults_lossGainSummaryGross_BLVSshipArrival_bbl() {
		return quantityResults_lossGainSummaryGross_BLVSshipArrival_bbl;
	}
	public void setQuantityResults_lossGainSummaryGross_BLVSshipArrival_bbl(
			String quantityResults_lossGainSummaryGross_BLVSshipArrival_bbl) {
		this.quantityResults_lossGainSummaryGross_BLVSshipArrival_bbl = quantityResults_lossGainSummaryGross_BLVSshipArrival_bbl;
	}
	public String getQuantityResults_lossGainSummaryGross_BLVSshipArrival_percent() {
		return quantityResults_lossGainSummaryGross_BLVSshipArrival_percent;
	}
	public void setQuantityResults_lossGainSummaryGross_BLVSshipArrival_percent(
			String quantityResults_lossGainSummaryGross_BLVSshipArrival_percent) {
		this.quantityResults_lossGainSummaryGross_BLVSshipArrival_percent = quantityResults_lossGainSummaryGross_BLVSshipArrival_percent;
	}
	public String getQuantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_bbl() {
		return quantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_bbl;
	}
	public void setQuantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_bbl(
			String quantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_bbl) {
		this.quantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_bbl = quantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_bbl;
	}
	public String getQuantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_percent() {
		return quantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_percent;
	}
	public void setQuantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_percent(
			String quantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_percent) {
		this.quantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_percent = quantityResults_lossGainSummaryGross_shipArrivalVSshipDischarged_percent;
	}
	public String getQuantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_bbl() {
		return quantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_bbl;
	}
	public void setQuantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_bbl(
			String quantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_bbl) {
		this.quantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_bbl = quantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_bbl;
	}
	public String getQuantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_percent() {
		return quantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_percent;
	}
	public void setQuantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_percent(
			String quantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_percent) {
		this.quantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_percent = quantityResults_lossGainSummaryGross_shipDischargedVSshoreTankReceived_percent;
	}
	public String getQuantityResults_lossGainSummaryGross_BLVSshoreTankReceived_bbl() {
		return quantityResults_lossGainSummaryGross_BLVSshoreTankReceived_bbl;
	}
	public void setQuantityResults_lossGainSummaryGross_BLVSshoreTankReceived_bbl(
			String quantityResults_lossGainSummaryGross_BLVSshoreTankReceived_bbl) {
		this.quantityResults_lossGainSummaryGross_BLVSshoreTankReceived_bbl = quantityResults_lossGainSummaryGross_BLVSshoreTankReceived_bbl;
	}
	public String getQuantityResults_lossGainSummaryGross_BLVSshoreTankReceived_percent() {
		return quantityResults_lossGainSummaryGross_BLVSshoreTankReceived_percent;
	}
	public void setQuantityResults_lossGainSummaryGross_BLVSshoreTankReceived_percent(
			String quantityResults_lossGainSummaryGross_BLVSshoreTankReceived_percent) {
		this.quantityResults_lossGainSummaryGross_BLVSshoreTankReceived_percent = quantityResults_lossGainSummaryGross_BLVSshoreTankReceived_percent;
	}
	public String getQuantityResults_lossGainSummaryNet_BLVSshipArrival_bbl() {
		return quantityResults_lossGainSummaryNet_BLVSshipArrival_bbl;
	}
	public void setQuantityResults_lossGainSummaryNet_BLVSshipArrival_bbl(
			String quantityResults_lossGainSummaryNet_BLVSshipArrival_bbl) {
		this.quantityResults_lossGainSummaryNet_BLVSshipArrival_bbl = quantityResults_lossGainSummaryNet_BLVSshipArrival_bbl;
	}
	public String getQuantityResults_lossGainSummaryNet_BLVSshipArrival_percent() {
		return quantityResults_lossGainSummaryNet_BLVSshipArrival_percent;
	}
	public void setQuantityResults_lossGainSummaryNet_BLVSshipArrival_percent(
			String quantityResults_lossGainSummaryNet_BLVSshipArrival_percent) {
		this.quantityResults_lossGainSummaryNet_BLVSshipArrival_percent = quantityResults_lossGainSummaryNet_BLVSshipArrival_percent;
	}
	public String getQuantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_bbl() {
		return quantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_bbl;
	}
	public void setQuantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_bbl(
			String quantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_bbl) {
		this.quantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_bbl = quantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_bbl;
	}
	public String getQuantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_percent() {
		return quantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_percent;
	}
	public void setQuantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_percent(
			String quantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_percent) {
		this.quantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_percent = quantityResults_lossGainSummaryNet_shipArrivalVSshipDischarged_percent;
	}
	public String getQuantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_bbl() {
		return quantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_bbl;
	}
	public void setQuantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_bbl(
			String quantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_bbl) {
		this.quantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_bbl = quantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_bbl;
	}
	public String getQuantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_percent() {
		return quantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_percent;
	}
	public void setQuantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_percent(
			String quantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_percent) {
		this.quantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_percent = quantityResults_lossGainSummaryNet_shipDischargedVSshoreTankReceived_percent;
	}
	public String getQuantityResults_lossGainSummaryNet_BLVSshoreTankReceived_bbl() {
		return quantityResults_lossGainSummaryNet_BLVSshoreTankReceived_bbl;
	}
	public void setQuantityResults_lossGainSummaryNet_BLVSshoreTankReceived_bbl(
			String quantityResults_lossGainSummaryNet_BLVSshoreTankReceived_bbl) {
		this.quantityResults_lossGainSummaryNet_BLVSshoreTankReceived_bbl = quantityResults_lossGainSummaryNet_BLVSshoreTankReceived_bbl;
	}
	public String getQuantityResults_lossGainSummaryNet_BLVSshoreTankReceived_percent() {
		return quantityResults_lossGainSummaryNet_BLVSshoreTankReceived_percent;
	}
	public void setQuantityResults_lossGainSummaryNet_BLVSshoreTankReceived_percent(
			String quantityResults_lossGainSummaryNet_BLVSshoreTankReceived_percent) {
		this.quantityResults_lossGainSummaryNet_BLVSshoreTankReceived_percent = quantityResults_lossGainSummaryNet_BLVSshoreTankReceived_percent;
	}
	public String getQualityTest_sampleLocation() {
		return qualityTest_sampleLocation;
	}
	public void setQualityTest_sampleLocation(String qualityTest_sampleLocation) {
		this.qualityTest_sampleLocation = qualityTest_sampleLocation;
	}
	public String getQualityTest_shipTankComposite() {
		return qualityTest_shipTankComposite;
	}
	public void setQualityTest_shipTankComposite(String qualityTest_shipTankComposite) {
		this.qualityTest_shipTankComposite = qualityTest_shipTankComposite;
	}
	public String getQualityTest_testItem_APIat60F_methodUsed() {
		return qualityTest_testItem_APIat60F_methodUsed;
	}
	public void setQualityTest_testItem_APIat60F_methodUsed(String qualityTest_testItem_APIat60F_methodUsed) {
		this.qualityTest_testItem_APIat60F_methodUsed = qualityTest_testItem_APIat60F_methodUsed;
	}
	public String getQualityTest_testItem_APIat60F_uom() {
		return qualityTest_testItem_APIat60F_uom;
	}
	public void setQualityTest_testItem_APIat60F_uom(String qualityTest_testItem_APIat60F_uom) {
		this.qualityTest_testItem_APIat60F_uom = qualityTest_testItem_APIat60F_uom;
	}
	public String getQualityTest_testItem_APIat60F_limit() {
		return qualityTest_testItem_APIat60F_limit;
	}
	public void setQualityTest_testItem_APIat60F_limit(String qualityTest_testItem_APIat60F_limit) {
		this.qualityTest_testItem_APIat60F_limit = qualityTest_testItem_APIat60F_limit;
	}
	public String getQualityTest_testItem_APIat60F_result() {
		return qualityTest_testItem_APIat60F_result;
	}
	public void setQualityTest_testItem_APIat60F_result(String qualityTest_testItem_APIat60F_result) {
		this.qualityTest_testItem_APIat60F_result = qualityTest_testItem_APIat60F_result;
	}
	public String getQualityTest_testItem_APIat60F_commentsOrRemark() {
		return qualityTest_testItem_APIat60F_commentsOrRemark;
	}
	public void setQualityTest_testItem_APIat60F_commentsOrRemark(String qualityTest_testItem_APIat60F_commentsOrRemark) {
		this.qualityTest_testItem_APIat60F_commentsOrRemark = qualityTest_testItem_APIat60F_commentsOrRemark;
	}
	public String getQualityTest_testItem_densityAt15C_methodUsed() {
		return qualityTest_testItem_densityAt15C_methodUsed;
	}
	public void setQualityTest_testItem_densityAt15C_methodUsed(String qualityTest_testItem_densityAt15C_methodUsed) {
		this.qualityTest_testItem_densityAt15C_methodUsed = qualityTest_testItem_densityAt15C_methodUsed;
	}
	public String getQualityTest_testItem_densityAt15C_uom() {
		return qualityTest_testItem_densityAt15C_uom;
	}
	public void setQualityTest_testItem_densityAt15C_uom(String qualityTest_testItem_densityAt15C_uom) {
		this.qualityTest_testItem_densityAt15C_uom = qualityTest_testItem_densityAt15C_uom;
	}
	public String getQualityTest_testItem_densityAt15C_limit() {
		return qualityTest_testItem_densityAt15C_limit;
	}
	public void setQualityTest_testItem_densityAt15C_limit(String qualityTest_testItem_densityAt15C_limit) {
		this.qualityTest_testItem_densityAt15C_limit = qualityTest_testItem_densityAt15C_limit;
	}
	public String getQualityTest_testItem_densityAt15C_result() {
		return qualityTest_testItem_densityAt15C_result;
	}
	public void setQualityTest_testItem_densityAt15C_result(String qualityTest_testItem_densityAt15C_result) {
		this.qualityTest_testItem_densityAt15C_result = qualityTest_testItem_densityAt15C_result;
	}
	public String getQualityTest_testItem_densityAt15C_commentsOrRemark() {
		return qualityTest_testItem_densityAt15C_commentsOrRemark;
	}
	public void setQualityTest_testItem_densityAt15C_commentsOrRemark(
			String qualityTest_testItem_densityAt15C_commentsOrRemark) {
		this.qualityTest_testItem_densityAt15C_commentsOrRemark = qualityTest_testItem_densityAt15C_commentsOrRemark;
	}
	public String getQualityTest_testItem_waterContentInCrudeOilByDistillation_methodUsed() {
		return qualityTest_testItem_waterContentInCrudeOilByDistillation_methodUsed;
	}
	public void setQualityTest_testItem_waterContentInCrudeOilByDistillation_methodUsed(
			String qualityTest_testItem_waterContentInCrudeOilByDistillation_methodUsed) {
		this.qualityTest_testItem_waterContentInCrudeOilByDistillation_methodUsed = qualityTest_testItem_waterContentInCrudeOilByDistillation_methodUsed;
	}
	public String getQualityTest_testItem_waterContentInCrudeOilByDistillation_uom() {
		return qualityTest_testItem_waterContentInCrudeOilByDistillation_uom;
	}
	public void setQualityTest_testItem_waterContentInCrudeOilByDistillation_uom(
			String qualityTest_testItem_waterContentInCrudeOilByDistillation_uom) {
		this.qualityTest_testItem_waterContentInCrudeOilByDistillation_uom = qualityTest_testItem_waterContentInCrudeOilByDistillation_uom;
	}
	public String getQualityTest_testItem_waterContentInCrudeOilByDistillation_limit() {
		return qualityTest_testItem_waterContentInCrudeOilByDistillation_limit;
	}
	public void setQualityTest_testItem_waterContentInCrudeOilByDistillation_limit(
			String qualityTest_testItem_waterContentInCrudeOilByDistillation_limit) {
		this.qualityTest_testItem_waterContentInCrudeOilByDistillation_limit = qualityTest_testItem_waterContentInCrudeOilByDistillation_limit;
	}
	public String getQualityTest_testItem_waterContentInCrudeOilByDistillation_result() {
		return qualityTest_testItem_waterContentInCrudeOilByDistillation_result;
	}
	public void setQualityTest_testItem_waterContentInCrudeOilByDistillation_result(
			String qualityTest_testItem_waterContentInCrudeOilByDistillation_result) {
		this.qualityTest_testItem_waterContentInCrudeOilByDistillation_result = qualityTest_testItem_waterContentInCrudeOilByDistillation_result;
	}
	public String getQualityTest_testItem_waterContentInCrudeOilByDistillation_commentsOrRemark() {
		return qualityTest_testItem_waterContentInCrudeOilByDistillation_commentsOrRemark;
	}
	public void setQualityTest_testItem_waterContentInCrudeOilByDistillation_commentsOrRemark(
			String qualityTest_testItem_waterContentInCrudeOilByDistillation_commentsOrRemark) {
		this.qualityTest_testItem_waterContentInCrudeOilByDistillation_commentsOrRemark = qualityTest_testItem_waterContentInCrudeOilByDistillation_commentsOrRemark;
	}
	public String getQualityTest_testItem_kinematicViscosityAt50C_methodUsed() {
		return qualityTest_testItem_kinematicViscosityAt50C_methodUsed;
	}
	public void setQualityTest_testItem_kinematicViscosityAt50C_methodUsed(
			String qualityTest_testItem_kinematicViscosityAt50C_methodUsed) {
		this.qualityTest_testItem_kinematicViscosityAt50C_methodUsed = qualityTest_testItem_kinematicViscosityAt50C_methodUsed;
	}
	public String getQualityTest_testItem_kinematicViscosityAt50C_uom() {
		return qualityTest_testItem_kinematicViscosityAt50C_uom;
	}
	public void setQualityTest_testItem_kinematicViscosityAt50C_uom(
			String qualityTest_testItem_kinematicViscosityAt50C_uom) {
		this.qualityTest_testItem_kinematicViscosityAt50C_uom = qualityTest_testItem_kinematicViscosityAt50C_uom;
	}
	public String getQualityTest_testItem_kinematicViscosityAt50C_limit() {
		return qualityTest_testItem_kinematicViscosityAt50C_limit;
	}
	public void setQualityTest_testItem_kinematicViscosityAt50C_limit(
			String qualityTest_testItem_kinematicViscosityAt50C_limit) {
		this.qualityTest_testItem_kinematicViscosityAt50C_limit = qualityTest_testItem_kinematicViscosityAt50C_limit;
	}
	public String getQualityTest_testItem_kinematicViscosityAt50C_result() {
		return qualityTest_testItem_kinematicViscosityAt50C_result;
	}
	public void setQualityTest_testItem_kinematicViscosityAt50C_result(
			String qualityTest_testItem_kinematicViscosityAt50C_result) {
		this.qualityTest_testItem_kinematicViscosityAt50C_result = qualityTest_testItem_kinematicViscosityAt50C_result;
	}
	public String getQualityTest_testItem_kinematicViscosityAt50C_commentsOrRemark() {
		return qualityTest_testItem_kinematicViscosityAt50C_commentsOrRemark;
	}
	public void setQualityTest_testItem_kinematicViscosityAt50C_commentsOrRemark(
			String qualityTest_testItem_kinematicViscosityAt50C_commentsOrRemark) {
		this.qualityTest_testItem_kinematicViscosityAt50C_commentsOrRemark = qualityTest_testItem_kinematicViscosityAt50C_commentsOrRemark;
	}
	public String getQualityTest_testItem_flashPoint_methodUsed() {
		return qualityTest_testItem_flashPoint_methodUsed;
	}
	public void setQualityTest_testItem_flashPoint_methodUsed(String qualityTest_testItem_flashPoint_methodUsed) {
		this.qualityTest_testItem_flashPoint_methodUsed = qualityTest_testItem_flashPoint_methodUsed;
	}
	public String getQualityTest_testItem_flashPoint_uom() {
		return qualityTest_testItem_flashPoint_uom;
	}
	public void setQualityTest_testItem_flashPoint_uom(String qualityTest_testItem_flashPoint_uom) {
		this.qualityTest_testItem_flashPoint_uom = qualityTest_testItem_flashPoint_uom;
	}
	public String getQualityTest_testItem_flashPoint_limit() {
		return qualityTest_testItem_flashPoint_limit;
	}
	public void setQualityTest_testItem_flashPoint_limit(String qualityTest_testItem_flashPoint_limit) {
		this.qualityTest_testItem_flashPoint_limit = qualityTest_testItem_flashPoint_limit;
	}
	public String getQualityTest_testItem_flashPoint_result() {
		return qualityTest_testItem_flashPoint_result;
	}
	public void setQualityTest_testItem_flashPoint_result(String qualityTest_testItem_flashPoint_result) {
		this.qualityTest_testItem_flashPoint_result = qualityTest_testItem_flashPoint_result;
	}
	public String getQualityTest_testItem_flashPoint_commentsOrRemark() {
		return qualityTest_testItem_flashPoint_commentsOrRemark;
	}
	public void setQualityTest_testItem_flashPoint_commentsOrRemark(
			String qualityTest_testItem_flashPoint_commentsOrRemark) {
		this.qualityTest_testItem_flashPoint_commentsOrRemark = qualityTest_testItem_flashPoint_commentsOrRemark;
	}
	public String getQualityTest_testItem_pourPoint_methodUsed() {
		return qualityTest_testItem_pourPoint_methodUsed;
	}
	public void setQualityTest_testItem_pourPoint_methodUsed(String qualityTest_testItem_pourPoint_methodUsed) {
		this.qualityTest_testItem_pourPoint_methodUsed = qualityTest_testItem_pourPoint_methodUsed;
	}
	public String getQualityTest_testItem_pourPoint_uom() {
		return qualityTest_testItem_pourPoint_uom;
	}
	public void setQualityTest_testItem_pourPoint_uom(String qualityTest_testItem_pourPoint_uom) {
		this.qualityTest_testItem_pourPoint_uom = qualityTest_testItem_pourPoint_uom;
	}
	public String getQualityTest_testItem_pourPoint_limit() {
		return qualityTest_testItem_pourPoint_limit;
	}
	public void setQualityTest_testItem_pourPoint_limit(String qualityTest_testItem_pourPoint_limit) {
		this.qualityTest_testItem_pourPoint_limit = qualityTest_testItem_pourPoint_limit;
	}
	public String getQualityTest_testItem_pourPoint_result() {
		return qualityTest_testItem_pourPoint_result;
	}
	public void setQualityTest_testItem_pourPoint_result(String qualityTest_testItem_pourPoint_result) {
		this.qualityTest_testItem_pourPoint_result = qualityTest_testItem_pourPoint_result;
	}
	public String getQualityTest_testItem_pourPoint_commentsOrRemark() {
		return qualityTest_testItem_pourPoint_commentsOrRemark;
	}
	public void setQualityTest_testItem_pourPoint_commentsOrRemark(String qualityTest_testItem_pourPoint_commentsOrRemark) {
		this.qualityTest_testItem_pourPoint_commentsOrRemark = qualityTest_testItem_pourPoint_commentsOrRemark;
	}
	public String getQualityTest_testItem_sulphur_methodUsed() {
		return qualityTest_testItem_sulphur_methodUsed;
	}
	public void setQualityTest_testItem_sulphur_methodUsed(String qualityTest_testItem_sulphur_methodUsed) {
		this.qualityTest_testItem_sulphur_methodUsed = qualityTest_testItem_sulphur_methodUsed;
	}
	public String getQualityTest_testItem_sulphur_uom() {
		return qualityTest_testItem_sulphur_uom;
	}
	public void setQualityTest_testItem_sulphur_uom(String qualityTest_testItem_sulphur_uom) {
		this.qualityTest_testItem_sulphur_uom = qualityTest_testItem_sulphur_uom;
	}
	public String getQualityTest_testItem_sulphur_limit() {
		return qualityTest_testItem_sulphur_limit;
	}
	public void setQualityTest_testItem_sulphur_limit(String qualityTest_testItem_sulphur_limit) {
		this.qualityTest_testItem_sulphur_limit = qualityTest_testItem_sulphur_limit;
	}
	public String getQualityTest_testItem_sulphur_result() {
		return qualityTest_testItem_sulphur_result;
	}
	public void setQualityTest_testItem_sulphur_result(String qualityTest_testItem_sulphur_result) {
		this.qualityTest_testItem_sulphur_result = qualityTest_testItem_sulphur_result;
	}
	public String getQualityTest_testItem_sulphur_commentsOrRemark() {
		return qualityTest_testItem_sulphur_commentsOrRemark;
	}
	public void setQualityTest_testItem_sulphur_commentsOrRemark(String qualityTest_testItem_sulphur_commentsOrRemark) {
		this.qualityTest_testItem_sulphur_commentsOrRemark = qualityTest_testItem_sulphur_commentsOrRemark;
	}
	public String getQualityTest_testItem_carbonResidue_methodUsed() {
		return qualityTest_testItem_carbonResidue_methodUsed;
	}
	public void setQualityTest_testItem_carbonResidue_methodUsed(String qualityTest_testItem_carbonResidue_methodUsed) {
		this.qualityTest_testItem_carbonResidue_methodUsed = qualityTest_testItem_carbonResidue_methodUsed;
	}
	public String getQualityTest_testItem_carbonResidue_uom() {
		return qualityTest_testItem_carbonResidue_uom;
	}
	public void setQualityTest_testItem_carbonResidue_uom(String qualityTest_testItem_carbonResidue_uom) {
		this.qualityTest_testItem_carbonResidue_uom = qualityTest_testItem_carbonResidue_uom;
	}
	public String getQualityTest_testItem_carbonResidue_limit() {
		return qualityTest_testItem_carbonResidue_limit;
	}
	public void setQualityTest_testItem_carbonResidue_limit(String qualityTest_testItem_carbonResidue_limit) {
		this.qualityTest_testItem_carbonResidue_limit = qualityTest_testItem_carbonResidue_limit;
	}
	public String getQualityTest_testItem_carbonResidue_result() {
		return qualityTest_testItem_carbonResidue_result;
	}
	public void setQualityTest_testItem_carbonResidue_result(String qualityTest_testItem_carbonResidue_result) {
		this.qualityTest_testItem_carbonResidue_result = qualityTest_testItem_carbonResidue_result;
	}
	public String getQualityTest_testItem_carbonResidue_commentsOrRemark() {
		return qualityTest_testItem_carbonResidue_commentsOrRemark;
	}
	public void setQualityTest_testItem_carbonResidue_commentsOrRemark(
			String qualityTest_testItem_carbonResidue_commentsOrRemark) {
		this.qualityTest_testItem_carbonResidue_commentsOrRemark = qualityTest_testItem_carbonResidue_commentsOrRemark;
	}
	public String getQualityTest_testItem_saltContent_methodUsed() {
		return qualityTest_testItem_saltContent_methodUsed;
	}
	public void setQualityTest_testItem_saltContent_methodUsed(String qualityTest_testItem_saltContent_methodUsed) {
		this.qualityTest_testItem_saltContent_methodUsed = qualityTest_testItem_saltContent_methodUsed;
	}
	public String getQualityTest_testItem_saltContent_uom() {
		return qualityTest_testItem_saltContent_uom;
	}
	public void setQualityTest_testItem_saltContent_uom(String qualityTest_testItem_saltContent_uom) {
		this.qualityTest_testItem_saltContent_uom = qualityTest_testItem_saltContent_uom;
	}
	public String getQualityTest_testItem_saltContent_limit() {
		return qualityTest_testItem_saltContent_limit;
	}
	public void setQualityTest_testItem_saltContent_limit(String qualityTest_testItem_saltContent_limit) {
		this.qualityTest_testItem_saltContent_limit = qualityTest_testItem_saltContent_limit;
	}
	public String getQualityTest_testItem_saltContent_result() {
		return qualityTest_testItem_saltContent_result;
	}
	public void setQualityTest_testItem_saltContent_result(String qualityTest_testItem_saltContent_result) {
		this.qualityTest_testItem_saltContent_result = qualityTest_testItem_saltContent_result;
	}
	public String getQualityTest_testItem_saltContent_commentsOrRemark() {
		return qualityTest_testItem_saltContent_commentsOrRemark;
	}
	public void setQualityTest_testItem_saltContent_commentsOrRemark(
			String qualityTest_testItem_saltContent_commentsOrRemark) {
		this.qualityTest_testItem_saltContent_commentsOrRemark = qualityTest_testItem_saltContent_commentsOrRemark;
	}
	public String getQualityTest_testItem_ash_methodUsed() {
		return qualityTest_testItem_ash_methodUsed;
	}
	public void setQualityTest_testItem_ash_methodUsed(String qualityTest_testItem_ash_methodUsed) {
		this.qualityTest_testItem_ash_methodUsed = qualityTest_testItem_ash_methodUsed;
	}
	public String getQualityTest_testItem_ash_uom() {
		return qualityTest_testItem_ash_uom;
	}
	public void setQualityTest_testItem_ash_uom(String qualityTest_testItem_ash_uom) {
		this.qualityTest_testItem_ash_uom = qualityTest_testItem_ash_uom;
	}
	public String getQualityTest_testItem_ash_limit() {
		return qualityTest_testItem_ash_limit;
	}
	public void setQualityTest_testItem_ash_limit(String qualityTest_testItem_ash_limit) {
		this.qualityTest_testItem_ash_limit = qualityTest_testItem_ash_limit;
	}
	public String getQualityTest_testItem_ash_result() {
		return qualityTest_testItem_ash_result;
	}
	public void setQualityTest_testItem_ash_result(String qualityTest_testItem_ash_result) {
		this.qualityTest_testItem_ash_result = qualityTest_testItem_ash_result;
	}
	public String getQualityTest_testItem_ash_commentsOrRemark() {
		return qualityTest_testItem_ash_commentsOrRemark;
	}
	public void setQualityTest_testItem_ash_commentsOrRemark(String qualityTest_testItem_ash_commentsOrRemark) {
		this.qualityTest_testItem_ash_commentsOrRemark = qualityTest_testItem_ash_commentsOrRemark;
	}
	public String getQualityTest_testItem_vanadium_methodUsed() {
		return qualityTest_testItem_vanadium_methodUsed;
	}
	public void setQualityTest_testItem_vanadium_methodUsed(String qualityTest_testItem_vanadium_methodUsed) {
		this.qualityTest_testItem_vanadium_methodUsed = qualityTest_testItem_vanadium_methodUsed;
	}
	public String getQualityTest_testItem_vanadium_uom() {
		return qualityTest_testItem_vanadium_uom;
	}
	public void setQualityTest_testItem_vanadium_uom(String qualityTest_testItem_vanadium_uom) {
		this.qualityTest_testItem_vanadium_uom = qualityTest_testItem_vanadium_uom;
	}
	public String getQualityTest_testItem_vanadium_limit() {
		return qualityTest_testItem_vanadium_limit;
	}
	public void setQualityTest_testItem_vanadium_limit(String qualityTest_testItem_vanadium_limit) {
		this.qualityTest_testItem_vanadium_limit = qualityTest_testItem_vanadium_limit;
	}
	public String getQualityTest_testItem_vanadium_result() {
		return qualityTest_testItem_vanadium_result;
	}
	public void setQualityTest_testItem_vanadium_result(String qualityTest_testItem_vanadium_result) {
		this.qualityTest_testItem_vanadium_result = qualityTest_testItem_vanadium_result;
	}
	public String getQualityTest_testItem_vanadium_commentsOrRemark() {
		return qualityTest_testItem_vanadium_commentsOrRemark;
	}
	public void setQualityTest_testItem_vanadium_commentsOrRemark(String qualityTest_testItem_vanadium_commentsOrRemark) {
		this.qualityTest_testItem_vanadium_commentsOrRemark = qualityTest_testItem_vanadium_commentsOrRemark;
	}
	public String getQualityTest_testItem_alumimSil_methodUsed() {
		return qualityTest_testItem_alumimSil_methodUsed;
	}
	public void setQualityTest_testItem_alumimSil_methodUsed(String qualityTest_testItem_alumimSil_methodUsed) {
		this.qualityTest_testItem_alumimSil_methodUsed = qualityTest_testItem_alumimSil_methodUsed;
	}
	public String getQualityTest_testItem_alumimSil_uom() {
		return qualityTest_testItem_alumimSil_uom;
	}
	public void setQualityTest_testItem_alumimSil_uom(String qualityTest_testItem_alumimSil_uom) {
		this.qualityTest_testItem_alumimSil_uom = qualityTest_testItem_alumimSil_uom;
	}
	public String getQualityTest_testItem_alumimSil_limit() {
		return qualityTest_testItem_alumimSil_limit;
	}
	public void setQualityTest_testItem_alumimSil_limit(String qualityTest_testItem_alumimSil_limit) {
		this.qualityTest_testItem_alumimSil_limit = qualityTest_testItem_alumimSil_limit;
	}
	public String getQualityTest_testItem_alumimSil_result() {
		return qualityTest_testItem_alumimSil_result;
	}
	public void setQualityTest_testItem_alumimSil_result(String qualityTest_testItem_alumimSil_result) {
		this.qualityTest_testItem_alumimSil_result = qualityTest_testItem_alumimSil_result;
	}
	public String getQualityTest_testItem_alumimSil_commentsOrRemark() {
		return qualityTest_testItem_alumimSil_commentsOrRemark;
	}
	public void setQualityTest_testItem_alumimSil_commentsOrRemark(String qualityTest_testItem_alumimSil_commentsOrRemark) {
		this.qualityTest_testItem_alumimSil_commentsOrRemark = qualityTest_testItem_alumimSil_commentsOrRemark;
	}
	public String getQualityTest_testItem_acidNumber_methodUsed() {
		return qualityTest_testItem_acidNumber_methodUsed;
	}
	public void setQualityTest_testItem_acidNumber_methodUsed(String qualityTest_testItem_acidNumber_methodUsed) {
		this.qualityTest_testItem_acidNumber_methodUsed = qualityTest_testItem_acidNumber_methodUsed;
	}
	public String getQualityTest_testItem_acidNumber_uom() {
		return qualityTest_testItem_acidNumber_uom;
	}
	public void setQualityTest_testItem_acidNumber_uom(String qualityTest_testItem_acidNumber_uom) {
		this.qualityTest_testItem_acidNumber_uom = qualityTest_testItem_acidNumber_uom;
	}
	public String getQualityTest_testItem_acidNumber_limit() {
		return qualityTest_testItem_acidNumber_limit;
	}
	public void setQualityTest_testItem_acidNumber_limit(String qualityTest_testItem_acidNumber_limit) {
		this.qualityTest_testItem_acidNumber_limit = qualityTest_testItem_acidNumber_limit;
	}
	public String getQualityTest_testItem_acidNumber_result() {
		return qualityTest_testItem_acidNumber_result;
	}
	public void setQualityTest_testItem_acidNumber_result(String qualityTest_testItem_acidNumber_result) {
		this.qualityTest_testItem_acidNumber_result = qualityTest_testItem_acidNumber_result;
	}
	public String getQualityTest_testItem_acidNumber_commentsOrRemark() {
		return qualityTest_testItem_acidNumber_commentsOrRemark;
	}
	public void setQualityTest_testItem_acidNumber_commentsOrRemark(
			String qualityTest_testItem_acidNumber_commentsOrRemark) {
		this.qualityTest_testItem_acidNumber_commentsOrRemark = qualityTest_testItem_acidNumber_commentsOrRemark;
	}
	public String getTimeLog_event_endOfSeaPassage_dateOrtime() {
		return timeLog_event_endOfSeaPassage_dateOrtime;
	}
	public void setTimeLog_event_endOfSeaPassage_dateOrtime(String timeLog_event_endOfSeaPassage_dateOrtime) {
		this.timeLog_event_endOfSeaPassage_dateOrtime = timeLog_event_endOfSeaPassage_dateOrtime;
	}
	public String getTimeLog_event_endOfSeaPassage_comment() {
		return timeLog_event_endOfSeaPassage_comment;
	}
	public void setTimeLog_event_endOfSeaPassage_comment(String timeLog_event_endOfSeaPassage_comment) {
		this.timeLog_event_endOfSeaPassage_comment = timeLog_event_endOfSeaPassage_comment;
	}
	public String getTimeLog_event_norTendered_dateOrtime() {
		return timeLog_event_norTendered_dateOrtime;
	}
	public void setTimeLog_event_norTendered_dateOrtime(String timeLog_event_norTendered_dateOrtime) {
		this.timeLog_event_norTendered_dateOrtime = timeLog_event_norTendered_dateOrtime;
	}
	public String getTimeLog_event_norTendered_comment() {
		return timeLog_event_norTendered_comment;
	}
	public void setTimeLog_event_norTendered_comment(String timeLog_event_norTendered_comment) {
		this.timeLog_event_norTendered_comment = timeLog_event_norTendered_comment;
	}
	public String getTimeLog_event_seaPilotOnBoard_dateOrtime() {
		return timeLog_event_seaPilotOnBoard_dateOrtime;
	}
	public void setTimeLog_event_seaPilotOnBoard_dateOrtime(String timeLog_event_seaPilotOnBoard_dateOrtime) {
		this.timeLog_event_seaPilotOnBoard_dateOrtime = timeLog_event_seaPilotOnBoard_dateOrtime;
	}
	public String getTimeLog_event_seaPilotOnBoard_comment() {
		return timeLog_event_seaPilotOnBoard_comment;
	}
	public void setTimeLog_event_seaPilotOnBoard_comment(String timeLog_event_seaPilotOnBoard_comment) {
		this.timeLog_event_seaPilotOnBoard_comment = timeLog_event_seaPilotOnBoard_comment;
	}
	public String getTimeLog_event_firstRopeAshore_dateOrtime() {
		return timeLog_event_firstRopeAshore_dateOrtime;
	}
	public void setTimeLog_event_firstRopeAshore_dateOrtime(String timeLog_event_firstRopeAshore_dateOrtime) {
		this.timeLog_event_firstRopeAshore_dateOrtime = timeLog_event_firstRopeAshore_dateOrtime;
	}
	public String getTimeLog_event_firstRopeAshore_comment() {
		return timeLog_event_firstRopeAshore_comment;
	}
	public void setTimeLog_event_firstRopeAshore_comment(String timeLog_event_firstRopeAshore_comment) {
		this.timeLog_event_firstRopeAshore_comment = timeLog_event_firstRopeAshore_comment;
	}
	public String getTimeLog_event_allFast_dateOrtime() {
		return timeLog_event_allFast_dateOrtime;
	}
	public void setTimeLog_event_allFast_dateOrtime(String timeLog_event_allFast_dateOrtime) {
		this.timeLog_event_allFast_dateOrtime = timeLog_event_allFast_dateOrtime;
	}
	public String getTimeLog_event_allFast_comment() {
		return timeLog_event_allFast_comment;
	}
	public void setTimeLog_event_allFast_comment(String timeLog_event_allFast_comment) {
		this.timeLog_event_allFast_comment = timeLog_event_allFast_comment;
	}
	public String getTimeLog_event_norReceived_dateOrtime() {
		return timeLog_event_norReceived_dateOrtime;
	}
	public void setTimeLog_event_norReceived_dateOrtime(String timeLog_event_norReceived_dateOrtime) {
		this.timeLog_event_norReceived_dateOrtime = timeLog_event_norReceived_dateOrtime;
	}
	public String getTimeLog_event_norReceived_comment() {
		return timeLog_event_norReceived_comment;
	}
	public void setTimeLog_event_norReceived_comment(String timeLog_event_norReceived_comment) {
		this.timeLog_event_norReceived_comment = timeLog_event_norReceived_comment;
	}
	public String getTimeLog_event_commencedLoad_dateOrtime() {
		return timeLog_event_commencedLoad_dateOrtime;
	}
	public void setTimeLog_event_commencedLoad_dateOrtime(String timeLog_event_commencedLoad_dateOrtime) {
		this.timeLog_event_commencedLoad_dateOrtime = timeLog_event_commencedLoad_dateOrtime;
	}
	public String getTimeLog_event_commencedLoad_comment() {
		return timeLog_event_commencedLoad_comment;
	}
	public void setTimeLog_event_commencedLoad_comment(String timeLog_event_commencedLoad_comment) {
		this.timeLog_event_commencedLoad_comment = timeLog_event_commencedLoad_comment;
	}
	public String getTimeLog_event_completedLoad_dateOrtime() {
		return timeLog_event_completedLoad_dateOrtime;
	}
	public void setTimeLog_event_completedLoad_dateOrtime(String timeLog_event_completedLoad_dateOrtime) {
		this.timeLog_event_completedLoad_dateOrtime = timeLog_event_completedLoad_dateOrtime;
	}
	public String getTimeLog_event_completedLoad_comment() {
		return timeLog_event_completedLoad_comment;
	}
	public void setTimeLog_event_completedLoad_comment(String timeLog_event_completedLoad_comment) {
		this.timeLog_event_completedLoad_comment = timeLog_event_completedLoad_comment;
	}
	public String getTimeLog_event_tanksInspectedVesselSailed_dateOrtime() {
		return timeLog_event_tanksInspectedVesselSailed_dateOrtime;
	}
	public void setTimeLog_event_tanksInspectedVesselSailed_dateOrtime(
			String timeLog_event_tanksInspectedVesselSailed_dateOrtime) {
		this.timeLog_event_tanksInspectedVesselSailed_dateOrtime = timeLog_event_tanksInspectedVesselSailed_dateOrtime;
	}
	public String getTimeLog_event_tanksInspectedVesselSailed_comment() {
		return timeLog_event_tanksInspectedVesselSailed_comment;
	}
	public void setTimeLog_event_tanksInspectedVesselSailed_comment(
			String timeLog_event_tanksInspectedVesselSailed_comment) {
		this.timeLog_event_tanksInspectedVesselSailed_comment = timeLog_event_tanksInspectedVesselSailed_comment;
	}
	public String getQualityTest_shoreTankComposite() {
		return qualityTest_shoreTankComposite;
	}
	public void setQualityTest_shoreTankComposite(String qualityTest_shoreTankComposite) {
		this.qualityTest_shoreTankComposite = qualityTest_shoreTankComposite;
	}

}
