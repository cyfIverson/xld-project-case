package com.xld.web.aop;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xld.common.other.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @descript 对请求的参数进行打印
 * @author cyfIverson
 * @date 2019-09-30 4:23 PM
 */
@Component
@Aspect
public class LogRecordAspect {
    /**
     * 请求controller之前
     * @param joinPoint 切点
     */
    @Before("within(com.xld.web.controller.*)")
    public void before(JoinPoint joinPoint){
        //切点获取参数
        Object[] args = joinPoint.getArgs();
        //获取目标方法的签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //获取request对象
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String queryString = request.getQueryString();
        String methodType = request.getMethod();
        String url = request.getRequestURL().toString();

        String params = "";
        //获取请求参数集合并进行遍历拼接,符合JSON入参的方式
        if(("POST".equals(methodType) || "PUT".equals(methodType)) && queryString == null){
            if(args.length > 0) {
                Object object = args[0];
                Map map = getKeyAndValue(object);
                params = JSON.toJSONString(map);
            }else {
                params = "未接受到请求参数";
            }
        }else {
            params = queryString;
        }
        LogUtil.printInfoLog("接口请求路由："+url+"\n请求执行方法："+method.getDeclaringClass().getName()+"."+method.getName()+"\n接口请求参数:"+ params);
    }

    /**
     * 请求controller之后
     * @param joinPoint 切点
     */
    @AfterReturning(value = "within(com.xld.web.controller.*)",returning = "rvt")
    public void after(JoinPoint joinPoint,Object rvt){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        LogUtil.printInfoLog("请求接口方法："+method.getDeclaringClass().getName()+"."+method.getName()+"\n请求返回数据:"+ new Gson().toJson(rvt));
    }

    /**
     * 获取对象中的可key和value对应的数据
     * @param obj 对象
     * @return 返回map
     */
    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>(16);
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /** 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            // 设置些属性是可以访问的
            f.setAccessible(true);
            Object val;
            try {
                val = f.get(obj);
                // 得到此属性的值 设置键值
                map.put(f.getName(), val);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
