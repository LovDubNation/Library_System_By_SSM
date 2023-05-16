package com.itcoder.config;

import com.itcoder.interceptor.MainInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@ComponentScan("com.itcoder.controller")
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer {
    /**
     * 开启静态资源访问不被拦截
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 配置视图解析器
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/pages/",".jsp");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MainInterceptor()).addPathPatterns("/index/**").excludePathPatterns("/index/loginIndex");
    }
}
