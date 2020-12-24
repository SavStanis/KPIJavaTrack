package com.savstanis.exhibitionservice.service.authorizeduser;

import com.savstanis.exhibitionservice.exception.InvalidExhibitionException;
import com.savstanis.exhibitionservice.model.dao.DaoFactoryImpl;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDao;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDaoImpl;
import com.savstanis.exhibitionservice.model.dao.ticket.TicketDao;
import com.savstanis.exhibitionservice.model.dao.ticket.TicketDaoImpl;
import com.savstanis.exhibitionservice.model.dto.TicketDto;
import com.savstanis.exhibitionservice.model.entity.Exhibition;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthorizedUserServiceTest {
    private AuthorizedUserService authorizedUserService;
    private DaoFactoryImpl daoFactoryMock;
    private ExhibitionDao exhibitionDaoMock;
    private TicketDao ticketDaoMock;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        daoFactoryMock = mock(DaoFactoryImpl.class);
        exhibitionDaoMock = mock(ExhibitionDaoImpl.class);
        ticketDaoMock = mock(TicketDaoImpl.class);
        authorizedUserService = new AuthorizedUserServiceImpl();

        Field daoFactoryField = authorizedUserService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(authorizedUserService, daoFactoryMock);
    }

    @Test
    public void buyTicketForValidExhibition() throws SQLException {
        Exhibition exhibition = Exhibition.builder()
                .setId(1)
                .setTitle("Modern")
                .setPrice(100)
                .setStatus("active")
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().minusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build();

        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        when(exhibitionDaoMock.getActiveById(1)).thenReturn(Optional.ofNullable(exhibition));
        when(daoFactoryMock.getTicketDao()).thenReturn(ticketDaoMock);

        authorizedUserService.buyTicket(1, 1);
    }

    @Test(expected = InvalidExhibitionException.class)
    public void buyTicketForInvalidExhibition() throws SQLException {
        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        when(exhibitionDaoMock.getActiveById(1)).thenReturn(Optional.empty());
        when(daoFactoryMock.getTicketDao()).thenReturn(ticketDaoMock);

        authorizedUserService.buyTicket(1,2);
    }

    @Test
    public void getTicketsByUserId() throws SQLException {
        List<TicketDto> ticketList = new ArrayList<>();
        ticketList.add(new TicketDto(1, "Modern", (double) 100, null));
        ticketList.add(new TicketDto(2, "Modern", (double) 100, null));
        ticketList.add(new TicketDto(3, "Modern", (double) 100, null));

        when(daoFactoryMock.getTicketDao()).thenReturn(ticketDaoMock);
        when(ticketDaoMock.getExpandedByUserId(1)).thenReturn(ticketList);

        assertEquals(authorizedUserService.getUsersTickets(1), ticketList);
    }
}
