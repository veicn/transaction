package com.sinochem.crude.trade.order.remote;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sinochem.it.b2b.common.exception.BizException;

/**
 * 订单状态机接口
 * 
 * @author Leo
 *
 */
public interface OrderStatusService {

	/**
	 * 设定状态，状态机会判断是否可以执行，执行失败会有报错
	 */
	public void setOrderStatus(Long orderId, String itemCode, int status,
			String detail, Long operId) throws BizException;

	/**
	 * 仅仅添加详情，状态机的状态是不会改变的
	 */
	public void addDetail(Long order, String detail, Long operId)
			throws BizException;

	/**
	 * 得到该订单当前所有的状态
	 */
	public List<StatusVO> getCurrentStatus(Long orderId);

	/**
     * 读取订单状态详情,itemCode可以为空如果为空就是获取整个日志
	 */
	public List<StatusDetailVO> getStatusDetail(Long orderId,String code) throws BizException;

	/**
	 * 得到该订单该状态的值
	 */
	public int getCurrentStatus(Long orderId, String item);

	/**
	 * 状态
	 *
	 * @author Leo
	 *
	 */
	public class StatusVO implements Serializable {

		/**
		 *
		 */
		private static final long serialVersionUID = -6357901111276803323L;

		private String item;

		private int value;

		public String getItem() {
			return item;
		}

		public void setItem(String item) {
			this.item = item;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

	}/**
	 * 状态详情
	 *
	 * @author Leo
	 *
	 */
	public class StatusDetailVO implements Serializable {

		public StatusDetailVO() {

		}


		/**
		 * 订单编号
		 */
		private Long orderId;

		/**
		 * 订单状态项目编号
		 */
		private String itemCode;

		/**
		 * 编号
		 */
		private int value;

		/**
		 * 描述
		 */
		private String desc;

		private Date createDate;

		private Long creater;

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

		public String getItemCode() {
			return itemCode;
		}

		public void setItemCode(String itemCode) {
			this.itemCode = itemCode;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public Long getCreater() {
			return creater;
		}

		public void setCreater(Long creater) {
			this.creater = creater;
		}
	}
}
