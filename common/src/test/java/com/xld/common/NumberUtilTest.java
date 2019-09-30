package com.xld.common;

import java.math.BigDecimal;

/**
 * @descript 数字工具测试验证
 * @author cyfIverson
 * @date 2019-09-30 10:54 AM
 */
public class NumberUtilTest {


    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal(0.1);
        System.out.println(b1);

        BigDecimal b2 = new BigDecimal("0.1");
        System.out.println(b2);

        BigDecimal b3 = new BigDecimal(Double.toString(0.1));
        System.out.println(b3);
    }
}
