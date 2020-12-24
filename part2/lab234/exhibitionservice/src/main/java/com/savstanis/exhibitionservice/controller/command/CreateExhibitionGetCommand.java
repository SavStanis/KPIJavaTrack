package com.savstanis.exhibitionservice.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateExhibitionGetCommand implements Command {
    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/createExhibition.jsp").forward(request, response);
    }
}
