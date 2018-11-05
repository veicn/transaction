package com.sinochem.crude.trade.member.model;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.MemberCredentials;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnterpriseDetail extends Enterprise {

    List<MemberCredentials > credentialsList;

    public List<MemberCredentials > getCredentialsList() {
        return credentialsList;
    }

    public void setCredentialsList(List<MemberCredentials > credentialsList) {
        this.credentialsList = credentialsList;
    }

    public String getCredentialsStr(){
        String str = "";
        for(MemberCredentials credentials : credentialsList){
            str += "," +  credentials.getCredentialsCode();
        }
        return str.length() > 1 ?  str.substring(1) : "";
    }

    public Map toMap() {
        String name = getFullName();
        if(StringUtil.isNotEmpty(getFullName()) && StringUtil.isNotEmpty(getEnglishName())){
            name = VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh",getFullName()},new String[]{"en",getEnglishName()}});
        }else{
            if(StringUtil.isEmpty(name)){
                name = getEnglishName();
            }
        }
        Map hashMap = new HashMap();
        hashMap.put("id",getId());
        hashMap.put("name",name);
        hashMap.put("cres",getCredentialsStr());
        hashMap.put("memberId",getMemberId());
        return hashMap;
    }
}
