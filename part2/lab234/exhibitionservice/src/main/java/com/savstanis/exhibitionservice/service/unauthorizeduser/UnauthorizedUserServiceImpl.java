package com.savstanis.exhibitionservice.service.unauthorizeduser;

import com.savstanis.exhibitionservice.model.dao.DaoFactory;
import com.savstanis.exhibitionservice.model.dao.DaoFactoryImpl;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDao;
import com.savstanis.exhibitionservice.model.entity.Exhibition;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UnauthorizedUserServiceImpl implements UnauthorizedUserService{

    private final DaoFactory daoFactory;

    public UnauthorizedUserServiceImpl() {
        daoFactory = new DaoFactoryImpl();
    }

    @Override
    public List<Exhibition> getActiveExhibitions() throws SQLException {
        ExhibitionDao exhibitionDao = daoFactory.getExhibitionDao();
        List<Exhibition> exhibitions =  exhibitionDao.getActive();
        exhibitionDao.close();

        return exhibitions;
    }

    @Override
    public List<Exhibition> getExhibitionsByTitle(String title) throws SQLException {
        ExhibitionDao exhibitionDao = daoFactory.getExhibitionDao();
        List<Exhibition> exhibitions =  exhibitionDao.getActiveByTitle(title);
        exhibitionDao.close();

        return exhibitions;
    }

    @Override
    public List<Exhibition> getCheaperExhibitionsThan(double price) throws SQLException {
        ExhibitionDao exhibitionDao = daoFactory.getExhibitionDao();
        List<Exhibition> exhibitions =  exhibitionDao.getCheaperThan(price);
        exhibitionDao.close();

        return exhibitions;
    }

    @Override
    public List<Exhibition> getMoreExpensiveExhibitionsThan(double price) throws SQLException {
        ExhibitionDao exhibitionDao = daoFactory.getExhibitionDao();
        List<Exhibition> exhibitions =  exhibitionDao.getMoreExpensiveThan(price);
        exhibitionDao.close();

        return exhibitions;
    }

    @Override
    public List<Exhibition> getExhibitionsByDate(Date date) throws SQLException {
        ExhibitionDao exhibitionDao = daoFactory.getExhibitionDao();
        List<Exhibition> exhibitions =  exhibitionDao.getByDate(date);
        exhibitionDao.close();

        return exhibitions;
    }
}
