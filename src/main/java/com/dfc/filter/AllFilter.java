package com.dfc.filter;


import lombok.extern.slf4j.Slf4j;
import com.dfc.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zsh
 * @Date:12:10 2018/5/11
 * @Description:
 */
@WebFilter(filterName = "AllFilter",urlPatterns = "/*")
@Slf4j
public class AllFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestURI = httpRequest.getRequestURI();
        if (requestURI.equals("/") || requestURI.contains("login") || requestURI.contains("images") ||
                requestURI.contains("js") || requestURI.contains("signin/to") || requestURI.contains("signin/record")||requestURI.contains("signin/hasTodaySigned")|| requestURI.contains("css")
                || requestURI.contains("/course/findByTime") || requestURI.contains("/signin/add")||requestURI.contains("/student")||requestURI.contains("/course")||requestURI.contains("/face")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            User user = (User) httpRequest.getSession().getAttribute("user");
            String returnUrl = "/";
            if (user != null){
                log.info("请求的url"+returnUrl);
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                httpRequest.setCharacterEncoding("UTF-8");
                // 转码
                httpResponse.setContentType("text/html; charset=UTF-8");
                httpResponse.getWriter().println("<script language=\"javascript\">" + "alert(\"登录失效！请重新登录\");"
                        + "if(window.opener==null){window.top.location.href=\"" + returnUrl
                        + "\";}else{window.opener.top.location.href=\"" + returnUrl + "\";window.close();}</script>");
                httpResponse.getWriter().close();
                return;
            }
        }
    }

    @Override
    public void destroy() {
        log.info("过滤器销毁");
    }
}
