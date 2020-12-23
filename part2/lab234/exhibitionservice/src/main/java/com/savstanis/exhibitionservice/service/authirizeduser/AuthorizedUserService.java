package com.savstanis.exhibitionservice.service.authirizeduser;

import com.savstanis.exhibitionservice.model.dto.TicketDto;
import com.savstanis.exhibitionservice.model.entity.Ticket;

import java.util.List;

public interface AuthorizedUserService {
    void buyTicket(int exhibitionId, int userId);
    List<TicketDto> getUsersTickets(int userId);
}
