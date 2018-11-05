package com.sinochem.crude.trade.shipagent.utils;



import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.Serializable;

public class ExportFileNameUtil implements Serializable {
	/**
	 *
	 * <br>
	 * <b>功能：</b>设置下载文件中文件的名称<br>
	 * <b>作者：</b>yixq<br>
	 * <b>@param filename
	 * <b>@param request
	 * <b>@return</b>
	 */
//	public static String encodeFilename(String filename,
//										HttpServletRequest request) {
//		/**
//		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
//		 * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
//		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
//		 * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
//		 */
//		String agent = request.getHeader("USER-AGENT");
//		try {
//			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
//				String newFileName = URLEncoder.encode(filename, "UTF-8");
//				newFileName = StringUtils.replace(newFileName, "+", "%20");
//				if (newFileName.length() > 150) {
//					newFileName = new String(filename.getBytes("GB2312"),
//							"ISO8859-1");
//					newFileName = StringUtils.replace(newFileName, " ", "%20");
//				}
//				return newFileName;
//			}
//			if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
//				return MimeUtility.encodeText(filename, "UTF-8", "B");
//
//			return filename;
//		} catch (Exception ex) {
//			return filename;
//		}
//	}

	/**
	 *
	 * <br>
	 * <b>功能：</b>列头样式<br>
	 * <b>作者：</b>yixq<br>
	 * <b>@param wb
	 * <b>@return</b>
	 */
	public static HSSFCellStyle initColumnHeadStyle(HSSFWorkbook wb) {
		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		HSSFFont columnHeadFont = wb.createFont();
		columnHeadFont.setFontName("宋体");
		columnHeadFont.setFontHeightInPoints((short) 10);
		columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		columnHeadStyle.setFont(columnHeadFont);
		// 左右居中
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 上下居中
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		// 左边框的颜色
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		// 边框的大小
		columnHeadStyle.setBorderLeft((short) 1);
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		return columnHeadStyle;
	}

	/**
	 *
	 * <br>
	 * <b>功能：</b>单元格的默认样式<br>
	 * <b>作者：</b>yixq<br>
	 * <b>@param wb
	 * <b>@return</b>
	 */
	public static HSSFCellStyle initColumnCenterstyle(HSSFWorkbook wb) {
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 10);
		HSSFCellStyle centerstyle = wb.createCellStyle();
		centerstyle.setFont(font);
		centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		centerstyle.setWrapText(true);
		centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
		centerstyle.setBorderLeft((short) 1);
		centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
		centerstyle.setBorderRight((short) 1);
		centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
		centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．
		return centerstyle;
	}
}