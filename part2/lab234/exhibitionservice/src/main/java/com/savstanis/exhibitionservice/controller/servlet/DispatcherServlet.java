package com.savstanis.exhibitionservice.controller.servlet;

import com.savstanis.exhibitionservice.controller.command.*;
import com.savstanis.exhibitionservice.service.admin.AdminService;
import com.savstanis.exhibitionservice.service.admin.AdminServiceImpl;
import com.savstanis.exhibitionservice.service.auth.AuthService;
import com.savstanis.exhibitionservice.service.auth.AuthServiceImpl;
import com.savstanis.exhibitionservice.service.authirizeduser.AuthorizedUserService;
import com.savstanis.exhibitionservice.service.authirizeduser.AuthorizedUserServiceImpl;
import com.savstanis.exhibitionservice.service.unauthorizeduser.UnauthorizedUserService;
import com.savstanis.exhibitionservice.service.unauthorizeduser.UnauthorizedUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DispatcherServlet", urlPatterns = {"/login", "/register", "/"})
public class DispatcherServlet extends HttpServlet {
    private Map<String, Command> commands;

    @Override
    public void init() {
        AuthService authService = new AuthServiceImpl();
        UnauthorizedUserService unauthorizedUserService = new UnauthorizedUserServiceImpl();
        AuthorizedUserService authorizedUserService = new AuthorizedUserServiceImpl();
        AdminService adminService = new AdminServiceImpl();

        commands = new HashMap<>();

        commands.put("/", new IndexCommand(unauthorizedUserService));
        commands.put("/login", new LoginCommand(authService));
        commands.put("/register", new RegisterCommand(authService));
        commands.put("/logout", new LogoutCommand());
        commands.put("/tickets", new TicketCommand(authorizedUserService));
        commands.put("/exhibitions/create", new CreateExhibition(adminService));
        commands.put("/exhibitions/cancel", new CancelExhibition(adminService));
        commands.put("/filter/", new FilterCommand(unauthorizedUserService));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = commands.get(req.getServletPath());

        if (command != null) {
            command.run(req, resp);
        }
    }
}
