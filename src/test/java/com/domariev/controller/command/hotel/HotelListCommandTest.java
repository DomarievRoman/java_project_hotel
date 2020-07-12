package com.domariev.controller.command.hotel;

import com.domariev.controller.dao.DbConnector;
import com.domariev.controller.dao.HotelDaoImpl;
import com.domariev.controller.dao.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class HotelListCommandTest {
    @InjectMocks
    private HotelListCommand service;

    @InjectMocks
    private DbConnector init;

    @Mock
    private HotelDaoImpl dao;

    final String HOTEL_LIST = "/view/hotel/hotelList.jsp";

    @BeforeEach
    void setUp() throws IOException, DaoException {
        MockitoAnnotations.initMocks(this);
        init.createTables();
    }

    @Test
    public void getAllHotelsTest() throws DaoException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(dao.getAll()).thenReturn(new ArrayList<>());
        String result = service.execute(req, resp);
        assertEquals(HOTEL_LIST, result);
    }
}
