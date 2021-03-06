package com.savstanis.exhibitionservice.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command{

    final static Logger logger = Logger.getLogger(LogoutCommand.class);

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("email");
        request.getSession().invalidate();
        logger.info("User with email " + email + " was logged out");
        response.sendRedirect(request.getContextPath() + "/");
    }
}
