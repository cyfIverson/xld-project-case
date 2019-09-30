package com.xld.common.config;

import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * 简单封装PageInfo分页插件，只有基本get属性值
 * @author xld
 */
public class CustomPageInfo<T> {

    /** 当前页 */
    private int pageNum;
    /** 每页的数量 */
    private int pageSize;
    /** 总记录数 */
    private long total;
    /** 结果集 */
    private List<T> list;

    private PageInfo pageInfo;

    public CustomPageInfo(List<T> list) {
        pageInfo = new PageInfo(list);
    }

    public int getPageNum() {
        return pageInfo.getPageNum();
    }

    public int getPageSize() {
        return pageInfo.getPageSize();
    }

    public long getTotal() {
        return pageInfo.getTotal();
    }

    public List<T> getList() {
        return pageInfo.getList();
    }
}
