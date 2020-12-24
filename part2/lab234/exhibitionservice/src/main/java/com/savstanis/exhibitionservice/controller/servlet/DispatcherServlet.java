package com.savstanis.exhibitionservice.controller.servlet;

import com.savstanis.exhibitionservice.controller.command.*;
import com.savstanis.exhibitionservice.service.admin.AdminService;
import com.savstanis.exhibitionservice.service.admin.AdminServiceImpl;
import com.savstanis.exhibitionservice.service.auth.AuthService;
import com.savstanis.exhibitionservice.service.auth.AuthServiceImpl;
import com.savstanis.exhibitionservice.service.authorizeduser.AuthorizedUserService;
import com.savstanis.exhibitionservice.service.authorizeduser.AuthorizedUserServiceImpl;
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
    private Map<String, Command> getCommands;
    private Map<String, Command> postCommands;

    @Override
    public void init() {
        AuthService authService = new AuthServiceImpl();
        UnauthorizedUserService unauthorizedUserService = new UnauthorizedUserServiceImpl();
        AuthorizedUserService authorizedUserService = new AuthorizedUserServiceImpl();
        AdminService adminService = new AdminServiceImpl();

        getCommands = new HashMap<>();
        
        getCommands.put("/", new IndexCommand(unauthorizedUserService));
        getCommands.put("/login", new LoginGetCommand());
        getCommands.put("/register", new RegisterGetCommand(authService));
        getCommands.put("/logout", new LogoutCommand());
        getCommands.put("/tickets", new TicketGetCommand(authorizedUserService));
        getCommands.put("/exhibitions/create", new CreateExhibitionGetCommand());
        getCommands.put("/filter/", new FilterCommand(unauthorizedUserService));
        
        postCommands = new HashMap<>();

        postCommands.put("/login", new LoginPostCommand(authService));
        postCommands.put("/register", new RegisterPostCommand(authService));
        postCommands.put("/tickets", new TicketPostCommand(authorizedUserService));
        postCommands.put("/exhibitions/create", new CreateExhibitionPostCommand(adminService));
        postCommands.put("/exhibitions/cancel", new CancelExhibitionPostCommand(adminService));

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = null;

        if ("GET".equals(req.getMethod())) {
            command = getCommands.get(req.getServletPath());
        }

        if ("POST".equals(req.getMethod())) {
            command = postCommands.get(req.getServletPath());
        }

        if (command != null) {
            command.run(req, resp);
        }
    }
}
