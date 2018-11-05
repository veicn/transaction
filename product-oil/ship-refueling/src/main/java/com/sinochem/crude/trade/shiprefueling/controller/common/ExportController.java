package com.sinochem.crude.trade.shiprefueling.controller.common;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.aliyun.oss.OSSClient;
import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.DownstreamSellerController;
import com.sinochem.crude.trade.shiprefueling.domain.po.Gory;
import com.sinochem.crude.trade.shiprefueling.domain.po.RIgnition;
import com.sinochem.crude.trade.shiprefueling.domain.po.RSupply;
import com.sinochem.crude.trade.shiprefueling.enums.*;
import com.sinochem.crude.trade.shiprefueling.model.query.QueryRIgnition;
import com.sinochem.crude.trade.shiprefueling.model.vo.QueryRIgnitionVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.QueryRSupplyVO;
import com.sinochem.crude.trade.shiprefueling.service.GoryService;
import com.sinochem.crude.trade.shiprefueling.service.RIgnitionService;
import com.sinochem.crude.trade.shiprefueling.service.RSupplyService;
import com.sinochem.crude.trade.shiprefueling.utils.ExportUtils;
import com.sinochem.crude.trade.shiprefueling.utils.OssFileManager;
import com.sinochem.it.b2b.common.exception.BizException;
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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ExportController {
    private Log log = LogFactory.getLog(DownstreamSellerController.class);

    private static String mess;
    private static Boolean flag = false;
    private static List<RSupply> rSupplies;

    @Value("${aliyun.oss.bucket}")
    private String publicBucket;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.endpoint}")
    private String host;

    @Value("${aliyun.oss.show.endpoint}")
    private String showHost;

    @Autowired
    private RSupplyService rSupplyService;

    @Autowired
    private RIgnitionService rIgnitionService;

    @Autowired
    private GoryService goryService;

    private HSSFWorkbook exports( List<String> headList,List<List<String>> contentList) {
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
                if (list != null && !list.isEmpty()){
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
     * 船燃模板导出
     */
    @WithoutAccess
    @RequestMapping(value = "/exportRIgnition.json", method = RequestMethod.POST )
    @ResponseBody
    public  ResultDatas<String> export(RIgnition vo){
        List<String> headList = new ArrayList<>();
        List<List<String>> contentList = new ArrayList<>();
        //标题
        ExportUtils.rIgnitionHeadline(headList);
        List<String> list = new ArrayList<>();
        list.add("2018-06-06(日期格式)");
        list.add("2018-06-06(日期格式)");
        list.add("201806062525(订单号最多32位)");
        list.add("请输入对应的数字(1：'油轮运输',2：'油罐车运输',3：'驳船送供',4：'自提',5：'面议')");
        list.add("(买家导入买家必须为当前用户才能导入成功)");
        list.add("(卖家导入卖家必须为当前用户才能导入成功)");
        list.add("(32.0最多两位小数)");
        list.add("请输入对应的数字(1001:'180#',1002:'120#',9999:'其他')");
        list.add("请输入对应的数字(1001:'燃料油',2001:'柴油',3001:'机油',9999:'其他')");
        list.add("(32.0最多两位小数)");
        list.add("(32.0最多两位小数)");
        list.add("32.0最多两位小数)");
        list.add("请输入对应的数字(1001:'180#',1002:'120#',9999:'其他')");
        list.add("请输入对应的数字(1001:'燃料油',2001:'柴油',3001:'机油',9999:'其他')");
        list.add("(32.0最多两位小数)");
        list.add("(32.0最多两位小数)");
        list.add("32.0最多两位小数)");
        list.add("请输入对应的数字(1001:'180#',1002:'120#',9999:'其他')");
        list.add("请输入对应的数字(1001:'燃料油',2001:'柴油',3001:'机油',9999:'其他')");
        list.add("(32.0最多两位小数)");
        list.add("(32.0最多两位小数)");
        list.add("32.0最多两位小数)");
        list.add("请输入对应的数字(1001:'180#',1002:'120#',9999:'其他')");
        list.add("请输入对应的数字(1001:'燃料油',2001:'柴油',3001:'机油',9999:'其他')");
        list.add("(32.0最多两位小数)");
        list.add("(32.0最多两位小数)");
        list.add("32.0最多两位小数)");
        contentList.add(list);
        ResultDatas<String> res = new ResultDatas<>();
        String storePath = "";
        HSSFWorkbook wkb = null;
        ByteArrayOutputStream out = null;
        InputStream excelStream = null;
        try {
            // 输出Excel文件
            out = new ByteArrayOutputStream();
            wkb = exports(headList, contentList);
            wkb.write(out);
            excelStream = new ByteArrayInputStream(out.toByteArray());
            storePath ="shiprefueling/export/" ;
            storePath = publicBucket+"/"+storePath + KeyGenUtils.newUuid()+".xls";
           // System.out.print("################"+storePath);
            OSSClient ossClient = new OSSClient(host, accessKeyId, accessKeySecret);
            OssFileManager ossFileManager = new OssFileManager(ossClient,accessKeyId,host);
            ossFileManager.save(excelStream, publicBucket, storePath);
            res.setDatas(showHost+"/"+storePath);
        } catch (Exception e ) {
            log.error("export error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setCode(Constants.EXCEPTION_CODE);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }



    /**
     * 船供模板导出
     */
    @WithoutAccess
    @RequestMapping(value = "/exportRSupply.json", method = RequestMethod.POST )
    @ResponseBody
    public  ResultDatas<String> export( RSupply vo){
        List<String> headList = new ArrayList<>();
        List<List<String>> contentList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("2018-06-06(日期格式)");
        list.add("201806062525(订单号最多32位)");
        list.add("2018-06-06(日期格式)");
        list.add("56154111255(供油单号最多32位)");
        list.add("(卖家导入卖家必须为当前用户才能导入成功)");
        list.add("(买家导入买家必须为当前用户才能导入成功)");
        list.add("船名");
        list.add("港口");
        list.add("(32.0最多两位小数)");
        list.add("请输入对应的数字(1001:'180#',1002:'120#',9999:'其他')");
        list.add("请输入对应的数字(1001:'燃料油',2001:'柴油',3001:'机油',9999:'其他')");
        list.add("(32.0最多两位小数)");
        list.add("(32.0最多两位小数)");
        list.add("32.0最多两位小数)");
        list.add("请输入对应的数字(1001:'180#',1002:'120#',9999:'其他')");
        list.add("请输入对应的数字(1001:'燃料油',2001:'柴油',3001:'机油',9999:'其他')");
        list.add("(32.0最多两位小数)");
        list.add("(32.0最多两位小数)");
        list.add("32.0最多两位小数)");
        list.add("请输入对应的数字(1001:'180#',1002:'120#',9999:'其他')");
        list.add("请输入对应的数字(1001:'燃料油',2001:'柴油',3001:'机油',9999:'其他')");
        list.add("(32.0最多两位小数)");
        list.add("(32.0最多两位小数)");
        list.add("32.0最多两位小数)");
        list.add("请输入对应的数字(1001:'180#',1002:'120#',9999:'其他')");
        list.add("请输入对应的数字(1001:'燃料油',2001:'柴油',3001:'机油',9999:'其他')");
        list.add("(32.0最多两位小数)");
        list.add("(32.0最多两位小数)");
        list.add("32.0最多两位小数)");
        contentList.add(list);
        //标题
        ExportUtils.rSupplyHeadline(headList);
        ResultDatas<String> res = new ResultDatas<>();
        String storePath = "";
        HSSFWorkbook wkb = null;
        ByteArrayOutputStream out = null;
        InputStream excelStream = null;
        try {
            // 输出Excel文件
            out = new ByteArrayOutputStream();
            wkb = exports(headList, contentList);
            wkb.write(out);
            excelStream = new ByteArrayInputStream(out.toByteArray());
            storePath ="shiprefueling/export/" ;
            storePath = publicBucket+"/"+storePath + KeyGenUtils.newUuid()+".xls";
            OSSClient ossClient = new OSSClient(host, accessKeyId, accessKeySecret);
            OssFileManager ossFileManager = new OssFileManager(ossClient,accessKeyId,host);
            ossFileManager.save(excelStream, publicBucket, storePath);
            res.setDatas(showHost+"/"+storePath);
        } catch (Exception e ) {
            log.error("export error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setCode(Constants.EXCEPTION_CODE);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }

    /**
     * 买家船燃导出
     */
    @WithoutAccess
    @RequestMapping(value = "/buyerExportRIgnitionData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<String> buyerExportRIgnition(@RequestBody QueryRIgnitionVO queryRIgnitionVO, CurrentUser user){
        System.out.print("######################buyerExportRIgnitionData,sysTag "+queryRIgnitionVO.getSysTag());

        if(null!=queryRIgnitionVO.getSysTag()&&"1".equals(queryRIgnitionVO.getSysTag())){
           return exportRIgnition(queryRIgnitionVO);
        }else{

            if(user != null&&user.getEpMemberId()!=null){
                queryRIgnitionVO.setBuyerCompanyId(user.getEpMemberId());
            }
            return exportRIgnition(queryRIgnitionVO);

        }
    }
    /**
     * 卖家家船燃导出
     */
    @WithoutAccess
    @RequestMapping(value = "/sellerExportRIgnitionData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<String> sellerExportRIgnition(@RequestBody QueryRIgnitionVO queryRIgnitionVO, CurrentUser user){


        if(user != null&&user.getEpMemberId()!=null){
            queryRIgnitionVO.setSellerCompanyId(user.getEpMemberId());
        }
        return exportRIgnition(queryRIgnitionVO);

    }



    /**
     * 船燃导出公共
     * @return
     */
    public ResultDatas<String> exportRIgnition( QueryRIgnitionVO queryRIgnitionVO){
        List<String> headList = new ArrayList<>();
        List<List<String>> contentList = new ArrayList<>();

        //标题
        ExportUtils.rIgnitionHeadline(headList);
        //内容
        List<RIgnition> rIgnitions = rIgnitionService.queryByQueryRIgnition(queryRIgnitionVO);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (RIgnition rSupply1 : rIgnitions) {
            List<String> list2 = new ArrayList<>();
            Date orderTime = rSupply1.getOrderTime();
            //订单日期
            if(orderTime != null){
                list2.add(dateFormat.format(orderTime));
            }else{
                list2.add("");
            }
            //提货日期
            if(rSupply1.getPickUpTime() != null){
                list2.add(dateFormat.format(rSupply1.getPickUpTime()));
            }else{
                list2.add("");
            }
            //订单号
            if(!StringUtil.isEmpty(rSupply1.getOrderCode())){
                list2.add(rSupply1.getOrderCode());
            }else{
                list2.add("");
            }
            //提货方式
            if(!StringUtil.isEmpty(rSupply1.getDeliveryWay())&& DeliveryModeEnums.getByCode(rSupply1.getDeliveryWay())!=null){
                //list2.add(rSupply1.getTransportWay());
                list2.add(DeliveryModeEnums.getByCode(rSupply1.getDeliveryWay()).getZhName());
            }else{
                list2.add("");
            }
            //运输方式
            if(null!=queryRIgnitionVO.getInfoType()&&queryRIgnitionVO.getInfoType().equals("9")){

                if(!StringUtil.isEmpty(rSupply1.getTransportWay())&& OilCrudeTypeOfShippingEnums.getByCode(rSupply1.getTransportWay())!=null){
                    //list2.add(rSupply1.getTransportWay());
                    list2.add(OilCrudeTypeOfShippingEnums.getByCode(rSupply1.getTransportWay()).getZhName());
                }else{
                    list2.add("");
                }
            }else if(null!=queryRIgnitionVO.getInfoType()&&!queryRIgnitionVO.getInfoType().equals("9")){
                if(!StringUtil.isEmpty(rSupply1.getTransportWay())&&TypeOfShippingEnums.getByCode(rSupply1.getTransportWay())!=null){
                    //list2.add(rSupply1.getTransportWay());
                    list2.add(TypeOfShippingEnums.getByCode(rSupply1.getTransportWay()).getZhName());
                }else{
                    list2.add("");
                }
            }

            //买家名称
            if(!StringUtil.isEmpty(rSupply1.getBuyCompanyName())){
                list2.add(rSupply1.getBuyCompanyName());
            }else{
                list2.add("");
            }
            //卖家名称
            if(!StringUtil.isEmpty(rSupply1.getSellCompanyName())){
                list2.add(rSupply1.getSellCompanyName());
            }else{
                list2.add("");
            }
            //总价
            /*if(rSupply1.getTotalPrice() != null){
                list2.add(rSupply1.getTotalPrice().toString());
            }else{
                list2.add("");
            }*/

            Long orderId = rSupply1.getOrderId();
            if(orderId != null){
                List<Gory> gories = goryService.queryGoryListByOrderId(orderId);
                for (Gory gory:gories) {
                    //油种
                   /* if(!StringUtil.isEmpty(gory.getOilVarieties())&&FuelOilEnums.getByCode(gory.getOilVarieties()) !=null) {

                            list2.add(FuelOilEnums.getByCode(gory.getOilVarieties()).getZhName());

                    }else
                        list2.add("");

                    //类型
                    if(!StringUtil.isEmpty(gory.getOilClassification())&&OilTypeEnums.getByCode(gory.getOilClassification())!=null) {
                        list2.add(OilTypeEnums.getByCode(gory.getOilClassification()).getZhName());
                    }else
                        list2.add("");*/
                    if(!StringUtil.isEmpty(gory.getOilClassification())){
                        list2.add(gory.getOilClassification());
                    }else{
                        list2.add("");
                    }
                    if(!StringUtil.isEmpty(gory.getOilVarieties())){
                        list2.add(gory.getOilVarieties());
                    }else{
                        list2.add("");
                    }
                    //单价
                    list2.add(gory.getUnitPrice() == null?"":gory.getUnitPrice()+"");
                    //数量
                    list2.add(gory.getNumber() == null?"":gory.getNumber()+"");
                    if(!StringUtil.isEmpty(gory.getUnit())){
                        list2.add(gory.getUnit());
                    }else{
                        list2.add("");
                    }

                    //分类价格
                    //list2.add(gory.getClassPrice() == null?"":gory.getClassPrice()+"");
                }
            }
            contentList.add(list2);
        }

        ResultDatas<String> res = new ResultDatas<>();
        String storePath = "";
        HSSFWorkbook wkb = null;
        ByteArrayOutputStream out = null;
        InputStream excelStream = null;
        /*OutputStream resOut = null;*/
        try {
            // 输出Excel文件
            out = new ByteArrayOutputStream();
            wkb = exports(headList, contentList);
            wkb.write(out);
            /*resOut = response.getOutputStream();*/
            /*wkb.write(resOut);
            String fileName = KeyGenUtils.newUuid()+".xls";
            response.setHeader("Content-disposition","attachment;filename="+fileName);
            response.setContentType("application/vnd.ms-excel,charset=UTF-8");
            /*resOut.close();*/
            excelStream = new ByteArrayInputStream(out.toByteArray());
            storePath ="shiprefueling/excel/" ;
            storePath = storePath + KeyGenUtils.newUuid()+".xls";
            OSSClient ossClient = new OSSClient(host, accessKeyId, accessKeySecret);
            OssFileManager ossFileManager = new OssFileManager(ossClient,accessKeyId,host);
            ossFileManager.save(excelStream, publicBucket, storePath);
            res.setDatas(showHost+"/"+storePath);
        } catch (Exception e ) {
            log.error("export error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setCode(Constants.EXCEPTION_CODE);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }

    /**
     * 买家船供导出
     */
    @WithoutAccess
    @RequestMapping(value = "/buyerExportRSupplyData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<String> buyerxportTransit(@RequestBody QueryRSupplyVO queryRSupplyVO, CurrentUser user){
        if(user != null){
            queryRSupplyVO.setBuyerCompanyId(user.getEpMemberId());
        }
        return exportRSupply(queryRSupplyVO);
    }
    /**
     * 卖家家船供导出
     */
    @WithoutAccess
    @RequestMapping(value = "/sellerExportRSupplyData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<String> sellerexportTransit(@RequestBody QueryRSupplyVO queryRSupplyVO, CurrentUser user){
        if(user != null){
            queryRSupplyVO.setSellerCompanyId(user.getEpMemberId());
        }
        return exportRSupply(queryRSupplyVO);
    }

    /**
     * 船供导出公共
     * @return
     */
    @RequestMapping(value = "/exportRSupplyData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<String> exportRSupply( QueryRSupplyVO queryRSupplyVO){
        List<String> headList = new ArrayList<>();
        List<List<String>> contentList = new ArrayList<>();

        //标题
        ExportUtils.rSupplyHeadline(headList);
        //内容
        List<RSupply> rSupplies = rSupplyService.queryByQueryRSupply(queryRSupplyVO);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (RSupply rSupply1 : rSupplies) {
            List<String> list2 = new ArrayList<>();
            Date orderTime = rSupply1.getOrderTime();
            //订单日期
            if(orderTime != null){
                list2.add(dateFormat.format(orderTime));
            }else{
                list2.add("");
            }
            //订单号
            if(!StringUtil.isEmpty(rSupply1.getOrderCode())){
                list2.add(rSupply1.getOrderCode());
            }else{
                list2.add("");
            }
            //加油日期
            if(rSupply1.getFuelChargingTime() != null){
                list2.add(dateFormat.format(rSupply1.getFuelChargingTime()));
            }else{
                list2.add("");
            }
            //供油单号
            if(!StringUtil.isEmpty(rSupply1.getFuelChargingCode())){
                list2.add(rSupply1.getFuelChargingCode());
            }else{
                list2.add("");
            }
            //卖家名称
            if(!StringUtil.isEmpty(rSupply1.getSellCompanyName())){
                list2.add(rSupply1.getSellCompanyName());
            }else{
                list2.add("");
            }
            //买家名称
            if(!StringUtil.isEmpty(rSupply1.getBuyCompanyName())){
                list2.add(rSupply1.getBuyCompanyName());
            }else{
                list2.add("");
            }
            //船名
            if(!StringUtil.isEmpty(rSupply1.getVesselName())){
                list2.add(rSupply1.getVesselName());
            }else{
                list2.add("");
            }
            //港口
            if(!StringUtil.isEmpty(rSupply1.getPort())){
                list2.add(rSupply1.getPort());
            }else{
                list2.add("");
            }
            //总价
            if(rSupply1.getTotalPrice() != null){
                list2.add(rSupply1.getTotalPrice().toString());
            }else{
                list2.add("");
            }

            Long orderId = rSupply1.getOrderId();
            if(orderId != null){
                List<Gory> gories = goryService.queryGoryListByOrderId(orderId);
                for (Gory gory:gories) {
                    //油种
                    if(!StringUtil.isEmpty(gory.getOilVarieties())&&FuelOilEnums.getByCode(gory.getOilVarieties())!=null)
                        list2.add(FuelOilEnums.getByCode(gory.getOilVarieties()).getZhName()   );
                    else
                        list2.add("");

                    //类型
                    if(!StringUtil.isEmpty(gory.getOilClassification())&&OilTypeEnums.getByCode(gory.getOilClassification())!=null)
                        list2.add(OilTypeEnums.getByCode(gory.getOilClassification()).getZhName());
                    else
                        list2.add("");
                    //单价
                    list2.add(gory.getUnitPrice() == null?"":gory.getUnitPrice()+"");
                    //数量
                    list2.add(gory.getNumber() == null?"":gory.getNumber()+"");
                    //分类价格
                    list2.add(gory.getClassPrice() == null?"":gory.getClassPrice()+"");
                }
            }
            contentList.add(list2);
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
            storePath ="shiprefueling/excel/" ;
            storePath = publicBucket+"/"+storePath + KeyGenUtils.newUuid()+".xls";
            OSSClient ossClient = new OSSClient(host, accessKeyId, accessKeySecret);
            OssFileManager ossFileManager = new OssFileManager(ossClient,accessKeyId,host);
            ossFileManager.save(excelStream, publicBucket, storePath);
            res.setDatas(showHost+"/"+storePath);
        } catch (Exception e ) {
            log.error("export error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setCode(Constants.EXCEPTION_CODE);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }

    private String makeFileName(String filename) {
        return UUID.randomUUID().toString() + "_" + filename;
    }

    private String makePath(String filename, String savePath) {
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf;
        int dir2 = (hashcode & 0xf0) >> 4;
        String dir = savePath + "\\" + dir1 + "\\" + dir2;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }
}
