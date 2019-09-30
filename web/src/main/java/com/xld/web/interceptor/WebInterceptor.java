package com.xld.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.xld.common.bean.ENUM.HttpStatusEnum;
import com.xld.common.config.SpringSessionConfig;
import com.xld.common.config.SystemCommonConfig;
import com.xld.common.other.NumberUtil;
import com.xld.common.other.SpringUtil;
import com.xld.common.other.StrUtil;
import com.xld.common.redis.RedisUtil;
import com.xld.common.result.Results;
import com.xld.common.time.DateUtil;
import com.xld.core.entity.BO.UserSessionBO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * WEB拦截器
 * @author xld
 */
@Component
public class WebInterceptor implements HandlerInterceptor {

	/** redis服务 */
	private static RedisUtil redisUtil;

	/** redis服务 */
	private static RedisUtil getRedisService() {
		if (redisUtil == null) {
			redisUtil = (RedisUtil) SpringUtil.getBean("redisUtil");
		}
		return redisUtil;
	}

	/**
	 * 在请求到达运行的方法之前，用于拦截非法请求
	 * 在controlller之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
		//token校验
		String sessionId = request.getHeader(SystemCommonConfig.ACCESS_TOKEN_WEB);
		if (StrUtil.isEmpty(sessionId)) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(JSONObject.toJSONString(Results.result(HttpStatusEnum.SESSION_ERROR)));
			return false;
		}
		//token对应的用户校验
		UserSessionBO userSession = (UserSessionBO)getRedisService().get(sessionId);
		if (userSession == null) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(JSONObject.toJSONString(Results.result(HttpStatusEnum.SESSION_ERROR)));
			out.close();
			return false;
		}
		//todo handle other things
		return true;
	}

    /**
	 * 登录成功记录
	 * @param request         当前用户请求
	 * @param userSessionBO   用户信息
	 * @return 操作成功：返回sessionId，操作失败：返回null
	 */
	public static String loginSuccess(HttpServletRequest request, UserSessionBO userSessionBO) {
		Timestamp curTime = DateUtil.getCurrentTime();
		String sessionId = SystemCommonConfig.LOGIN_USER + DateUtil.longToTimeStr(curTime.getTime(), DateUtil.dateFormat10)
				+ NumberUtil.getBinaryStr(12);
		//session缓存outTime
		userSessionBO.setOutTime(curTime.getTime());

        //记录用户Id与sessionid的映射关系
        String SESSION_USER_KEY = SpringSessionConfig.USER_SESSION_NAME_WEB + userSessionBO.getUserId();

        //删除原来的token
        if (getRedisService().get(SESSION_USER_KEY) != null) {
            String oldSession = (String) getRedisService().get(SESSION_USER_KEY);
            getRedisService().delete(oldSession);
        }
		getRedisService().set(SESSION_USER_KEY,sessionId, SystemCommonConfig.SESSIONTIME);
		getRedisService().set(sessionId,userSessionBO, SystemCommonConfig.SESSIONTIME);

        request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		return sessionId;
	}

	/**
	 * 登出 删除redis信息
	 * @param sessionId redis存入的key值
	 */
	public static void loginOut(HttpServletRequest request,String sessionId) {
        UserSessionBO userSession = (UserSessionBO) getRedisService().get(sessionId);
		if (userSession != null){
		    //删除token
			getRedisService().delete(sessionId);
		}
	}

	/**
	 * 获取缓存key
	 * 同时使用，使用token保存登录信息，优先使用token，如果获取失败则取session
	 */
	public static String getSessionKey(HttpServletRequest request) {
		String sessionId = "";
		Object sessionIdAttribute = request.getAttribute(SystemCommonConfig.ACCESS_TOKEN_WEB);
		if (sessionIdAttribute != null) {
			sessionId = sessionIdAttribute.toString();
		}
		if (StrUtil.isBlank(sessionId)) {
			sessionId = request.getHeader(SystemCommonConfig.ACCESS_TOKEN_WEB);
		}
		if (StrUtil.isBlank(sessionId)) {
			sessionId = request.getSession().getId();
		}
		return sessionId;
	}

	/**
	 * 获取用户信息
	 * @param request 当前用户请求
	 * @return 查询成功：返回数据，未查询到数据或查询失败：返回NULL
	 */
	public static UserSessionBO getUser(HttpServletRequest request) {
		String sessionId = request.getHeader(SystemCommonConfig.ACCESS_TOKEN_WEB);
		if (sessionId != null){
			return (UserSessionBO)getRedisService().get(sessionId);
		}else {
			return null;
		}
	}

	/**
	 * 用于重定向方法，这个方法可以重新返回一个新的页面，进行新的数据展示
	 * 在controller之后
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object controller, ModelAndView modelAndView){
	}

	/**
	 * 所有程序完成之后最终会执行的方法，一般用于销毁对象IO等操作
	 * 在postHandle之后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object controller, Exception exception){
	}
}
