package com.savstanis.exhibitionservice.model.dao.exhibition;

import com.savstanis.exhibitionservice.model.entity.Exhibition;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ExhibitionDaoImpl implements ExhibitionDao {

    private Connection connection;
    private ExhibitionMapper exhibitionMapper;

    public ExhibitionDaoImpl(Connection connection) {
        this.connection = connection;
        exhibitionMapper = new ExhibitionMapper();
    }

    private final String CREATE_EXHIBITION = "insert into exhibitions (title, ticket_price, opening_date, closing_date, status) values (?,?,?,?,?)";
    private final String FIND_EXHIBITION_BY_ID = "select * from exhibitions where id = ?";
    private final String FIND_ACTIVE_EXHIBITION_BY_ID = "select * from exhibitions where id = ? and status = 'active'";
    private final String FIND_ACTIVE_EXHIBITIONS = "select * from exhibitions where status = 'active'";
    private final String FIND_EXHIBITIONS_BY_TITLE = "select * from exhibitions where UPPER(title) like UPPER(CONCAT('%',?,'%'))";
    private final String FIND_ACTIVE_EXHIBITIONS_BY_TITLE = "select * from exhibitions where UPPER(title) like UPPER(CONCAT('%',?,'%')) and status = 'active'";
    private final String FIND_EXHIBITIONS_CHEAPER_THAN = "select * from exhibitions where ticket_price <= ? and status = 'active'";
    private final String FIND_EXHIBITIONS_MORE_EXPENSIVE_THAN = "select * from exhibitions where ticket_price >= ? and status = 'active'";
    private final String FIND_EXHIBITIONS_BY_DATE = "select * from exhibitions where status = 'active' and ? between opening_date and closing_date";
    private final String UPDATE_EXHIBITION = "update exhibitions set title = ?, ticket_price = ?, opening_date = ?, closing_date = ?, status = ? where id = ?";

    @Override
    public void create(Exhibition exhibition) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_EXHIBITION)) {
            preparedStatement.setString(1, exhibition.getTitle());
            preparedStatement.setDouble(2, exhibition.getPrice());
            preparedStatement.setDate(3, new java.sql.Date(exhibition.getOpeningDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(exhibition.getClosingDate().getTime()));
            preparedStatement.setString(5, exhibition.getStatus());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Exhibition> getById(int id) {
        return getExhibitionById(id, FIND_EXHIBITION_BY_ID);
    }

    @Override
    public Optional<Exhibition> getActiveById(int id) {
        return getExhibitionById(id, FIND_ACTIVE_EXHIBITION_BY_ID);
    }

    private Optional<Exhibition> getExhibitionById(int id, String sqlQuery) {
        Exhibition exhibition = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                exhibition = exhibitionMapper.getExhibitionFromResultSet(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(exhibition);
    }

    @Override
    public List<Exhibition> getActive() {
        List<Exhibition> exhibitions = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ACTIVE_EXHIBITIONS);

            while (resultSet.next()) {
                exhibitions.add(exhibitionMapper.getExhibitionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exhibitions;
    }

    @Override
    public List<Exhibition> getByTitle(String title) {
        return getExhibitionsByTitle(title, FIND_EXHIBITIONS_BY_TITLE);
    }

    @Override
    public List<Exhibition> getActiveByTitle(String title) {
        return getExhibitionsByTitle(title, FIND_ACTIVE_EXHIBITIONS_BY_TITLE);
    }

    private List<Exhibition> getExhibitionsByTitle(String title, String sqlQuery) {
        List<Exhibition> exhibitions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                exhibitions.add(exhibitionMapper.getExhibitionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exhibitions;
    }

    @Override
    public List<Exhibition> getCheaperThan(double price) {
        return getExhibitionsByPrice(price, FIND_EXHIBITIONS_CHEAPER_THAN);
    }

    @Override
    public List<Exhibition> getMoreExpensiveThan(double price) {

        return getExhibitionsByPrice(price, FIND_EXHIBITIONS_MORE_EXPENSIVE_THAN);
    }

    private List<Exhibition> getExhibitionsByPrice(double price, String sqlQuery) {
        List<Exhibition> exhibitions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setDouble(1, price);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                exhibitions.add(exhibitionMapper.getExhibitionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exhibitions;
    }

    @Override
    public List<Exhibition> getByDate(Date date) {
        List<Exhibition> exhibitions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_EXHIBITIONS_BY_DATE)) {
            preparedStatement.setDate(1,new java.sql.Date(date.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                exhibitions.add(exhibitionMapper.getExhibitionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exhibitions;
    }

    @Override
    public void update(Exhibition exhibition) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EXHIBITION)) {
            preparedStatement.setString(1, exhibition.getTitle());
            preparedStatement.setDouble(2, exhibition.getPrice());
            preparedStatement.setDate(3, new java.sql.Date(exhibition.getOpeningDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(exhibition.getClosingDate().getTime()));
            preparedStatement.setString(5, exhibition.getStatus());
            preparedStatement.setInt(6, exhibition.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
