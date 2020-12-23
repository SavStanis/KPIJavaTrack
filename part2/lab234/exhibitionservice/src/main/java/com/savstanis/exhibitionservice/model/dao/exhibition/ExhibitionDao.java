package com.savstanis.exhibitionservice.model.dao.exhibition;

import com.savstanis.exhibitionservice.model.entity.Exhibition;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExhibitionDao {
    void create(Exhibition exhibition);

    Optional<Exhibition> getById(int id);
    Optional<Exhibition> getActiveById(int id);

    List<Exhibition> getActive();

    List<Exhibition> getByTitle(String title);
    List<Exhibition> getActiveByTitle(String title);

    List<Exhibition> getCheaperThan(double price);
    List<Exhibition> getMoreExpensiveThan(double price);
    List<Exhibition> getByDate(Date date);

    void update(Exhibition exhibition);
}
