package com.savstanis.exhibitionservice.controller.command;

import com.savstanis.exhibitionservice.service.auth.AuthService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginGetCommand implements Command {
    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}
