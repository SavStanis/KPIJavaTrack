package com.savstanis.exhibitionservice.controller.command;

import com.savstanis.exhibitionservice.model.dto.RegisterDto;
import com.savstanis.exhibitionservice.service.auth.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements Command {

    private AuthService authService;

    public RegisterCommand(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("GET".equals(request.getMethod())) {
            doGet(request, response);
            return;
        }

        if ("POST".equals(request.getMethod())) {
            doPost(request, response);
        }
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegisterDto registerDto = new RegisterDto();

        registerDto.setEmail(request.getParameter("email"));
        registerDto.setName(request.getParameter("name"));
        registerDto.setPassword(request.getParameter("password"));

        boolean result = authService.register(registerDto);

        if (!result) {
            request.setAttribute("error", "User already exists");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "User was registered");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}
