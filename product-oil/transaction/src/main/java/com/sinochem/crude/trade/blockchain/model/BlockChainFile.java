package com.sinochem.crude.trade.blockchain.model;

public class BlockChainFile {

    /**
     * 文件摘要码：egh567jk45tyuifghjk45hj89fghjk12hd,具体生成策略待定。
     文件名称：中化泉州商检报告
     文件类型：PDF
     大小：1.10MB
     数据所有权：SGS
     创建时间：2018/7/1 14:25:36
     文件路径path
     事件代码eventCode
     */

    private String fileSummary;
    private String fileName;
    private String fileType;
    private String fileSize;
    private String dataCopyright;
    private String createTime;
    private String filePath;

    private String eventCode;

    public String getFileSummary() {
        return fileSummary;
    }

    public void setFileSummary(String fileSummary) {
        this.fileSummary = fileSummary;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getDataCopyright() {
        return dataCopyright;
    }

    public void setDataCopyright(String dataCopyright) {
        this.dataCopyright = dataCopyright;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }


}
