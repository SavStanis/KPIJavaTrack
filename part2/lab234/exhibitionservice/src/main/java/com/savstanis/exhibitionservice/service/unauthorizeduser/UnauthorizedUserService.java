package com.savstanis.exhibitionservice.service.unauthorizeduser;

import com.savstanis.exhibitionservice.model.entity.Exhibition;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface UnauthorizedUserService {
    List<Exhibition> getActiveExhibitions() throws SQLException;
    List<Exhibition> getExhibitionsByTitle(String title) throws SQLException;
    List<Exhibition> getCheaperExhibitionsThan(double price) throws SQLException;
    List<Exhibition> getMoreExpensiveExhibitionsThan(double price) throws SQLException;
    List<Exhibition> getExhibitionsByDate(Date date) throws SQLException;
}
