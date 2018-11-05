package com.sinochem.crude.trade.goods.domain;


import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 物性表
 *
 * @author Leo
 *
 */
public class BodyProperties<T> {

	/**
	 * 默认物性值倍数
	 */
	private static final BigDecimal indicatorMultiple = new BigDecimal("100");

	/**
	 * 物性id
	 */
	private Long id;

	private List<Entry> entries;

	/**
	 * 指标项（值*100）
	 */
	private long indicator1Min;
	/**
	 * 指标项（值*100）
	 */
	private long indicator1Max;
	/**
	 * 指标项（值*100）
	 */
	private long indicator2Min;
	/**
	 * 指标项（值*100）
	 */
	private long indicator2Max;
	/**
	 * 指标项（值*100）
	 */
	private long indicator3Min;
	/**
	 * 指标项（值*100）
	 */
	private long indicator3Max;
	/**
	 * 指标项（值*100）
	 */
	private long indicator4Min;
	/**
	 * 指标项（值*100）
	 */
	private long indicator4Max;
	/**
	 * 指标项（值*100）
	 */
	private long indicator5Min;
	/**
	 * 指标项（值*100）
	 */
	private long indicator5Max;
	/**
	 * 指标项（值*100）
	 */
	private long indicator6Min;
	/**
	 * 指标项（值*100）
	 */
	private long indicator6Max;
	/**
	 * 指标项（值*100）
	 */
	private long indicator7Min;
	/**
	 * 指标项（值*100）
	 */
	private long indicator7Max;
	/**
	 * 指标项（值*100）
	 */
	private long indicator8Min;
	/**
	 * 指标项（值*100）
	 */
	private long indicator8Max;
	/**
	 * 指标项（值*100）
	 */
	private long indicator9Min;
	/**
	 * 指标项（值*100）
	 */
	private long indicator9Max;
	/**
	 * 指标项（值*100）
	 */
	private long indicator10Min;
	/**
	 * 指标项（值*100）
	 */
	private long indicator10Max;
	/**
	 * 指标项（值*100）
	 */
	private long indicator11Min;
	/**
	 * 指标项（值*100）
	 */
	private long indicator11Max;
	/**
	 * 指标项（值*100）
	 */
	private long indicator12Min;
	/**
	 * 指标项（值*100）
	 */
	private long indicator12Max;

	public class Entry {

		/**
		 * 指表项
		 */
		private T indicator;

		/**
		 * 对应的最小值
		 */
		private BigDecimal min;

		/**
		 * 对应的最大值
		 */
		private BigDecimal max;

		/**
		 * 排序号
		 */
		private int order;

		/**
		 * 是否是范围指标,如是是范围指标，就是mix和max不一样，如果是固定值，就是两个一样
		 */
		public boolean isRange() {
			return !this.min.equals(max);
		}

		public T getIndicator() {
			return indicator;
		}

		public void T(T indicator) {
			this.indicator = indicator;
		}

		public BigDecimal getValue() {
			return isRange() ? min : null;
		}

		public void setValue(BigDecimal value) {
			this.min = value;
			this.max = value;
		}

		public BigDecimal getMin() {
			return min;
		}

		public void setMin(BigDecimal min) {
			this.min = min;
		}

		public BigDecimal getMax() {
			return max;
		}

		public void setMax(BigDecimal max) {
			this.max = max;
		}

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Entry> getEntries() {
		if (CollectionUtils.isEmpty(entries)) {
			resetEntries();
		}
		return entries;
	}

	public long getIndicator1Min() {
		return indicator1Min;
	}

	public void setIndicator1Min(long indicator1Min) {
		this.indicator1Min = indicator1Min;
	}

	public long getIndicator1Max() {
		return indicator1Max;
	}

	public void setIndicator1Max(long indicator1Max) {
		this.indicator1Max = indicator1Max;
	}

	public long getIndicator2Min() {
		return indicator2Min;
	}

	public void setIndicator2Min(long indicator2Min) {
		this.indicator2Min = indicator2Min;
	}

	public long getIndicator2Max() {
		return indicator2Max;
	}

	public void setIndicator2Max(long indicator2Max) {
		this.indicator2Max = indicator2Max;
	}

	public long getIndicator3Min() {
		return indicator3Min;
	}

	public void setIndicator3Min(long indicator3Min) {
		this.indicator3Min = indicator3Min;
	}

	public long getIndicator3Max() {
		return indicator3Max;
	}

	public void setIndicator3Max(long indicator3Max) {
		this.indicator3Max = indicator3Max;
	}

	public long getIndicator4Min() {
		return indicator4Min;
	}

	public void setIndicator4Min(long indicator4Min) {
		this.indicator4Min = indicator4Min;
	}

	public long getIndicator4Max() {
		return indicator4Max;
	}

	public void setIndicator4Max(long indicator4Max) {
		this.indicator4Max = indicator4Max;
	}

	public long getIndicator5Min() {
		return indicator5Min;
	}

	public void setIndicator5Min(long indicator5Min) {
		this.indicator5Min = indicator5Min;
	}

	public long getIndicator5Max() {
		return indicator5Max;
	}

	public void setIndicator5Max(long indicator5Max) {
		this.indicator5Max = indicator5Max;
	}

	public long getIndicator6Min() {
		return indicator6Min;
	}

	public void setIndicator6Min(long indicator6Min) {
		this.indicator6Min = indicator6Min;
	}

	public long getIndicator6Max() {
		return indicator6Max;
	}

	public void setIndicator6Max(long indicator6Max) {
		this.indicator6Max = indicator6Max;
	}

	public long getIndicator7Min() {
		return indicator7Min;
	}

	public void setIndicator7Min(long indicator7Min) {
		this.indicator7Min = indicator7Min;
	}

	public long getIndicator7Max() {
		return indicator7Max;
	}

	public void setIndicator7Max(long indicator7Max) {
		this.indicator7Max = indicator7Max;
	}

	public long getIndicator8Min() {
		return indicator8Min;
	}

	public void setIndicator8Min(long indicator8Min) {
		this.indicator8Min = indicator8Min;
	}

	public long getIndicator8Max() {
		return indicator8Max;
	}

	public void setIndicator8Max(long indicator8Max) {
		this.indicator8Max = indicator8Max;
	}

	public long getIndicator9Min() {
		return indicator9Min;
	}

	public void setIndicator9Min(long indicator9Min) {
		this.indicator9Min = indicator9Min;
	}

	public long getIndicator9Max() {
		return indicator9Max;
	}

	public void setIndicator9Max(long indicator9Max) {
		this.indicator9Max = indicator9Max;
	}

	public long getIndicator10Min() {
		return indicator10Min;
	}

	public void setIndicator10Min(long indicator10Min) {
		this.indicator10Min = indicator10Min;
	}

	public long getIndicator10Max() {
		return indicator10Max;
	}

	public void setIndicator10Max(long indicator10Max) {
		this.indicator10Max = indicator10Max;
	}

	public long getIndicator11Min() {
		return indicator11Min;
	}

	public void setIndicator11Min(long indicator11Min) {
		this.indicator11Min = indicator11Min;
	}

	public long getIndicator11Max() {
		return indicator11Max;
	}

	public void setIndicator11Max(long indicator11Max) {
		this.indicator11Max = indicator11Max;
	}

	public long getIndicator12Min() {
		return indicator12Min;
	}

	public void setIndicator12Min(long indicator12Min) {
		this.indicator12Min = indicator12Min;
	}

	public long getIndicator12Max() {
		return indicator12Max;
	}

	public void setIndicator12Max(long indicator12Max) {
		this.indicator12Max = indicator12Max;
	}

	/**
	 * 重设entries
	 */
	public void resetEntries() {
		entries = new ArrayList<Entry>(12);
		Entry entry1 = new Entry();
		entry1.setOrder(0);
		entry1.setMin(getTrueValue(indicator1Min));
		entry1.setMax(getTrueValue(indicator1Max));
		entries.add(entry1);
		Entry entry2 = new Entry();
		entry2.setOrder(1);
		entry2.setMin(getTrueValue(indicator2Min));
		entry2.setMax(getTrueValue(indicator2Max));
		entries.add(entry2);
		Entry entry3 = new Entry();
		entry3.setOrder(2);
		entry3.setMin(getTrueValue(indicator3Min));
		entry3.setMax(getTrueValue(indicator3Max));
		entries.add(entry3);
		Entry entry4 = new Entry();
		entry4.setOrder(3);
		entry4.setMin(getTrueValue(indicator4Min));
		entry4.setMax(getTrueValue(indicator4Max));
		entries.add(entry4);
		Entry entry5 = new Entry();
		entry5.setOrder(4);
		entry5.setMin(getTrueValue(indicator5Min));
		entry5.setMax(getTrueValue(indicator5Max));
		entries.add(entry5);
		Entry entry6 = new Entry();
		entry6.setOrder(5);
		entry6.setMin(getTrueValue(indicator6Min));
		entry6.setMax(getTrueValue(indicator6Max));
		entries.add(entry6);
		Entry entry7 = new Entry();
		entry7.setOrder(6);
		entry7.setMin(getTrueValue(indicator7Min));
		entry7.setMax(getTrueValue(indicator7Max));
		entries.add(entry7);
		Entry entry8 = new Entry();
		entry8.setOrder(7);
		entry8.setMin(getTrueValue(indicator8Min));
		entry8.setMax(getTrueValue(indicator8Max));
		entries.add(entry8);
		Entry entry9 = new Entry();
		entry9.setOrder(8);
		entry9.setMin(getTrueValue(indicator9Min));
		entry9.setMax(getTrueValue(indicator9Max));
		entries.add(entry9);
		Entry entry10 = new Entry();
		entry10.setOrder(9);
		entry10.setMin(getTrueValue(indicator10Min));
		entry10.setMax(getTrueValue(indicator10Max));
		entries.add(entry10);
		Entry entry11 = new Entry();
		entry11.setOrder(10);
		entry11.setMin(getTrueValue(indicator11Min));
		entry11.setMax(getTrueValue(indicator11Max));
		entries.add(entry11);
		Entry entry12 = new Entry();
		entry12.setOrder(11);
		entry12.setMin(getTrueValue(indicator12Min));
		entry12.setMax(getTrueValue(indicator12Max));
		entries.add(entry12);
	}

	/**
	 * 获取真实值
	 * <p>
	 *     按说只会有2位小数，所以直接舍成两位小数
	 * </p>
	 * @param indicator
	 * @return
	 */
	private BigDecimal getTrueValue(long indicator) {
		return new BigDecimal(indicator).divide(indicatorMultiple, 2, BigDecimal.ROUND_DOWN);
	}
}
