package com.savstanis.exhibitionservice.service.auth;

import com.savstanis.exhibitionservice.exception.InvalidEmailException;
import com.savstanis.exhibitionservice.exception.InvalidPasswordException;
import com.savstanis.exhibitionservice.exception.UserAlreadyExistsException;
import com.savstanis.exhibitionservice.model.dao.DaoFactoryImpl;
import com.savstanis.exhibitionservice.model.dao.user.UserDaoImpl;
import com.savstanis.exhibitionservice.model.dto.LoginDto;
import com.savstanis.exhibitionservice.model.dto.RegisterDto;
import com.savstanis.exhibitionservice.model.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AuthServiceTest {
    AuthServiceImpl authService;
    DaoFactoryImpl daoFactoryMock;
    UserDaoImpl userDaoMock;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        daoFactoryMock = mock(DaoFactoryImpl.class);
        userDaoMock = mock(UserDaoImpl.class);
        authService = new AuthServiceImpl();

        Field daoFactoryField = authService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(authService, daoFactoryMock);
    }

    @Test
    public void loginValidUser() throws SQLException {
        String email = "email@gmail.com";

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword("password");

        User foundUser = User.builder()
                .setId(1)
                .setName("Username")
                .setEmail(email)
                .setPassword("password")
                .setRole("admin")
                .build();

        Optional<User> foundUserOptional = Optional.of(foundUser);

        when(daoFactoryMock.getUserDao()).thenReturn(userDaoMock);
        when(userDaoMock.findByEmail(email)).thenReturn(foundUserOptional);
        assertEquals(authService.login(loginDto), foundUser);
    }

    @Test
    public void loginInvalidUser() throws SQLException {
        String email = "email@gmail.com";

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword("password");

        Optional<User> foundUserOptional = Optional.empty();

        when(daoFactoryMock.getUserDao()).thenReturn(userDaoMock);
        when(userDaoMock.findByEmail(email)).thenReturn(foundUserOptional);
        assertNull(authService.login(loginDto));
    }

    @Test
    public void registerValidUser() throws SQLException {
        String email = "email@gmail.com";

        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail(email);
        registerDto.setPassword("password");
        registerDto.setName("Name");

        Optional<User> foundUserOptional = Optional.empty();

        when(daoFactoryMock.getUserDao()).thenReturn(userDaoMock);
        when(userDaoMock.findByEmail(email)).thenReturn(foundUserOptional);
        authService.register(registerDto);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void registerValidUserWithExistingEmail() throws SQLException {
        String email = "email@gmail.com";

        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail(email);
        registerDto.setPassword("password");
        registerDto.setName("Name");

        Optional<User> foundUserOptional = Optional.of(new User());

        when(daoFactoryMock.getUserDao()).thenReturn(userDaoMock);
        when(userDaoMock.findByEmail(email)).thenReturn(foundUserOptional);
        authService.register(registerDto);
    }

    @Test(expected = InvalidEmailException.class)
    public void registerUserWithInvalidEmail() throws SQLException {
        String email = "emailgmail.com";

        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail(email);
        registerDto.setPassword("password");
        registerDto.setName("Name");

        Optional<User> foundUserOptional = Optional.empty();

        when(daoFactoryMock.getUserDao()).thenReturn(userDaoMock);
        when(userDaoMock.findByEmail(email)).thenReturn(foundUserOptional);
        authService.register(registerDto);
    }



    @Test(expected = InvalidPasswordException.class)
    public void registerUserWithInvalidPassword() throws SQLException {
        String email = "email@gmail.com";

        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail(email);
        registerDto.setPassword("pas");
        registerDto.setName("Name");

        Optional<User> foundUserOptional = Optional.empty();

        when(daoFactoryMock.getUserDao()).thenReturn(userDaoMock);
        when(userDaoMock.findByEmail(email)).thenReturn(foundUserOptional);
        authService.register(registerDto);
    }
}
