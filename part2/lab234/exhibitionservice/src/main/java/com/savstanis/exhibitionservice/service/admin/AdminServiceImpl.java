package com.savstanis.exhibitionservice.service.admin;

import com.savstanis.exhibitionservice.model.ConnectionPoolSupplier;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDao;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDaoImpl;
import com.savstanis.exhibitionservice.model.dao.ticket.TicketDao;
import com.savstanis.exhibitionservice.model.dao.ticket.TicketDaoImpl;
import com.savstanis.exhibitionservice.model.dto.ExhibitionCreationDto;
import com.savstanis.exhibitionservice.model.entity.Exhibition;

import java.sql.SQLException;
import java.util.Optional;

public class AdminServiceImpl implements AdminService {

    private ExhibitionDao exhibitionDao;
    private TicketDao ticketDao;

    public AdminServiceImpl() {
        try {
            this.exhibitionDao = new ExhibitionDaoImpl(ConnectionPoolSupplier.getDataSource().getConnection());
            this.ticketDao = new TicketDaoImpl(ConnectionPoolSupplier.getDataSource().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createExhibition(ExhibitionCreationDto exhibitionDto) {
        exhibitionDao.create(exhibitionDto.toExhibition());
    }

    @Override
    public void cancelExhibitionById(int exhibitionId) {
        Optional<Exhibition> exhibitionOptional = exhibitionDao.getActiveById(exhibitionId);

        if (exhibitionOptional.isEmpty()) {
            return;
        }

        Exhibition exhibition = exhibitionOptional.get();
        exhibition.setStatus("canceled");

        exhibitionDao.update(exhibition);
    }

    @Override
    public int getStatisticsByExhibitionId(int exhibitionId) {
        return ticketDao.countTicketsByExhibitionId(exhibitionId);
    }
}
