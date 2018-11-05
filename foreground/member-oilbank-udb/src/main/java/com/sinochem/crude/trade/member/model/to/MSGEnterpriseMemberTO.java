package com.sinochem.crude.trade.member.model.to;

import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.Member;
import com.sinochem.crude.trade.member.model.vo.MemberInfoVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * class_name: MSGEnterpriseMemberTO
 * package: com.sinochem.member.api.vo
 * describe: MIC 消息队列 企业会员信息
 * create_user: wj
 * create_date: 5/20/2018
 * create_time: 11:10 AM
 **/
@SuppressWarnings("unused")
public class MSGEnterpriseMemberTO implements Serializable {

	private static final long serialVersionUID = 5551313233181282560L;
	/**主键**/
	private String id;
	/**企业编号 全网会员编号**/
	private String enterpriseId;
	/**企业编号**/
	private String enterpriseUid;
	/**会员ID**/
	private String memberId;
	/**企业编号**/
	private String memberUid;
	/**手机号码**/
	private String mobile;
	/**是否企业管理员**/
	private String isAdmin;
	/**是否已确认**/
	private String isConfirm;
	/**备注**/
	private String memo;
	/**修改人**/
	private String updateUser;
	/**更新数据来源**/
	private String updateSource;
	/**创建人**/
	private String createUser;
	/**修改时间**/
	private Date updateTime;
	/**创建数据来源**/
	private String createSource;
	/**创建时间**/
	private Date createTime;
	/**删除标志**/
	private String delFlg;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseUid() {
		return enterpriseUid;
	}

	public void setEnterpriseUid(String enterpriseUid) {
		this.enterpriseUid = enterpriseUid;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberUid() {
		return memberUid;
	}

	public void setMemberUid(String memberUid) {
		this.memberUid = memberUid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateSource() {
		return updateSource;
	}

	public void setUpdateSource(String updateSource) {
		this.updateSource = updateSource;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateSource() {
		return createSource;
	}

	public void setCreateSource(String createSource) {
		this.createSource = createSource;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

    public MSGEnterpriseMemberTO(MemberInfoVO member, Enterprise enterprise){
		Date date = new Date();
		this.enterpriseUid = enterprise.getCode();
		this.memberUid = member.getCode();
		this.mobile = member.getMobile();
		this.isAdmin = "0";
		this.isConfirm = "1";
		this.updateSource = "mycrudeoil";
		this.createSource = "mycrudeoil";
		this.delFlg = "0";
		this.updateTime = date;
		this.createTime = date;
		this.createUser = "mycrudeoil";
		this.updateUser = "mycrudeoil";
	}
}
