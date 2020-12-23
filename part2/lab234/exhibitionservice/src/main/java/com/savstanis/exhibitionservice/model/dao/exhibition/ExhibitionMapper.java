package com.savstanis.exhibitionservice.model.dao.exhibition;

import com.savstanis.exhibitionservice.model.entity.Exhibition;
import com.savstanis.exhibitionservice.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExhibitionMapper {
    public Exhibition getExhibitionFromResultSet(ResultSet resultSet) throws SQLException {
        return Exhibition.builder()
                .setId(resultSet.getInt("id"))
                .setPrice(resultSet.getDouble("ticket_price"))
                .setTitle(resultSet.getString("title"))
                .setStatus(resultSet.getString("status"))
                .setOpeningDate(resultSet.getDate("opening_date"))
                .setClosingDate(resultSet.getDate("closing_date"))
                .build();
    }
}
