package com.xld.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 文件操作路径配置（文件服务器）
 * @author xld
 */
@Component
public class FileUrlConfig {

	/** 文件访问根地址（外网访问） */
	public static String VISIT_URL;

	/**  文件操作根地址（文件服务器，内网访问） */
	public static String HANDLE_URL;

	/**  文件服务器操作凭证 */
	public static String FILE_REMOTE_TOKEN;

	/** 文件服务器上传文件访问地址 */
	public static final String UPLOAD_URL = "/fileService/file";

	/** 文件服务器删除文件访问地址 */
	public static final String DELETE_URL = "/fileService/delete";

	/** 设置文件访问根地址 */
	@Value("${fileUrlConfig.visitUrl}")
	public void setFile_visit_url(String visitUrl) {
		FileUrlConfig.VISIT_URL = visitUrl;
	}

	/** 设置 文件操作根地址 */
	@Value("${fileUrlConfig.handleUrl}")
	public void setFile_handle_url(String handleUrl) {
		FileUrlConfig.HANDLE_URL = handleUrl;
	}

	/** 设置 文件服务器操作凭证 */
	@Value("${fileUrlConfig.token}")
	public void setFile_remote_token(String token) {
		FileUrlConfig.FILE_REMOTE_TOKEN = token;
	}

    /** 具体业务文件上传设置的路径 */
    public static final String FILE_REMOTE_LOGO_URL ="logo";
}
