package com.sinochem.crude.trade.member.service.impl;

import com.sinochem.crude.trade.member.domain.MemberCredentials;
import com.sinochem.crude.trade.member.service.CredentialsDao;
import com.sinochem.crude.trade.member.domain.Credentials;
import com.sinochem.crude.trade.member.domain.CredentialsDetail;
import com.sinochem.it.b2b.common.exception.BizException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class CredentialsDaoImpl implements CredentialsDao,InitializingBean {

    Logger logger = LoggerFactory.getLogger(CredentialsDaoImpl.class);

    @Value("${member.permission.config.file:conf/Credentials.xml}")
    private String configFile;

    private static List<Credentials> credentials = new ArrayList<>();
    private static List<CredentialsDetail> credentialsDetials = new ArrayList<>();
    private static List<String> allRoles = new ArrayList<>();

    @Override
    public List<Credentials> getCredentials() {
        return credentials;
    }

    @Override
    public List<CredentialsDetail> getCredentialsDetials() {
        return credentialsDetials;
    }

    @Override
    public List<String> getAllRoles() {
        return allRoles;
    }


    @Override
    public Credentials getByCode(String code){
        for(Credentials credential : credentials){
            if(credential.getCode().equals(code) ){
                return credential;
            }
        }
        return null;
    }

    @Override
    public Credentials getByUdbCode(String udbCode){
        for(Credentials credential : credentials){
            if(credential.getUdbCode()!= null && credential.getUdbCode().equals(udbCode) ){
                return credential;
            }
        }
        return null;
    }

    @Override
    public List<String> getUdbCodesByList( List<MemberCredentials> list ) {
        if(list != null && list.size()>0){
            List<String> strList = new ArrayList<>();
            for(int i=0;i<list.size();i++){
                Credentials credentials = getByCode(list.get(i).getCredentialsCode());
                if(credentials != null && credentials.getUdbCode()!= null){
                    strList.add(credentials.getUdbCode());
                }
            }
            return strList;
        }
        return null;
    }
    @Override
    public List<String> getCodesByList( String[] strs ) {
        if(strs!=null && strs.length>0){
            List<String> strList = new ArrayList<>();
            for(int i=0;i<strs.length;i++){
                Credentials credentials = getByUdbCode(strs[i]);
                if(credentials != null && credentials.getCode() != null){
                    strList.add(credentials.getCode());
                }
            }
            return strList;
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(configFile);
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(url.getFile());
        } catch (DocumentException e) {
            logger.error("MemberCredential配置文件读取错误");
            throw new BizException("MemberCredential配置文件读取错误");
        }

        Element root = doc.getRootElement();
        Element credentialsNode;
        Iterator credentialConfigIterator = root.elementIterator("Credentials");
        //获取Credentials节点
        if(credentialConfigIterator.hasNext()){
            credentialsNode =(Element) credentialConfigIterator.next();
        }else{
            logger.error("Credential配置文件中没有配置Credentials节点");
            throw new BizException("Credential配置文件中没有配置Credentials节点");
        }


        //获取所有Credential
        for (Iterator credentialIterator = credentialsNode.elementIterator("Credential"); credentialIterator.hasNext(); ) {
            Credentials credential = new Credentials();
            CredentialsDetail credentialsDetail = new CredentialsDetail();

            Element credentialNode = (Element) credentialIterator.next();
            credential.setCode(credentialNode.attributeValue("code"));
            credential.setName(credentialNode.attributeValue("name"));
            credential.setNameEn(credentialNode.attributeValue("nameEn"));
            credential.setUdbCode(credentialNode.attributeValue("udbCode"));

            credentialsDetail.setCode(credential.getCode());
            List<String> roleList = new ArrayList<>();

            //获取一个资质对应的role
            for (Iterator credentialsDetialIterator = credentialNode.elementIterator("role"); credentialsDetialIterator.hasNext(); ) {
                Element roleNode = (Element) credentialsDetialIterator.next();
                roleList.add(roleNode.attributeValue("code"));
            }
            credentialsDetail.setRoles(roleList.toArray(new String[0]));
            credentials.add(credential);
            credentialsDetials.add(credentialsDetail);
        }


        //获取全部role
        Element rolesNode;
        Iterator rolesIterator = root.elementIterator("Roles");
        if(rolesIterator.hasNext()){
            rolesNode = (Element)rolesIterator.next();
        }else{
            logger.error("Credential配置文件中没有配置Roles节点");
            throw new BizException("Credential配置文件中没有配置Roles节点");
        }
        for(Iterator roleIterator =rolesNode.elementIterator("role");roleIterator.hasNext();){
            Element role = (Element) roleIterator.next();
            allRoles.add(role.attributeValue("code"));
        }


        if (credentials.size() == 0) {
            logger.warn("于资质配置文件中没有读取到资质配置信息");
            throw new BizException("没有读取到资质配置信息");
        }else{
            if(allRoles.size() == 0){
                logger.warn("于资质配置文件中没有读取到角色配置信息");
                throw new BizException("没有读取到角色配置信息");
            }else{
                logger.info("资质配置文件读取完毕");
            }
        }
    }


}
