package com.savstanis.exhibitionservice.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TicketDto {
    private int id;
    private String exhibitionTitle;
    private Double price;
    private Date purchaseTime;

    public TicketDto() {
    }

    public TicketDto(int id, String exhibitionTitle, Double price, Date purchaseTime) {
        this.id = id;
        this.exhibitionTitle = exhibitionTitle;
        this.price = price;
        this.purchaseTime = purchaseTime;
    }
}
