package com.savstanis.exhibitionservice.model.dao;

import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDao;
import com.savstanis.exhibitionservice.model.dao.ticket.TicketDao;
import com.savstanis.exhibitionservice.model.dao.user.UserDao;

import java.sql.SQLException;

public interface DaoFactory {
    UserDao getUserDao() throws SQLException;
    TicketDao getTicketDao() throws SQLException;
    ExhibitionDao getExhibitionDao() throws SQLException;
}
