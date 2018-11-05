package com.sinochem.crude.trade.order.model.vo;

import com.sinochem.crude.trade.goods.remote.BodyPropertiesVO;
import com.sinochem.crude.trade.order.domain.ProductOilResource;
import com.sinochem.crude.trade.order.util.DictUtils;
import com.sinochem.it.b2b.common.utils.DateUtil;

import java.util.Date;
import java.util.Map;

/**
 * ProductOilResource的VO
 * @author Yichen Zhao
 * date: 20180129
 */
public class ProductOilResourceVO extends BodyPropertiesVO {

    /**
     *油号
     */
    private String no;

    /**
     * 名字
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
     * 成品油分类
     */
    private Integer productOilClassify;
    private String productOilClassifyContent;

    /**
     * 成品油种类
     */
    private Integer productOilKind;
    private String productOilKindContent;

    /**
     * 成品油规格
     */
    private Integer productOilSpecs;
    private String productOilSpecsContent;

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

    public static void convertToProductOilResourceVO(
            ProductOilResource productOilResource, ProductOilResourceVO productOilResourceVO) {
        if (productOilResource == null || productOilResourceVO == null) {
            return;
        }

        productOilResourceVO.setNo(productOilResource.getNo());
        productOilResourceVO.setName(productOilResource.getName());
        productOilResourceVO.setCnName(productOilResource.getCnName());

        Long origin = productOilResource.getOrigin();
        if (origin != null) {
            productOilResourceVO.setOrigin(origin);
            Map<Object, String> productOilRegionMap = DictUtils.getRegionProductOil();
            productOilResourceVO.setOriginContent(productOilRegionMap.get(origin));
        }

        Integer productOilClassify = productOilResource.getProductOilClassify();
        if (productOilClassify != null) {
            productOilResourceVO.setProductOilClassify(productOilClassify);
            Map<Object, String> productOilClassifyMap = DictUtils.getProductOilClassifyMap();
            productOilResourceVO.setProductOilClassifyContent(productOilClassifyMap.get(productOilClassify));
        }

        Integer productOilKind = productOilResource.getProductOilKind();
        if (productOilKind != null) {
            productOilResourceVO.setProductOilKind(productOilKind);
            Map<Integer, String> productOilKindMap = DictUtils.getProductOilKind();
            productOilResourceVO.setProductOilKindContent(productOilKindMap.get(productOilKind));
        }

        Integer productOilSpecs = productOilResource.getProductOilSpecs();
        if (productOilSpecs != null) {
            productOilResourceVO.setProductOilSpecs(productOilSpecs);
            Map<Integer, String> productOilSpecsMap = DictUtils.getProductOilSpecs();
            productOilResourceVO.setProductOilSpecsContent(productOilSpecsMap.get(productOilSpecs));
        }

        productOilResourceVO.setPropertyId(productOilResource.getPropertyId());

        Date version = productOilResource.getVersion();
        if (version != null) {
            productOilResourceVO.setVersion(version);
            productOilResourceVO.setVersionAsString(DateUtil.formatDate(version));
        }

        productOilResourceVO.setDesc(productOilResource.getDesc());
        productOilResourceVO.setContractId(productOilResource.getContractId());

        BodyPropertiesVO.convertToBodyPropertiesVO(productOilResource, productOilResourceVO);
    }

    public static void convertToProductOilResource(
            ProductOilResourceVO productOilResourceVO, ProductOilResource productOilResource) {
        if (productOilResourceVO == null || productOilResource == null) {
            return;
        }

        productOilResource.setNo(productOilResourceVO.getNo());
        productOilResource.setName(productOilResourceVO.getName());
        productOilResource.setCnName(productOilResourceVO.getCnName());
        productOilResource.setOrigin(productOilResourceVO.getOrigin());
        productOilResource.setProductOilClassify(productOilResourceVO.getProductOilClassify());
        productOilResource.setProductOilKind(productOilResourceVO.getProductOilKind());
        productOilResource.setProductOilSpecs(productOilResourceVO.getProductOilSpecs());
        productOilResource.setPropertyId(productOilResourceVO.getPropertyId());
        productOilResource.setVersion(productOilResourceVO.getVersion());
        productOilResource.setDesc(productOilResourceVO.getDesc());
        productOilResource.setContractId(productOilResourceVO.getContractId());

        BodyPropertiesVO.convertToBodyProperties(productOilResourceVO, productOilResource);
    }

    /** getters and setters */
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public Integer getProductOilClassify() {
        return productOilClassify;
    }

    public void setProductOilClassify(Integer productOilClassify) {
        this.productOilClassify = productOilClassify;
    }

    public Integer getProductOilKind() {
        return productOilKind;
    }

    public void setProductOilKind(Integer productOilKind) {
        this.productOilKind = productOilKind;
    }

    public Integer getProductOilSpecs() {
        return productOilSpecs;
    }

    public void setProductOilSpecs(Integer productOilSpecs) {
        this.productOilSpecs = productOilSpecs;
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

    public String getProductOilClassifyContent() {
        return productOilClassifyContent;
    }

    public void setProductOilClassifyContent(String productOilClassifyContent) {
        this.productOilClassifyContent = productOilClassifyContent;
    }

    public String getProductOilKindContent() {
        return productOilKindContent;
    }

    public void setProductOilKindContent(String productOilKindContent) {
        this.productOilKindContent = productOilKindContent;
    }

    public String getProductOilSpecsContent() {
        return productOilSpecsContent;
    }

    public void setProductOilSpecsContent(String productOilSpecsContent) {
        this.productOilSpecsContent = productOilSpecsContent;
    }

    public String getVersionAsString() {
        return versionAsString;
    }

    public void setVersionAsString(String versionAsString) {
        this.versionAsString = versionAsString;
    }

    public String getOriginContent() {
        return originContent;
    }

    public void setOriginContent(String originContent) {
        this.originContent = originContent;
    }
}
