package com.xld.web.config;

import com.xld.web.interceptor.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.util.List;

/**
 * @description 拦截器配置
 * @author xld
 * @date 2019/09/29
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    /** 配置拦截器 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对所有web请求拦截
        registry.addInterceptor(new WebInterceptor()).addPathPatterns("/web/**")
                //排除不拦截
                .excludePathPatterns("/web/user/**");
    }

    /**
     * 自定义消息转换
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        ResultMessageConverter resultMessageConverter = new ResultMessageConverter();
        converters.add(resultMessageConverter);
    }
}
