package com.savstanis.exhibitionservice.service.authirizeduser;

import com.savstanis.exhibitionservice.model.dto.TicketDto;
import com.savstanis.exhibitionservice.model.entity.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface AuthorizedUserService {
    void buyTicket(int exhibitionId, int userId) throws SQLException;
    List<TicketDto> getUsersTickets(int userId) throws SQLException;
}
