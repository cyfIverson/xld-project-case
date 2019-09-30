package com.xld.common.other;

import java.util.UUID;

/**
 * 生成uuid
 * @author xld
 * @date 2019-09-29
 */
public class UUIDutil {

	/**
	 * 生成UUID字符串
	 * @return 小写的UUID字符串,包含横杠
	 */
	public static String createUUID(){
		return UUID.randomUUID().toString();
	}

	/**
	 * 生成UUID字符串
	 * @return 大写的UUID字符串,包含横杠
	 */
	public static String uppercaseUUID(){
		return createUUID().toUpperCase();
	}

	/**
	 * 生成UUID字符串
	 * @return 小写的UUID字符串,不包含横杠
	 */
	public static String noLineUUID(){
		return createUUID().replaceAll("-", "");
	}

	/**
	 * 生成UUID字符串
	 * @return 大写的UUID字符串,不包含横杠
	 */
	public static String noLineUpperUUID(){
		return uppercaseUUID().replaceAll("-", "");
	}
}
