package com.savstanis.exhibitionservice.controller.command;

import com.savstanis.exhibitionservice.exception.InvalidDateException;
import com.savstanis.exhibitionservice.exception.InvalidExhibitionTitleException;
import com.savstanis.exhibitionservice.exception.InvalidPriceException;
import com.savstanis.exhibitionservice.model.dto.ExhibitionCreationDto;
import com.savstanis.exhibitionservice.service.admin.AdminService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateExhibitionPostCommand implements Command {

    private AdminService adminService;
    final static Logger logger = Logger.getLogger(CreateExhibitionPostCommand.class);

    public CreateExhibitionPostCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            logger.error(e.getStackTrace());

            request.setAttribute("error", "Sorry, some problems on our server");
            request.getRequestDispatcher("/jsp/createExhibition.jsp").forward(request, response);
        } catch (InvalidDateException | InvalidPriceException | InvalidExhibitionTitleException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/jsp/createExhibition.jsp").forward(request, response);
        }
    }
}
