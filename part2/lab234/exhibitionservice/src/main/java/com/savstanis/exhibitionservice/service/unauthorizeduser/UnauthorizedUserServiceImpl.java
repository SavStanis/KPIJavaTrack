package com.savstanis.exhibitionservice.service.unauthorizeduser;

import com.savstanis.exhibitionservice.model.ConnectionPoolSupplier;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDao;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDaoImpl;
import com.savstanis.exhibitionservice.model.entity.Exhibition;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UnauthorizedUserServiceImpl implements UnauthorizedUserService{

    private ExhibitionDao exhibitionDao;

    public UnauthorizedUserServiceImpl() {
        try {
            this.exhibitionDao = new ExhibitionDaoImpl(ConnectionPoolSupplier.getDataSource().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Exhibition> getActiveExhibitions() {
        return exhibitionDao.getActive();
    }

    @Override
    public List<Exhibition> getExhibitionsByTitle(String title) {
        return exhibitionDao.getActiveByTitle(title);
    }

    @Override
    public List<Exhibition> getCheaperExhibitionsThan(double price) {
        return exhibitionDao.getCheaperThan(price);
    }

    @Override
    public List<Exhibition> getMoreExpensiveExhibitionsThan(double price) {
        return exhibitionDao.getMoreExpensiveThan(price);
    }

    @Override
    public List<Exhibition> getExhibitionsByDate(Date date) {
        return exhibitionDao.getByDate(date);
    }
}
