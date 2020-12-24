package com.savstanis.exhibitionservice.controller.command;

import com.savstanis.exhibitionservice.model.dto.LoginDto;
import com.savstanis.exhibitionservice.model.entity.User;
import com.savstanis.exhibitionservice.service.auth.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginCommand implements Command {

    private AuthService authService;

    public LoginCommand(AuthService authService) {
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
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(request.getParameter("email"));
        loginDto.setPassword(request.getParameter("password"));

        User user = null;
        try {
            user = authService.login(loginDto);
        } catch (SQLException throwables) {
            request.setAttribute("error", "Sorry, some problems on our server");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }

        if (user != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user_id", user.getId());
            httpSession.setAttribute("email", user.getEmail());
            httpSession.setAttribute("role", user.getRole());
            httpSession.setMaxInactiveInterval(3600);

            response.sendRedirect(request.getContextPath() + "/");
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}
