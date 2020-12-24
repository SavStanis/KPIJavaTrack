package com.savstanis.exhibitionservice.model.dao.ticket;

import com.savstanis.exhibitionservice.model.dto.TicketDto;
import com.savstanis.exhibitionservice.model.entity.Ticket;

import java.util.List;

public interface TicketDao {
    void create(Ticket ticket);
    List<Ticket> getByUserId(int userId);
    List<Ticket> getByExhibitionId(int exhibitionId);

    List<TicketDto> getExpandedByUserId(int userId);
    List<TicketDto> getExpandedByExhibitionId(int exhibitionId);

    int countTicketsByExhibitionId(int exhibitionId);
    void close();
}
