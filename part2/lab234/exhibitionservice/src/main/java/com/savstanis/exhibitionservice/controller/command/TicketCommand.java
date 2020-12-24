package com.savstanis.exhibitionservice.controller.command;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.savstanis.exhibitionservice.model.dto.TicketDto;
import com.savstanis.exhibitionservice.service.authirizeduser.AuthorizedUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class TicketCommand implements Command {

    private AuthorizedUserService authorizedUserService;

    public TicketCommand(AuthorizedUserService authorizedUserService) {
        this.authorizedUserService = authorizedUserService;
    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ("GET".equals(request.getMethod())) {
            doGet(request, response);
        }

        if ("POST".equals(request.getMethod())) {
            doPost(request, response);
        }
    }

    void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<TicketDto> tickets = null;
        try {
            tickets = authorizedUserService.getUsersTickets((Integer) session.getAttribute("user_id"));
        } catch (SQLException throwables) {
            request.setAttribute("error", "Sorry, some problems on our server");
            request.getRequestDispatcher("/jsp/createExhibition.jsp").forward(request, response);
        }

        request.setAttribute("tickets", tickets);

        request.getRequestDispatcher("/jsp/tickets.jsp").forward(request, response);
    }

    void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String body = request.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();

        int exhibitionId = gson.fromJson(body, JsonObject.class).get("exhibitionId").getAsInt();
        int userId = (int) request.getSession().getAttribute("user_id");

        System.out.println("doPost");

        try {
            authorizedUserService.buyTicket(exhibitionId, userId);
        } catch (SQLException throwables) {
            request.setAttribute("error", "Sorry, some problems on our server");
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        }
    }
}
