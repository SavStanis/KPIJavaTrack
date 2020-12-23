package com.savstanis.exhibitionservice.service.admin;

import com.savstanis.exhibitionservice.model.dto.ExhibitionCreationDto;

public interface AdminService {
    void createExhibition(ExhibitionCreationDto exhibitionDto);
    void cancelExhibitionById(int exhibitionId);
    int getStatisticsByExhibitionId(int exhibitionId);
}
