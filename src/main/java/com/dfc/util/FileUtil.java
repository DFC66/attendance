package com.dfc.util;

import com.dfc.api.StorageApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

/**
 * 巨鲸文件服务
 * 
 * @author xiaobowang 2019年4月19日
 */
public class FileUtil {

	private static Logger log = LoggerFactory.getLogger(FileUtil.class);

	private  static StorageApi fileInterface;

	public static void delete(String objectName) {
		fileInterface.delete(objectName);
	}

	public static void upload(String objectName, byte[] data) {
		try {
			fileInterface.upload(objectName, data);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BizException("文件上传异常: " + e.getMessage());
		}
	}

	public static void upload(String objectName, InputStream is) {
		try {
			fileInterface.upload(objectName, is);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BizException("文件上传异常: " + e.getMessage());
		}
	}

	public static String upload(String localFileAbsolutePath, String folder) {
		if (folder == null) {
			folder = "default";
		}
		int index = localFileAbsolutePath.lastIndexOf(".");
		if (index == -1) {
			throw new BizException("文件名不对，没有后缀名");
		}

		String ext = localFileAbsolutePath.substring(index + 1);

//		String objectName = folder + "/" + CommonUtil.format(new Date(), "yyyyMMdd") + "/" + CommonUtil.uuid32() + "." + ext;
		String objectName = folder + "/"  + BaseCommonUtil.uuid() + "." + ext;

		try (FileInputStream fis = new FileInputStream(localFileAbsolutePath)) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			bos.flush();
			byte[] buffer = bos.toByteArray();
			upload(objectName, buffer);
			return objectName;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static byte[] download(String objectName) {

		try {
			return fileInterface.download(objectName);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BizException("文件下载异常: " + e.getMessage());
		}
	}

	public static byte[] download(String objectName, int times) {

		for (int i = 0; i < times; i++) {
			try {
				return fileInterface.download(objectName);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				continue;
			}
		}

		throw new BizException("文件下载异常");
	}

	public static void download2OutputStream(OutputStream os, String objectName) {
		try {
			byte[] bytes = download(objectName);
			os.write(bytes);
			os.flush();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static Map<String, String> generateUploadSession() {
		return fileInterface.generateUploadSession();
	}

	public static void setFileInterface(StorageApi fileInterface) {
		FileUtil.fileInterface = fileInterface;
	}
}
