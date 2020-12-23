package com.savstanis.exhibitionservice.model.dao.ticket;

import com.savstanis.exhibitionservice.model.dto.TicketDto;
import com.savstanis.exhibitionservice.model.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper {
    public Ticket getTicketFromResultSet(ResultSet resultSet) throws SQLException {
        return Ticket.builder()
                .setId(resultSet.getInt("id"))
                .setUserId(resultSet.getInt("user_id"))
                .setPrice(resultSet.getDouble("price"))
                .setExhibitionId(resultSet.getInt("exhibition_id"))
                .setPurchaseTime(resultSet.getDate("purchase_time"))
                .build();
    }

    public TicketDto getTicketDtoFromResultSet(ResultSet resultSet) throws SQLException {
        TicketDto ticketDto = new TicketDto();

        ticketDto.setId(resultSet.getInt("id"));
        ticketDto.setExhibitionTitle(resultSet.getString("title"));
        ticketDto.setPrice(resultSet.getDouble("price"));
        ticketDto.setPurchaseTime(resultSet.getDate("purchase_time"));

        return ticketDto;
    }
}
