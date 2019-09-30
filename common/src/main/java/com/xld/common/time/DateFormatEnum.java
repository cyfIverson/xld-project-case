package com.xld.common.time;

/**
 * 时间格式枚举类
 * @author xld
 * @description
 * @date 2018/12/11 23:37
 */
public enum DateFormatEnum implements IDateFormat {

    DATE_FORMAT_1("yyyy-MM-dd HH:mm:ss.SSS"),
    DATE_FORMAT_2("yyyy-MM-dd HH:mm:ss"),
    DATE_FORMAT_3("yyyy-MM-dd HH:mm"),
    DATE_FORMAT_4("yyyy-MM-dd HH"),
    DATE_FORMAT_5("yyyy-MM-dd"),
    DATE_FORMAT_6("yyyy-MM"),
    DATE_FORMAT_7("yyyy"),
    DATE_FORMAT_8("HH:mm:ss"),

    DATE_FORMAT_9("yyyyMMddHHmmss"),
    DATE_FORMAT_10("yyyyMMddHHmm"),
    DATE_FORMAT_11("yyMMddHHmmss"),
    DATE_FORMAT_12("yyMMddHHmm"),
    DATE_FORMAT_13("yyMMdd"),
    DATE_FORMAT_14("yyyyMMdd"),

    DATE_FORMAT_15("yyyy-MM-dd HH:mm:00.0"),
    DATE_FORMAT_16("yyyy-MM-dd HH:00:00.0"),
    DATE_FORMAT_17("yyyy-MM-dd 00:00:00.0");

    /** 时间格式 */
    private String format;

    DateFormatEnum(String format) {
        this.format = format;
    }

    /**
     * 获取时间格式
     *
     * @return
     */
    @Override
    public String getFormat() {
        return format;
    }

}
