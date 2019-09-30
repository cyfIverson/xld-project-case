package com.xld.web.controller;

import com.xld.common.validator.PatternUtil;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;

/**
 * BaseController
 * @author xld
 * @date 2019/09/29
 */
public class BaseController {

	/**
	 * 请求对象
	 */
	@Autowired
	protected HttpServletRequest request;

	/**
	 * 获取分页页码
	 */
	protected Integer getPageNum() {
		String pageNumber = request.getParameter("pageNum");
		if (!PatternUtil.validate(pageNumber, PatternUtil.INTEGER_LIMIT)) {
			pageNumber = "1";
		}
		Integer pageNum = Integer.parseInt(pageNumber);
		return pageNum;
	}

	/**
	 * 获取分页条数
	 */
	protected Integer getPageSize() {
		String pageSizeStr = request.getParameter("pageSize");
		if (!PatternUtil.validate(pageSizeStr, PatternUtil.INTEGER_LIMIT)) {
			pageSizeStr = "10";
		}
		Integer pageSize = Integer.parseInt(pageSizeStr);
		return pageSize;
	}
}
