package com.savstanis.exhibitionservice.service.admin;

import com.savstanis.exhibitionservice.exception.InvalidDateException;
import com.savstanis.exhibitionservice.exception.InvalidExhibitionTitleException;
import com.savstanis.exhibitionservice.exception.InvalidPriceException;
import com.savstanis.exhibitionservice.model.dao.DaoFactoryImpl;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDao;
import com.savstanis.exhibitionservice.model.dao.exhibition.ExhibitionDaoImpl;
import com.savstanis.exhibitionservice.model.dto.ExhibitionCreationDto;
import com.savstanis.exhibitionservice.model.entity.Exhibition;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminServiceTest {
    private AdminService adminService;
    private DaoFactoryImpl daoFactoryMock;
    private ExhibitionDao exhibitionDaoMock;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        daoFactoryMock = mock(DaoFactoryImpl.class);
        exhibitionDaoMock = mock(ExhibitionDaoImpl.class);
        adminService = new AdminServiceImpl();

        Field daoFactoryField = adminService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(adminService, daoFactoryMock);
    }

    @Test
    public void createValidExhibition() throws SQLException {
        ExhibitionCreationDto exhibitionCreationDto = new ExhibitionCreationDto();

        exhibitionCreationDto.setTitle("Modern");
        exhibitionCreationDto.setStatus("active");
        exhibitionCreationDto.setOpeningDate(Date.from(LocalDate.now().minusDays(3).atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        exhibitionCreationDto.setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        exhibitionCreationDto.setPrice(100);

        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        adminService.createExhibition(exhibitionCreationDto);
    }

    @Test(expected = InvalidPriceException.class)
    public void createExhibitionWithInvalidPrice() throws SQLException {
        ExhibitionCreationDto exhibitionCreationDto = new ExhibitionCreationDto();

        exhibitionCreationDto.setTitle("Modern");
        exhibitionCreationDto.setStatus("active");
        exhibitionCreationDto.setOpeningDate(Date.from(LocalDate.now().minusDays(3).atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        exhibitionCreationDto.setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        exhibitionCreationDto.setPrice(-100);

        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        adminService.createExhibition(exhibitionCreationDto);
    }

    @Test(expected = InvalidExhibitionTitleException.class)
    public void createExhibitionWithInvalidTitle() throws SQLException {
        ExhibitionCreationDto exhibitionCreationDto = new ExhibitionCreationDto();

        exhibitionCreationDto.setStatus("active");
        exhibitionCreationDto.setOpeningDate(Date.from(LocalDate.now().minusDays(3).atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        exhibitionCreationDto.setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        exhibitionCreationDto.setPrice(100);

        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        adminService.createExhibition(exhibitionCreationDto);
    }

    @Test(expected = InvalidDateException.class)
    public void createExhibitionWithOpeningDateAfterClosingDate() throws SQLException {
        ExhibitionCreationDto exhibitionCreationDto = new ExhibitionCreationDto();

        exhibitionCreationDto.setStatus("active");
        exhibitionCreationDto.setOpeningDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        exhibitionCreationDto.setClosingDate(Date.from(LocalDate.now().minusDays(3).atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        exhibitionCreationDto.setPrice(100);

        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        adminService.createExhibition(exhibitionCreationDto);
    }

    @Test(expected = InvalidDateException.class)
    public void createExhibitionWithClosingDateBeforeNow() throws SQLException {
        ExhibitionCreationDto exhibitionCreationDto = new ExhibitionCreationDto();

        exhibitionCreationDto.setStatus("active");
        exhibitionCreationDto.setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        exhibitionCreationDto.setClosingDate(Date.from(LocalDate.now().minusDays(3).atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        exhibitionCreationDto.setPrice(100);

        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        adminService.createExhibition(exhibitionCreationDto);
    }

    @Test
    public void cancelExhibitionTest() throws SQLException {
        Exhibition exhibition = Exhibition.builder()
                .setId(1)
                .setTitle("Modern")
                .setPrice(100)
                .setOpeningDate(Date.from(LocalDate.now().minusDays(6).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setClosingDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build();

        when(daoFactoryMock.getExhibitionDao()).thenReturn(exhibitionDaoMock);
        when(exhibitionDaoMock.getActiveById(1)).thenReturn(Optional.of(exhibition));
        adminService.cancelExhibitionById(1);
    }
}
