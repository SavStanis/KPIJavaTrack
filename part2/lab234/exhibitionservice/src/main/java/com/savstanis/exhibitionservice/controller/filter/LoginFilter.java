package com.savstanis.exhibitionservice.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession();

        System.out.println("Login filter");

        if (session != null && session.getAttribute("email") != null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else  {
            httpServletResponse.sendRedirect("/login");
        }
    }

}
