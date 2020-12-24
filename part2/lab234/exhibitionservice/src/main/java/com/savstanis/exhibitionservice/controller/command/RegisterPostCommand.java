package com.savstanis.exhibitionservice.controller.command;

import com.savstanis.exhibitionservice.exception.InvalidEmailException;
import com.savstanis.exhibitionservice.exception.InvalidPasswordException;
import com.savstanis.exhibitionservice.exception.UserAlreadyExistsException;
import com.savstanis.exhibitionservice.model.dto.RegisterDto;
import com.savstanis.exhibitionservice.service.auth.AuthService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterPostCommand implements Command {
    private AuthService authService;

    final static Logger logger = Logger.getLogger(RegisterPostCommand.class);

    public RegisterPostCommand(AuthService authService) {
        this.authService = authService;
    }
    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegisterDto registerDto = new RegisterDto();

        registerDto.setEmail(request.getParameter("email"));
        registerDto.setName(request.getParameter("name"));
        registerDto.setPassword(request.getParameter("password"));

        boolean result = false;
        try {
            authService.register(registerDto);
            logger.warn("User registered with email " + registerDto.getEmail());
            request.setAttribute("message", "User was registered");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        } catch (SQLException e) {
            logger.error(e.getStackTrace());

            request.setAttribute("error", "Sorry, some problems on our server");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        } catch (InvalidEmailException | InvalidPasswordException | UserAlreadyExistsException e ) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        }
    }
}
