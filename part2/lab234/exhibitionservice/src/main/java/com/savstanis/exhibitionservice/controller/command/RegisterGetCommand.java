package com.savstanis.exhibitionservice.controller.command;

import com.savstanis.exhibitionservice.service.auth.AuthService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterGetCommand implements Command {
    private AuthService authService;

    final static Logger logger = Logger.getLogger(RegisterGetCommand.class);

    public RegisterGetCommand(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
    }
}
