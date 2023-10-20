package com.example.bootvueadmin.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AuthFilter implements Filter {

    // 白名单
    // 把不需要登录就可以访问的请求放到白名单中
    private static final String[] WHITE_URLS = {"/login.html", "/register.html", "/user/login", "/user/register"};
    private static final String[] FILE_SUFFIX = {"jpeg", "jpg","png","gif","bmp", "webp", "css","js", "woff", "woff2"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();
        //禁止浏览器使用缓存，防止退出登录后后退回页面
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");

        // 判断是否是白名单中的请求
        if (Arrays.stream(WHITE_URLS).anyMatch(url -> url.equals(servletPath)) || endWith(servletPath)) {
            filterChain.doFilter(request, response); // 放行请求
        } else {
            Object user = request.getSession().getAttribute("user");
            // 判断是否登录
            if (user != null) {
                filterChain.doFilter(request, response); // 放行请求
            } else {
                response.sendRedirect("/login.html"); // 推荐后台跳转, 防止缓存
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    // 放行静态资源方法一
    private boolean isStaticResource(String path) {
        return path.startsWith("/css/") || path.startsWith("/js/") || path.startsWith("/imgs/") || path.startsWith(
                "/static/");
    }

    // 放行静态资源方法二
    private boolean endWith(String path) {
        for (String fileSuffix : FILE_SUFFIX) {
            if (path.endsWith(fileSuffix)) {
                return true;
            }
        }
        return false;
    }
}
