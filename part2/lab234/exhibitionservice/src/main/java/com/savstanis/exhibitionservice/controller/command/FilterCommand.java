package com.savstanis.exhibitionservice.controller.command;

import com.savstanis.exhibitionservice.service.unauthorizeduser.UnauthorizedUserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilterCommand implements Command {

    private final UnauthorizedUserService unauthorizedUserService;
    final static Logger logger = Logger.getLogger(FilterCommand.class);

    public FilterCommand(UnauthorizedUserService unauthorizedUserService) {
        this.unauthorizedUserService = unauthorizedUserService;
    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("GET".equals(request.getMethod())) {
            doGet(request, response);
        }
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        try {
            switch (type) {
                case "byTitle": {
                    String title = request.getParameter("title");
                    System.out.println("Here");
                    request.setAttribute("exhibitions", unauthorizedUserService.getExhibitionsByTitle(title));
                    break;
                }
                case "cheaper": {
                    double price = Double.parseDouble(request.getParameter("price"));
                    request.setAttribute("exhibitions", unauthorizedUserService.getCheaperExhibitionsThan(price));
                    break;
                }
                case "expensive": {
                    double price = Double.parseDouble(request.getParameter("price"));
                    request.setAttribute("exhibitions", unauthorizedUserService.getMoreExpensiveExhibitionsThan(price));
                    break;
                }
                case "byDate": {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
                    request.setAttribute("exhibitions", unauthorizedUserService.getExhibitionsByDate(date));
                    break;
                }
                default: {
                    request.setAttribute("exhibitions", unauthorizedUserService.getActiveExhibitions());
                    break;
                }
            }
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        } catch (ParseException | SQLException e) {
            logger.error(e.getStackTrace());

            request.setAttribute("error", "Sorry, some problems on our server");
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        }
    }
}
