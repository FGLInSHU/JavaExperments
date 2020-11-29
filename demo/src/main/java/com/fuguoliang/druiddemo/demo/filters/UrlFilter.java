package com.fuguoliang.druiddemo.demo.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author FGL_S
 */
@WebFilter(filterName = "urlFilter",urlPatterns = "/*")
public class UrlFilter implements Filter {
    private static String LoginUrl = "/login";
    private static String DefaultUrl = "/hello/find";
    private static ArrayList<String> whiteUrls;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("[filter] init " + new Date());
        whiteUrls = new ArrayList<String>();
        whiteUrls.add("/hello/find");
        whiteUrls.add("/hello/put");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("[filter] doFilter " + new Date());
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("[filter] doFilter, uri is:" + request.getRequestURI() + ".");
        System.out.println("[filter] doFilter, url is:" + request.getRequestURL() + ".");

        if (LoginUrl.equals(request.getRequestURI()) || whiteUrls.contains(request.getRequestURI())) {
            System.out.println("[filter] doFilter, no need to redirect");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //检测用户是否登录
        HttpSession session =request.getSession();
        try{
            System.out.println("[filter] doFilter , redirect to /hello/find");
            response.sendRedirect(DefaultUrl);
        }catch (Exception e){
            System.out.println("[filter] doFilter, error is:" + e);
        }
    }

    @Override
    public void destroy() {
        System.out.println("[filter] destroy " + new Date());
    }
}
