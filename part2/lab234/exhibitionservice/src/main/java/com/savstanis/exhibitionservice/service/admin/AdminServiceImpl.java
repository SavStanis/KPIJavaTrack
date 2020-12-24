package com.savstanis.exhibitionservice.service.admin;

import com.savstanis.exhibitionservice.exception.InvalidDateException;
import com.savstanis.exhibitionservice.exception.InvalidPriceException;
import com.savstanis.exhibitionservice.model.ConnectionPoolSupplier;
import com.savstanis.exhibitionservice.model.dao.DaoFactory;
import com.savstanis.exhibitionservice.model.dao.DaoFactoryImpl;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDao;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDaoImpl;
import com.savstanis.exhibitionservice.model.dao.ticket.TicketDao;
import com.savstanis.exhibitionservice.model.dao.ticket.TicketDaoImpl;
import com.savstanis.exhibitionservice.model.dto.ExhibitionCreationDto;
import com.savstanis.exhibitionservice.model.entity.Exhibition;
import com.savstanis.exhibitionservice.validator.Validator;

import java.sql.SQLException;
import java.util.Optional;

public class AdminServiceImpl implements AdminService {

    private final DaoFactory daoFactory;

    public AdminServiceImpl() {
        this.daoFactory = new DaoFactoryImpl();
    }

    @Override
    public void createExhibition(ExhibitionCreationDto exhibitionDto) throws SQLException, InvalidDateException, InvalidPriceException {
        Validator.validateOpeningClosingDates(exhibitionDto.getOpeningDate(), exhibitionDto.getClosingDate());
        Validator.validatePrice(exhibitionDto.getPrice());
        Validator.validateExhibitionTitle(exhibitionDto.getTitle());

        ExhibitionDao exhibitionDao = daoFactory.getExhibitionDao();
        exhibitionDao.create(exhibitionDto.toExhibition());
        exhibitionDao.close();
    }

    @Override
    public void cancelExhibitionById(int exhibitionId) throws SQLException {
        ExhibitionDao exhibitionDao = daoFactory.getExhibitionDao();
        Optional<Exhibition> exhibitionOptional = exhibitionDao.getActiveById(exhibitionId);

        if (exhibitionOptional.isEmpty()) {
            return;
        }

        Exhibition exhibition = exhibitionOptional.get();
        exhibition.setStatus("canceled");

        exhibitionDao.update(exhibition);
        exhibitionDao.close();
    }

    @Override
    public int getStatisticsByExhibitionId(int exhibitionId) throws SQLException {
        TicketDao ticketDao = daoFactory.getTicketDao();
        int tickets = ticketDao.countTicketsByExhibitionId(exhibitionId);
        ticketDao.close();
        return tickets;
    }
}
