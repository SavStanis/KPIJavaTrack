package com.savstanis.exhibitionservice.controller.command;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.savstanis.exhibitionservice.exception.InvalidExhibitionException;
import com.savstanis.exhibitionservice.service.authorizeduser.AuthorizedUserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class TicketPostCommand implements Command {
    private AuthorizedUserService authorizedUserService;
    final static Logger logger = Logger.getLogger(TicketPostCommand.class);

    public TicketPostCommand(AuthorizedUserService authorizedUserService) {
        this.authorizedUserService = authorizedUserService;
    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = request.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();

        int exhibitionId = gson.fromJson(body, JsonObject.class).get("exhibitionId").getAsInt();
        int userId = (int) request.getSession().getAttribute("user_id");

        try {
            authorizedUserService.buyTicket(exhibitionId, userId);
        } catch (SQLException e) {
            logger.error(e.getStackTrace());

            request.setAttribute("error", "Sorry, some problems on our server");
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        } catch (InvalidExhibitionException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        }
    }
}
