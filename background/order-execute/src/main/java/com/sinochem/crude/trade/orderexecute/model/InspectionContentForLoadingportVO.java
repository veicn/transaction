package com.sinochem.crude.trade.orderexecute.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 商检详情信息（装港）
 * @author me
 *
 */
public class InspectionContentForLoadingportVO {
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
	private String voyageSummary_surveyorAppointmen;
	
	@Expose
	@SerializedName("a7")
	private String voyageSummary_stowagePlan;
	
	@Expose
	@SerializedName("a8")
	private String voyageSummary_loadVolumesSummary_freeWaterAfterLoading_method;
	
	@Expose
	@SerializedName("a9")
	private String voyageSummary_loadVolumesSummary_freeWaterAfterLoading_bbl;
	
	@Expose
	@SerializedName("a10")
	private String voyageSummary_loadVolumesSummary_freeWaterAfterLoading_mt;
	
	@Expose
	@SerializedName("a11")
	private String voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_method;
	
	@Expose
	@SerializedName("a12")
	private String voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_bbl;
	
	@Expose
	@SerializedName("a13")
	private String voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_mt;
	
	@Expose
	@SerializedName("a14")
	private String voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_method;
	
	@Expose
	@SerializedName("a15")
	private String voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl;
	
	@Expose
	@SerializedName("a16")
	private String voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_mt;
	
	@Expose
	@SerializedName("a17")
	private String voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_method;
	
	@Expose
	@SerializedName("a18")
	private String voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_bbl;
	
	@Expose
	@SerializedName("a19")
	private String voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_mt;
	
	@Expose
	@SerializedName("a20")
	private String voyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_bbl;
	
	@Expose
	@SerializedName("a21")
	private String voyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_percent;
	
	@Expose
	@SerializedName("a22")
	private String voyageSummary_lossGainSummary_vesselFreeWaterDifference_bbl;
	
	@Expose
	@SerializedName("a23")
	private String voyageSummary_lossGainSummary_vesselFreeWaterDifference_percent;
	
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
	private String quantityResults_OBQTotalTanksToBeLoaded_BBL60F;
	
	@Expose
	@SerializedName("b21")
	private String quantityResults_OBQTotalTanksToBeLoaded_M3at15C;
	
	@Expose
	@SerializedName("b22")
	private String quantityResults_OBQTotalTanksToBeLoaded_Mtinair;
	
	@Expose
	@SerializedName("b23")
	private String quantityResults_OBQTotalTanksToBeLoaded_MtTonnesVac;
	
	@Expose
	@SerializedName("b24")
	private String quantityResults_OBQLiquid_BBL60F;
	
	@Expose
	@SerializedName("b25")
	private String quantityResults_OBQLiquid_M3at15C;
	
	@Expose
	@SerializedName("b26")
	private String quantityResults_OBQLiquid_Mtinair;
	
	@Expose
	@SerializedName("b27")
	private String quantityResults_OBQLiquid_MtTonnesVac;
	
	@Expose
	@SerializedName("b28")
	private String quantityResults_OBQNonLiquid_BBL60F;
	
	@Expose
	@SerializedName("b29")
	private String quantityResults_OBQNonLiquid_M3at15C;
	
	@Expose
	@SerializedName("b30")
	private String quantityResults_OBQNonLiquid_Mtinair;
	
	@Expose
	@SerializedName("b31")
	private String quantityResults_OBQNonLiquid_MtTonnesVac;
	
	@Expose
	@SerializedName("b32")
	private String quantityResults_OBQFreeWatersToBeLoaded_BBL60F;
	
	@Expose
	@SerializedName("b33")
	private String quantityResults_OBQFreeWatersToBeLoaded_M3at15C;
	
	@Expose
	@SerializedName("b34")
	private String quantityResults_OBQFreeWatersToBeLoaded_Mtinair;
	
	@Expose
	@SerializedName("b35")
	private String quantityResults_OBQFreeWatersToBeLoaded_MtTonnesVac;
	
	@Expose
	@SerializedName("b36")
	private String quantityResults_TCVAfterLoading_BBL60F;
	
	@Expose
	@SerializedName("b37")
	private String quantityResults_TCVAfterLoading_M3at15C;
	
	@Expose
	@SerializedName("b38")
	private String quantityResults_TCVAfterLoading_Mtinair;
	
	@Expose
	@SerializedName("b39")
	private String quantityResults_TCVAfterLoading_MtTonnesVac;
	
	@Expose
	@SerializedName("b40")
	private String quantityResults_GSVAfterLoading_BBL60F;
	
	@Expose
	@SerializedName("b41")
	private String quantityResults_GSVAfterLoading_M3at15C;
	
	@Expose
	@SerializedName("b42")
	private String quantityResults_GSVAfterLoading_Mtinair;
	
	@Expose
	@SerializedName("b43")
	private String quantityResults_GSVAfterLoading_MtTonnesVac;
	
	
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
	private String qualityTest_testItem_microCarbonResidue_methodUsed;
	
	@Expose
	@SerializedName("c39")
	private String qualityTest_testItem_microCarbonResidue_uom;
	
	@Expose
	@SerializedName("c40")
	private String qualityTest_testItem_microCarbonResidue_limit;
	
	@Expose
	@SerializedName("c41")
	private String qualityTest_testItem_microCarbonResidue_result;
	
	@Expose
	@SerializedName("c42")
	private String qualityTest_testItem_microCarbonResidue_commentsOrRemark;
	
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

	public String getVoyageSummary_surveyorAppointmen() {
		return voyageSummary_surveyorAppointmen;
	}

	public void setVoyageSummary_surveyorAppointmen(String voyageSummary_surveyorAppointmen) {
		this.voyageSummary_surveyorAppointmen = voyageSummary_surveyorAppointmen;
	}

	public String getVoyageSummary_stowagePlan() {
		return voyageSummary_stowagePlan;
	}

	public void setVoyageSummary_stowagePlan(String voyageSummary_stowagePlan) {
		this.voyageSummary_stowagePlan = voyageSummary_stowagePlan;
	}

	public String getVoyageSummary_loadVolumesSummary_freeWaterAfterLoading_method() {
		return voyageSummary_loadVolumesSummary_freeWaterAfterLoading_method;
	}

	public void setVoyageSummary_loadVolumesSummary_freeWaterAfterLoading_method(
			String voyageSummary_loadVolumesSummary_freeWaterAfterLoading_method) {
		this.voyageSummary_loadVolumesSummary_freeWaterAfterLoading_method = voyageSummary_loadVolumesSummary_freeWaterAfterLoading_method;
	}

	public String getVoyageSummary_loadVolumesSummary_freeWaterAfterLoading_bbl() {
		return voyageSummary_loadVolumesSummary_freeWaterAfterLoading_bbl;
	}

	public void setVoyageSummary_loadVolumesSummary_freeWaterAfterLoading_bbl(
			String voyageSummary_loadVolumesSummary_freeWaterAfterLoading_bbl) {
		this.voyageSummary_loadVolumesSummary_freeWaterAfterLoading_bbl = voyageSummary_loadVolumesSummary_freeWaterAfterLoading_bbl;
	}

	public String getVoyageSummary_loadVolumesSummary_freeWaterAfterLoading_mt() {
		return voyageSummary_loadVolumesSummary_freeWaterAfterLoading_mt;
	}

	public void setVoyageSummary_loadVolumesSummary_freeWaterAfterLoading_mt(
			String voyageSummary_loadVolumesSummary_freeWaterAfterLoading_mt) {
		this.voyageSummary_loadVolumesSummary_freeWaterAfterLoading_mt = voyageSummary_loadVolumesSummary_freeWaterAfterLoading_mt;
	}

	public String getVoyageSummary_loadVolumesSummary_shipTotalArrivalVolume_method() {
		return voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_method;
	}

	public void setVoyageSummary_loadVolumesSummary_shipTotalArrivalVolume_method(
			String voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_method) {
		this.voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_method = voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_method;
	}

	public String getVoyageSummary_loadVolumesSummary_shipTotalArrivalVolume_bbl() {
		return voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_bbl;
	}

	public void setVoyageSummary_loadVolumesSummary_shipTotalArrivalVolume_bbl(
			String voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_bbl) {
		this.voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_bbl = voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_bbl;
	}

	public String getVoyageSummary_loadVolumesSummary_shipTotalArrivalVolume_mt() {
		return voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_mt;
	}

	public void setVoyageSummary_loadVolumesSummary_shipTotalArrivalVolume_mt(
			String voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_mt) {
		this.voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_mt = voyageSummary_loadVolumesSummary_shipTotalArrivalVolume_mt;
	}

	public String getVoyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_method() {
		return voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_method;
	}

	public void setVoyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_method(
			String voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_method) {
		this.voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_method = voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_method;
	}

	public String getVoyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl() {
		return voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl;
	}

	public void setVoyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl(
			String voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl) {
		this.voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl = voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_bbl;
	}

	public String getVoyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_mt() {
		return voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_mt;
	}

	public void setVoyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_mt(
			String voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_mt) {
		this.voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_mt = voyageSummary_loadVolumesSummary_shipTotalArrivalVolumeVefApplied_mt;
	}

	public String getVoyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_method() {
		return voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_method;
	}

	public void setVoyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_method(
			String voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_method) {
		this.voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_method = voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_method;
	}

	public String getVoyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_bbl() {
		return voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_bbl;
	}

	public void setVoyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_bbl(
			String voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_bbl) {
		this.voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_bbl = voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_bbl;
	}

	public String getVoyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_mt() {
		return voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_mt;
	}

	public void setVoyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_mt(
			String voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_mt) {
		this.voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_mt = voyageSummary_loadVolumesSummary_shipNetArrivalVolumeVefApplied_mt;
	}

	public String getVoyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_bbl() {
		return voyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_bbl;
	}

	public void setVoyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_bbl(
			String voyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_bbl) {
		this.voyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_bbl = voyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_bbl;
	}

	public String getVoyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_percent() {
		return voyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_percent;
	}

	public void setVoyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_percent(
			String voyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_percent) {
		this.voyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_percent = voyageSummary_lossGainSummary_shipShoreTcvDiffVefApplied_percent;
	}

	public String getVoyageSummary_lossGainSummary_vesselFreeWaterDifference_bbl() {
		return voyageSummary_lossGainSummary_vesselFreeWaterDifference_bbl;
	}

	public void setVoyageSummary_lossGainSummary_vesselFreeWaterDifference_bbl(
			String voyageSummary_lossGainSummary_vesselFreeWaterDifference_bbl) {
		this.voyageSummary_lossGainSummary_vesselFreeWaterDifference_bbl = voyageSummary_lossGainSummary_vesselFreeWaterDifference_bbl;
	}

	public String getVoyageSummary_lossGainSummary_vesselFreeWaterDifference_percent() {
		return voyageSummary_lossGainSummary_vesselFreeWaterDifference_percent;
	}

	public void setVoyageSummary_lossGainSummary_vesselFreeWaterDifference_percent(
			String voyageSummary_lossGainSummary_vesselFreeWaterDifference_percent) {
		this.voyageSummary_lossGainSummary_vesselFreeWaterDifference_percent = voyageSummary_lossGainSummary_vesselFreeWaterDifference_percent;
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

	public String getQuantityResults_OBQTotalTanksToBeLoaded_BBL60F() {
		return quantityResults_OBQTotalTanksToBeLoaded_BBL60F;
	}

	public void setQuantityResults_OBQTotalTanksToBeLoaded_BBL60F(String quantityResults_OBQTotalTanksToBeLoaded_BBL60F) {
		this.quantityResults_OBQTotalTanksToBeLoaded_BBL60F = quantityResults_OBQTotalTanksToBeLoaded_BBL60F;
	}

	public String getQuantityResults_OBQTotalTanksToBeLoaded_M3at15C() {
		return quantityResults_OBQTotalTanksToBeLoaded_M3at15C;
	}

	public void setQuantityResults_OBQTotalTanksToBeLoaded_M3at15C(String quantityResults_OBQTotalTanksToBeLoaded_M3at15C) {
		this.quantityResults_OBQTotalTanksToBeLoaded_M3at15C = quantityResults_OBQTotalTanksToBeLoaded_M3at15C;
	}

	public String getQuantityResults_OBQTotalTanksToBeLoaded_Mtinair() {
		return quantityResults_OBQTotalTanksToBeLoaded_Mtinair;
	}

	public void setQuantityResults_OBQTotalTanksToBeLoaded_Mtinair(String quantityResults_OBQTotalTanksToBeLoaded_Mtinair) {
		this.quantityResults_OBQTotalTanksToBeLoaded_Mtinair = quantityResults_OBQTotalTanksToBeLoaded_Mtinair;
	}

	public String getQuantityResults_OBQTotalTanksToBeLoaded_MtTonnesVac() {
		return quantityResults_OBQTotalTanksToBeLoaded_MtTonnesVac;
	}

	public void setQuantityResults_OBQTotalTanksToBeLoaded_MtTonnesVac(
			String quantityResults_OBQTotalTanksToBeLoaded_MtTonnesVac) {
		this.quantityResults_OBQTotalTanksToBeLoaded_MtTonnesVac = quantityResults_OBQTotalTanksToBeLoaded_MtTonnesVac;
	}

	public String getQuantityResults_OBQLiquid_BBL60F() {
		return quantityResults_OBQLiquid_BBL60F;
	}

	public void setQuantityResults_OBQLiquid_BBL60F(String quantityResults_OBQLiquid_BBL60F) {
		this.quantityResults_OBQLiquid_BBL60F = quantityResults_OBQLiquid_BBL60F;
	}

	public String getQuantityResults_OBQLiquid_M3at15C() {
		return quantityResults_OBQLiquid_M3at15C;
	}

	public void setQuantityResults_OBQLiquid_M3at15C(String quantityResults_OBQLiquid_M3at15C) {
		this.quantityResults_OBQLiquid_M3at15C = quantityResults_OBQLiquid_M3at15C;
	}

	public String getQuantityResults_OBQLiquid_Mtinair() {
		return quantityResults_OBQLiquid_Mtinair;
	}

	public void setQuantityResults_OBQLiquid_Mtinair(String quantityResults_OBQLiquid_Mtinair) {
		this.quantityResults_OBQLiquid_Mtinair = quantityResults_OBQLiquid_Mtinair;
	}

	public String getQuantityResults_OBQLiquid_MtTonnesVac() {
		return quantityResults_OBQLiquid_MtTonnesVac;
	}

	public void setQuantityResults_OBQLiquid_MtTonnesVac(String quantityResults_OBQLiquid_MtTonnesVac) {
		this.quantityResults_OBQLiquid_MtTonnesVac = quantityResults_OBQLiquid_MtTonnesVac;
	}

	public String getQuantityResults_OBQNonLiquid_BBL60F() {
		return quantityResults_OBQNonLiquid_BBL60F;
	}

	public void setQuantityResults_OBQNonLiquid_BBL60F(String quantityResults_OBQNonLiquid_BBL60F) {
		this.quantityResults_OBQNonLiquid_BBL60F = quantityResults_OBQNonLiquid_BBL60F;
	}

	public String getQuantityResults_OBQNonLiquid_M3at15C() {
		return quantityResults_OBQNonLiquid_M3at15C;
	}

	public void setQuantityResults_OBQNonLiquid_M3at15C(String quantityResults_OBQNonLiquid_M3at15C) {
		this.quantityResults_OBQNonLiquid_M3at15C = quantityResults_OBQNonLiquid_M3at15C;
	}

	public String getQuantityResults_OBQNonLiquid_Mtinair() {
		return quantityResults_OBQNonLiquid_Mtinair;
	}

	public void setQuantityResults_OBQNonLiquid_Mtinair(String quantityResults_OBQNonLiquid_Mtinair) {
		this.quantityResults_OBQNonLiquid_Mtinair = quantityResults_OBQNonLiquid_Mtinair;
	}

	public String getQuantityResults_OBQNonLiquid_MtTonnesVac() {
		return quantityResults_OBQNonLiquid_MtTonnesVac;
	}

	public void setQuantityResults_OBQNonLiquid_MtTonnesVac(String quantityResults_OBQNonLiquid_MtTonnesVac) {
		this.quantityResults_OBQNonLiquid_MtTonnesVac = quantityResults_OBQNonLiquid_MtTonnesVac;
	}

	public String getQuantityResults_OBQFreeWatersToBeLoaded_BBL60F() {
		return quantityResults_OBQFreeWatersToBeLoaded_BBL60F;
	}

	public void setQuantityResults_OBQFreeWatersToBeLoaded_BBL60F(String quantityResults_OBQFreeWatersToBeLoaded_BBL60F) {
		this.quantityResults_OBQFreeWatersToBeLoaded_BBL60F = quantityResults_OBQFreeWatersToBeLoaded_BBL60F;
	}

	public String getQuantityResults_OBQFreeWatersToBeLoaded_M3at15C() {
		return quantityResults_OBQFreeWatersToBeLoaded_M3at15C;
	}

	public void setQuantityResults_OBQFreeWatersToBeLoaded_M3at15C(String quantityResults_OBQFreeWatersToBeLoaded_M3at15C) {
		this.quantityResults_OBQFreeWatersToBeLoaded_M3at15C = quantityResults_OBQFreeWatersToBeLoaded_M3at15C;
	}

	public String getQuantityResults_OBQFreeWatersToBeLoaded_Mtinair() {
		return quantityResults_OBQFreeWatersToBeLoaded_Mtinair;
	}

	public void setQuantityResults_OBQFreeWatersToBeLoaded_Mtinair(String quantityResults_OBQFreeWatersToBeLoaded_Mtinair) {
		this.quantityResults_OBQFreeWatersToBeLoaded_Mtinair = quantityResults_OBQFreeWatersToBeLoaded_Mtinair;
	}

	public String getQuantityResults_OBQFreeWatersToBeLoaded_MtTonnesVac() {
		return quantityResults_OBQFreeWatersToBeLoaded_MtTonnesVac;
	}

	public void setQuantityResults_OBQFreeWatersToBeLoaded_MtTonnesVac(
			String quantityResults_OBQFreeWatersToBeLoaded_MtTonnesVac) {
		this.quantityResults_OBQFreeWatersToBeLoaded_MtTonnesVac = quantityResults_OBQFreeWatersToBeLoaded_MtTonnesVac;
	}

	public String getQuantityResults_TCVAfterLoading_BBL60F() {
		return quantityResults_TCVAfterLoading_BBL60F;
	}

	public void setQuantityResults_TCVAfterLoading_BBL60F(String quantityResults_TCVAfterLoading_BBL60F) {
		this.quantityResults_TCVAfterLoading_BBL60F = quantityResults_TCVAfterLoading_BBL60F;
	}

	public String getQuantityResults_TCVAfterLoading_M3at15C() {
		return quantityResults_TCVAfterLoading_M3at15C;
	}

	public void setQuantityResults_TCVAfterLoading_M3at15C(String quantityResults_TCVAfterLoading_M3at15C) {
		this.quantityResults_TCVAfterLoading_M3at15C = quantityResults_TCVAfterLoading_M3at15C;
	}

	public String getQuantityResults_TCVAfterLoading_Mtinair() {
		return quantityResults_TCVAfterLoading_Mtinair;
	}

	public void setQuantityResults_TCVAfterLoading_Mtinair(String quantityResults_TCVAfterLoading_Mtinair) {
		this.quantityResults_TCVAfterLoading_Mtinair = quantityResults_TCVAfterLoading_Mtinair;
	}

	public String getQuantityResults_TCVAfterLoading_MtTonnesVac() {
		return quantityResults_TCVAfterLoading_MtTonnesVac;
	}

	public void setQuantityResults_TCVAfterLoading_MtTonnesVac(String quantityResults_TCVAfterLoading_MtTonnesVac) {
		this.quantityResults_TCVAfterLoading_MtTonnesVac = quantityResults_TCVAfterLoading_MtTonnesVac;
	}

	public String getQuantityResults_GSVAfterLoading_BBL60F() {
		return quantityResults_GSVAfterLoading_BBL60F;
	}

	public void setQuantityResults_GSVAfterLoading_BBL60F(String quantityResults_GSVAfterLoading_BBL60F) {
		this.quantityResults_GSVAfterLoading_BBL60F = quantityResults_GSVAfterLoading_BBL60F;
	}

	public String getQuantityResults_GSVAfterLoading_M3at15C() {
		return quantityResults_GSVAfterLoading_M3at15C;
	}

	public void setQuantityResults_GSVAfterLoading_M3at15C(String quantityResults_GSVAfterLoading_M3at15C) {
		this.quantityResults_GSVAfterLoading_M3at15C = quantityResults_GSVAfterLoading_M3at15C;
	}

	public String getQuantityResults_GSVAfterLoading_Mtinair() {
		return quantityResults_GSVAfterLoading_Mtinair;
	}

	public void setQuantityResults_GSVAfterLoading_Mtinair(String quantityResults_GSVAfterLoading_Mtinair) {
		this.quantityResults_GSVAfterLoading_Mtinair = quantityResults_GSVAfterLoading_Mtinair;
	}

	public String getQuantityResults_GSVAfterLoading_MtTonnesVac() {
		return quantityResults_GSVAfterLoading_MtTonnesVac;
	}

	public void setQuantityResults_GSVAfterLoading_MtTonnesVac(String quantityResults_GSVAfterLoading_MtTonnesVac) {
		this.quantityResults_GSVAfterLoading_MtTonnesVac = quantityResults_GSVAfterLoading_MtTonnesVac;
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

	public String getQualityTest_testItem_microCarbonResidue_methodUsed() {
		return qualityTest_testItem_microCarbonResidue_methodUsed;
	}

	public void setQualityTest_testItem_microCarbonResidue_methodUsed(
			String qualityTest_testItem_microCarbonResidue_methodUsed) {
		this.qualityTest_testItem_microCarbonResidue_methodUsed = qualityTest_testItem_microCarbonResidue_methodUsed;
	}

	public String getQualityTest_testItem_microCarbonResidue_uom() {
		return qualityTest_testItem_microCarbonResidue_uom;
	}

	public void setQualityTest_testItem_microCarbonResidue_uom(String qualityTest_testItem_microCarbonResidue_uom) {
		this.qualityTest_testItem_microCarbonResidue_uom = qualityTest_testItem_microCarbonResidue_uom;
	}

	public String getQualityTest_testItem_microCarbonResidue_limit() {
		return qualityTest_testItem_microCarbonResidue_limit;
	}

	public void setQualityTest_testItem_microCarbonResidue_limit(String qualityTest_testItem_microCarbonResidue_limit) {
		this.qualityTest_testItem_microCarbonResidue_limit = qualityTest_testItem_microCarbonResidue_limit;
	}

	public String getQualityTest_testItem_microCarbonResidue_result() {
		return qualityTest_testItem_microCarbonResidue_result;
	}

	public void setQualityTest_testItem_microCarbonResidue_result(String qualityTest_testItem_microCarbonResidue_result) {
		this.qualityTest_testItem_microCarbonResidue_result = qualityTest_testItem_microCarbonResidue_result;
	}

	public String getQualityTest_testItem_microCarbonResidue_commentsOrRemark() {
		return qualityTest_testItem_microCarbonResidue_commentsOrRemark;
	}

	public void setQualityTest_testItem_microCarbonResidue_commentsOrRemark(
			String qualityTest_testItem_microCarbonResidue_commentsOrRemark) {
		this.qualityTest_testItem_microCarbonResidue_commentsOrRemark = qualityTest_testItem_microCarbonResidue_commentsOrRemark;
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
