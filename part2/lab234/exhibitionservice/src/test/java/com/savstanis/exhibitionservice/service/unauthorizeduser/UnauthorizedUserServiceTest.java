package com.savstanis.exhibitionservice.service.unauthorizeduser;

import com.savstanis.exhibitionservice.model.dao.DaoFactoryImpl;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDao;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDaoImpl;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnauthorizedUserServiceTest {
    private UnauthorizedUserService unauthorizedUserService;
    private DaoFactoryImpl daoFactoryMock;
    private ExhibitionDao exhibitionDaoMock;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        daoFactoryMock = mock(DaoFactoryImpl.class);
        exhibitionDaoMock = mock(ExhibitionDaoImpl.class);
        unauthorizedUserService = new UnauthorizedUserServiceImpl();

        Field daoFactoryField = unauthorizedUserService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(unauthorizedUserService, daoFactoryMock);
    }

    @Test
    public void getActiveExhibitions() throws SQLException {
        List<Exhibition> exhibitionList = new ArrayList<>();

        exhibitionList.add(Exhibition.builder()
                .setId(1)
                .setTitle("Modern")
                .setPrice(100)
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build()
        );
        exhibitionList.add(Exhibition.builder()
                .setId(2)
                .setTitle("Postodern")
                .setPrice(100)
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build()
        );
        exhibitionList.add(Exhibition.builder()
                .setId(3)
                .setTitle("Italy")
                .setPrice(100)
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build()
        );

        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        when(exhibitionDaoMock.getActive()).thenReturn(exhibitionList);

        assertEquals(unauthorizedUserService.getActiveExhibitions(), exhibitionList);
    }

    @Test
    public void getExhibitionsByTitle() throws SQLException {
        List<Exhibition> exhibitionList = new ArrayList<>();

        exhibitionList.add(Exhibition.builder()
                .setId(1)
                .setTitle("Modern")
                .setPrice(100)
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build()
        );
        exhibitionList.add(Exhibition.builder()
                .setId(2)
                .setTitle("Postmodern")
                .setPrice(100)
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build()
        );


        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        when(exhibitionDaoMock.getActiveByTitle("modern")).thenReturn(exhibitionList);

        assertEquals(unauthorizedUserService.getExhibitionsByTitle("modern"), exhibitionList);
    }

    @Test
    public void getCheaperExhibitionsThan() throws SQLException {
        List<Exhibition> exhibitionList = new ArrayList<>();

        exhibitionList.add(Exhibition.builder()
                .setId(2)
                .setTitle("Postmodern")
                .setPrice(50)
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build()
        );
        exhibitionList.add(Exhibition.builder()
                .setId(3)
                .setTitle("Italy")
                .setPrice(50)
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build()
        );


        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        when(exhibitionDaoMock.getCheaperThan(100)).thenReturn(exhibitionList);

        assertEquals(unauthorizedUserService.getCheaperExhibitionsThan(100), exhibitionList);
    }

    @Test
    public void getMoreExpensiveExhibitionsThan() throws SQLException {
        List<Exhibition> exhibitionList = new ArrayList<>();

        exhibitionList.add(Exhibition.builder()
                .setId(2)
                .setTitle("Postmodern")
                .setPrice(50)
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build()
        );
        exhibitionList.add(Exhibition.builder()
                .setId(3)
                .setTitle("Italy")
                .setPrice(50)
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build()
        );

        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        when(exhibitionDaoMock.getMoreExpensiveThan(10)).thenReturn(exhibitionList);

        assertEquals(unauthorizedUserService.getMoreExpensiveExhibitionsThan(10), exhibitionList);
    }

    @Test
    public void getExhibitionsByDate() throws SQLException {
        Date now = new Date();
        List<Exhibition> exhibitionList = new ArrayList<>();

        exhibitionList.add(Exhibition.builder()
                .setId(2)
                .setTitle("Postmodern")
                .setPrice(50)
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build()
        );
        exhibitionList.add(Exhibition.builder()
                .setId(3)
                .setTitle("Italy")
                .setPrice(50)
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build()
        );

        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        when(exhibitionDaoMock.getByDate(now)).thenReturn(exhibitionList);

        assertEquals(unauthorizedUserService.getExhibitionsByDate(now), exhibitionList);
    }
}
