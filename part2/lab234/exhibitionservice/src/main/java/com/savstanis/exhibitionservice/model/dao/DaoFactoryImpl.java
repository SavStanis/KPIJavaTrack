package com.savstanis.exhibitionservice.model.dao;

import com.savstanis.exhibitionservice.model.ConnectionPoolSupplier;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDao;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDaoImpl;
import com.savstanis.exhibitionservice.model.dao.ticket.TicketDao;
import com.savstanis.exhibitionservice.model.dao.ticket.TicketDaoImpl;
import com.savstanis.exhibitionservice.model.dao.user.UserDao;
import com.savstanis.exhibitionservice.model.dao.user.UserDaoImpl;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DaoFactoryImpl implements DaoFactory {
    private final DataSource dataSource;

    public DaoFactoryImpl() {
        this.dataSource = ConnectionPoolSupplier.getDataSource();
    }

    @Override
    public UserDao getUserDao() throws SQLException {
        return new UserDaoImpl(dataSource.getConnection());
    }

    @Override
    public TicketDao getTicketDao() throws SQLException {
        return new TicketDaoImpl(dataSource.getConnection());
    }

    @Override
    public ExhibitionDao getExhibitionDao() throws SQLException {
        return new ExhibitionDaoImpl(dataSource.getConnection());
    }
}
