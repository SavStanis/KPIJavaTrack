package com.savstanis.exhibitionservice.service.admin;

import com.savstanis.exhibitionservice.model.dto.ExhibitionCreationDto;

import java.sql.SQLException;

public interface AdminService {
    void createExhibition(ExhibitionCreationDto exhibitionDto) throws SQLException;
    void cancelExhibitionById(int exhibitionId) throws SQLException;
    int getStatisticsByExhibitionId(int exhibitionId) throws SQLException;
}
