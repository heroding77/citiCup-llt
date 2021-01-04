package com.dzc.llt.Config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author:dzc
 * @date 2021-01-04 9:40
 */

@WebFilter(filterName = "sessionFilter",urlPatterns = {"/*"})
public class SessionFilter implements Filter {

    //不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{"/login","/","/register","/login_data","注册.html","/MailService"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession(false);
        String url = request.getRequestURI();
        //System.out.println("filter-url:"+url);
        boolean needFilter = isNeedFilter(url);
        if(!needFilter){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            // 验证登录状态
            if(session != null){
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                response.sendRedirect("/login");
            }
        }

    }

    public boolean isNeedFilter(String uri) {
        for (String includeUrl : includeUrls) {
            if(includeUrl.equals(uri)) {
                return false;
            }
            if(uri.contains(".css")||uri.contains(".js")||uri.contains(".jpg")||uri.contains(".png")){
                return false;
            }
        }

        return true;
    }


    @Override
    public void destroy() {

    }
}
