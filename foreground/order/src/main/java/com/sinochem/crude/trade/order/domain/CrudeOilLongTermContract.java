package com.sinochem.crude.trade.order.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 长约
 * 
 * @author Leo
 *
 */
public class CrudeOilLongTermContract extends CrudeOilBuyContract {

	public CrudeOilLongTermContract() {
		super();
		super.setContractType("L");
	}

	private int startTime;

	private int endTime;

	private List<CrudeOilLongTermContractPlan> plans = new ArrayList<CrudeOilLongTermContractPlan>();

	public List<CrudeOilLongTermContractPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<CrudeOilLongTermContractPlan> plans) {
		this.plans = plans;
	}

	/**
	 * 添加一条长约计划
	 * @param plan
	 */
	public void addCrudeOilLongTermContractPlan(CrudeOilLongTermContractPlan plan) {
		if (null == this.plans) {
			this.plans = new ArrayList<CrudeOilLongTermContractPlan>();
		}
		this.plans.add(plan);
		if (plan.getDate() < startTime || startTime == 0) {
			this.startTime = plan.getDate();
		}
		if (plan.getDate() > endTime || endTime == 0) {
			this.endTime = plan.getDate();
		}
	}
}
