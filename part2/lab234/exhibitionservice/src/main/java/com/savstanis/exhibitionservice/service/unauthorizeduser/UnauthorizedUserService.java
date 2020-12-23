package com.savstanis.exhibitionservice.service.unauthorizeduser;

import com.savstanis.exhibitionservice.model.entity.Exhibition;

import java.util.Date;
import java.util.List;

public interface UnauthorizedUserService {
    List<Exhibition> getActiveExhibitions();
    List<Exhibition> getExhibitionsByTitle(String title);
    List<Exhibition> getCheaperExhibitionsThan(double price);
    List<Exhibition> getMoreExpensiveExhibitionsThan(double price);
    List<Exhibition> getExhibitionsByDate(Date date);
}
