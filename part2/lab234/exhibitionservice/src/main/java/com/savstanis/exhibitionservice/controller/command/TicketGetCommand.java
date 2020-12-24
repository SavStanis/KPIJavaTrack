package com.savstanis.exhibitionservice.controller.command;

import com.savstanis.exhibitionservice.model.dto.TicketDto;
import com.savstanis.exhibitionservice.service.authorizeduser.AuthorizedUserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TicketGetCommand implements Command{
    private AuthorizedUserService authorizedUserService;
    final static Logger logger = Logger.getLogger(TicketGetCommand.class);

    public TicketGetCommand(AuthorizedUserService authorizedUserService) {
        this.authorizedUserService = authorizedUserService;
    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
