package com.savstanis.exhibitionservice.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Ticket {
    private int id;
    private int userId;
    private int exhibitionId;
    private double price;
    private Date purchaseTime;

    public static TicketBuilder builder() {
        return new TicketBuilder();
    }

    public static class TicketBuilder {
        private int id;
        private int userId;
        private int exhibitionId;
        private double price;
        private Date purchaseTime;

        public TicketBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public TicketBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public TicketBuilder setExhibitionId(int exhibitionId) {
            this.exhibitionId = exhibitionId;
            return this;
        }

        public TicketBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public TicketBuilder setPurchaseTime(Date purchaseTime) {
            this.purchaseTime = purchaseTime;
            return this;
        }

        public Ticket build() {
            Ticket ticket = new Ticket();

            ticket.setId(id);
            ticket.setExhibitionId(exhibitionId);
            ticket.setPrice(price);
            ticket.setPurchaseTime(purchaseTime);
            ticket.setUserId(userId);

            return ticket;
        }


    }
}
