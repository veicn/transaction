package com.sinochem.crude.trade.shiprefueling.controller.common;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.eyeieye.melody.util.StringUtil;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.domain.RIgnitionCombine;
import com.sinochem.crude.trade.shiprefueling.domain.RSupplyCombine;
import com.sinochem.crude.trade.shiprefueling.domain.po.Gory;
import com.sinochem.crude.trade.shiprefueling.domain.po.RIgnition;
import com.sinochem.crude.trade.shiprefueling.domain.po.RSupply;
import com.sinochem.crude.trade.shiprefueling.service.GoryService;
import com.sinochem.crude.trade.shiprefueling.service.RIgnitionService;
import com.sinochem.crude.trade.shiprefueling.service.RSupplyService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ImportController {
    private Log log = LogFactory.getLog(ImportController.class);

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

    @Autowired
    private RSupplyService rSupplyService;

    @Autowired
    private GoryService goryService;

    @Autowired
    private RIgnitionService rIgnitionService;

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 卖家船燃订单导入
     */
    @WithoutAccess
    @RequestMapping(value = "/sellerImportRIgnitionData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<String> sellerImportsBt(HttpServletRequest request, CurrentUser user){
        ResultDatas<String> res = new ResultDatas<>();
        try {

            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
            String oilType = req.getParameter("oilType");
            //oilType="1";
            System.out.print("######################oilType: "+oilType);
            MultipartFile file = req.getFile("file");
            //String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            Workbook wb = new HSSFWorkbook(inputStream);

            List<RIgnitionCombine> list = new ArrayList<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //取出所有公司名称用来获取对应的公司id
            List<String> names = new ArrayList<String>();
            for (int numSheet = 0; numSheet <wb.getNumberOfSheets(); numSheet++) {
                //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                Sheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行Row
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    Row row = hssfSheet.getRow(rowNum);
                    if (row != null) {
                        if(row.getCell(5) != null){
                            names.add(row.getCell(5).getStringCellValue());
                        }
                        if(row.getCell(6) != null){
                            names.add(row.getCell(6).getStringCellValue());
                        }
                    }
                }
            }

            //获取所有公司对象并让名称和id匹配
            Map<String,Long> map = new HashMap<>();
            List<EnterpriseVo> enterpriseVos = enterpriseService.queryEnterpriseVoList(names);
            if(enterpriseVos != null){
                for (EnterpriseVo enterpriseVo:enterpriseVos) {
                    map.put(enterpriseVo.getName(),enterpriseVo.getMemberId());
                }
            }
            for (int numSheet = 0; numSheet <wb.getNumberOfSheets(); numSheet++) {
                //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                Sheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行Row
                int lastRowNum = hssfSheet.getLastRowNum();
                int begRow = 8; // 油种
                //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
                    try {
                        Row row = hssfSheet.getRow(rowNum);
                        if(row.getCell(2) == null||"".equals(row.getCell(2).getStringCellValue()) )
                            break;

                        if (row != null) {
                            RIgnition bt = new RIgnition();
                            if (row.getCell(0) != null) {
                                Cell cell = row.getCell(0);
                                String stringCellValue = cell.getStringCellValue();
                                bt.setOrderTime(simpleDateFormat.parse(row.getCell(0).getStringCellValue()));
                            }
                            if (row.getCell(1) != null) {
                                Cell cell = row.getCell(1);
                                String stringCellValue = cell.getStringCellValue();
                                bt.setPickUpTime(simpleDateFormat.parse(row.getCell(1).getStringCellValue()));
                            }
                            if (row.getCell(2) != null) {
                                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                                bt.setOrderCode(row.getCell(2).getStringCellValue());
                            }
                            if (row.getCell(3) != null) {
                                bt.setDeliveryWay(row.getCell(3).getStringCellValue());
                            }
                            if (row.getCell(4) != null) {
                                bt.setTransportWay(row.getCell(4).getStringCellValue());
                            }
                            if (row.getCell(5) != null) {
                                bt.setBuyCompanyName(row.getCell(5).getStringCellValue());
                                bt.setBuyCompanyId(map.get(row.getCell(5).getStringCellValue()));
                            }
                            if (row.getCell(6) != null) {
                                bt.setSellCompanyName(row.getCell(6).getStringCellValue());
                                bt.setSellCompanyId(map.get(row.getCell(6).getStringCellValue()));
                            }


                            if (row.getCell(7) != null) {
                                bt.setCurrency(row.getCell(7).getStringCellValue());

                            }

                            List<Gory> list1 = new ArrayList<>();
                            Gory gory = new Gory();
                            if (row.getCell(begRow+0) != null) {
                                gory.setOilClassification(row.getCell(begRow+0).getStringCellValue());
                            }
                            if (row.getCell(begRow+1) != null) {
                                gory.setOilVarieties(row.getCell(begRow+1).getStringCellValue());
                            }
                            if (row.getCell(begRow+2) != null) {
                                String cell9 = row.getCell(begRow+2).getStringCellValue();
                                if (!StringUtils.isNullOrEmpty(cell9)) {
                                    BigDecimal bigDecimal1 = new BigDecimal(cell9);
                                    gory.setUnitPrice(bigDecimal1);
                                }
                            }

                            if (row.getCell(begRow+3) != null) {
                                String cell10 = row.getCell(begRow+3).getStringCellValue();
                                if (!StringUtils.isNullOrEmpty(cell10)) {
                                    BigDecimal bigDecimal2 = new BigDecimal(cell10);
                                    gory.setNumber(bigDecimal2);
                                }
                            }
                            if (row.getCell(begRow+4) != null) {
                                gory.setUnit(row.getCell(begRow+4).getStringCellValue());
                            }

                            Gory gory1 = new Gory();
                            if (row.getCell(begRow+5) != null) {
                                gory1.setOilClassification(row.getCell(begRow+5).getStringCellValue());
                            }
                            if (row.getCell(begRow+6) != null) {
                                gory1.setOilVarieties(row.getCell(begRow+6).getStringCellValue());
                            }
                            if (row.getCell(begRow+7) != null) {
                                String cell14 = row.getCell(begRow+7).getStringCellValue();
                                if (!StringUtils.isNullOrEmpty(cell14)) {
                                    BigDecimal bigDecimal1 = new BigDecimal(cell14);
                                    gory1.setUnitPrice(bigDecimal1);
                                }
                            }

                            if (row.getCell(begRow+8) != null) {
                                String cell15 = row.getCell(begRow+8).getStringCellValue();
                                if (!StringUtils.isNullOrEmpty(cell15)) {
                                    BigDecimal bigDecimal5 = new BigDecimal(cell15);
                                    gory1.setNumber(bigDecimal5);
                                }
                            }
                            if (row.getCell(begRow+9) != null) {
                                gory1.setUnit(row.getCell(begRow+9).getStringCellValue());
                            }




                            Gory gory2 = new Gory();
                            if (row.getCell(begRow+10) != null) {
                                gory2.setOilClassification(row.getCell(begRow+10).getStringCellValue());
                            }
                            if (row.getCell(begRow+11) != null) {
                                gory2.setOilVarieties(row.getCell(begRow+11).getStringCellValue());
                            }
                            if (row.getCell(begRow+12) != null) {
                                String cell19 = row.getCell(begRow+12).getStringCellValue();
                                if (!StringUtils.isNullOrEmpty(cell19)) {
                                    BigDecimal bigDecimal1 = new BigDecimal(cell19);
                                    gory2.setUnitPrice(bigDecimal1);
                                }
                            }
                            if (row.getCell(begRow+13) != null) {
                                String cell20 = row.getCell(begRow+13).getStringCellValue();
                                if (!StringUtils.isNullOrEmpty(cell20)) {
                                    BigDecimal bigDecimal20 = new BigDecimal(cell20);
                                    gory2.setNumber(bigDecimal20);
                                }
                            }
                            if (row.getCell(begRow+14) != null) {
                                gory2.setUnit(row.getCell(begRow+14).getStringCellValue());
                            }


                            Gory gory3 = new Gory();
                            if (row.getCell(begRow+15) != null) {
                                gory3.setOilClassification(row.getCell(begRow+15).getStringCellValue());
                            }
                            if (row.getCell(begRow+16) != null) {
                                gory3.setOilVarieties(row.getCell(begRow+16).getStringCellValue());
                            }
                            if (row.getCell(begRow+17) != null) {
                                String cell24 = row.getCell(begRow+17).getStringCellValue();
                                if (!StringUtils.isNullOrEmpty(cell24)) {
                                    BigDecimal bigDecimal24 = new BigDecimal(cell24);
                                    gory3.setUnitPrice(bigDecimal24);
                                }
                            }
                            if (row.getCell(begRow+18) != null) {
                                String cell25 = row.getCell(begRow+18).getStringCellValue();
                                if (!StringUtils.isNullOrEmpty(cell25)) {
                                    BigDecimal bigDecimal25 = new BigDecimal(cell25);
                                    gory3.setNumber(bigDecimal25);
                                }
                            }
                            if (row.getCell(begRow+19) != null) {
                                gory3.setUnit(row.getCell(begRow+19).getStringCellValue());
                            }


                            if(!StringUtil.isEmpty(gory.getOilVarieties()) || !StringUtil.isEmpty(gory.getOilClassification())
                                    || gory.getNumber() != null || gory.getUnitPrice() != null){
                                list1.add(gory);
                            }
                            if( !StringUtil.isEmpty(gory1.getOilVarieties()) || !StringUtil.isEmpty(gory1.getOilClassification())
                                    || gory1.getNumber() != null || gory1.getUnitPrice() != null){
                                list1.add(gory1);
                            }
                            if( !StringUtil.isEmpty(gory2.getOilVarieties()) || !StringUtil.isEmpty(gory2.getOilClassification())
                                    || gory2.getNumber() != null || gory2.getUnitPrice() != null){
                                list1.add(gory2);
                            }
                            if( !StringUtil.isEmpty(gory3.getOilVarieties()) || !StringUtil.isEmpty(gory3.getOilClassification())
                                    || gory3.getNumber() != null || gory3.getUnitPrice() != null){
                                list1.add(gory3);
                            }

                            BigDecimal ttlPrice=BigDecimal.ZERO;

                            if(gory.getNumber() != null && gory.getUnitPrice() != null){
                                BigDecimal p = gory.getNumber().multiply(gory.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP) ;
                                ttlPrice=ttlPrice.add(p);

                            }
                            if(gory1.getNumber() != null && gory1.getUnitPrice() != null){
                                BigDecimal p = gory1.getNumber().multiply(gory1.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP)   ;
                                ttlPrice=ttlPrice.add(p);

                            }
                            if(gory2.getNumber() != null && gory2.getUnitPrice() != null){
                                BigDecimal p = gory2.getNumber().multiply(gory2.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP)   ;
                                ttlPrice=ttlPrice.add(p);

                            }
                            if(gory3.getNumber() != null && gory3.getUnitPrice() != null){
                                BigDecimal p = gory3.getNumber().multiply(gory3.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP)   ;
                                ttlPrice=ttlPrice.add(p);

                            }
                            bt.setTotalPrice(ttlPrice);
                            RIgnitionCombine rIgnitionCombine = new RIgnitionCombine();
                            rIgnitionCombine.setrIgnition(bt);
                            rIgnitionCombine.setList(list1);
                            list.add(rIgnitionCombine);
                        }
                    } catch (Exception e) {
                        log.error("import error", e);
                    }
            }
            String message = rIgnitionService.sellerImportRIgnition(list,user,oilType);
            res.setDatas(message);
            res.setMessage("OK");
            flag= true;
        }catch (Exception e ) {
            flag= false;
            log.error("importRIgnition error", e);
            e.printStackTrace();
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setCode(Constants.EXCEPTION_CODE);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }
    /**
     * om船燃订单导入
     */
    @WithoutAccess
    @RequestMapping(value = "/omImportRIgnitionData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<String> omImportsBt(HttpServletRequest request, CurrentUser user){
        ResultDatas<String> res = new ResultDatas<>();
        try {

            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
            String oilType = req.getParameter("oilType");
            System.out.print("######################oilType: "+oilType);
            MultipartFile file = req.getFile("file");
            //String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            Workbook wb = new HSSFWorkbook(inputStream);

            List<RIgnitionCombine> list = new ArrayList<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //取出所有公司名称用来获取对应的公司id
            List<String> names = new ArrayList<String>();
            for (int numSheet = 0; numSheet <wb.getNumberOfSheets(); numSheet++) {
                //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                Sheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行Row
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    Row row = hssfSheet.getRow(rowNum);
                    if (row != null) {
                        if(row.getCell(5) != null){
                            names.add(row.getCell(5).getStringCellValue());
                        }
                        if(row.getCell(6) != null){
                            names.add(row.getCell(6).getStringCellValue());
                        }
                    }
                }
            }

            //获取所有公司对象并让名称和id匹配
            Map<String,Long> map = new HashMap<>();
            List<EnterpriseVo> enterpriseVos = enterpriseService.queryEnterpriseVoList(names);
            if(enterpriseVos != null){
                for (EnterpriseVo enterpriseVo:enterpriseVos) {
                    map.put(enterpriseVo.getName(),enterpriseVo.getMemberId());
                }
            }
            for (int numSheet = 0; numSheet <wb.getNumberOfSheets(); numSheet++) {
                //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                Sheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行Row
                int lastRowNum = hssfSheet.getLastRowNum();
                int begRow = 8; // 油种
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    try{
                        Row row = hssfSheet.getRow(rowNum);
                        if(row.getCell(2) == null||"".equals(row.getCell(2).getStringCellValue()) )
                            break;

                        if (row != null) {
                            RIgnition bt = new RIgnition();
                            if(row.getCell(0) != null){
                                bt.setOrderTime(simpleDateFormat.parse(row.getCell(0).getStringCellValue()));
                            }
                            if(row.getCell(1) != null){
                                bt.setPickUpTime(simpleDateFormat.parse(row.getCell(1).getStringCellValue()));
                            }
                            if(row.getCell(2) != null){
                                bt.setOrderCode(row.getCell(2).getStringCellValue());
                            }
                            if(row.getCell(3) != null){
                                bt.setDeliveryWay(row.getCell(3).getStringCellValue());
                            }
                            if(row.getCell(4) != null){
                                bt.setTransportWay(row.getCell(4).getStringCellValue());
                            }
                            if(row.getCell(5) != null){
                                bt.setBuyCompanyName(row.getCell(5).getStringCellValue());
                                bt.setBuyCompanyId(map.get(row.getCell(5).getStringCellValue()));
                            }
                            if(row.getCell(6) != null){
                                bt.setSellCompanyName(row.getCell(6).getStringCellValue());
                                bt.setSellCompanyId(map.get(row.getCell(6).getStringCellValue()));
                            }
                            if(row.getCell(7) != null){
                                bt.setCurrency(row.getCell(7).getStringCellValue());
                            }

                            List<Gory> list1 = new ArrayList<>();
                            Gory gory = new Gory();
                            if(row.getCell(begRow) != null){
                                gory.setOilClassification(row.getCell(begRow).getStringCellValue());
                            }
                            if(row.getCell(begRow+1) != null){
                                gory.setOilVarieties(row.getCell(begRow+1).getStringCellValue());
                            }
                            if(row.getCell(begRow+2) != null){
                                String cell9 = row.getCell(begRow+2).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell9)){
                                    BigDecimal bigDecimal1 = new BigDecimal(cell9);
                                    gory.setUnitPrice(bigDecimal1);
                                }
                            }
                            if(row.getCell(begRow+3) != null){
                                String cell10 = row.getCell(begRow+3).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell10)){
                                    BigDecimal bigDecimal2 = new BigDecimal(cell10);
                                    gory.setNumber(bigDecimal2);
                                }
                            }
                            if (row.getCell(begRow+4) != null) {
                                gory.setUnit(row.getCell(begRow+4).getStringCellValue());
                            }


                            Gory gory1 = new Gory();
                            if(row.getCell(begRow+5) != null){
                                gory1.setOilClassification(row.getCell(begRow+5).getStringCellValue());
                            }
                            if(row.getCell(begRow+6) != null){
                                gory1.setOilVarieties(row.getCell(begRow+6).getStringCellValue());
                            }
                            if(row.getCell(begRow+7) != null){
                                String cell14 = row.getCell(begRow+7).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell14)){
                                    BigDecimal bigDecimal1 = new BigDecimal(cell14);
                                    gory1.setUnitPrice(bigDecimal1);
                                }
                            }
                            if(row.getCell(begRow+8) != null){
                                String cell15 = row.getCell(begRow+8).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell15)){
                                    BigDecimal bigDecimal5 = new BigDecimal(cell15);
                                    gory1.setNumber(bigDecimal5);
                                }
                            }
                            if (row.getCell(begRow+9) != null) {
                                gory1.setUnit(row.getCell(begRow+9).getStringCellValue());
                            }


                            Gory gory2 = new Gory();
                            if(row.getCell(begRow+10) != null){
                                gory2.setOilClassification(row.getCell(begRow+10).getStringCellValue());
                            }
                            if(row.getCell(begRow+11) != null){
                                gory2.setOilVarieties(row.getCell(begRow+11).getStringCellValue());
                            }
                            if(row.getCell(begRow+12) != null){
                                String cell19 = row.getCell(begRow+12).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell19)){
                                    BigDecimal bigDecimal1 = new BigDecimal(cell19);
                                    gory2.setUnitPrice(bigDecimal1);
                                }
                            }
                            if(row.getCell(begRow+13) != null){
                                String cell20 = row.getCell(begRow+13).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell20)){
                                    BigDecimal bigDecimal20 = new BigDecimal(cell20);
                                    gory2.setNumber(bigDecimal20);
                                }
                            }
                            if (row.getCell(begRow+14) != null) {
                                gory2.setUnit(row.getCell(begRow+14).getStringCellValue());
                            }



                            Gory gory3 = new Gory();
                            if(row.getCell(begRow+15) != null){
                                gory3.setOilClassification(row.getCell(begRow+15).getStringCellValue());
                            }
                            if(row.getCell(begRow+16) != null){
                                gory3.setOilVarieties(row.getCell(begRow+16).getStringCellValue());
                            }
                            if(row.getCell(begRow+17) != null){
                                String cell24 = row.getCell(begRow+17).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell24)){
                                    BigDecimal bigDecimal24 = new BigDecimal(cell24);
                                    gory3.setUnitPrice(bigDecimal24);
                                }
                            }
                            if(row.getCell(begRow+18) != null){
                                String cell25 = row.getCell(begRow+18).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell25)){
                                    BigDecimal bigDecimal25 = new BigDecimal(cell25);
                                    gory3.setNumber(bigDecimal25);
                                }
                            }
                            if (row.getCell(begRow+19) != null) {
                                gory3.setUnit(row.getCell(begRow+19).getStringCellValue());
                            }


                            if(!StringUtil.isEmpty(gory.getOilVarieties()) || !StringUtil.isEmpty(gory.getOilClassification())
                                    || gory.getNumber() != null || gory.getUnitPrice() != null){
                                list1.add(gory);
                            }
                            if(!StringUtil.isEmpty(gory1.getOilVarieties()) || !StringUtil.isEmpty(gory1.getOilClassification())
                                    || gory1.getNumber() != null || gory1.getUnitPrice() != null){
                                list1.add(gory1);
                            }
                            if( !StringUtil.isEmpty(gory2.getOilVarieties()) || !StringUtil.isEmpty(gory2.getOilClassification())
                                    || gory2.getNumber() != null || gory2.getUnitPrice() != null){
                                list1.add(gory2);
                            }
                            if( !StringUtil.isEmpty(gory3.getOilVarieties()) || !StringUtil.isEmpty(gory3.getOilClassification())
                                    || gory3.getNumber() != null || gory3.getUnitPrice() != null){
                                list1.add(gory3);
                            }

                            //RIgnitionCombine rIgnitionCombine = new RIgnitionCombine();
                            //rIgnitionCombine.setrIgnition(bt);
                            BigDecimal ttlPrice=BigDecimal.ZERO;

                            if(gory.getNumber() != null && gory.getUnitPrice() != null){
                                BigDecimal p = gory.getNumber().multiply(gory.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP) ;
                                ttlPrice=ttlPrice.add(p);

                            }
                            if(gory1.getNumber() != null && gory1.getUnitPrice() != null){
                                BigDecimal p = gory1.getNumber().multiply(gory1.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP)   ;
                                ttlPrice=ttlPrice.add(p);

                            }
                            if(gory2.getNumber() != null && gory2.getUnitPrice() != null){
                                BigDecimal p = gory2.getNumber().multiply(gory2.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP)   ;
                                ttlPrice=ttlPrice.add(p);

                            }
                            if(gory3.getNumber() != null && gory3.getUnitPrice() != null){
                                BigDecimal p = gory3.getNumber().multiply(gory3.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP)   ;
                                ttlPrice=ttlPrice.add(p);

                            }
                            bt.setTotalPrice(ttlPrice);
                            RIgnitionCombine rIgnitionCombine = new RIgnitionCombine();
                            rIgnitionCombine.setrIgnition(bt);
                            rIgnitionCombine.setList(list1);
                            list.add(rIgnitionCombine);
                        }
                    }catch(Exception e){
                        log.error("import error", e);
                        e.printStackTrace();
                    }

                }
            }
            String message = rIgnitionService.omImportRIgnition(list,user,oilType);
            res.setDatas(message);
            res.setMessage("OK");
            flag= true;
        } catch (Exception e ) {
            flag= false;
            log.error("importRIgnition error", e);
            e.printStackTrace();
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setCode(Constants.EXCEPTION_CODE);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }
    /**
     * 买家船燃订单导入
     */
    @WithoutAccess
    @RequestMapping(value = "/buyImportRIgnitionData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<String> buyImportsBt(HttpServletRequest request, CurrentUser user){
        ResultDatas<String> res = new ResultDatas<>();
        try {

            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
            String oilType = req.getParameter("oilType");
            System.out.print("######################oilType: "+oilType);
            MultipartFile file = req.getFile("file");
            //String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            Workbook wb = new HSSFWorkbook(inputStream);

            List<RIgnitionCombine> list = new ArrayList<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //取出所有公司名称用来获取对应的公司id
            List<String> names = new ArrayList<String>();
            for (int numSheet = 0; numSheet <wb.getNumberOfSheets(); numSheet++) {
                //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                Sheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行Row
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    Row row = hssfSheet.getRow(rowNum);
                    if (row != null) {
                        if(row.getCell(5) != null){
                            names.add(row.getCell(5).getStringCellValue());
                        }
                        if(row.getCell(6) != null){
                            names.add(row.getCell(6).getStringCellValue());
                        }
                    }
                }
            }

            //获取所有公司对象并让名称和id匹配
            Map<String,Long> map = new HashMap<>();
            List<EnterpriseVo> enterpriseVos = enterpriseService.queryEnterpriseVoList(names);
            if(enterpriseVos != null){
                for (EnterpriseVo enterpriseVo:enterpriseVos) {
                    map.put(enterpriseVo.getName(),enterpriseVo.getMemberId());
                }
            }
            for (int numSheet = 0; numSheet <wb.getNumberOfSheets(); numSheet++) {
                //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                Sheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行Row
                int lastRowNum = hssfSheet.getLastRowNum();
                int begRow = 8; // 油种
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    try{
                        Row row = hssfSheet.getRow(rowNum);
                        if(row.getCell(2) == null||"".equals(row.getCell(2).getStringCellValue()) )
                            break;

                        if (row != null) {
                            RIgnition bt = new RIgnition();
                            if(row.getCell(0) != null){
                                bt.setOrderTime(simpleDateFormat.parse(row.getCell(0).getStringCellValue()));
                            }
                            if(row.getCell(1) != null){
                                bt.setPickUpTime(simpleDateFormat.parse(row.getCell(1).getStringCellValue()));
                            }
                            if(row.getCell(2) != null){
                                bt.setOrderCode(row.getCell(2).getStringCellValue());
                            }
                            if(row.getCell(3) != null){
                                bt.setDeliveryWay(row.getCell(3).getStringCellValue());
                            }
                            if(row.getCell(4) != null){
                                bt.setTransportWay(row.getCell(4).getStringCellValue());
                            }
                            if(row.getCell(5) != null){
                                bt.setBuyCompanyName(row.getCell(5).getStringCellValue());
                                bt.setBuyCompanyId(map.get(row.getCell(5).getStringCellValue()));
                            }
                            if(row.getCell(6) != null){
                                bt.setSellCompanyName(row.getCell(6).getStringCellValue());
                                bt.setSellCompanyId(map.get(row.getCell(6).getStringCellValue()));
                            }
                            if(row.getCell(7) != null){
                                bt.setCurrency(row.getCell(7).getStringCellValue());
                            }

                            //RIgnitionCombine rIgnitionCombine = new RIgnitionCombine();
                            //rIgnitionCombine.setrIgnition(bt);
                            List<Gory> list1 = new ArrayList<>();
                            Gory gory = new Gory();
                            if(row.getCell(begRow) != null){
                                gory.setOilClassification(row.getCell(begRow).getStringCellValue());
                            }
                            if(row.getCell(begRow+1) != null){
                                gory.setOilVarieties(row.getCell(begRow+1).getStringCellValue());
                            }
                            if(row.getCell(begRow+2) != null){
                                String cell9 = row.getCell(begRow+2).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell9)){
                                    BigDecimal bigDecimal1 = new BigDecimal(cell9);
                                    gory.setUnitPrice(bigDecimal1);
                                }
                            }
                            if(row.getCell(begRow+3) != null){
                                String cell10 = row.getCell(begRow+3).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell10)){
                                    BigDecimal bigDecimal2 = new BigDecimal(cell10);
                                    gory.setNumber(bigDecimal2);
                                }
                            }
                            if (row.getCell(begRow+4) != null) {
                                gory.setUnit(row.getCell(begRow+4).getStringCellValue());
                            }


                            Gory gory1 = new Gory();
                            if(row.getCell(begRow+5) != null){
                                gory1.setOilClassification(row.getCell(begRow+5).getStringCellValue());
                            }
                            if(row.getCell(begRow+6) != null){
                                gory1.setOilVarieties(row.getCell(begRow+6).getStringCellValue());
                            }
                            if(row.getCell(begRow+7) != null){
                                String cell14 = row.getCell(begRow+7).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell14)){
                                    BigDecimal bigDecimal1 = new BigDecimal(cell14);
                                    gory1.setUnitPrice(bigDecimal1);
                                }
                            }
                            if(row.getCell(begRow+8) != null){
                                String cell15 = row.getCell(begRow+8).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell15)){
                                    BigDecimal bigDecimal5 = new BigDecimal(cell15);
                                    gory1.setNumber(bigDecimal5);
                                }
                            }
                            if (row.getCell(begRow+9) != null) {
                                gory1.setUnit(row.getCell(begRow+9).getStringCellValue());
                            }


                            Gory gory2 = new Gory();
                            if(row.getCell(begRow+10) != null){
                                gory2.setOilClassification(row.getCell(begRow+10).getStringCellValue());
                            }
                            if(row.getCell(begRow+11) != null){
                                gory2.setOilVarieties(row.getCell(begRow+11).getStringCellValue());
                            }
                            if(row.getCell(begRow+12) != null){
                                String cell19 = row.getCell(begRow+12).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell19)){
                                    BigDecimal bigDecimal1 = new BigDecimal(cell19);
                                    gory2.setUnitPrice(bigDecimal1);
                                }
                            }
                            if(row.getCell(begRow+13) != null){
                                String cell20 = row.getCell(begRow+13).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell20)){
                                    BigDecimal bigDecimal20 = new BigDecimal(cell20);
                                    gory2.setNumber(bigDecimal20);
                                }
                            }
                            if (row.getCell(begRow+14) != null) {
                                gory2.setUnit(row.getCell(begRow+14).getStringCellValue());
                            }


                            Gory gory3 = new Gory();
                            if(row.getCell(begRow+15) != null){
                                gory3.setOilClassification(row.getCell(begRow+15).getStringCellValue());
                            }
                            if(row.getCell(begRow+16) != null){
                                gory3.setOilVarieties(row.getCell(begRow+16).getStringCellValue());
                            }
                            if(row.getCell(begRow+17) != null){
                                String cell24 = row.getCell(begRow+17).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell24)){
                                    BigDecimal bigDecimal24 = new BigDecimal(cell24);
                                    gory3.setUnitPrice(bigDecimal24);
                                }
                            }
                            if(row.getCell(begRow+18) != null){
                                String cell25 = row.getCell(begRow+18).getStringCellValue();
                                if(!StringUtils.isNullOrEmpty(cell25)){
                                    BigDecimal bigDecimal25 = new BigDecimal(cell25);
                                    gory3.setNumber(bigDecimal25);
                                }
                            }
                            if (row.getCell(begRow+19) != null) {
                                gory3.setUnit(row.getCell(begRow+19).getStringCellValue());
                            }

                            if( !StringUtil.isEmpty(gory.getOilVarieties()) || !StringUtil.isEmpty(gory.getOilClassification())
                                    || gory.getNumber() != null || gory.getUnitPrice() != null){
                                list1.add(gory);
                            }
                            if( !StringUtil.isEmpty(gory1.getOilVarieties()) || !StringUtil.isEmpty(gory1.getOilClassification())
                                    || gory1.getNumber() != null || gory1.getUnitPrice() != null){
                                list1.add(gory1);
                            }
                            if( !StringUtil.isEmpty(gory2.getOilVarieties()) || !StringUtil.isEmpty(gory2.getOilClassification())
                                    || gory2.getNumber() != null || gory2.getUnitPrice() != null){
                                list1.add(gory2);
                            }
                            if( !StringUtil.isEmpty(gory3.getOilVarieties()) || !StringUtil.isEmpty(gory3.getOilClassification())
                                    || gory3.getNumber() != null || gory3.getUnitPrice() != null){
                                list1.add(gory3);
                            }

                            BigDecimal ttlPrice=BigDecimal.ZERO;

                            if(gory.getNumber() != null && gory.getUnitPrice() != null){
                                BigDecimal p = gory.getNumber().multiply(gory.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP) ;
                                ttlPrice=ttlPrice.add(p);

                            }
                            if(gory1.getNumber() != null && gory1.getUnitPrice() != null){
                                BigDecimal p = gory1.getNumber().multiply(gory1.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP)   ;
                                ttlPrice=ttlPrice.add(p);

                            }
                            if(gory2.getNumber() != null && gory2.getUnitPrice() != null){
                                BigDecimal p = gory2.getNumber().multiply(gory2.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP)   ;
                                ttlPrice=ttlPrice.add(p);

                            }
                            if(gory3.getNumber() != null && gory3.getUnitPrice() != null){
                                BigDecimal p = gory3.getNumber().multiply(gory3.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP)   ;
                                ttlPrice=ttlPrice.add(p);

                            }
                            bt.setTotalPrice(ttlPrice);
                            RIgnitionCombine rIgnitionCombine = new RIgnitionCombine();
                            rIgnitionCombine.setrIgnition(bt);
                            rIgnitionCombine.setList(list1);
                            list.add(rIgnitionCombine);
                        }
                    }catch(Exception e){
                        log.error("import error", e);
                        e.printStackTrace();
                    }

                }
            }
            String message = rIgnitionService.buyImportRIgnition(list,user,oilType);
            res.setDatas(message);
            res.setMessage("OK");
            flag= true;
        }  catch (Exception e ) {
            flag= false;
            log.error("importRIgnition error", e);
            e.printStackTrace();
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setCode(Constants.EXCEPTION_CODE);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }
    /**
     * 买船供订单导入
     */
    @WithoutAccess
    @RequestMapping(value = "/buyImportRSupplyData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<String> importsRSupplyData(HttpServletRequest request, CurrentUser user){
        ResultDatas<String> res = new ResultDatas<>();
        try {

            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
            MultipartFile file = req.getFile("file");
            //String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            Workbook wb = new HSSFWorkbook(inputStream);
            Sheet sheet = wb.getSheetAt(0);
            List<RSupplyCombine> list = new ArrayList<>();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //取出所有公司名称用来获取对应的公司id
            List<String> names = new ArrayList<String>();
            for (Row row : sheet) {
                if(row.getRowNum()>=1){
                    if(row.getCell(5) != null){
                        names.add(row.getCell(5).getStringCellValue());
                    }
                    if(row.getCell(6) != null){
                        names.add(row.getCell(6).getStringCellValue());
                    }
                }
            }
            //获取所有公司对象并让名称和id匹配
            Map<String,Long> map = new HashMap<>();
            List<EnterpriseVo> enterpriseVos = enterpriseService.queryEnterpriseVoList(names);
            if(enterpriseVos != null){
                for (EnterpriseVo enterpriseVo:enterpriseVos) {
                    map.put(enterpriseVo.getName(),enterpriseVo.getMemberId());
                }
            }
            for (Row row : sheet) {
                if(row.getRowNum()>=1){
                    try {
                        if(row.getCell(2) == null||"".equals(row.getCell(2).getStringCellValue()) )
                            break;
                        RSupply bt = new RSupply();
                        RSupplyCombine rSupplyCombine = new RSupplyCombine();
                        for (int i = 0; i < row.getLastCellNum(); i++) {
                            if(row.getCell(i)!=null){
                                row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                            }
                        }

                        if(row.getCell(0) != null){
                            bt.setOrderTime(simpleDateFormat.parse(row.getCell(0).getStringCellValue()));
                        }
                        if(row.getCell(1) != null){
                            bt.setOrderCode(row.getCell(1).getStringCellValue());
                        }
                        if(row.getCell(2) != null){
                            bt.setFuelChargingTime(simpleDateFormat.parse(row.getCell(2).getStringCellValue()));
                        }
                        if(row.getCell(3) != null){
                            bt.setFuelChargingCode(row.getCell(3).getStringCellValue());
                        }
                        if(row.getCell(4) != null){
                            bt.setSellCompanyName(row.getCell(4).getStringCellValue());
                            bt.setSellCompanyId(map.get(row.getCell(4).getStringCellValue()));
                        }
                        if(row.getCell(5) != null){
                            bt.setBuyCompanyName(row.getCell(5).getStringCellValue());
                            bt.setBuyCompanyId(map.get(row.getCell(5).getStringCellValue()));
                        }
                        if(row.getCell(6) != null){
                            bt.setVesselName(row.getCell(6).getStringCellValue());
                        }
                        if(row.getCell(7) != null){
                            bt.setPort(row.getCell(7).getStringCellValue());
                        }
                        if(row.getCell(8) != null){
                            String cell8 = row.getCell(8).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell8)){
                                BigDecimal bigDecimal1 = new BigDecimal(cell8);
                                bt.setTotalPrice(bigDecimal1);
                            }
                        }
                        rSupplyCombine.setrSupply(bt);
                        List<Gory> list1 = new ArrayList<>();
                        Gory gory = new Gory();
                        if(row.getCell(9) != null){
                            gory.setOilClassification(row.getCell(9).getStringCellValue());
                        }
                        if(row.getCell(10) != null){
                            gory.setOilVarieties(row.getCell(10).getStringCellValue());
                        }
                        if(row.getCell(11) != null){
                            String cell11 = row.getCell(11).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell11)){
                                BigDecimal bigDecimal11 = new BigDecimal(cell11);
                                gory.setUnitPrice(bigDecimal11);
                            }
                        }
                        if(row.getCell(12) != null){
                            String cell12 = row.getCell(12).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell12)){
                                BigDecimal bigDecimal12 = new BigDecimal(cell12);
                                gory.setNumber(bigDecimal12);
                            }
                        }
                        if(row.getCell(13) != null){
                            String price = row.getCell(13).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(price)){
                                BigDecimal bigDecimal13 = new BigDecimal(price);
                                gory.setClassPrice(bigDecimal13);
                            }
                        }
                        Gory gory1 = new Gory();
                        if(row.getCell(14) != null){
                            gory1.setOilClassification(row.getCell(14).getStringCellValue());
                        }
                        if(row.getCell(15) != null){
                            gory1.setOilVarieties(row.getCell(15).getStringCellValue());
                        }
                        if(row.getCell(16) != null){
                            String cell16 = row.getCell(16).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell16)){
                                BigDecimal bigDecimal16 = new BigDecimal(cell16);
                                gory1.setUnitPrice(bigDecimal16);
                            }
                        }
                        if(row.getCell(17) != null){
                            String cell17 = row.getCell(17).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell17)){
                                BigDecimal bigDecimal7 = new BigDecimal(cell17);
                                gory1.setNumber(bigDecimal7);
                            }
                        }
                        if(row.getCell(18) != null){
                            String price = row.getCell(18).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(price)){
                                BigDecimal bigDecimal8 = new BigDecimal(price);
                                gory1.setClassPrice(bigDecimal8);
                            }
                        }
                        Gory gory2 = new Gory();
                        if(row.getCell(19) != null){
                            gory2.setOilClassification(row.getCell(19).getStringCellValue());
                        }
                        if(row.getCell(20) != null){
                            gory2.setOilVarieties(row.getCell(20).getStringCellValue());
                        }
                        if(row.getCell(21) != null){
                            String cell21 = row.getCell(21).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell21)){
                                BigDecimal bigDecimal21 = new BigDecimal(cell21);
                                gory2.setUnitPrice(bigDecimal21);
                            }
                        }
                        if(row.getCell(22) != null){
                            String cell22 = row.getCell(22).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell22)){
                                BigDecimal bigDecimal22 = new BigDecimal(cell22);
                                gory2.setNumber(bigDecimal22);
                            }
                        }
                        if(row.getCell(23) != null){
                            String price = row.getCell(23).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(price)){
                                BigDecimal bigDecimal23 = new BigDecimal(price);
                                gory2.setClassPrice(bigDecimal23);
                            }
                        }
                        Gory gory3 = new Gory();
                        if(row.getCell(24) != null){
                            gory3.setOilClassification(row.getCell(24).getStringCellValue());
                        }
                        if(row.getCell(25) != null){
                            gory3.setOilVarieties(row.getCell(25).getStringCellValue());
                        }
                        if(row.getCell(26) != null){
                            String cell26 = row.getCell(26).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell26)){
                                BigDecimal bigDecimal26 = new BigDecimal(cell26);
                                gory3.setUnitPrice(bigDecimal26);
                            }
                        }
                        if(row.getCell(27) != null){
                            String cell27 = row.getCell(27).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell27)){
                                BigDecimal bigDecimal27 = new BigDecimal(cell27);
                                gory3.setNumber(bigDecimal27);
                            }
                        }
                        if(row.getCell(28) != null){
                            String price = row.getCell(28).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(price)){
                                BigDecimal bigDecimal28 = new BigDecimal(price);
                                gory3.setClassPrice(bigDecimal28);
                            }
                        }
                        if(gory.getClassPrice() != null || !StringUtil.isEmpty(gory.getOilVarieties()) || !StringUtil.isEmpty(gory.getOilClassification())
                                || gory.getNumber() != null || gory.getUnitPrice() != null){
                            list1.add(gory);
                        }
                        if(gory1.getClassPrice() != null || !StringUtil.isEmpty(gory1.getOilVarieties()) || !StringUtil.isEmpty(gory1.getOilClassification())
                                || gory1.getNumber() != null || gory1.getUnitPrice() != null){
                            list1.add(gory1);
                        }
                        if(gory2.getClassPrice() != null || !StringUtil.isEmpty(gory2.getOilVarieties()) || !StringUtil.isEmpty(gory2.getOilClassification())
                                || gory2.getNumber() != null || gory2.getUnitPrice() != null){
                            list1.add(gory2);
                        }
                        if(gory3.getClassPrice() != null || !StringUtil.isEmpty(gory3.getOilVarieties()) || !StringUtil.isEmpty(gory3.getOilClassification())
                                || gory3.getNumber() != null || gory3.getUnitPrice() != null){
                            list1.add(gory3);
                        }
                        rSupplyCombine.setList(list1);
                        list.add(rSupplyCombine);
                    } catch (Exception e) {
                        log.error("import error", e);
                        e.printStackTrace();
                    }
                }
            }
            String message = rSupplyService.buyImportRSupply(list,user);
            res.setDatas(message);
            res.setMessage("OK");
            flag= true;
        }  catch (Exception e ) {
            flag= false;
            log.error("importRSupply error", e);
            e.printStackTrace();
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setCode(Constants.EXCEPTION_CODE);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }
    /**
     * 卖家船供订单导入
     */
    @WithoutAccess
    @RequestMapping(value = "/sellerImportRSupplyData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<String> sellerImportsRSupplyData(HttpServletRequest request, CurrentUser user){
        ResultDatas<String> res = new ResultDatas<>();
        try {

            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
            MultipartFile file = req.getFile("file");
            //String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            Workbook wb = new HSSFWorkbook(inputStream);
            Sheet sheet = wb.getSheetAt(0);
            List<RSupplyCombine> list = new ArrayList<>();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //取出所有公司名称用来获取对应的公司id
            List<String> names = new ArrayList<String>();
            for (Row row : sheet) {
                if(row.getRowNum()>=1){
                    if(row.getCell(4) != null){
                        names.add(row.getCell(4).getStringCellValue());
                    }
                    if(row.getCell(5) != null){
                        names.add(row.getCell(5).getStringCellValue());
                    }
                }
            }
            //获取所有公司对象并让名称和id匹配
            Map<String,Long> map = new HashMap<>();
            List<EnterpriseVo> enterpriseVos = enterpriseService.queryEnterpriseVoList(names);
            if(enterpriseVos != null){
                for (EnterpriseVo enterpriseVo:enterpriseVos) {
                    map.put(enterpriseVo.getName(),enterpriseVo.getMemberId());
                }
            }
            for (Row row : sheet) {
                if(row.getRowNum()>=1){
                    try {
                        if(row.getCell(2) == null||"".equals(row.getCell(2).getStringCellValue()) )
                            break;
                        RSupply bt = new RSupply();
                        RSupplyCombine rSupplyCombine = new RSupplyCombine();
                        for (int i = 0; i < row.getLastCellNum(); i++) {
                            if(row.getCell(i)!=null){
                                row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                            }
                        }

                        if(row.getCell(0) != null){
                            bt.setOrderTime(simpleDateFormat.parse(row.getCell(0).getStringCellValue()));
                        }
                        if(row.getCell(1) != null){
                            bt.setOrderCode(row.getCell(1).getStringCellValue());
                        }
                        if(row.getCell(2) != null){
                            bt.setFuelChargingTime(simpleDateFormat.parse(row.getCell(2).getStringCellValue()));
                        }
                        if(row.getCell(3) != null){
                            bt.setFuelChargingCode(row.getCell(3).getStringCellValue());
                        }
                        if(row.getCell(4) != null){
                            bt.setSellCompanyName(row.getCell(4).getStringCellValue());
                            bt.setSellCompanyId(map.get(row.getCell(4).getStringCellValue()));
                        }
                        if(row.getCell(5) != null){
                            bt.setBuyCompanyName(row.getCell(5).getStringCellValue());
                            bt.setBuyCompanyId(map.get(row.getCell(5).getStringCellValue()));
                        }
                        if(row.getCell(6) != null){
                            bt.setVesselName(row.getCell(6).getStringCellValue());
                        }
                        if(row.getCell(7) != null){
                            bt.setPort(row.getCell(7).getStringCellValue());
                        }
                        if(row.getCell(8) != null){
                            String cell8 = row.getCell(8).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell8)){
                                BigDecimal bigDecimal1 = new BigDecimal(cell8);
                                bt.setTotalPrice(bigDecimal1);
                            }
                        }
                        rSupplyCombine.setrSupply(bt);
                        List<Gory> list1 = new ArrayList<>();
                        Gory gory = new Gory();
                        if(row.getCell(9) != null){
                            gory.setOilVarieties(row.getCell(9).getStringCellValue());
                        }
                        if(row.getCell(10) != null){
                            gory.setOilClassification(row.getCell(10).getStringCellValue());
                        }
                        if(row.getCell(11) != null){
                            String cell11 = row.getCell(11).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell11)){
                                BigDecimal bigDecimal11 = new BigDecimal(cell11);
                                gory.setUnitPrice(bigDecimal11);
                            }
                        }
                        if(row.getCell(12) != null){
                            String cell12 = row.getCell(12).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell12)){
                                BigDecimal bigDecimal12 = new BigDecimal(cell12);
                                gory.setNumber(bigDecimal12);
                            }
                        }
                        if(row.getCell(13) != null){
                            String price = row.getCell(13).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(price)){
                                BigDecimal bigDecimal13 = new BigDecimal(price);
                                gory.setClassPrice(bigDecimal13);
                            }
                        }
                        Gory gory1 = new Gory();
                        if(row.getCell(14) != null){
                            gory1.setOilVarieties(row.getCell(14).getStringCellValue());
                        }
                        if(row.getCell(15) != null){
                            gory1.setOilClassification(row.getCell(15).getStringCellValue());
                        }
                        if(row.getCell(16) != null){
                            String cell16 = row.getCell(16).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell16)){
                                BigDecimal bigDecimal16 = new BigDecimal(cell16);
                                gory1.setUnitPrice(bigDecimal16);
                            }
                        }
                        if(row.getCell(17) != null){
                            String cell17 = row.getCell(17).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell17)){
                                BigDecimal bigDecimal7 = new BigDecimal(cell17);
                                gory1.setNumber(bigDecimal7);
                            }
                        }
                        if(row.getCell(18) != null){
                            String price = row.getCell(18).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(price)){
                                BigDecimal bigDecimal8 = new BigDecimal(price);
                                gory1.setClassPrice(bigDecimal8);
                            }
                        }
                        Gory gory2 = new Gory();
                        if(row.getCell(19) != null){
                            gory2.setOilVarieties(row.getCell(19).getStringCellValue());
                        }
                        if(row.getCell(20) != null){
                            gory2.setOilClassification(row.getCell(20).getStringCellValue());
                        }
                        if(row.getCell(21) != null){
                            String cell21 = row.getCell(21).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell21)){
                                BigDecimal bigDecimal21 = new BigDecimal(cell21);
                                gory2.setUnitPrice(bigDecimal21);
                            }
                        }
                        if(row.getCell(22) != null){
                            String cell22 = row.getCell(22).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell22)){
                                BigDecimal bigDecimal22 = new BigDecimal(cell22);
                                gory2.setNumber(bigDecimal22);
                            }
                        }
                        if(row.getCell(23) != null){
                            String price = row.getCell(23).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(price)){
                                BigDecimal bigDecimal23 = new BigDecimal(price);
                                gory2.setClassPrice(bigDecimal23);
                            }
                        }
                        Gory gory3 = new Gory();
                        if(row.getCell(24) != null){
                            gory3.setOilVarieties(row.getCell(24).getStringCellValue());
                        }
                        if(row.getCell(25) != null){
                            gory3.setOilClassification(row.getCell(25).getStringCellValue());
                        }
                        if(row.getCell(26) != null){
                            String cell26 = row.getCell(26).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell26)){
                                BigDecimal bigDecimal26 = new BigDecimal(cell26);
                                gory3.setUnitPrice(bigDecimal26);
                            }
                        }
                        if(row.getCell(27) != null){
                            String cell27 = row.getCell(27).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell27)){
                                BigDecimal bigDecimal27 = new BigDecimal(cell27);
                                gory3.setNumber(bigDecimal27);
                            }
                        }
                        if(row.getCell(28) != null){
                            String price = row.getCell(28).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(price)){
                                BigDecimal bigDecimal28 = new BigDecimal(price);
                                gory3.setClassPrice(bigDecimal28);
                            }
                        }
                        if(gory.getClassPrice() != null || !StringUtil.isEmpty(gory.getOilVarieties()) || !StringUtil.isEmpty(gory.getOilClassification())
                                || gory.getNumber() != null || gory.getUnitPrice() != null){
                            list1.add(gory);
                        }
                        if(gory1.getClassPrice() != null || !StringUtil.isEmpty(gory1.getOilVarieties()) || !StringUtil.isEmpty(gory1.getOilClassification())
                                || gory1.getNumber() != null || gory1.getUnitPrice() != null){
                            list1.add(gory1);
                        }
                        if(gory2.getClassPrice() != null || !StringUtil.isEmpty(gory2.getOilVarieties()) || !StringUtil.isEmpty(gory2.getOilClassification())
                                || gory2.getNumber() != null || gory2.getUnitPrice() != null){
                            list1.add(gory2);
                        }
                        if(gory3.getClassPrice() != null || !StringUtil.isEmpty(gory3.getOilVarieties()) || !StringUtil.isEmpty(gory3.getOilClassification())
                                || gory3.getNumber() != null || gory3.getUnitPrice() != null){
                            list1.add(gory3);
                        }
                        rSupplyCombine.setList(list1);
                        list.add(rSupplyCombine);
                    } catch (Exception e) {
                        log.error("import error", e);
                        e.printStackTrace();
                    }
                }
            }
            String message = rSupplyService.sellerImportRSupply(list,user);
            res.setDatas(message);
            res.setMessage("OK");
            flag= true;
        }  catch (Exception e ) {
            flag= false;
            log.error("importRSupply error", e);
            e.printStackTrace();
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setCode(Constants.EXCEPTION_CODE);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }
    /**
     * om船供订单导入
     */
    @WithoutAccess
    @RequestMapping(value = "/omImportRSupplyData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<String> omImportsRSupplyData(HttpServletRequest request, CurrentUser user){
        ResultDatas<String> res = new ResultDatas<>();
        try {

            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
            MultipartFile file = req.getFile("file");
            //String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            Workbook wb = new HSSFWorkbook(inputStream);
            Sheet sheet = wb.getSheetAt(0);
            List<RSupplyCombine> list = new ArrayList<>();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //取出所有公司名称用来获取对应的公司id
            List<String> names = new ArrayList<String>();
            for (Row row : sheet) {
                if(row.getRowNum()>=1){
                    if(row.getCell(4) != null){
                        names.add(row.getCell(4).getStringCellValue());
                    }
                    if(row.getCell(5) != null){
                        names.add(row.getCell(5).getStringCellValue());
                    }
                }
            }
            //获取所有公司对象并让名称和id匹配
            Map<String,Long> map = new HashMap<>();
            List<EnterpriseVo> enterpriseVos = enterpriseService.queryEnterpriseVoList(names);
            if(enterpriseVos != null){
                for (EnterpriseVo enterpriseVo:enterpriseVos) {
                    map.put(enterpriseVo.getName(),enterpriseVo.getMemberId());
                }
            }
            for (Row row : sheet) {
                if(row.getRowNum()>=1){
                    try {
                        if(row.getCell(2) == null||"".equals(row.getCell(2).getStringCellValue()) )
                            break;
                        RSupply bt = new RSupply();
                        RSupplyCombine rSupplyCombine = new RSupplyCombine();
                        for (int i = 0; i < row.getLastCellNum(); i++) {
                            if(row.getCell(i)!=null){
                                row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                            }
                        }

                        if(row.getCell(0) != null){
                            bt.setOrderTime(simpleDateFormat.parse(row.getCell(0).getStringCellValue()));
                        }
                        if(row.getCell(1) != null){
                            bt.setOrderCode(row.getCell(1).getStringCellValue());
                        }
                        if(row.getCell(2) != null){
                            bt.setFuelChargingTime(simpleDateFormat.parse(row.getCell(2).getStringCellValue()));
                        }
                        if(row.getCell(3) != null){
                            bt.setFuelChargingCode(row.getCell(3).getStringCellValue());
                        }
                        if(row.getCell(4) != null){
                            bt.setSellCompanyName(row.getCell(4).getStringCellValue());
                            bt.setSellCompanyId(map.get(row.getCell(4).getStringCellValue()));
                        }
                        if(row.getCell(5) != null){
                            bt.setBuyCompanyName(row.getCell(5).getStringCellValue());
                            bt.setBuyCompanyId(map.get(row.getCell(5).getStringCellValue()));
                        }
                        if(row.getCell(6) != null){
                            bt.setVesselName(row.getCell(6).getStringCellValue());
                        }
                        if(row.getCell(7) != null){
                            bt.setPort(row.getCell(7).getStringCellValue());
                        }
                        if(row.getCell(8) != null){
                            String cell8 = row.getCell(8).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell8)){
                                BigDecimal bigDecimal1 = new BigDecimal(cell8);
                                bt.setTotalPrice(bigDecimal1);
                            }
                        }
                        rSupplyCombine.setrSupply(bt);
                        List<Gory> list1 = new ArrayList<>();
                        Gory gory = new Gory();
                        if(row.getCell(9) != null){
                            gory.setOilVarieties(row.getCell(9).getStringCellValue());
                        }
                        if(row.getCell(10) != null){
                            gory.setOilClassification(row.getCell(10).getStringCellValue());
                        }
                        if(row.getCell(11) != null){
                            String cell11 = row.getCell(11).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell11)){
                                BigDecimal bigDecimal11 = new BigDecimal(cell11);
                                gory.setUnitPrice(bigDecimal11);
                            }
                        }
                        if(row.getCell(12) != null){
                            String cell12 = row.getCell(12).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell12)){
                                BigDecimal bigDecimal12 = new BigDecimal(cell12);
                                gory.setNumber(bigDecimal12);
                            }
                        }
                        if(row.getCell(13) != null){
                            String price = row.getCell(13).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(price)){
                                BigDecimal bigDecimal13 = new BigDecimal(price);
                                gory.setClassPrice(bigDecimal13);
                            }
                        }
                        Gory gory1 = new Gory();
                        if(row.getCell(14) != null){
                            gory1.setOilVarieties(row.getCell(14).getStringCellValue());
                        }
                        if(row.getCell(15) != null){
                            gory1.setOilClassification(row.getCell(15).getStringCellValue());
                        }
                        if(row.getCell(16) != null){
                            String cell16 = row.getCell(16).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell16)){
                                BigDecimal bigDecimal16 = new BigDecimal(cell16);
                                gory1.setUnitPrice(bigDecimal16);
                            }
                        }
                        if(row.getCell(17) != null){
                            String cell17 = row.getCell(17).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell17)){
                                BigDecimal bigDecimal7 = new BigDecimal(cell17);
                                gory1.setNumber(bigDecimal7);
                            }
                        }
                        if(row.getCell(18) != null){
                            String price = row.getCell(18).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(price)){
                                BigDecimal bigDecimal8 = new BigDecimal(price);
                                gory1.setClassPrice(bigDecimal8);
                            }
                        }
                        Gory gory2 = new Gory();
                        if(row.getCell(19) != null){
                            gory2.setOilVarieties(row.getCell(19).getStringCellValue());
                        }
                        if(row.getCell(20) != null){
                            gory2.setOilClassification(row.getCell(20).getStringCellValue());
                        }
                        if(row.getCell(21) != null){
                            String cell21 = row.getCell(21).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell21)){
                                BigDecimal bigDecimal21 = new BigDecimal(cell21);
                                gory2.setUnitPrice(bigDecimal21);
                            }
                        }
                        if(row.getCell(22) != null){
                            String cell22 = row.getCell(22).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell22)){
                                BigDecimal bigDecimal22 = new BigDecimal(cell22);
                                gory2.setNumber(bigDecimal22);
                            }
                        }
                        if(row.getCell(23) != null){
                            String price = row.getCell(23).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(price)){
                                BigDecimal bigDecimal23 = new BigDecimal(price);
                                gory2.setClassPrice(bigDecimal23);
                            }
                        }
                        Gory gory3 = new Gory();
                        if(row.getCell(24) != null){
                            gory3.setOilVarieties(row.getCell(24).getStringCellValue());
                        }
                        if(row.getCell(25) != null){
                            gory3.setOilClassification(row.getCell(25).getStringCellValue());
                        }
                        if(row.getCell(26) != null){
                            String cell26 = row.getCell(26).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell26)){
                                BigDecimal bigDecimal26 = new BigDecimal(cell26);
                                gory3.setUnitPrice(bigDecimal26);
                            }
                        }
                        if(row.getCell(27) != null){
                            String cell27 = row.getCell(27).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(cell27)){
                                BigDecimal bigDecimal27 = new BigDecimal(cell27);
                                gory3.setNumber(bigDecimal27);
                            }
                        }
                        if(row.getCell(28) != null){
                            String price = row.getCell(28).getStringCellValue();
                            if(!StringUtils.isNullOrEmpty(price)){
                                BigDecimal bigDecimal28 = new BigDecimal(price);
                                gory3.setClassPrice(bigDecimal28);
                            }
                        }
                        if(gory.getClassPrice() != null || !StringUtil.isEmpty(gory.getOilVarieties()) || !StringUtil.isEmpty(gory.getOilClassification())
                                || gory.getNumber() != null || gory.getUnitPrice() != null){
                            list1.add(gory);
                        }
                        if(gory1.getClassPrice() != null || !StringUtil.isEmpty(gory1.getOilVarieties()) || !StringUtil.isEmpty(gory1.getOilClassification())
                                || gory1.getNumber() != null || gory1.getUnitPrice() != null){
                            list1.add(gory1);
                        }
                        if(gory2.getClassPrice() != null || !StringUtil.isEmpty(gory2.getOilVarieties()) || !StringUtil.isEmpty(gory2.getOilClassification())
                                || gory2.getNumber() != null || gory2.getUnitPrice() != null){
                            list1.add(gory2);
                        }
                        if(gory3.getClassPrice() != null || !StringUtil.isEmpty(gory3.getOilVarieties()) || !StringUtil.isEmpty(gory3.getOilClassification())
                                || gory3.getNumber() != null || gory3.getUnitPrice() != null){
                            list1.add(gory3);
                        }
                        rSupplyCombine.setList(list1);
                        list.add(rSupplyCombine);
                    } catch (Exception e) {
                        log.error("import error", e);
                        e.printStackTrace();
                    }
                }
            }
            String message = rSupplyService.omImportRSupply(list,user);
            res.setDatas(message);
            res.setMessage("OK");
            flag= true;
        }  catch (Exception e ) {
            flag= false;
            log.error("importRSupply error", e);
            e.printStackTrace();
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
    /**
     * 上传结果
     * @param request
     * @return
     */
    @RightAccess(2007)
    @WithoutAccess
    @RequestMapping(value = "/import/returnFlag.json", method = RequestMethod.POST )
    @ResponseBody
    public ResultDatas<String> returnFlag(HttpServletRequest request) {
        ResultDatas<String> res = new ResultDatas<>();
        try {
            if (!flag){
                throw new Exception("import error");
            }
            res.setDatas(mess);
        } catch (Exception e) {
            log.error("returnFlag error", e);
            e.printStackTrace();
            res.setStatus(9999);
            res.setCode("9999");
            res.setMessage("returnFlag error");
        }
        return res;
    }
}
