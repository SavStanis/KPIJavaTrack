package com.savstanis.exhibitionservice.controller.command;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.savstanis.exhibitionservice.service.admin.AdminService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class CancelExhibitionPostCommand implements Command{
    private AdminService adminService;

    final static Logger logger = Logger.getLogger(CancelExhibitionPostCommand.class);

    public CancelExhibitionPostCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String body = request.getReader().lines().collect(Collectors.joining());
            Gson gson = new Gson();

            int exhibitionId = gson.fromJson(body, JsonObject.class).get("exhibitionId").getAsInt();

            adminService.cancelExhibitionById(exhibitionId);
        } catch (SQLException e) {
            logger.error(e.getStackTrace());
            request.setAttribute("error", "Sorry, some problems on our server");
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        }
    }
}
