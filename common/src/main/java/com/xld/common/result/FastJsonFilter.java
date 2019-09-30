package com.xld.common.result;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.xld.common.time.DateUtil;
import java.sql.Timestamp;

/**
 * fastJson处理Timestamp
 * @author xld
 */
public class FastJsonFilter implements ValueFilter {

    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Object process(Object object, String name, Object value) {
        if (value instanceof Timestamp) {
            return DateUtil.longToTimeStr(((Timestamp) value).getTime(), dateFormat);
        }
        return value;
    }
}
