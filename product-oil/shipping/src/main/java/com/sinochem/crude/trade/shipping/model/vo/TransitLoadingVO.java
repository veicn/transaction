package com.sinochem.crude.trade.shipping.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.shipping.domain.TransitLoading;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransitLoadingVO extends BaseDomainVO<TransitLoading> {


    /**
     * UUID
     */
    private String uuid;

    /**
     * 船舶确认单ID
     */
    private Long shipConfirmationSheetId;

    /**
     * 确认单uuid
     */
    private String confirmUUid;

    /**
     * 日期
     */
    private String date;

    /**
     * 到达港口
     */
    private String loadingPort;

    /**
     * 预计到港时间
     */
    private String eta;

    /**
     * 预计离港时间
     */
    private String etb;

    /**
     * 坐标（经纬度）
     */
    private String posn;

    /**
     * 海况CD
     */
    private String seaId;

    /**
     * 海况
     */
    private String sea;

    /**
     * 航速（24小时）
     */
    private java.math.BigDecimal aveSpd24h;

    /**
     * 预留字段1
     */
    private String ext1;

    /**
     * 版本号
     */
    private String version;

    /**
     * 当前有效状态
     */
    private String aliveFlag;

    /**
     * 创建时间
     */
    private java.util.Date createDate;

    /**
     * 创建者
     */
    private Long createUser;

    /**
     * 更新时间
     */
    private java.util.Date updateDate;

    /**
     * 更新者
     */
    private Long updateUser;

    /**
     * 使用构造函数获得VO
     */
    public TransitLoadingVO(TransitLoading domain) {
        super(domain);
    }

    /**
     * 使用构造函数获得VO
     */
    public TransitLoadingVO() {
        super();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getShipConfirmationSheetId() {
        return shipConfirmationSheetId;
    }

    public void setShipConfirmationSheetId(Long shipConfirmationSheetId) {
        this.shipConfirmationSheetId = shipConfirmationSheetId;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getEtb() {
        return etb;
    }

    public void setEtb(String etb) {
        this.etb = etb;
    }

    public String getLoadingPort() {
        return loadingPort;
    }

    public void setLoadingPort(String loadingPort) {
        this.loadingPort = loadingPort;
    }


    public String getPosn() {
        return posn;
    }

    public void setPosn(String posn) {
        this.posn = posn;
    }

    public String getSeaId() {
        return seaId;
    }

    public void setSeaId(String seaId) {
        this.seaId = seaId;
    }

    public String getSea() {
        return sea;
    }

    public void setSea(String sea) {
        this.sea = sea;
    }

    public java.math.BigDecimal getAveSpd24h() {
        return aveSpd24h;
    }

    public void setAveSpd24h(java.math.BigDecimal aveSpd24h) {
        this.aveSpd24h = aveSpd24h;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag;
    }

    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public java.util.Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }


    @Override
    protected void convertToVO(TransitLoading domain) {

        if (domain.getUuid() != null) {
            this.uuid = domain.getUuid();
        }
    }

    @Override
    protected TransitLoading convertToDomain() {
        TransitLoading transitLoadingQuery = new TransitLoading();
        //时间
        if (StringUtil.isNotBlank(this.date)) {
            transitLoadingQuery.setDate(DateUtil.getDate(this.date));
        }
        if (StringUtil.isNotBlank(this.loadingPort)) {
            transitLoadingQuery.setLoadingPort(this.loadingPort);
        }
        //时间
        if (StringUtil.isNotBlank(this.eta)) {
            transitLoadingQuery.setEta(DateUtil.getDate(this.eta));
        }
        if (StringUtil.isNotBlank(this.etb)) {
            transitLoadingQuery.setEtb(DateUtil.getDate(this.etb));
        }
        if (StringUtil.isNotBlank(this.posn)) {
            transitLoadingQuery.setPosn(this.posn);
        }
        if (StringUtil.isNotBlank(this.seaId)) {
            transitLoadingQuery.setSeaId(this.seaId);
        }
        if (StringUtil.isNotBlank(this.sea)) {
            transitLoadingQuery.setSea(this.sea);
        }
        if (this.aveSpd24h != null) {
            transitLoadingQuery.setAveSpd24h(this.aveSpd24h);
        }
        if (StringUtil.isNotBlank(this.uuid)) {
            transitLoadingQuery.setUuid(this.uuid);
        }
        if (this.shipConfirmationSheetId != null) {
            transitLoadingQuery.setShipConfirmationSheetId(this.shipConfirmationSheetId);
        }
        return transitLoadingQuery;
    }

    public List<TransitLoadingVO> toListVo(List<TransitLoading> list) {
        List<TransitLoadingVO> listVo = new ArrayList<TransitLoadingVO>();
        for (TransitLoading transitLoading : list) {
            //获取对象
            TransitLoadingVO transitLoadingVO = new TransitLoadingVO();

            Date datevo = transitLoading.getDate();
            if (datevo != null) {
                String formatDate = DateUtil.formatDate(datevo);
                transitLoadingVO.setDate(formatDate);
            }
            if (StringUtil.isNotBlank(transitLoading.getLoadingPort())) {
                transitLoadingVO.setLoadingPort(transitLoading.getLoadingPort());
            }

            Date etavo = transitLoading.getEta();
            if (etavo != null) {
                String formatDate = DateUtil.formatDate(etavo);
                transitLoadingVO.setEta(formatDate);
            }
            Date etbvo = transitLoading.getEtb();
            if (etbvo != null) {
                String formatDate = DateUtil.formatDate(etbvo);
                transitLoadingVO.setEtb(formatDate);
            }


            if (StringUtil.isNotBlank(transitLoading.getPosn())) {
                transitLoadingVO.setPosn(transitLoading.getPosn());
            }
            if (StringUtil.isNotBlank(transitLoading.getSea())) {
                transitLoadingVO.setSea(transitLoading.getSea());
            }
            if (StringUtil.isNotBlank(transitLoading.getSeaId())) {
                transitLoadingVO.setSeaId(transitLoading.getSeaId());
            }
            if (transitLoading.getAveSpd24h() != null) {
                transitLoadingVO.setAveSpd24h(transitLoading.getAveSpd24h().setScale(2));
            }
            if (StringUtil.isNotBlank(transitLoading.getUuid())) {
                transitLoadingVO.setUuid(transitLoading.getUuid());
            }

            listVo.add(transitLoadingVO);

        }

        return listVo;
    }


    public TransitLoadingVO toObjectVo(TransitLoading transitLoading) {
        TransitLoadingVO transitLoadingVO = new TransitLoadingVO();
        Date datevo = transitLoading.getDate();
        if (datevo != null) {
            String formatDate = DateUtil.formatDate(datevo);
            transitLoadingVO.setDate(formatDate);
        }
        if (StringUtil.isNotBlank(transitLoading.getLoadingPort())) {
            transitLoadingVO.setLoadingPort(transitLoading.getLoadingPort());
        }

        Date etavo = transitLoading.getEta();
        if (etavo != null) {
            String formatDate = DateUtil.formatDate(etavo);
            transitLoadingVO.setEta(formatDate);
        }
        Date etbvo = transitLoading.getEtb();
        if (etbvo != null) {
            String formatDate = DateUtil.formatDate(etbvo);
            transitLoadingVO.setEtb(formatDate);
        }


        if (StringUtil.isNotBlank(transitLoading.getPosn())) {
            transitLoadingVO.setPosn(transitLoading.getPosn());
        }
        if (StringUtil.isNotBlank(transitLoading.getSea())) {
            transitLoadingVO.setSea(transitLoading.getSea());
        }
        if (StringUtil.isNotBlank(transitLoading.getSeaId())) {
            transitLoadingVO.setSeaId(transitLoading.getSeaId());
        }
        if (transitLoading.getAveSpd24h() != null) {
            transitLoadingVO.setAveSpd24h(transitLoading.getAveSpd24h().setScale(2));
        }
        if (StringUtil.isNotBlank(transitLoading.getUuid())) {
            transitLoadingVO.setUuid(transitLoading.getUuid());
        }

        return transitLoadingVO;
    }

    public String getConfirmUUid() {
        return confirmUUid;
    }

    public void setConfirmUUid(String confirmUUid) {
        this.confirmUUid = confirmUUid;
    }
}