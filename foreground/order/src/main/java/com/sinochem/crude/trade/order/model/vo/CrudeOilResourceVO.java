package com.sinochem.crude.trade.order.model.vo;

import com.sinochem.crude.trade.goods.remote.BodyPropertiesVO;
import com.sinochem.crude.trade.order.domain.CrudeOilResource;
import com.sinochem.crude.trade.order.util.DictUtils;
import com.sinochem.it.b2b.common.utils.DateUtil;

import java.util.Date;
import java.util.Map;

/**
 * CrudeOilResource的VO
 * @author Yichen Zhao
 * date: 20180129
 */
public class CrudeOilResourceVO extends BodyPropertiesVO {

    /**
     * 区域
     */
    private Long area;

    /**
     * 英文名字
     */
    private String name;

    /**
     * 中文名字
     */
    private String cnName;

    /**
     * 产地
     */
    private Long origin;
    private String originContent;

    /**
     * 物性表id
     */
    private Long propertyId;

    /**
     * 发布的版本
     */
    private Date version;
    private String versionAsString;

    /**
     * 备注
     */
    private String desc;

    /**
     * 合约id
     */
    private Long contractId;

    public static void convertToCrudeOilResourceVO(
            CrudeOilResource crudeOilResource, CrudeOilResourceVO crudeOilResourceVO) {
        if (crudeOilResource == null || crudeOilResourceVO == null) {
            return;
        }
        crudeOilResourceVO.setId(crudeOilResource.getId());
        crudeOilResourceVO.setArea(crudeOilResource.getArea());
        crudeOilResourceVO.setName(crudeOilResource.getName());
        crudeOilResourceVO.setCnName(crudeOilResource.getCnName());

        Long origin = crudeOilResource.getOrigin();
        if (origin != null) {
            crudeOilResourceVO.setOrigin(origin);
            Map<Object, String> crudeOilOriginMap = DictUtils.getCrudeOilOrigin();
            crudeOilResourceVO.setOriginContent(crudeOilOriginMap.get(origin));
        }

        crudeOilResourceVO.setPropertyId(crudeOilResource.getPropertyId());

        Date version = crudeOilResource.getVersion();
        if (version != null) {
            crudeOilResourceVO.setVersion(version);
            crudeOilResourceVO.setVersionAsString(DateUtil.formatDate(version));
        }

        crudeOilResourceVO.setDesc(crudeOilResource.getDesc());
        crudeOilResourceVO.setContractId(crudeOilResource.getContractId());
    }

    public static void convertToCrudeOilResource(
            CrudeOilResourceVO crudeOilResourceVO, CrudeOilResource crudeOilResource) {
        if (crudeOilResourceVO == null || crudeOilResource == null) {
            return;
        }
        crudeOilResource.setId(crudeOilResourceVO.getId());
        crudeOilResource.setArea(crudeOilResourceVO.getArea());
        crudeOilResource.setName(crudeOilResourceVO.getName());
        crudeOilResource.setCnName(crudeOilResourceVO.getCnName());
        crudeOilResource.setOrigin(crudeOilResourceVO.getOrigin());
        crudeOilResource.setPropertyId(crudeOilResourceVO.getPropertyId());
        crudeOilResource.setVersion(crudeOilResourceVO.getVersion());
        crudeOilResource.setDesc(crudeOilResourceVO.getDesc());
        crudeOilResource.setContractId(crudeOilResourceVO.getContractId());
    }

    /** getters and setters */
    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public Long getOrigin() {
        return origin;
    }

    public void setOrigin(Long origin) {
        this.origin = origin;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getOriginContent() {
        return originContent;
    }

    public void setOriginContent(String originContent) {
        this.originContent = originContent;
    }

    public String getVersionAsString() {
        return versionAsString;
    }

    public void setVersionAsString(String versionAsString) {
        this.versionAsString = versionAsString;
    }
}
