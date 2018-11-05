package com.sinochem.crude.trade.member.service;

import com.sinochem.crude.trade.member.domain.Credentials;
import com.sinochem.crude.trade.member.domain.CredentialsDetail;
import com.sinochem.crude.trade.member.domain.MemberCredentials;

import java.util.List;

public interface CredentialsDao {
    public List<Credentials> getCredentials();
    public List<CredentialsDetail> getCredentialsDetials();
    public List<String> getAllRoles();
    Credentials getByCode(String code);
    Credentials getByUdbCode(String udbCode);

    List<String> getUdbCodesByList( List<MemberCredentials> list );
    List<String> getCodesByList( String[] strs );


}
