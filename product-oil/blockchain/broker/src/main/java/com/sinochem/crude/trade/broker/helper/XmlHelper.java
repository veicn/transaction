package com.sinochem.crude.trade.broker.helper;

import com.sinochem.crude.trade.broker.domain.xml.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/6/20 9:51
 * @Version: [v1.0]
 */
public class XmlHelper {

    /**
     * Java类向XML进行转换
     * @param cls
     * @param obj
     * @throws JAXBException
     */
    public static <T> String java2Xml(Class<T> cls,Object obj) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(cls);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }

    /**
     * XML向JAVA类转换
     * @param cls
     * @param content
     * @return
     * @throws JAXBException
     */
    public static <T> Object xml2Java(Class<T> cls,String content) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(cls);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(new StringReader(content));

    }
    public static void main(String args[]) {
        CustomsXmlVO accessToken=new CustomsXmlVO();
        DecHeadXMLVO decHeadVo=new DecHeadXMLVO();
        decHeadVo.setAgentCode("申报单位代码");
        decHeadVo.setBillNo("提单号");
        accessToken.setDecHead(decHeadVo);
        accessToken.setversion("3.1");
        accessToken.setXmlns("http://www.chinaport.gov.cn/dec");
        DecListsXMLVO decListsXMLVO=new DecListsXMLVO();
        DecListXMLVO decListXMLVO=new DecListXMLVO();
        decListXMLVO.setClassMark("归类标志");

        List<DecListXMLVO> listXMLVOList=new ArrayList<DecListXMLVO>();
        decListsXMLVO.setDecLists(listXMLVOList);
        listXMLVOList.add(decListXMLVO);
//        listXMLVOList.add(decListXMLVO);
        accessToken.setDecLists(decListsXMLVO);

        DecContainersXMLVO decContainersXMLVO=new DecContainersXMLVO();
        ContainerXMLVO containerXMLVO=new ContainerXMLVO();
        containerXMLVO.setContainerId("234");
        decContainersXMLVO.setContainer(containerXMLVO);
        accessToken.setDecContainers(decContainersXMLVO);

        DecLicenseDocusXMLVO decLicenseDocusXMLVO=new DecLicenseDocusXMLVO();
        LicenseDocuXMLVO licenseDocuXMLVO=new LicenseDocuXMLVO();
        licenseDocuXMLVO.setCertCode("单证编号");
        decLicenseDocusXMLVO.setLicenseDocu(licenseDocuXMLVO);
        accessToken.setDecLicenseDocus(decLicenseDocusXMLVO);


        DecFreeTxtXMLVO decFreeTxtXMLVO=new DecFreeTxtXMLVO();
        decFreeTxtXMLVO.setBonNo("监管仓号");
        accessToken.setDecFreeTxt(decFreeTxtXMLVO);

        DecSignXMLVO decSignXMLVO=new DecSignXMLVO();
        decSignXMLVO.setCertificate("操作员卡对应的证书号");
        accessToken.setDecSign(decSignXMLVO);


        String xmlstr="";
        try {
             xmlstr=java2Xml(CustomsXmlVO.class,accessToken);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        System.out.println(xmlstr);
        return;
    }

    }
