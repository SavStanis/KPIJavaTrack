package com.savstanis.exhibitionservice.controller.command;

import com.savstanis.exhibitionservice.model.entity.Exhibition;
import com.savstanis.exhibitionservice.service.unauthorizeduser.UnauthorizedUserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class IndexCommand implements Command {

    private final UnauthorizedUserService unauthorizedUserService;
    final static Logger logger = Logger.getLogger(IndexCommand.class);

    public IndexCommand(UnauthorizedUserService unauthorizedUserService) {
        this.unauthorizedUserService = unauthorizedUserService;
    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Exhibition> exhibitions = null;
        try {
            exhibitions = unauthorizedUserService.getActiveExhibitions();
        } catch (SQLException e) {
            logger.error(e.getStackTrace());

            request.setAttribute("error", "Sorry, some problems on our server");
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        }
        request.setAttribute("exhibitions", exhibitions);
        request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
    }
}
