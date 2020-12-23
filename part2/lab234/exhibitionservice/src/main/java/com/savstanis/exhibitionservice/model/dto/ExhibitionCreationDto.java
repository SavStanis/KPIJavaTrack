package com.savstanis.exhibitionservice.model.dto;

import com.savstanis.exhibitionservice.model.entity.Exhibition;
import lombok.Data;

import java.util.Date;

@Data
public class ExhibitionCreationDto {
    private String title;
    private double price;
    private Date openingDate;
    private Date closingDate;
    private String status;

    public Exhibition toExhibition() {
        return new Exhibition().builder()
                .setTitle(title)
                .setPrice(price)
                .setOpeningDate(openingDate)
                .setClosingDate(closingDate)
                .setStatus(status)
                .build();
    }
}
