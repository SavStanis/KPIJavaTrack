package com.savstanis.exhibitionservice.controller.command;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.savstanis.exhibitionservice.service.admin.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class CancelExhibition implements Command {

    private AdminService adminService;

    public CancelExhibition(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("GET".equals(request.getMethod())) {
            response.sendRedirect(request.getContextPath() + "/");
        }

        if ("POST".equals(request.getMethod())) {
            doPost(request, response);
        }
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();

        int exhibitionId = gson.fromJson(body, JsonObject.class).get("exhibitionId").getAsInt();

        adminService.cancelExhibitionById(exhibitionId);
    }
}
