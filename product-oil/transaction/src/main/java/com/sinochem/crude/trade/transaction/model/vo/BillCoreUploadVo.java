package com.sinochem.crude.trade.transaction.model.vo;


import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.transaction.domain.po.BillCoreUpload;

public class BillCoreUploadVo  {

	private static final long serialVersionUID = 1L;

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	private String endpoint;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUid() {
		return uid;
	}



	public void setUid(String uid) {
		this.uid = uid;
	}

	private String uid;

	/**主键_ID*/
	private Long documentFileId;

	/**UUID*/
	private String documentFileUuid;

	/**订单ID*/
	private Long orderId;

	/**附件种类代码*/
	private String fileTypeCode;

	/**附件种类名字*/
	private String fileTypeName;

	/**附件名*/
	private String fileName;

	/**附件地址*/
	private String filePath;

	/**附件格式*/
	private String fileFormat;

    /**附件格式*/
    private String fileSize;

    /**附件SHA*/
    private String fileSHA;

	/**上传人*/
	private Long uploadPerson;

	/**预留字段1*/
	private String ext1;

	/**版本号*/
	private String version;

	/**当前有效状态*/
	private String aliveFlag;

	/**创建时间*/
	private java.util.Date createDate;

	/**创建者*/
	private Long createUser;

	/**更新时间*/
	private java.util.Date updateDate;

	/**更新者*/
	private Long updateUser;
    /**
     * 合约单的uuid
     */
	private String contractUUid;
	/**主键_ID*/
	public void setDocumentFileId(Long documentFileId){
		this.documentFileId=documentFileId;
	}
	/**主键_ID*/
	public Long getDocumentFileId(){
		return this.documentFileId;
	}

	/**UUID*/
	public void setDocumentFileUuid(String documentFileUuid){
		this.documentFileUuid=documentFileUuid;
	}
	/**UUID*/
	public String getDocumentFileUuid(){
		return this.documentFileUuid;
	}

	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}

	/**附件种类代码*/
	public void setFileTypeCode(String fileTypeCode){
		this.fileTypeCode=fileTypeCode;
	}
	/**附件种类代码*/
	public String getFileTypeCode(){
		return this.fileTypeCode;
	}

	/**附件种类名字*/
	public void setFileTypeName(String fileTypeName){
		this.fileTypeName=fileTypeName;
	}
	/**附件种类名字*/
	public String getFileTypeName(){
		return this.fileTypeName;
	}

	/**附件名*/
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	/**附件名*/
	public String getFileName(){
		return this.fileName;
	}

	/**附件地址*/
	public void setFilePath(String filePath){
		this.filePath=filePath;
	}
	/**附件地址*/
	public String getFilePath(){
		return this.filePath;
	}

	/**附件格式*/
	public void setFileFormat(String fileFormat){
		this.fileFormat=fileFormat;
	}
	/**附件格式*/
	public String getFileFormat(){
		return this.fileFormat;
	}

	/**上传人*/
	public void setUploadPerson(Long uploadPerson){
		this.uploadPerson=uploadPerson;
	}
	/**上传人*/
	public Long getUploadPerson(){
		return this.uploadPerson;
	}

	/**预留字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**预留字段1*/
	public String getExt1(){
		return this.ext1;
	}

	/**版本号*/
	public void setVersion(String version){
		this.version=version;
	}
	/**版本号*/
	public String getVersion(){
		return this.version;
	}

	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}

	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**创建者*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建者*/
	public Long getCreateUser(){
		return this.createUser;
	}

	/**更新时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**更新时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**更新者*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**更新者*/
	public Long getUpdateUser(){
		return this.updateUser;
	}

    public String getContractUUid() {
        return contractUUid;
    }

    public void setContractUUid(String contractUUid) {
        this.contractUUid = contractUUid;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }


    public String getFileSHA() {
        return fileSHA;
    }

    public void setFileSHA(String fileSHA) {
        this.fileSHA = fileSHA;
    }


    /**
     * VO转domain
     * @return
     */
    public BillCoreUpload voToDomain(){
        BillCoreUpload billCoreUpload = new BillCoreUpload();
        if (StringUtil.isNotBlank(this.documentFileUuid)) {
            billCoreUpload.setDocumentFileUuid(this.documentFileUuid);
        }
        if (null != this.orderId) {
            billCoreUpload.setOrderId(this.orderId);
        }
        if (StringUtil.isNotBlank(this.fileTypeCode)) {
            billCoreUpload.setFileTypeCode(this.fileTypeCode);
        }
        if (StringUtil.isNotBlank(this.fileTypeName)) {
            billCoreUpload.setFileTypeName(this.fileTypeName);
        }
        if (StringUtil.isNotBlank(this.filePath)) {
            billCoreUpload.setFilePath(this.filePath);
        }
        if (StringUtil.isNotBlank(this.fileFormat)) {
            billCoreUpload.setFileFormat(this.fileFormat);
        }
        if (null != this.uploadPerson) {
            billCoreUpload.setUploadPerson(this.uploadPerson);
        }
        if (StringUtil.isNotBlank(this.fileName)) {
            billCoreUpload.setFileName(this.fileName);
        }
        if (StringUtil.isNotBlank(this.fileSize)) {
            billCoreUpload.setFileSize(this.fileSize);
        }
        if (StringUtil.isNotBlank(this.fileSHA)) {
            billCoreUpload.setFileSHA(this.fileSHA);
        }
        return billCoreUpload;
	}

    /**
     * domain 转 vo
     * @param billCoreUpload
     */
    public BillCoreUploadVo domainToVo (BillCoreUpload billCoreUpload) {
        BillCoreUploadVo billCoreUploadVo = new BillCoreUploadVo();
        if ( null != billCoreUpload.getCreateDate() ) {

		}
		if (StringUtil.isNotBlank(billCoreUpload.getDocumentFileUuid())) {
            billCoreUploadVo.setDocumentFileUuid(billCoreUpload.getDocumentFileUuid());
		}
		if (null != billCoreUpload.getOrderId()) {
            billCoreUploadVo.setOrderId(billCoreUpload.getOrderId());
		}
		if (StringUtil.isNotBlank(billCoreUpload.getFileTypeCode())) {
            billCoreUploadVo.setFileTypeCode(billCoreUpload.getFileTypeCode());
		}
		if (StringUtil.isNotBlank(billCoreUpload.getFileTypeName())) {
            billCoreUploadVo.setFileTypeName(billCoreUpload.getFileTypeName());
		}
		if (StringUtil.isNotBlank(billCoreUpload.getFilePath())) {
            billCoreUploadVo.setFilePath(billCoreUpload.getFilePath());

		}
		if (StringUtil.isNotBlank(billCoreUpload.getFileFormat())) {
            billCoreUploadVo.setFileFormat(billCoreUpload.getFileFormat());
		}
		if (null != billCoreUpload.getUploadPerson()) {
            billCoreUploadVo.setUploadPerson(billCoreUpload.getUploadPerson());
		}

        if (StringUtil.isNotBlank(billCoreUpload.getFileName())) {
            billCoreUploadVo.setFileName(billCoreUpload.getFileName());
        }
        if (StringUtil.isNotBlank(billCoreUpload.getFileSize())) {
            billCoreUploadVo.setFileSize(billCoreUpload.getFileSize());
        }
        if (StringUtil.isNotBlank(billCoreUpload.getFileSHA())) {
            billCoreUploadVo.setFileSHA(billCoreUpload.getFileSHA());
        }
		return billCoreUploadVo;

	}
}