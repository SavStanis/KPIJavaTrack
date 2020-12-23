package com.savstanis.exhibitionservice.service.authirizeduser;

import com.savstanis.exhibitionservice.model.ConnectionPoolSupplier;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDao;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDaoImpl;
import com.savstanis.exhibitionservice.model.dao.ticket.TicketDao;
import com.savstanis.exhibitionservice.model.dao.ticket.TicketDaoImpl;
import com.savstanis.exhibitionservice.model.dto.TicketDto;
import com.savstanis.exhibitionservice.model.entity.Exhibition;
import com.savstanis.exhibitionservice.model.entity.Ticket;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AuthorizedUserServiceImpl implements AuthorizedUserService {

    TicketDao ticketDao;
    ExhibitionDao exhibitionDao;

    public AuthorizedUserServiceImpl() {
        try {
            this.exhibitionDao = new ExhibitionDaoImpl(ConnectionPoolSupplier.getDataSource().getConnection());
            this.ticketDao = new TicketDaoImpl(ConnectionPoolSupplier.getDataSource().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buyTicket(int exhibitionId, int userId) {
        System.out.println("buyTicket");
        Optional<Exhibition> exhibition = exhibitionDao.getActiveById(exhibitionId);

        if (exhibition.isEmpty()) {
            return;
        }

        System.out.println("Helloi" + exhibition.get());

        Ticket ticket = Ticket.builder()
                .setExhibitionId(exhibitionId)
                .setPrice(exhibition.get().getPrice())
                .setUserId(userId)
                .setPurchaseTime(new Date())
                .build();

        System.out.println(ticket);

        ticketDao.create(ticket);
    }

    @Override
    public List<TicketDto> getUsersTickets(int userId) {
        return ticketDao.getExpandedByUserId(userId);
    }
}
