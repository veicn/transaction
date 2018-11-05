package com.sinochem.crude.trade.orderexecute.commons.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;


public class Java2XML {
	
	private static final Log log = LogFactory.getLog(Java2XML.class);
	
	public void BuildXMLDoc() throws Exception {
		
		String filePath = "C:\\Users\\LiQing\\Desktop\\中化项目\\集成通xml\\request\\" + System.currentTimeMillis()+ ".xml";
		
		Document doc = DocumentHelper.createDocument();
		//创建节点操作对象
		Element rootElement = doc.addElement("DecMessage");
		rootElement.addNamespace("", "http://www.chinaport.gov.cn/dec");
		rootElement.addAttribute("version", "3.1");
		//设置节点属性
		Element decHead = rootElement.addElement("decHead");
		decHead.addElement("AgentCode").setText("3.1");    //申报单位代码，必填，此版本为3.1
		decHead.addElement("AgentName").setText("ZHQZ");    //申报单位名称，必填
		decHead.addElement("ApprNo").setText("中化泉州");    //批准文号，必填，实填“外汇核销单号”
		decHead.addElement("BillNo").setText("TD201804110001");    //提单号，必填
		decHead.addElement("ContrNo").setText("");    //合同号
		decHead.addElement("CopCode").setText("LRDW001");    //录入单位代码， 必填
		decHead.addElement("CopName").setText("录入单位001");    //录入单位名称，必填
		decHead.addElement("CustomMaster").setText("厦门");    //申报地海关，必填
		decHead.addElement("CutMode").setText("");    //征免性质
		decHead.addElement("DataSource").setText("");    //数据来源，空值，预留字段
		decHead.addElement("DeclTrnRel").setText("0");    //报关/转关关系标志，必填，0：一般报关单；1：转关提前报关单
		decHead.addElement("DistinatePort").setText("");    //装货港
		decHead.addElement("DistrictCode").setText("");    //境内目的地
		decHead.addElement("EdiId").setText("");    //报关标志
		decHead.addElement("EntryId").setText("");    //海关编号
		decHead.addElement("EntryType").setText("");    //报关单类型
		decHead.addElement("FeeCurr").setText("");    //运费币制
		decHead.addElement("FeeMark").setText("");    //运费标记
		decHead.addElement("FeeRate").setText("");    //运费／率
		decHead.addElement("GrossWet").setText("");    //毛重
		decHead.addElement("IEDate").setText("");    //进出日期
		decHead.addElement("IEFlag").setText("I");    //进出口标志，必填，“I”：进口。“E”：出口
		decHead.addElement("IEPort").setText("");    //进出口岸
		decHead.addElement("InputerName").setText("小王");    //录入员姓名，必填
		decHead.addElement("InRatio").setText("");    //内销比率
		decHead.addElement("InsurCurr").setText("");    //保险费币制
		decHead.addElement("InsurMark").setText("");    //保险费标记
		decHead.addElement("InsurRate").setText("");    //保险费／率
		decHead.addElement("LicenseNo").setText("");    //许可证编号
		decHead.addElement("ManualNo").setText("");    //备案号
		decHead.addElement("NetWt").setText("");    //净重
		decHead.addElement("NoteS").setText("");    //备注
		decHead.addElement("OtherCurr").setText("");    //杂费币制
		decHead.addElement("OtherMark").setText("");    //杂费标志
		decHead.addElement("OtherRate").setText("");    //杂费／率
		decHead.addElement("OwnerCode").setText("");    //货主单位代码
		decHead.addElement("OwnerName").setText("");    //货主单位名称
		decHead.addElement("PackNo").setText("");    //件数
		decHead.addElement("PartenerID").setText("");    //申报人标识
		decHead.addElement("PayWay").setText("");    //征税比例
		decHead.addElement("PaymentMark").setText("");    //纳税单位
		decHead.addElement("PDate").setText("");    //首次进行暂存操作的系统时间
		decHead.addElement("PreEntryId").setText("");    //预录入编号
		decHead.addElement("Risk").setText("");    //风险评估参数
		decHead.addElement("SeqNo").setText("");    //报关单统一编号
		decHead.addElement("TgdNo").setText("");    //通关申请单号	
		decHead.addElement("TradeCode").setText("JYDWBH");    //经营单位编号，必填
		decHead.addElement("TradeCountry").setText("");    //贸易国别
		decHead.addElement("TradeMode").setText("");    //贸易方式
		decHead.addElement("TradeName").setText("JYDWMC");    //经营单位名称，必填
		decHead.addElement("TrafMode").setText("");    //运输方式代码
		decHead.addElement("TrafName").setText("");    //运输工具代码及名称
		decHead.addElement("TransMode").setText("");    //成交方式
		decHead.addElement("Type").setText("");    //EDI申报备注
		decHead.addElement("TypistNo").setText("IC0001");    //录入员IC卡号，必填
		decHead.addElement("WrapType").setText("");    //包装种类
		decHead.addElement("ChkSurety").setText("");    //担保验放标志
		decHead.addElement("BillType").setText("");    //备案清单类型
		decHead.addElement("AgentCodeScc").setText("");    //申报单位统一编码
		decHead.addElement("CopCodeScc").setText("");    //录入单位统一编码
		decHead.addElement("OwnerCodeScc").setText("");    //货主单位统一编码
		decHead.addElement("TradeCodeScc").setText("");    //经营单位统一编码
		decHead.addElement("PromiseItmes").setText("");    //承诺事项，必填
		decHead.addElement("TradeAreaCode").setText("");    //贸易国别， 必填	
		
		Element decLists = rootElement.addElement("DecLists");
		Element DecList = decLists.addElement("DecList");
		DecList.addElement("ClassMark").setText("");    //归类标志，空值
		DecList.addElement("CodeTS").setText("SPBH01");    //商品编号，必填
		DecList.addElement("ContrItem").setText("");    //备案序号
		DecList.addElement("DeclPrice").setText("");    //申报单价
		DecList.addElement("DeclTotal").setText("");    //申报总价
		DecList.addElement("DutyMode").setText("");    //征减免税方式
		DecList.addElement("ExgNo").setText("");    //货号
		DecList.addElement("ExgVersion").setText("");    //版本号
		DecList.addElement("Factor").setText("");    //申报计量单位与法定单位比例因子
		DecList.addElement("FirstQty").setText("");    //第一法定数量
		DecList.addElement("FirstUnit").setText("");    //第一计量单位
		DecList.addElement("GUnit").setText("");    //申报计量单位
		DecList.addElement("GModel").setText("");    //商品规格、型号
		DecList.addElement("GName").setText("");    //商品名称
		DecList.addElement("GNo").setText("0001");    //商品序号，必填
		DecList.addElement("GQty").setText("");    //申报数量（成交计量单位）
		DecList.addElement("OriginCountry").setText("");    //原产地
		DecList.addElement("SecondUnit").setText("");    //第二计量单位
		DecList.addElement("SecondQty").setText("");    //第二法定数量
		DecList.addElement("TradeCurr").setText("");    //成交币制
		DecList.addElement("UseTo").setText("");    //用途/生产厂家
		DecList.addElement("WorkUsd").setText("");    //工缴费
		DecList.addElement("DestinationCountry").setText("中国-福建-泉州");    //最终目的国（地区），必填
		
		Element decContainers = rootElement.addElement("DecContainers");
		Element container = decContainers.addElement("Container");
		container.addElement("ContainerId").setText("JZXH001");    //集装箱号，必填
		container.addElement("ContainerMd").setText("");    //集装箱规格
		container.addElement("ContainerWt").setText("");    //集装箱自重
				
		Element decLicenseDocus = rootElement.addElement("DecLicenseDocus");
		Element licenseDocu = decLicenseDocus.addElement("LicenseDocu");
		licenseDocu.addElement("DocuCode").setText("DZDM01");    //单证代码，必填
		licenseDocu.addElement("CertCode").setText("");    //单证编号
		
		
		Element decFreeTxt = rootElement.addElement("DecFreeTxt");
		decFreeTxt.addElement("BonNo").setText("");    //监管仓号
		decFreeTxt.addElement("CusFie").setText("");    //货场代码
		decFreeTxt.addElement("DecBpNo").setText("");    //报关员联系方式
		decFreeTxt.addElement("DecNo").setText("");    //报关员号
		decFreeTxt.addElement("RelId").setText("");    //关联报关单号
		decFreeTxt.addElement("RelManNo").setText("");    //关联备案号
		decFreeTxt.addElement("VoyNo").setText("");    //航次号
		
		Element decSign = rootElement.addElement("DecSign");
		decSign.addElement("ClientSeqNo").setText(String.valueOf(System.currentTimeMillis()));    //客户端报关单编号，必填，自编唯一
		decSign.addElement("CopCode").setText("CZQYJGDM");    //操作企业组织机构代码，必填
		decSign.addElement("ICCode").setText("IC0002");    //操作员IC卡号，必填，取企业注册信息中的卡号固定值
		decSign.addElement("OperType").setText("G");    //操作类型，必填，G：报关单暂存（转关提前报关单暂存）厦门关区 A：上载
		decSign.addElement("OperName").setText("小张");    //操作员姓名，必填
		decSign.addElement("Sign").setText("");    //报关单签名，必填
		decSign.addElement("SignDate").setText("");    //签名时间，必填
		decSign.addElement("HostId").setText("");    //邮箱ID，必填
		decSign.addElement("Certificate").setText("");    //操作员卡对应的证书号，必填		
	    
		OutputFormat fmt = new OutputFormat();
		 //创建输出格式对象
		fmt.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(fmt);
		//以输出格式为参数,创建XML文件输出对象
		OutputStream out = new FileOutputStream(filePath);
		//创建输出流..
		writer.setOutputStream(out);
		//设置输出流
		writer.write(doc);
	  }
        
	  public static void main(String[] args) {
	    try {
	      Java2XML j2x = new Java2XML();
	      log.info("正在生成xml 文件...");
	      j2x.BuildXMLDoc();
	      log.info("文件已生成");
	      
	     } catch (Exception e) {
	    	 log.error("", e);
	    }
        
	    
	  }
	
		

}
