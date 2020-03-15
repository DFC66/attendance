package com.dfc.api.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author aiyou
 */
@Data
public class UploadResult {

	/**
	 * 数据库主键加密id
	 */
	private String securityId;

	/**
	 * 文件名字
	 */
	private String fileName;

	/**
	 * 文件url
	 */
	private String filePath;

	/**
	 * 文件大小
	 */
	private Long fileSize;

	/**
	 * 文件后缀名
	 */
	private String fileExt;

	/**
	 * 上传时间
	 */
	private Date uploadTime;


	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
}
