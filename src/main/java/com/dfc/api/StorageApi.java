package com.dfc.api;

import com.dfc.api.entity.UploadResult;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * 文件存储接口
 * 
 * @author aiyou
 */
public interface StorageApi {

	public UploadResult uploadByServletRequest(HttpServletRequest request, String folder) throws Exception;

	public void list();

	public void delete(String objectName);

	public String upload(String objectName, byte[] data);

	public void upload(String objectName, InputStream is);

	public byte[] download(String objectName);

	public void downloadOutputStream(OutputStream os, String objectName);

	/**
	 * 仅oss方案可用
	 * 
	 * @return
	 */
	public Map<String, String> generateUploadSession();


	String uploadFaceImage(Integer courseCode, String stuNumber,String folder,String base64) throws IOException;


	String uploadCourseImage(Integer courseCode,String objectName,MultipartFile multipartFile) throws IOException;

}
