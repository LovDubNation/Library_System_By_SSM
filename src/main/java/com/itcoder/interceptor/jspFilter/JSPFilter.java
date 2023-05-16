package com.itcoder.interceptor.jspFilter;

import com.itcoder.utils.URIUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.Finishings;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JSPFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSPFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //实现直接访问jsp页面的过滤
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith(".jsp")){
            //跳转到DispatcherServlet可以拦截的请求
            String uriName = URIUtils.getURIName(URIUtils.getLastURI((requestURI)));
            LOGGER.info("用户直接访问jsp，交给"+"/index/"+uriName+"Index"+"处理");
            servletRequest.getRequestDispatcher("/index/"+uriName+"Index").forward(servletRequest,servletResponse);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
