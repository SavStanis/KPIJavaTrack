package com.savstanis.exhibitionservice.controller.command;

import com.savstanis.exhibitionservice.exception.InvalidDateException;
import com.savstanis.exhibitionservice.exception.InvalidPriceException;
import com.savstanis.exhibitionservice.model.dto.ExhibitionCreationDto;
import com.savstanis.exhibitionservice.service.admin.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateExhibition implements Command{
    private AdminService adminService;

    public CreateExhibition(AdminService adminService) {
        this.adminService = adminService;
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
        request.getRequestDispatcher("/jsp/createExhibition.jsp").forward(request, response);
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExhibitionCreationDto exhibitionCreationDto = new ExhibitionCreationDto();

        try {
            exhibitionCreationDto.setTitle(request.getParameter("title"));
            exhibitionCreationDto.setPrice(Double.parseDouble(request.getParameter("price")));
            exhibitionCreationDto.setStatus("active");
            exhibitionCreationDto.setOpeningDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("openingDate")));
            exhibitionCreationDto.setClosingDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("closingDate")));

            adminService.createExhibition(exhibitionCreationDto);

            response.sendRedirect(request.getContextPath() + "/");
        } catch (ParseException | SQLException e) {
            request.setAttribute("error", "Sorry, some problems on our server");
            request.getRequestDispatcher("/jsp/createExhibition.jsp").forward(request, response);
        } catch (InvalidDateException | InvalidPriceException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/jsp/createExhibition.jsp").forward(request, response);
        }
    }
}
