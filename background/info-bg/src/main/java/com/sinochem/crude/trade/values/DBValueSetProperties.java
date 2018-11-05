package com.sinochem.crude.trade.values;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * Created by GHuang on 2016/11/25.
 */
@Component
public class DBValueSetProperties {

    private String table = "T_SYS_CODE_SET";

    private Map<String, String> resultMap = new HashMap<String, String>() {{
        put("id", "ID");
        put("group", "SET_CODE");
        put("groupName", "SET_NAME");
        put("code", "ITEM_CODE");
        put("value", "ITEM_NAME");
        put("parentId", "ITEM_PARENT");
        put("subGroup", "ITEM_RELATION");
        put("sort", "ITEM_SORT");
        put("langVer", "LANG_VER");
        put("ext1", "EXT1");
    }};

    private String createDateField = "CREATE_DATE";

    private String updateDateField = "UPDATE_DATE";

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
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
