package com.sinochem.crude.trade.orderexecute.commons.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.orderexecute.domain.OrderCustoms;
import com.sinochem.crude.trade.orderexecute.domain.OrderInspection;
import com.sinochem.crude.trade.orderexecute.model.OrderCustomsVO;

public class PdfTemplateUtil {
	public static void main(String[] args) throws FileNotFoundException {
//		// 模板路径  
//        String templatePath = "D:/test/inspection_template.pdf";  
////        String templatePath = "D:/test/inspection_template.pdf";  
//        // 生成的新文件路径  
//        String newPDFPath = "D:/test/a.pdf";
//        
//        OrderInspection entity = new OrderInspection();
//        entity.setQuarantineBureau("泉州");
//        entity.setOrganizationCode("SC1243423");
//        entity.setCode("114332434");
//        entity.setCommodityName("这是品名");
//        entity.setHsCode("2709000000");
//        entity.setNightstool(BigDecimal.valueOf(543221));
//    	entity.setNetTon(BigDecimal.valueOf(543221));
//    	entity.setPackaging("verferf");
//    	entity.setContractNo("C432312435");
//    	entity.setLicenseNumber("verfwe");
//    	entity.setReceivingCompany("vwebtrhb");
//    	entity.setTransportNo("F4325432");;  
//    	entity.setOtherRequirements("thgtrfed");;  
//    	entity.setConsignor("nhbgfvewde");;  
//    	entity.setRegistrationNo("654323F");;  
//    	entity.setOtherMatters("其他事项");;  
//    	entity.setContactPerson("bvwerfw");;  
//    	entity.setContactPhone("14343665453");;  
//    	entity.setEntrustedContact("张三");;  
//    	entity.setEntrustedPhone("14343665453");;  
//        
//        Map<String, Object> data = entity.toMap();
//		data.put("validPeriod_year", "2017");
//		data.put("validPeriod_month", "11");
//		data.put("validPeriod_day", "22");
//		data.put("entrustDate_year", "2017");
//		data.put("entrustDate_month", "11");
//		data.put("entrustDate_day", "22");
//		data.put("entrustedDate_year", "2017");
//		data.put("entrustedDate_month", "11");
//		data.put("entrustedDate_day", "22");
//        
//		exportPdf(templatePath, data, new FileOutputStream(new File(newPDFPath)));
	}

	public static void exportPdf(String pdfTemplatePath,Map<String, Object> data, OutputStream os) {

        try {
            PdfDocument pdf = new PdfDocument(new PdfReader(pdfTemplatePath), new PdfWriter(os));
            PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);  
            Map<String, PdfFormField> fields = form.getFormFields();  
            //处理中文问题    
            PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
            
            Iterator<Entry<String, PdfFormField>> fieldIterator = fields.entrySet().iterator();
            while (fieldIterator.hasNext()) {
                //获取文本域名称
            	Entry<String, PdfFormField> fieldItem = fieldIterator.next();
                //填充文本域
            	if(data.get(fieldItem.getKey()) != null) {
            		fieldItem.getValue()
            		.setValue(String.valueOf(data.get(fieldItem.getKey())))
            		.setFont(font);
//            		.setFontSize(12);
            	}
            }
            form.flattenFields();//设置表单域不可编辑
            pdf.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
	}
}
