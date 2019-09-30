package com.xld.common.file;
import java.io.InputStream;

/**
 * 文件数据实体
 * @author xld
 */
public class FileDataEntity {

	/** 文件名称 */
	private String fileName;
	/** 文件流 */
	private InputStream fileInputStream;
	
	public FileDataEntity(String fileName, InputStream fileInputStream){
		this.fileName = fileName;
		this.fileInputStream = fileInputStream;
	}
	
	/** 文件名称 */
	public String getFileName() {
		return fileName;
	}
	
	/** 文件名称 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/** 文件流 */
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	
	/** 文件流 */
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
}