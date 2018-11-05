package com.sinochem.crude.trade.info.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSON;
import com.sinochem.crude.trade.common.aliyun.oss.FileManager;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.ImageFileUtils;
import com.sinochem.crude.trade.common.utils.ImageFileUtils.ImageType;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.common.utils.SHA1;
import com.sinochem.crude.trade.info.dao.InfoAttachmentMapper;
import com.sinochem.crude.trade.info.dao.InfoContentMapper;
import com.sinochem.crude.trade.info.domain.InfoAttachment;
import com.sinochem.crude.trade.info.domain.InfoContent;
import com.sinochem.crude.trade.info.service.ImageProcessService;

/**
 * 
 * @author Down
 *
 */
public class ImageProcessServiceImpl implements ImageProcessService {
	private static Log log = LogFactory.getLog(ImageProcessServiceImpl.class);
	
//	@Autowired
	private InfoContentMapper infoContentMapper;
	
//	@Autowired
	private InfoAttachmentMapper infoAttachmentMapper;
	
//	@Autowired
	private FileManager fileManager;
	
//	@Value("${infoImg.imgPath:/}")
	private String imgPaths;

//	@Value("${aliyun.oss.group.public}")
	private String publicBucket;

	public void imageProcessingAsync(Long infoId, String empId) {

		log.info("imageProcessingAsync BEGIN infoId=" + infoId + ", empId=" + empId);
		try {
			InfoContent infoContent  = infoContentMapper.findByInfoId(infoId);

			Boolean flag = false;// 判断html里面图片是否有外链图片
			Boolean flag1 = false;// 判断附件list是否有外链图片 
			String texHtml = "";// html
			if (infoContent != null) {// 替换html里的链接
				log.info("图片处理执行");
				texHtml = infoContent.getTexHtml();
				Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
				Matcher m_img = p_img.matcher(texHtml);
				boolean result_img = m_img.find();
				if (result_img) {
					while (result_img) {
						// 获取到匹配的<img />标签中的内容
						String str_img = m_img.group(2);

						// 开始匹配<img />标签中的src
						Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
						Matcher m_src = p_src.matcher(str_img);
						if (m_src.find()) {
							String str_src = m_src.group(3);
							// if (!str_src.contains("information")) {
							flag = true;
							try {
								// 构造URL
								URL url = new URL(str_src);
								// 打开连接
								URLConnection con = url.openConnection();
								// 设置请求超时为5s
								con.setConnectTimeout(5 * 1000);
								// 输入流
								InputStream is = con.getInputStream();
								ByteArrayOutputStream bos = new ByteArrayOutputStream();
								BufferedInputStream br = new BufferedInputStream(is);
								int i;
								byte[] b = new byte[1024];
								StringBuilder sb = new StringBuilder();
								while ((i = br.read(b)) != -1) {
									// sb.append(new String(b, 0, i));
									bos.write(b, 0, i);
								}
								InputStream bis = new ByteArrayInputStream(bos.toByteArray());
								bis.reset();
								while ((i = bis.read(b)) != -1) {
									sb.append(new String(b, 0, i));
								}

								bis.reset();
								ImageType imageType = ImageFileUtils.getImageType(bis);// 根据流获取图片后缀名
								String name = imageType.getName();

								String encode = SHA1.encode(sb.toString());// 加密后数据
								if (str_src.contains(imgPaths)) {
									log.info("内部图片存图库");
								} else {
									flag = true;
									String storePath = "info/"
											+ empId
											+ "/"
											+ DateTimeUtils.currentDateString("yyyyMMdd") + "/";
									storePath = storePath+ KeyGenUtils.newUuid();
									log.info("外链图片存图库");
									bis.reset();
									fileManager.save(bis, publicBucket, storePath);
									texHtml = texHtml.replace(str_src, imgPaths + storePath);
									
									infoContent.setTexHtml(texHtml);
									infoContent.setUpdateDate(DateTimeUtils.currentDate());
									infoContent.setUpdateUser(empId);
									infoContentMapper.updateInfoContentTexAndTextHtml(infoContent);
								}

							} catch (Exception e) {
								log.error("imageProcessingAsync error", e);
							}
						}
						// 结束匹配<img />标签中的src
						result_img = m_img.find();
					}

					// 匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
					// result_img = m_img.find();

				}

			}
			
			// 附件处理
			InfoAttachment attaQuery = new InfoAttachment();
			attaQuery.setInfoId(infoId);
			List<InfoAttachment> attas = infoAttachmentMapper.queryByEntitys(attaQuery);
			log.info("imageProcessingAsync cmsInfoAttRes=" + JSON.toJSONString(attas));
			
			for (InfoAttachment atta : attas) {
				try {
					// 构造URL
					URL url = new URL(atta.getAttachmentPath());
					// 打开连接
					URLConnection con = url.openConnection();
					// 设置请求超时为5s
					con.setConnectTimeout(5 * 1000);
					// 输入流
					InputStream is = con.getInputStream();
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					BufferedInputStream br = new BufferedInputStream(is);
					
					int i;
					byte[] b = new byte[1024];
					StringBuilder sb = new StringBuilder();
					while ((i = br.read(b)) != -1) {
						// sb.append(new String(b, 0, i));
						bos.write(b, 0, i);
					}
					InputStream bis = new ByteArrayInputStream(
							bos.toByteArray());
					bis.reset();
					while ((i = bis.read(b)) != -1) {
						sb.append(new String(b, 0, i));
					}
					
					bis.reset();
					ImageType imageType = ImageFileUtils.getImageType(bis);// 根据流获取图片后缀名
					String name = imageType.getName();
					
					String encode = SHA1.encode(sb.toString());// 加密后数据
					if (atta.getAttachmentPath().contains(imgPaths)) {// 不是外链图片
						// do nothing
					} else {// 是外链图片
						flag1 = true;
						String storePath = "info"
								+ empId
								+ "/"
								+ DateTimeUtils.currentDateString("yyyyMMdd") + "/";
						storePath = storePath + KeyGenUtils.newUuid();
						log.info("imageProcessingAsync data2=" + JSON.toJSONString(atta));
						
						bis.reset();
						fileManager.save(bis, publicBucket, storePath);
						
						InfoAttachment attaUpdate = new InfoAttachment();
						attaUpdate.setId(atta.getId());
						attaUpdate.setAttachmentPath(imgPaths + storePath);
						attaUpdate.setUpdateDate(DateTimeUtils.currentDate());
						attaUpdate.setUpdateUser(empId);
						infoAttachmentMapper.updateRecordById(attaUpdate);
					}
				} catch (Exception e) {
					log.error("imageProcessingAsync error2", e);
				}
			}
		} catch (Exception e) {
			log.error("imageProcessingAsync final error", e);
		}

	}
}
