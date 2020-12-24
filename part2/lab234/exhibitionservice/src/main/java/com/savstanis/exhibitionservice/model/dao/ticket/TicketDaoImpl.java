package com.savstanis.exhibitionservice.model.dao.ticket;

import com.savstanis.exhibitionservice.model.dto.TicketDto;
import com.savstanis.exhibitionservice.model.entity.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    private Connection connection;
    private TicketMapper ticketMapper;

    public TicketDaoImpl(Connection connection) {
        this.connection = connection;
        ticketMapper = new TicketMapper();
    }

    private final String CREATE_NEW_TICKET = "insert into tickets (user_id, exhibition_id, price, purchase_time) values (?,?,?,?)";
    private final String FIND_TICKETS_BY_USER_ID = "select * from tickets where user_id = ?";
    private final String FIND_TICKETS_BY_EXHIBITION_ID = "select * from tickets where exhibition_id = ?";
    private final String COUNT_TICKETS_BY_EXHIBITION_ID = "select count(*) from tickets where exhibition_id = ?";

    private final String FIND_TICKETS_BY_USER_ID_EXPANDED = "select * from tickets join exhibitions on (tickets.exhibition_id = exhibitions.id) where user_id = ?";
    private final String FIND_TICKETS_BY_EXHIBITION_ID_EXPANDED = "select * from tickets join exhibitions on (tickets.exhibition_id = exhibitions.id) where exhibition_id = ?";
    private final String COUNT_TICKETS_BY_EXHIBITION_ID_EXPANDED = "select count(*) from join exhibitions on (tickets.exhibition_id = exhibitions.id) tickets where exhibition_id = ?";

    @Override
    public void create(Ticket ticket) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_TICKET)) {
            preparedStatement.setInt(1, ticket.getUserId());
            preparedStatement.setInt(2, ticket.getExhibitionId());
            preparedStatement.setDouble(3, ticket.getPrice());
            preparedStatement.setTimestamp(4, new Timestamp(ticket.getPurchaseTime().getTime()));

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticket> getByUserId(int userId) {
        List<Ticket> tickets = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TICKETS_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tickets.add(ticketMapper.getTicketFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public List<Ticket> getByExhibitionId(int exhibitionId) {
        List<Ticket> tickets = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TICKETS_BY_EXHIBITION_ID)) {
            preparedStatement.setInt(1, exhibitionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tickets.add(ticketMapper.getTicketFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public List<TicketDto> getExpandedByUserId(int userId) {
        List<TicketDto> tickets = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TICKETS_BY_USER_ID_EXPANDED)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tickets.add(ticketMapper.getTicketDtoFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public List<TicketDto> getExpandedByExhibitionId(int exhibitionId) {
        List<TicketDto> tickets = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TICKETS_BY_EXHIBITION_ID_EXPANDED)) {
            preparedStatement.setInt(1, exhibitionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tickets.add(ticketMapper.getTicketDtoFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public int countTicketsByExhibitionId(int exhibitionId) {
        int numberOfTickets = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(COUNT_TICKETS_BY_EXHIBITION_ID)) {
            preparedStatement.setInt(1, exhibitionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                numberOfTickets = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numberOfTickets;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
