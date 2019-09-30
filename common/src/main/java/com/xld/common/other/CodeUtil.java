package com.xld.common.other;

import java.util.Random;

/**
 * 编号生成工具类 [去除相似的字母和数字]
 * @author xld
 */
public class CodeUtil {


    public static String createInvitationCode(int digitNumber) {
        StringBuffer invitationCode = new StringBuffer();
        Random random = new Random();
        String[] seed = new String[]{
                "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "3", "4", "5", "6", "7", "8", "9"
        };
        for (int i = 0; i < digitNumber; i++) {
            int index = random.nextInt(seed.length);
            invitationCode.append(seed[index]);
        }
        return invitationCode.toString();
    }

}
