package com.xld.common.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xld
 * @description 特殊字符串转义映射Map表
 * @date 2019-09-29
 * */
public class SpecialStringMapUtil {

    public static final Map<String, String> specialStringMap = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
            put(" ", "&nbsp;");
            put("'", "&apos;");
            put("&", "&amp;");
            put("<", "&lt;");
            put(">", "&gt;");
            put("¡", "&iexcl;");
            put("¢", "&cent;");
            put("£", "&pound;");
            put("¤", "&curren;");
            put("¥", "&yen;");
            put("¦", "&brvbar;");
            put("§", "&sect;");
            put("¨", "&uml;");
            put("©", "&copy;");
            put("ª", "&ordf;");
            put("«", "&laquo;");
            put("¬", "&not;");
            put("®", "&reg;");
            put("¯", "&macr;");
            put("°", "&deg;");
            put("±", "&plusmn;");
            put("²", "&sup2;");
            put("³", "&sup3;");
            put("´", "&acute;");
            put("µ", "&micro;");
            put("¶", "&para;");
            put("·", "&middot;");
            put("¸", "&cedil;");
            put("¹", "&sup1;");
            put("º", "&ordm;");
            put("»", "&raquo;");
            put("¼", "&frac14;");
            put("½", "&frac12;");
            put("¾", "&frac34;");
            put("¿", "&iquest;");
            put("×", "&times;");
            put("÷", "&divide;");
            put("?", "&iexcl;");
            put("￥", "&yen;");
            put("￡", "&pound;");
            put("|", "&brvbar;");
            put("‰", "&permil;");
            put("⁄", "&frasl;");
            put("\\", "\\\\");
            put("\"", "&quot;");
            put("™", "™");
        }
    };
}
