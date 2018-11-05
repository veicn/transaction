package com.sinochem.crude.trade.listed.enums;

import java.util.ArrayList;
import java.util.List;

import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.model.vo.KeyValueVO;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;

/**
 * 采购方式（招标，询价）枚举
 * @author Yichen Zhao
 * date: 20180110
 */
public enum PurchaseType {
    /** 招标 */
    //BIDDING(1, "招标"),
    //ENQUIRY(2, "询价"); 
	
    BIDDING(1, Constant.LISTED_0135),
    ENQUIRY(2, Constant.LISTED_0136);
    
    Integer code;
    String[][] names;
    String name;

    PurchaseType(Integer code, String[][] names) {
        this.code = code;
        this.names = names;
    }

    public static PurchaseType getPurchaseTypeByCode(Integer code) {
        for (PurchaseType purchaseType : PurchaseType.values()) {
            if (purchaseType.getCode().equals(code)) {
                return purchaseType;
            }
        }

        return null;
    }

    public static List<KeyValueVO> convertToVOList() {
        List<KeyValueVO> keyValueVOList = new ArrayList<>();

        for(PurchaseType purchaseType : PurchaseType.values()) {
            KeyValueVO keyValueVO = new KeyValueVO();

            keyValueVO.setKey(purchaseType.getCode().toString());
            keyValueVO.setValue(purchaseType.getName());

            keyValueVOList.add(keyValueVO);
        }

        return keyValueVOList;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String[][] getNames() {
        return names;
    }

    public void setNames(String[][] names) {
        this.names = names;
    }
    
    public String getName() {
    	return VisitorLocale.getByCurrentLanguage(names);
    }

    public void setName(String name) {
        this.name = name;
    }
}
