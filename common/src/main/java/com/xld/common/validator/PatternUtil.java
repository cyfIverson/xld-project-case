package com.xld.common.validator;

import java.util.regex.Pattern;

/**
 * value 正则校验器
 * @author xld
 * @description
 * @date 2018/11/14 1:11
 */
public class PatternUtil {

    /**
     * 在使用正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度。
     * 说明：不要在方法体内定义：Pattern pattern = Pattern.compile(规则);
     * */

    /** 数字、字母不区分大小写，不限制位数 */
    public static Pattern NUMBER_ALPHABET = Pattern.compile("^[A-Za-z0-9]+$");
    /** Number 正负数，不限制位数 */
    public static Pattern NUMBER_PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");
    /** Integer 正数，限制10位数 */
    public static Pattern INTEGER_LIMIT = Pattern.compile("^[\\+]?[\\d]{0,10}$");
    /** Long 正数，限制18位数 */
    public static Pattern LONG_LIMIT = Pattern.compile("^[\\+]?[\\d]{0,18}$");
    /** Double 正负数限制整数12位数、小数5位 */
    public static Pattern DOUBLE_LIMIT = Pattern.compile("^[-\\+]?[0-9]{0,12}.?[0-9]{0,5}");
    /** Double 正负数不限制小数位 */
    public static Pattern DOUBLE = Pattern.compile("^[-\\+]?[0-9]+.?[0-9]+");
    /** Double 正数，不限制小数位 */
    public static Pattern DOUBLE_PLUS = Pattern.compile("^[\\+]?[0-9]+.?[0-9]+");
    /** Double 负数，不限制小数位 */
    public static Pattern DOUBLE_MINUS = Pattern.compile("-[0-9]+.?[0-9]+");
    /** 数字、字母不区分大小写，限制位数10位 */
    public static Pattern NUMBER_ALPHABET_LIMIT = Pattern.compile("^[A-Za-z0-9]{10}$");
    /** 数字、字母不区分大小写，限制位数32位 */
    public static Pattern NUMBER_ALPHABET_LIMIT_TXID = Pattern.compile("^[A-Za-z0-9]{64}$");
    /** Integer 正数 不限制位数*/
    public static Pattern INTEGER_NOLIMIT = Pattern.compile("^[\\+]?[\\d]$");
    /** Integer 正数 不限制位数*/
    public static Pattern NUMBER = Pattern.compile("^[0-9]+$");


    /** 登录验证码 */
    public static final Pattern VERIFY_CODE = Pattern.compile("[A-Za-z0-9]{4}$");
    /** 账号 */
    public static final Pattern ACCOUNT = Pattern.compile("[A-Za-z0-9]{6,16}$");
    /** 密码 */
    public static final Pattern PASSWORD = Pattern.compile("[A-Za-z0-9]{6,16}$");
    /** Base64加密密码 */
    public static final Pattern BASE64_PASSWORD = Pattern.compile("[A-Za-z0-9=]{6,32}$");
    /** 手机号 */
    public static final Pattern MOBIL = Pattern.compile("1[3-9]\\d{9}$");
    /** 电话号码 */
    public static final Pattern PHONE = Pattern.compile("((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$");
    /** 邮政编码 */
    public static final Pattern POST_NUMBER = Pattern.compile("[1-9]\\d{5}$");
    /** 中文 */
    public static final Pattern CHINESE = Pattern.compile("[\\u0391-\\uFFE5]+$");
    /** E-mail地址 */
    public static final Pattern EMAIL = Pattern.compile("[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
    /** URL */
    public static final Pattern URL = Pattern.compile("[a-zA-Z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\s*)?$");
    /** 匹配HTML标记 */
    public static final Pattern HTML = Pattern.compile("<(.*)>.*<\\/\\1>|<(.*) \\/>");
    /** IP */
    public static final Pattern IP = Pattern.compile("(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))");
    /** QQ号 */
    public static final Pattern QQ = Pattern.compile("[1-9]\\d{4,14}");
    /** 中英文数字下划线 */
    public static final Pattern CHINESE_ENGLISH = Pattern.compile("^[\\w\\u0391-\\uFFE5]+$");
    /** 允许全部字符  */
    public static final Pattern ALL = Pattern.compile("^[\\w\\W]+$");
    /**  linux下相对路径 */
    public static final Pattern FILE_URL = Pattern.compile("(/\\w+)+\\.(doc|ppt|pptx|xls|xlsx|docx|pdf)");
    /** 文件名规则 */
    public static final Pattern FILE_NAME = Pattern.compile("^[\\w\\u0391-\\uFFE5]+\\.(doc|ppt|pptx|xls|xlsx|docx|pdf)$");
    /** 字符串判空 */
    private static final boolean isEmpty(String str) {
        return (null == str || str.isEmpty()) ? true : false;
    }

    /**
     * 判断str是否符合规则
     * @param str 待校验的字符串，不可为空
     * @param pattern Pattern 正则，不可为空
     * @return 是：返回true，否：返回false
     */
    public static boolean validate(String str, Pattern pattern) {
        return (isEmpty(str) || null == pattern) ? false : pattern.matcher(str).matches();
    }

    /**
     * 判断str是否符合规则
     * @param str 待校验的字符串，可为空
     * @param pattern Pattern 正则，不可为空
     * @return 是：返回true，否：返回false
     */
    public static boolean validateAllowEmpty(String str, Pattern pattern) {
        return  null == pattern ? false : isEmpty(str) ? true : pattern.matcher(str).matches();
    }

    /**
     * 获取指定最大位数的 浮点数校验
     * @param integerNumber 整数最大位数
     * @param decimalNumber 小数最大位数
     * @return 正则规则
     */
    public static Pattern doubleLimitPattern(int integerNumber, int decimalNumber) {
        String rule = "^[+\\-]?\\d{1," + integerNumber + "}(\\.\\d{1," + decimalNumber +"})?$";
        return Pattern.compile(rule);
    }

}
