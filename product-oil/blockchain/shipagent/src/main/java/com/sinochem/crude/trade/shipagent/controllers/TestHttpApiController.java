package com.sinochem.crude.trade.shipagent.controllers;


import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.sinochem.crude.trade.shipagent.feign.HttpFeignClient;
import com.sinochem.crude.trade.shipagent.domain.SaleSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestHttpApiController {

    @Autowired
    HttpFeignClient httpFeignClient;

    @RequestMapping("/test")
    public String show(){
        return "Hello World";
    }

    @RequestMapping(value = "/testHttpAPI", method = RequestMethod.POST)
    public SaleSheet testHttpAPI(@RequestBody SaleSheet saleSheetQuery) {
        return httpFeignClient.testHttpAPI(saleSheetQuery);
    }

    @RequestMapping(value = "/testApi")
    public String testApi() {

        return httpFeignClient.testApi();
    }

    // 下载pdf文档
    @RequestMapping("/downloadpdf")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uuid= request.getParameter("uuid");


        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.pdf");

        //中文字体,解决中文不能显示问题
        BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        //设置字体样式
        Font textFont = new Font(bfChinese,11,Font.NORMAL); //正常
        Font boldFont = new Font(bfChinese,11,Font.BOLD); //加粗
        Font titleFont = new Font(bfChinese,15,Font.BOLD); //二级标题
        //  Color grayColor = new Color(204,204,204);

//        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//设置中文字体
//        Font font = new Font(bfChinese, 10);
//        Font headFont = new Font(bfChinese, 14, Font.BOLD);//设置字体
//        Font littleHeadFont = new Font(bfChinese, 12, Font.BOLD);
        Document document = new com.itextpdf.text.Document(PageSize.A4);  //设置A4纸张样式，每页填充满后自动换行
//        Document document = new com.itextpdf.text.Document(PageSize.A4,
//                MARGIN_OF_ONE_CM, MARGIN_OF_ONE_CM, MARGIN_OF_ONE_CM, MARGIN_OF_ONE_CM);  //设置A4纸张样式，每页填充满后自动换行
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
//设置文本属性
        document.addAuthor("fengzk");
        document.addSubject("sinochem");
        document.addLanguage("chinese");
        document.addCreationDate();
        document.addCreator("fengzk");
        document.addTitle("title");
        writer.setTagged();
        writer.createXmpMetadata();
        document.open();
        //        Paragraph bigTitle = new Paragraph("一、概述", headFont);
        Paragraph bigTitle = new Paragraph("概述",boldFont);
        bigTitle.setSpacingAfter(20);
        bigTitle.setSpacingBefore(20);

        document.add(bigTitle);

//定义段落
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(textFont);
//设置段落前后间距
        paragraph.setSpacingAfter(10);
        paragraph.setSpacingBefore(10);
//        paragraph.setFont(font);
        SaleSheet saleSheet = new SaleSheet();
        saleSheet.setUuid("123445678998");
        saleSheet.setProductSourceCode("欢迎你");
        StringBuffer overViewBuffer = new StringBuffer("参数："+uuid);
        overViewBuffer.append(saleSheet.getUuid());
        overViewBuffer.append("；产品名称：");

        overViewBuffer.append(saleSheet.getProductSourceCode());

        paragraph.add(overViewBuffer.toString());

        document.add(paragraph); //document文件流中添加数据

        document.close();
    }


}