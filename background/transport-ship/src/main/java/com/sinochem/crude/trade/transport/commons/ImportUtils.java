package com.sinochem.crude.trade.transport.commons;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.model.ExportVO;
import com.sinochem.crude.trade.transport.model.res.LoadPortImport;
import com.sinochem.crude.trade.transport.model.res.ShipPlateImport;
import com.sinochem.crude.trade.transport.model.res.SysShipImport;
import com.sinochem.crude.trade.transport.model.res.TransitImport;
import com.sinochem.crude.trade.transport.model.res.UnloadPortImport;
import com.sinochem.crude.trade.transport.model.res.VoyageStartImport;

import jxl.Sheet;
import jxl.Workbook;

public class ImportUtils {
	private static Logger log = LoggerFactory.getLogger(ImportUtils.class);
	
	 /**
     * 查询在途信息电子表格中所有的数据--只支持 excel 2003 (*.xls)
     * @param file 文件完整路径
     * @return
     */
    public static List<TransitImport> getTransit(String file){
        List<TransitImport> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)  rwb.getSheet("Test Shee 1")
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            int rr=rs.getRows()-1;//得到所有的行
            System.out.println("########读取到数据："+clos+"列  ;"+rr+"条");
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                	//默认最左边编号也算一列 所以这里得j++
                	String dateNow=rs.getCell(j++, i).getContents();
                    String timeNow=rs.getCell(j++, i).getContents();
                    String position=rs.getCell(j++, i).getContents();
                    String speedH=rs.getCell(j++, i).getContents();
                    String speedAll=rs.getCell(j++, i).getContents();
                    String rpm=rs.getCell(j++, i).getContents();
                    String sea=rs.getCell(j++, i).getContents();
                    String unloadEta=rs.getCell(j++, i).getContents();
                    String water=rs.getCell(j++, i).getContents();
                    String sulfide=rs.getCell(j++, i).getContents();
                    String remark=rs.getCell(j++, i).getContents();
                    Date date ;
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					try {
						if (!StringUtils.isNullOrEmpty(dateNow)){
							String[] split = dateNow.split("/");
							if (split.length>0){
								String str = split[0];
								if (str.length() == 4){
									format = new SimpleDateFormat("yyyy/MM/dd");
								}
							}
						}
						date = format.parse(dateNow);
					} catch (Exception e) {
						log.error("日期格式不正确",e);
						throw new TransportException(TransportException.TYPE.ITSH551);
					}
                    System.out.println("########读取到数据："+" dateNow:"+dateNow+"timeNow:"+timeNow+" position:"+position
                    		+" speedH:"+speedH+" speedAll:"+speedAll+" rpm:"+rpm+" unloadEta:"+unloadEta
                    		+" sea:"+sea+" water:"+water+" sulfide:"+sulfide+" remark:"+remark);
                    list.add(new TransitImport(date, timeNow, position, speedH, speedAll, rpm, unloadEta, sea, water, sulfide, remark));
                }
            }
        } catch (Exception e) {
            list.add(new TransitImport(null, null, null, null, null, null, null, null, null, null, null));
        	log.error(e);
        } 
        return list;
    }
    
    /**
     * 查询航次开始信息电子表格中所有的数据--只支持 excel 2003 (*.xls)
     * @param file 文件完整路径
     * @return
     */
    public static List<VoyageStartImport> getVoyageStart(String file){
    	List<VoyageStartImport> list=new ArrayList<>();
    	try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)  rwb.getSheet("Test Shee 1")
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            int rr=rs.getRows()-1;//得到所有的行
            System.out.println("########读取到数据："+clos+"列  ;"+rr+"条");
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                	//默认最左边编号也算一列 所以这里得j++
                	String oilType=rs.getCell(j++, i).getContents();
                    String quantityCask=rs.getCell(j++, i).getContents();
                    String quantity=rs.getCell(j++, i).getContents();
                    String api=rs.getCell(j++, i).getContents();
                    String loadTemp=rs.getCell(j++, i).getContents();
                    String loadDraft=rs.getCell(j++, i).getContents();
                    String unloadDraft=rs.getCell(j++, i).getContents();
                    BigDecimal quantityCasks = new BigDecimal(quantityCask);
                    BigDecimal quantitys = new BigDecimal(quantity);
                    BigDecimal apis = new BigDecimal(api);
                    BigDecimal loadTemps = new BigDecimal(loadTemp);
                    BigDecimal loadDrafts = new BigDecimal(loadDraft);
                    BigDecimal unloadDrafts = new BigDecimal(unloadDraft);
                    System.out.println("########读取到数据："+" oilType:"+oilType+"quantityCask:"+quantityCask+" quantity:"+quantity
                    		+" api:"+api+" loadTemp:"+loadTemp+" loadDraft:"+loadDraft+" unloadDraft:"+unloadDraft);
                    list.add(new VoyageStartImport(oilType, quantityCasks, quantitys, apis, loadTemps, loadDrafts, unloadDrafts));
                }
            }
        } catch (Exception e) {
        	log.error(e);
        } 
    	return list;
    }
    
    /**
     * 查询装港信息电子表格中所有的数据--只支持 excel 2003 (*.xls)
     * @param file 文件完整路径
     * @return
     */
    public static List<LoadPortImport> getloadPort(String file){
    	List<LoadPortImport> list=new ArrayList<>();
    	try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)  rwb.getSheet("Test Shee 1")
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            int rr=rs.getRows()-1;//得到所有的行
            System.out.println("########读取到数据："+clos+"列  ;"+rr+"条");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                	//默认最左边编号也算一列 所以这里得j++
                    
                    Date eta=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date exBerth=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date norDate=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date waterDate=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date atripDate=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date berthDate=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date acStart=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date acFinish=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date remTubeDate=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date exLeave=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date acLeave=dateFormat.parse(rs.getCell(j++, i).getContents());
                    System.out.println("########读取到数据："+" eta:"+eta+" exBerth:"+exBerth+" norDate:"+norDate+" waterDate:"+waterDate
                    		+" atripDate:"+atripDate+" berthDate:"+berthDate+" acStart:"+acStart+" acFinish:"+acFinish+" remTubeDate:"+remTubeDate+" exLeave:"+exLeave+" acLeave:"+acLeave);
                    list.add(new LoadPortImport(eta, exBerth, norDate, waterDate,atripDate,berthDate,acStart,acFinish,remTubeDate,exLeave,acLeave));
                }
            }
        } catch (Exception e) {
        	log.error(e);
        } 
    	return list;
    }
    
    /**
     * 查询卸港信息电子表格中所有的数据--只支持 excel 2003 (*.xls)
     * @param file 文件完整路径
     * @return
     */
    public static List<UnloadPortImport> getUnLoadPort(String file){
    	List<UnloadPortImport> list=new ArrayList<>();
    	try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)  rwb.getSheet("Test Shee 1")
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            int rr=rs.getRows()-1;//得到所有的行
            System.out.println("########读取到数据："+clos+"列  ;"+rr+"条");
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                	//默认最左边编号也算一列 所以这里得j++

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                	
                    Date eta=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date exBerth=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date norDate=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date waterDate=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date atripDate=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date berthDate=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date acStart=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date acFinish=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date remTubeDate=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date exLeave=dateFormat.parse(rs.getCell(j++, i).getContents());
                    Date acLeave=dateFormat.parse(rs.getCell(j++, i).getContents());
                    
                    System.out.println("########读取到数据："+" eta:"+eta+" exBerth:"+exBerth+" norDate:"+norDate+" waterDate:"+waterDate
                    		+" atripDate:"+atripDate+" berthDate:"+berthDate+" acStart:"+acStart+" acFinish:"+acFinish+" remTubeDate:"+remTubeDate+" exLeave:"+exLeave+" acLeave:"+acLeave);
                    list.add(new UnloadPortImport( eta, exBerth, norDate, waterDate,atripDate,berthDate,acStart,acFinish,remTubeDate,exLeave,acLeave));
                }
            }
        } catch (Exception e) {
        	log.error(e);
        } 
    	return list;
    }
    
    
    /**
     * 查询船盘信息电子表格中所有的数据--只支持 excel 2003 (*.xls)
     * @param file 文件完整路径
     * @return
     */
    public static List<ShipPlateImport> getShipPlate(String file){
    	List<ShipPlateImport> list=new ArrayList<>();
    	try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)  rwb.getSheet("Test Shee 1")
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            int rr=rs.getRows()-1;//得到所有的行
            System.out.println("########读取到数据："+clos+"列  ;"+rr+"条");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                	//默认最左边编号也算一列 所以这里得j++
                	String name=rs.getCell(j++, i).getContents();	//船名
                	String position=rs.getCell(j++, i).getContents();	//船位
                	
                	//获取open
                	Date open=null;
                	String openStr = rs.getCell(j++, i).getContents();
                	if(!openStr.equals("") && openStr!=null){
                		open=dateFormat.parse(openStr);
                	}
                	
                	String eta=rs.getCell(j++, i).getContents();	//eta
                	String etaCabinda=rs.getCell(j++, i).getContents();	//etaCabinda
                	String shipOwner=rs.getCell(j++, i).getContents();	//船东
                	
                	//时效
                	Integer period=null;
                	String periodStr=rs.getCell(j++, i).getContents();
                	if(!periodStr.equals("") && periodStr!=null){
                		period=Integer.valueOf(periodStr);
                	}
                	
                    System.out.println("########读取到数据："+" name:"+name+" position:"+position+" open:"+open+
                    		" eta:"+eta+" etaCabinda:"+etaCabinda+" shipOwner:"+shipOwner+" period:"+period);
                    
                    list.add(new ShipPlateImport(name, shipOwner, position, eta, etaCabinda, open, period));
                }
            }
        } catch (Exception e) {
        	log.error(e);
        } 
    	return list;
    }
    
    
    /**
     * 查询船舶信息电子表格中所有的数据--只支持 excel 2003 (*.xls)
     * @param file 文件完整路径
     * @return
     */
    public static List<SysShipImport> getSysShip(String file){
    	
    	List<SysShipImport> list=new ArrayList<>();
    	
    	try {
    		Workbook rwb=Workbook.getWorkbook(new File(file));
    		Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)  rwb.getSheet("Test Shee 1")
    		int clos=rs.getColumns();//得到所有的列
    		int rows=rs.getRows();//得到所有的行
    		int rr=rs.getRows()-1;//得到所有的行
    		System.out.println("########读取到数据："+clos+"列  ;"+rr+"条");

    		for (int i = 1; i < rows; i++) {
    			for (int j = 0; j < clos; j++) {
    				//第一个是列数，第二个是行数
    				//默认最左边编号也算一列 所以这里得j++
    				String name=rs.getCell(j++, i).getContents();	//船名
    				String imo=rs.getCell(j++, i).getContents();	//船舶代码
    				String mmsi=rs.getCell(j++, i).getContents();	//船舶MMSI
    				String completeDate=rs.getCell(j++, i).getContents();	//建成年份
    				String useType=rs.getCell(j++, i).getContents();	//船舶用途类型
    				String type=rs.getCell(j++, i).getContents();	//船型
    				
    				BigDecimal capacity=null;
    				String capacityStr=rs.getCell(j++, i).getContents();	//载重容积
    				if(!capacityStr.equals("") && capacityStr!=null){
    					capacity=new BigDecimal(capacityStr);
    				}
    				
    				BigDecimal loadQuantity=null;
    				String loadQuantityStr=rs.getCell(j++, i).getContents();	//载重吨 
    				if(!loadQuantityStr.equals("") && loadQuantityStr!=null){
    					loadQuantity=new BigDecimal(loadQuantityStr);
    				}
    				
    				BigDecimal length=null;
    				String lengthStr=rs.getCell(j++, i).getContents();	//船长
    				if(!lengthStr.equals("") && lengthStr!=null){
    					length=new BigDecimal(lengthStr);
    				}
    				
    				BigDecimal wide=null;
    				String wideStr=rs.getCell(j++, i).getContents();	//船宽
    				if(!wideStr.equals("") && wideStr!=null){
    					wide=new BigDecimal(wideStr);
    				}
    				
    				BigDecimal draft=null;
    				String draftStr=rs.getCell(j++, i).getContents();	//吃水米
    				if(!draftStr.equals("") && draftStr!=null){
    					draft=new BigDecimal(draftStr);
    				}

    				String hull=rs.getCell(j++, i).getContents();	//船体
    				String pennant=rs.getCell(j++, i).getContents();	//国籍
    				
    				System.out.println("########读取到数据："+" name:"+name+" imo:"+imo+" mmsi:"+mmsi+
    						" completeDate:"+completeDate+" useType:"+useType+" type:"+type+" capacity:"+capacity+
    						" loadQuantity:"+loadQuantity+" length:"+length+" wide:"+wide+" draft:"+draft+" hull:"+hull+" pennant:"+pennant);
    				
    				list.add(new SysShipImport(imo, mmsi, useType, name, type, completeDate, hull, length, wide, draft, loadQuantity, capacity, pennant));
    			}
    		}
    	} catch (Exception e) {
    		log.error(e);
    	} 
    	return list;
    }
    
    
    /**
     * 模板信息
     * @param 
     * @return
     */
    public static void template(ExportVO vo,List<String> headList,List<List<String>> contentList){
    	if("1".equals(vo.getType())){
    		transitHeadline(headList);
    		transitTemplate(contentList);
    	}else if ("2".equals(vo.getType())){
    		voyageStartHeadline(headList);
    		voyageStartTemplate(contentList);
		}else if ("3".equals(vo.getType())){
			loadPortHeadline(headList);
			loadPortTemplate(contentList);
		}else if ("4".equals(vo.getType())){
			unloadPortHeadline(headList);
			unloadPortTemplate(contentList);
		}else if("5".equals(vo.getType())){
			shipPlateHeadlineTemplae(headList);
			shipPlateContentTemplate(contentList);
		}
    }

    /**
     * 在途信息标题
     * @param 
     * @return
     */
    public static void transitHeadline(List<String> headList){
    	//标题
  		headList.add("日期（yyyy/MM/dd）");
  		headList.add("时间（hh:mm）");
  		headList.add("位置");
  		headList.add("平均速度(24H(数字))");
  		headList.add("平均速度（全程(数字)）");
  		headList.add("RPM(转/分(数字))");
  		headList.add("海况");
  		headList.add("卸港ETA（卸港/ETA(yyyy-MM-dd hh:mm);卸港/ETA(yyyy-MM-dd hh:mm);）");
  		headList.add("明水(桶 )");
  		headList.add("气相硫化氢");
  		headList.add("备注");
	}
    
    /**
     * 在途信息模板数据
     * @param 
     * @return
     */
    public static void transitTemplate(List<List<String>> contentList){
    	List<String> list2 = new ArrayList<>();
		list2.add("2017/01/01");
		list2.add("10:30");
		list2.add("这是位置");
		list2.add("1.00");
		list2.add("1.00");
		list2.add("1.00");
		list2.add("这是海况");
		list2.add("卸港1/2017-01-01 01:10;卸港2/2017-03-01 01:10;");
		list2.add("1000");
		list2.add("这是气相硫化氢");
		list2.add("这是备注");
		contentList.add(list2);
	}
    
    
    /**
     * 航次开始标题
     * @param 
     * @return
     */
    public static void voyageStartHeadline(List<String> headList){
    	//标题
		headList.add("配载计划油种");
		headList.add("配载计划装量(桶)");
		headList.add("配载计划装量（吨）");
		headList.add("配载计划API");
		headList.add("配载计划装货温度(℃)");
		headList.add("配载计划装港吃水(米)");
		headList.add("配载计划卸港吃水(米)");
		//内容
    }
    
    /**
     * 航次开始模板数据
     * @param 
     * @return
     */
    public static void voyageStartTemplate(List<List<String>> contentList){
    	//内容
		List<String> list2 = new ArrayList<>();
		list2.add("NAPO CRUDE");
		list2.add("1.00");
		list2.add("1.000");
		list2.add("1.00");
		list2.add("1.00");
		list2.add("1.00");
		list2.add("1.00");
		contentList.add(list2);	
    }
    

    /**
     * 装港标题(模板)
     * @param 
     * @return
     */
    public static void loadPortHeadline(List<String> headList){
    	//标题
		headList.add("ETA(yyyy/MM/dd HH:mm)");
		headList.add("预计靠泊时间(yyyy/MM/dd HH:mm)");
		headList.add("NOR递交时间(yyyy/MM/dd HH:mm)");
		headList.add("引水上船时间(yyyy/MM/dd HH:mm)");
		headList.add("起锚时间(yyyy/MM/dd HH:mm)");
		headList.add("靠泊完成时间(yyyy/MM/dd HH:mm)");
		headList.add("实际开始装货时间(yyyy/MM/dd HH:mm)");
		headList.add("实际装货完成时间(yyyy/MM/dd HH:mm)");
		headList.add("拆管时间(yyyy/MM/dd HH:mm)");
		headList.add("预计离港时间(yyyy/MM/dd HH:mm)");
		headList.add("实际离港时间(yyyy/MM/dd HH:mm)");
    }
    /**
     * 装港标题(数据)
     * @param 
     * @return
     */
    public static void loadPortDataHeadline(List<String> headList){
    	//标题
    	headList.add("装港");
    	headList.add("油种");
		headList.add("ETA(yyyy/MM/dd HH:mm)");
		headList.add("预计靠泊时间(yyyy/MM/dd HH:mm)");
		headList.add("NOR递交时间(yyyy/MM/dd HH:mm)");
		headList.add("引水上船时间(yyyy/MM/dd HH:mm)");
		headList.add("起锚时间(yyyy/MM/dd HH:mm)");
		headList.add("靠泊完成时间(yyyy/MM/dd HH:mm)");
		headList.add("实际开始装货时间(yyyy/MM/dd HH:mm)");
		headList.add("实际装货完成时间(yyyy/MM/dd HH:mm)");
		headList.add("拆管时间(yyyy/MM/dd HH:mm)");
		headList.add("预计离港时间(yyyy/MM/dd HH:mm)");
		headList.add("实际离港时间(yyyy/MM/dd HH:mm)");
		headList.add("装港商检");
		headList.add("装港船代");
		headList.add("代理编号");
		headList.add("代理Uuid");
    }
    
    /**
     * 装港模板数据
     * @param 
     * @return
     */
    public static void loadPortTemplate(List<List<String>> contentList){
    	//内容
    	List<String> list2 = new ArrayList<>();
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		contentList.add(list2);	
    }
    	
    /**
     * 卸港标题(模板）
     * @param 
     * @return
     */
    public static void unloadPortHeadline(List<String> headList){
    	//标题
		headList.add("ETA(yyyy/MM/dd HH:mm)");
		headList.add("预计靠泊时间(yyyy/MM/dd HH:mm)");
		headList.add("NOR递交时间(yyyy/MM/dd HH:mm)");
		
		headList.add("引水上船时间(yyyy/MM/dd HH:mm)");
		headList.add("起锚时间(yyyy/MM/dd HH:mm)");
		headList.add("靠泊完成时间(yyyy/MM/dd HH:mm)");
		headList.add("实际开始卸货时间(yyyy/MM/dd HH:mm)");
		
		headList.add("实际卸货完成时间(yyyy/MM/dd HH:mm)");
		headList.add("拆管时间(yyyy/MM/dd HH:mm)");
		headList.add("预计离港时间(yyyy/MM/dd HH:mm)");
		headList.add("实际离港时间(yyyy/MM/dd HH:mm)");
    }
    /**
     * 卸港标题(模板)
     * @param 
     * @return
     */
    public static void unloadPortDataHeadline(List<String> headList){
    	//标题
    	headList.add("卸港");
    	headList.add("油种");
		headList.add("ETA(yyyy/MM/dd HH:mm)");
		headList.add("预计靠泊时间(yyyy/MM/dd HH:mm)");
		headList.add("NOR递交时间(yyyy/MM/dd HH:mm)");
		
		headList.add("引水上船时间(yyyy/MM/dd HH:mm)");
		headList.add("起锚时间(yyyy/MM/dd HH:mm)");
		headList.add("靠泊完成时间(yyyy/MM/dd HH:mm)");
		headList.add("实际开始卸货时间(yyyy/MM/dd HH:mm)");
		
		headList.add("实际卸货完成时间(yyyy/MM/dd HH:mm)");
		headList.add("拆管时间(yyyy/MM/dd HH:mm)");
		headList.add("预计离港时间(yyyy/MM/dd HH:mm)");
		headList.add("实际离港时间(yyyy/MM/dd HH:mm)");
		headList.add("代理协议编号");
		headList.add("代理协议Uuid");
		headList.add("卸港商检");
		headList.add("卸港商检联系方式");
		headList.add("卸港船代");
		headList.add("卸港船代联系方式");
		headList.add("卸港监卸");
		headList.add("卸港监卸联系方式");
    }
    
    /**
     * 卸港模板数据
     * @param 
     * @return
     */
    public static void unloadPortTemplate(List<List<String>> contentList){
		//内容
		List<String> list2 = new ArrayList<>();
		
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		list2.add("2018/01/01 00:00");
		contentList.add(list2);		
    }
    
    
    /**
     * 船盘标题(模板)
     * @param 
     * @return
     */
    public static void shipPlateHeadlineTemplae(List<String> headList){
    	//标题
		headList.add("船名");
		headList.add("船位");
		headList.add("open(yyyy/MM/dd)");
		headList.add("ETA Fujairah(yyyy/MM/dd)");
		headList.add("ETA Cabinda(yyyy/MM/dd)");
		headList.add("船东");
		headList.add("时效 (天)");
    }
    
    /**
     * 船盘数据(模板)
     * @param 
     * @return
     */
    public static void shipPlateContentTemplate(List<List<String>> contentList){
    	List<String> list=new ArrayList<>();
    	//内容
    	list.add("Toyo");
    	list.add("船位1");
    	list.add("2018/01/01");
    	list.add("2018/01/01");
    	list.add("2018/01/01");
    	list.add("TILOS SHIPPING CORPORATION");
    	list.add("7");
    	contentList.add(list);
    }
    
    
    /**
     * 船舶标题(模板)
     * @param 
     * @return
     */
    public static void sysShipHeadlineTemplae(List<String> headList){
    	//标题
		headList.add("name（船名）");
		headList.add("imo（船舶代码）");
		headList.add("mmsi（船舶MMSI）");
		headList.add("completeDate（建造年份）");
		headList.add("useType（用途类型）");
		headList.add("type（船型）");
		headList.add("capacity（载重容积）");
		headList.add("loadQuantity（载重吨）");
		headList.add("length（船长米）");
		headList.add("wide（船宽米）");
		headList.add("draft（吃水米）");
		headList.add("hull（船体）");
		headList.add("pennant（国籍）");
    }
    
    /**
     * 船舶数据(模板)
     * @param 
     * @return
     */
    public static void sysShipContentTemplate(List<List<String>> contentList){
    	List<String> list=new ArrayList<>();
    	//内容
    	list.add("DHT Lotus");
    	list.add("9385037");
    	list.add("235115049");
    	list.add("2018");
    	list.add("Crude/Oil Products Tanker");
    	list.add("VLCC");
    	list.add("351090");
    	list.add("320142");
    	list.add("332");
    	list.add("60");
    	list.add("20.000");
    	list.add("Double Hull");
    	list.add("China");
    	contentList.add(list);
    }
    
    
    /**
     * 保留2位有效小数
     * @param 
     * @return
     */
    public static BigDecimal reserveTwo(BigDecimal i){
    	if(i!=null){
    		i = i.setScale(2,BigDecimal.ROUND_DOWN);
    	}
    	return i;
    }
}
