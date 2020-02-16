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




}
