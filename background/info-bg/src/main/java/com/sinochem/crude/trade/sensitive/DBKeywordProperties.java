package com.sinochem.crude.trade.sensitive;

/**
 * Created by HuangWj on 2016/12/19.
 */
public class DBKeywordProperties {

    private String table = "T_SYS_SENSITIVE_WD";

    private String column = "SENSITIVE_WD";

    private String levelColumn = "LEVEL";

    private String createDateField = "CREATE_DATE";

    private String updateDateField = "UPDATE_DATE";

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public String getLevelColumn() {
        return levelColumn;
    }

    public void setLevelColumn(String levelColumn) {
        this.levelColumn = levelColumn;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getCreateDateField() {
        return createDateField;
    }

    public void setCreateDateField(String createDateField) {
        this.createDateField = createDateField;
    }

    public String getUpdateDateField() {
        return updateDateField;
    }

    public void setUpdateDateField(String updateDateField) {
        this.updateDateField = updateDateField;
    }
}
