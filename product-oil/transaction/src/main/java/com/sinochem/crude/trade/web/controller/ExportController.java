package com.sinochem.crude.trade.web.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.aliyun.oss.OSSClient;
import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetQueryVO;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetWXVO;
import com.sinochem.crude.trade.transaction.utils.OssFileManager;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ExportController {
    private Log log = LogFactory.getLog(ExportController.class);

    @Value("${aliyun.oss.bucket}")
    private String publicBucket;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.endpoint}")
    private String host;

//    @Value("${aliyun.oss.show.endpoint}")
//    private String showHost;

    @Autowired
    private OMContractController omContractController;


    private HSSFWorkbook exports(List<String> headList, List<List<String>> contentList) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个sheet表格
        HSSFSheet sheet = workbook.createSheet("sheet1");
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 35);
        sheet.setDefaultRowHeightInPoints(18);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        /*style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);*/
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headList.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headList.get(i));
            cell.setCellValue(text);
        }

        //遍历集合数据，产生数据行
        if (contentList != null && !contentList.isEmpty()) {
            int index = 1;
            for (List<String> list : contentList) {
                if (list != null && !list.isEmpty()) {
                    row = sheet.createRow(index);
                    /**
                     * 合并单元格
                     *    第一个参数：第一个单元格的行数（从0开始）
                     *    第二个参数：第二个单元格的行数（从0开始）
                     *    第三个参数：第一个单元格的列数（从0开始）
                     *    第四个参数：第二个单元格的列数（从0开始）
                     */
            		/*CellRangeAddress range = new CellRangeAddress(1, 2, 0, 0);
            		sheet.addMergedRegion(range); */
                    for (int i = 0; i < list.size(); i++) {
                        row.createCell(i).setCellValue(list.get(i));
                    }
                    index++;
                }
            }
        }
        return workbook;
    }


    /**
     * 导出订单
     *
     * @return
     */
    @RequestMapping(value = "/exportContractSheet.json")
//    @ResponseBody
    @WithoutAccess
    public ResultDatas<String> exportRSupply(@RequestBody ContractSheetQueryVO queryvo) {

        queryvo.setPageSize(99999);
        queryvo.setPageNum(1);
        List<String> headList = new ArrayList<>();
        List<List<String>> contentList = new ArrayList<>();
        com.sinochem.crude.trade.transaction.model.vo.ResultDatas datas = omContractController.getContractSheetListByeEpmemberid(queryvo);
        if (datas.getStatus() == 0) {
            //内容
            List<ContractSheetWXVO> rSupplies = (List<ContractSheetWXVO>) datas.getDatas();
            if (rSupplies != null && rSupplies.size() > 0) {
                //标题
                contractHeadline(headList);

                for (ContractSheetWXVO rSupply1 : rSupplies) {
                    List<String> list2 = new ArrayList<>();

                    //订单编号
                    list2.add(rSupply1.getSerialNumber());

                    //合同编号
                    list2.add(rSupply1.getContractNO());
                    //合同时间
                    list2.add(rSupply1.getContractDate());
                    //订单状态
                    list2.add(rSupply1.getContractSheetStatus());
                    //创建人
                    list2.add(rSupply1.getCreateuser());
                    //创建时间
                    list2.add(rSupply1.getCreatetime());
                    //油品
                    list2.add(rSupply1.getProductCategory());
                    //规格
                    list2.add(rSupply1.getProductSpecification());
                    //升贴水
                    list2.add(rSupply1.getPremiumsAndDiscounts());
                    //数量
                    list2.add(rSupply1.getQuantity());
                    //贸易条款
                    list2.add(rSupply1.getTradeTerm());
                    //溢短装
                    list2.add(rSupply1.getTolerance());
                    //基准
                    list2.add(rSupply1.getPricingBenchmark());
                    //计价期
                    list2.add(rSupply1.getPricingPeriod());
                    //装期
                    list2.add(rSupply1.getLaycanStartDateAsString() + "-" + rSupply1.getLaycanEndDateAsString());
                    //卸港
                    list2.add(rSupply1.getDischargePort());
                    //结算量
                    list2.add(rSupply1.getSettleNum());
                    //结算金额
                    list2.add(rSupply1.getSettleAmt());
                    //其他
                    list2.add(rSupply1.getOtherTerm());
                    contentList.add(list2);
                }
            }
        }
        ResultDatas<String> res = new ResultDatas<>();
        HSSFWorkbook wkb = null;
        String storePath = "";
        ByteArrayOutputStream out = null;
        InputStream excelStream = null;
        try {
            // 输出Excel文件
            out = new ByteArrayOutputStream();
            wkb = exports(headList, contentList);
            wkb.write(out);
            excelStream = new ByteArrayInputStream(out.toByteArray());
            storePath = "omtransaction/excel/";
//            Calendar nowtime = new GregorianCalendar();
//            String strDateTime=String.format("%04d", nowtime.get(Calendar.YEAR))+
//                    String.format("%02d", nowtime.get(Calendar.MONTH))+
//                    String.format("%02d", nowtime.get(Calendar.DATE))+
//                    String.format("%02d", nowtime.get(Calendar.HOUR))+
//                    String.format("%02d", nowtime.get(Calendar.MINUTE)) +
//                    String.format("%02d", nowtime.get(Calendar.SECOND)) +
//                    String.format("%03d", nowtime.get(Calendar.MILLISECOND));
//            storePath = publicBucket+"/"+storePath + strDateTime+queryvo.getEpMemberId()+".xls";
            storePath = publicBucket + "/" + storePath + KeyGenUtils.newUuid() + ".xls";
            OSSClient ossClient = new OSSClient(host, accessKeyId, accessKeySecret);
            OssFileManager ossFileManager = new OssFileManager(ossClient, accessKeyId, host);
            ossFileManager.save(excelStream, publicBucket, storePath);
            res.setDatas(host + "/" + storePath);
        } catch (Exception e) {
            log.error("export error", e);
            res.setFail(e.getMessage(), ExceptionEnum.RUNTIME_ERROR.getCode());
        }
        return res;
    }


    /**
     * 订单导出标题
     *
     * @param
     * @return
     */
    public void contractHeadline(List<String> headList) {
        //标题
        headList.add("订单编号");
        headList.add("合同编号");
        headList.add("合同时间");
        headList.add("订单状态");
        headList.add("创建人");
        headList.add("创建时间");
        headList.add("油品");
        headList.add("规格");
        headList.add("升贴水");
        headList.add("数量");
        headList.add("贸易条款");
        headList.add("溢短装");
        headList.add("基准");
        headList.add("计价期");
        headList.add("装期");
        headList.add("卸港");
        headList.add("结算量");
        headList.add("结算金额");
        headList.add("其他");
    }
}
