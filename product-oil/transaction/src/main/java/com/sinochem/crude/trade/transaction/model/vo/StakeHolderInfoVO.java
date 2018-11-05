package com.sinochem.crude.trade.transaction.model.vo;

import com.sinochem.crude.trade.common.base.BasePersistVO;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.transaction.domain.po.StakeholderInfo;
import org.springframework.web.context.ContextLoader;

import java.util.Locale;

/**
 * 干系人（买家，买家）信息的VO
 * @author Yichen Zhao
 * date: 20180303
 */
public class StakeHolderInfoVO extends BasePersistVO<StakeholderInfo> {

    /**
     * 干系企业的公司ID
     */
    private Long enterpriseId;

    /**
     * 干系企业名称通过member模块带出，需要请求接口防止数据不一致
     */
    private String enterpriseName;

    /**
     * 干系企业地址
     */
    private String address;

    /**
     * 交易员
     */
    private String traderName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 传真
     */
    private String fax;

    
    public StakeHolderInfoVO() {
	
	}

	public StakeHolderInfoVO(StakeholderInfo stakeholderInfo) {
        super(stakeholderInfo);
    }

    @Override
    protected void convertToVO(StakeholderInfo domain) {
        EnterpriseService enterpriseService = ContextLoader.getCurrentWebApplicationContext().getBean(EnterpriseService.class);

        this.enterpriseId = domain.getEnterpriseId();

        //enterpriseName通过member模块带出
        Long enterpriseId = domain.getEnterpriseId();
        if (enterpriseId != null) {
            EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(enterpriseId);

            if (enterpriseVo != null) {
                this.enterpriseName = enterpriseVo.getEnglishName();
            }
        }

        this.address = domain.getAddress();
        this.traderName = domain.getTraderName();
        this.email = domain.getEmail();
        this.telephone = domain.getTelephone();
        this.fax = domain.getFax();
    }

    @Override
    protected StakeholderInfo convertToDomain() {
        StakeholderInfo stakeholderInfo = new StakeholderInfo();

        stakeholderInfo.setAddress(this.address);
        stakeholderInfo.setTraderName(this.traderName);
        stakeholderInfo.setEmail(this.email);
        stakeholderInfo.setTelephone(this.telephone);
        stakeholderInfo.setFax(this.fax);

        return stakeholderInfo;
    }

    /** getters and setters */
    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
