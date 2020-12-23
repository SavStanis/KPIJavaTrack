package com.savstanis.exhibitionservice.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TicketDto {
    private int id;
    private String exhibitionTitle;
    private Double price;
    private Date purchaseTime;
}
