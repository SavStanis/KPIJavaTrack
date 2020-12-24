package com.savstanis.exhibitionservice.service.authorizeduser;

import com.savstanis.exhibitionservice.exception.InvalidExhibitionException;
import com.savstanis.exhibitionservice.model.dao.DaoFactory;
import com.savstanis.exhibitionservice.model.dao.DaoFactoryImpl;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDao;
import com.savstanis.exhibitionservice.model.dao.ticket.TicketDao;
import com.savstanis.exhibitionservice.model.dto.TicketDto;
import com.savstanis.exhibitionservice.model.entity.Exhibition;
import com.savstanis.exhibitionservice.model.entity.Ticket;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AuthorizedUserServiceImpl implements AuthorizedUserService {

    private final DaoFactory daoFactory;

    public AuthorizedUserServiceImpl() {
        daoFactory = new DaoFactoryImpl();
    }

    @Override
    public void buyTicket(int exhibitionId, int userId) throws SQLException {
        ExhibitionDao exhibitionDao = daoFactory.getExhibitionDao();
        Optional<Exhibition> exhibition = exhibitionDao.getActiveById(exhibitionId);
        exhibitionDao.close();

        if (exhibition.isEmpty()) {
            throw new InvalidExhibitionException();
        }

        Ticket ticket = Ticket.builder()
                .setExhibitionId(exhibitionId)
                .setPrice(exhibition.get().getPrice())
                .setUserId(userId)
                .setPurchaseTime(new Date())
                .build();

        TicketDao ticketDao = daoFactory.getTicketDao();
        ticketDao.create(ticket);
        ticketDao.close();
    }

    @Override
    public List<TicketDto> getUsersTickets(int userId) throws SQLException {
        TicketDao ticketDao = daoFactory.getTicketDao();
        List<TicketDto> result = ticketDao.getExpandedByUserId(userId);
        ticketDao.close();
        return result;
    }
}
