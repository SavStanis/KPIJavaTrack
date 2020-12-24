package com.savstanis.exhibitionservice.service.authorizeduser;

import com.savstanis.exhibitionservice.model.dto.TicketDto;

import java.sql.SQLException;
import java.util.List;

public interface AuthorizedUserService {
    void buyTicket(int exhibitionId, int userId) throws SQLException;
    List<TicketDto> getUsersTickets(int userId) throws SQLException;
}
