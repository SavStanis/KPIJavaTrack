package com.savstanis.exhibitionservice.controller.filter;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();

        if (session != null && session.getAttribute("email") != null && "admin".equals(session.getAttribute("role"))) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else  {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
        }
    }
}
