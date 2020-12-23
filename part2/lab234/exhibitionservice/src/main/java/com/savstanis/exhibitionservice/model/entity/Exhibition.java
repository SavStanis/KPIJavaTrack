package com.savstanis.exhibitionservice.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Exhibition {
    private int id;
    private String title;
    private double price;
    private Date openingDate;
    private Date closingDate;
    private String status;

    public static ExhibitionBuilder builder() {
        return new ExhibitionBuilder();
    }

    public static class ExhibitionBuilder {
        private int id;
        private String title;
        private double price;
        private Date openingDate;
        private Date closingDate;
        private String status;

        public ExhibitionBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ExhibitionBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ExhibitionBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public ExhibitionBuilder setOpeningDate(Date openingDate) {
            this.openingDate = openingDate;
            return this;
        }

        public ExhibitionBuilder setClosingDate(Date closingDate) {
            this.closingDate = closingDate;
            return this;
        }

        public ExhibitionBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Exhibition build() {
            Exhibition exhibition = new Exhibition();

            exhibition.setId(id);
            exhibition.setTitle(title);
            exhibition.setPrice(price);
            exhibition.setOpeningDate(openingDate);
            exhibition.setClosingDate(closingDate);
            exhibition.setStatus(status);

            return exhibition;
        }
    }
}
