package com.sinochem.crude.trade.orderexecute.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {
	
	
	private static final Log log = LogFactory.getLog(FTPUtil.class);

	/**
     * 获取FTPClient对象
     *
     * @param ftpHost     FTP主机服务器
     * @param ftpPassword FTP 登录密码
     * @param ftpUserName FTP登录用户名
     * @param ftpPort     FTP端口 默认为21
     * @return
     */
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            	log.info("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                log.info("FTP连接成功。");
            }
            //ftpClient.enterLocalPassiveMode(); 
        } catch (SocketException e) {
            log.error("", e);
            log.info("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            log.error("", e);
            log.info("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }

    /*
     * 从FTP服务器下载文件
     *
     * @param ftpHost FTP IP地址
     * @param ftpUserName FTP 用户名
     * @param ftpPassword FTP用户名密码
     * @param ftpPort FTP端口
     * @param ftpPath FTP服务器中文件所在路径 格式： ftptest/aa
     * @param localPath 下载到本地的位置 格式：H:/download
     * @param fileName 文件名称
     */
    public static void downloadFtpFile(String ftpHost, String ftpUserName,
                                       String ftpPassword, int ftpPort, String ftpPath, String localPath,
                                       String fileName) {

        FTPClient ftpClient = null;

        try {
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.changeWorkingDirectory(ftpPath);

            File localFile = new File(localPath + File.separatorChar + fileName);
            OutputStream os = new FileOutputStream(localFile);
            ftpClient.retrieveFile(fileName, os);
            os.close();
            ftpClient.logout();

        } catch (FileNotFoundException e) {
            log.info("没有找到" + ftpPath + "文件");
            log.error("", e);
        } catch (SocketException e) {
            log.info("连接FTP失败.");
            log.error("", e);
        } catch (IOException e) {
            log.error("", e);
            log.info("文件读取错误。");
            log.error("", e);
        }

    }

    /**
     * Description: 向FTP服务器上传文件
     * @param ftpHost FTP服务器hostname
     * @param ftpUserName 账号
     * @param ftpPassword 密码
     * @param ftpPort 端口
     * @param ftpPath  FTP服务器中文件所在路径 格式： ftptest/aa
     * @param fileName ftp文件名称
     * @param input 文件流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String ftpHost, String ftpUserName,
                                     String ftpPassword, int ftpPort, String ftpPath,
                                     String fileName,InputStream input) {
        boolean success = false;
        FTPClient ftpClient = null;
        try {
            int reply;
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            reply = ftpClient.getReplyCode();
            log.info("reply--->" + reply);
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return success;
            }
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);
            log.info(ftpClient.printWorkingDirectory());
            ftpClient.enterLocalPassiveMode();

            //ftpClient.enterLocalActiveMode();
            ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            /*FTPFile[] ftpFiles = ftpClient.listFiles(); 
            for(FTPFile t : ftpFiles){
            	log.info("--->" + ftpFiles.toString());
            }*/

            ftpClient.setFileTransferMode(FTP.COMPRESSED_TRANSFER_MODE);
            ftpClient.storeFile(fileName, input);
            log.info("传输完毕");
            input.close();
            ftpClient.logout();
            ftpClient.disconnect();
            log.info("退出ftp，关闭连接");
            success = true;
        } catch (IOException e) {
            log.error("", e);
        } finally {
            if (ftpClient!=null && ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                	log.error("", ioe);
                }
            }
        }
        return success;
    }
    
    public static void main(String[] args) {
        /*String ftpHost = "192.168.1.184";
        String ftpUserName = "LiQing";
        String ftpPassword = "liqing1025";*/
    	String ftpHost = "110.80.36.202";
        String ftpUserName = "BJZH";
        String ftpPassword = "bjzhBJZH";
        int ftpPort = 21;
        String ftpPath = "\\";
        String localPath = "D:\\11.txt";
        String fileName = System.currentTimeMillis() + ".xml";

        //上传一个文件
        try{
            FileInputStream in=new FileInputStream(new File(localPath));
            boolean test = FTPUtil.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, fileName,in);
            log.info(test);
        } catch (Exception e){
            log.error("", e);
            log.info(e);
        }

        //在FTP服务器上生成一个文件，并将一个字符串写入到该文件中
        /*try {
            InputStream input = new ByteArrayInputStream("1234567890".getBytes("GBK"));
            boolean flag = FtpUtil.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, fileName,input);
            log.info(flag);
        } catch (Exception e) {
            log.error("", e);
        }*/

                
        //新建并写入文件
        /*try {
			FTPClient ftpClient = FtpUtil.getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
			PrintWriter pw = new  PrintWriter(new OutputStreamWriter(ftpClient.appendFileStream(fileName),"utf-8"),true); // 写入的文件名   
			pw.write("12345678"); 
			pw.write("1234567");
			pw.flush();  
			pw.close();
		} catch (Exception e) {
			log.error("", e);
		} */
        
        
        //读取文件
        /*FTPClient ftpClient = FtpUtil.getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
        try {
        	ftpClient.changeWorkingDirectory(ftpPath);
        	InputStream in = ftpClient.retrieveFileStream("/REFINERY_QZ4.xml");
        	BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String temp = "";
            while((temp=reader.readLine())!=null){
            	log.info(temp);
            }
        	
		} catch (IOException e) {
			log.error("", e);
		}*/
        
        //下载响应回执xml并读取成map
        /*FtpUtil.downloadFtpFile(ftpHost, ftpUserName, ftpPassword, ftpPort, "/", "C:/Users/LiQing/Desktop/中化项目/集成通xml/response", "test_response.xml");
        XmlParseUtil parse = new XmlParseUtil();  
		Map<String, Object> map = parse.parseResponse("C:/Users/LiQing/Desktop/中化项目/集成通xml/response/test_response.xml");*/
        
        //下载业务回执xml并读取成map
        /*FtpUtil.downloadFtpFile(ftpHost, ftpUserName, ftpPassword, ftpPort, "/", "C:/Users/LiQing/Desktop/中化项目/集成通xml/response", "test_busniess.xml");
        XmlParseUtil parse = new XmlParseUtil();  
		Map<String, Object> map = parse.parseResponse("C:/Users/LiQing/Desktop/中化项目/集成通xml/response/test_busniess.xml");*/
		//BeanConvertUtils.mapToBean(map, clazz)
    }
    
    
        
}
