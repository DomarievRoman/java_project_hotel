package com.domariev.controller.command.hotel;

import com.domariev.controller.dao.DbConnector;
import com.domariev.controller.dao.HotelDaoImpl;
import com.domariev.controller.dao.exception.DaoException;
import com.domariev.model.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateHotelCommandTest {
    @InjectMocks
    private UpdateHotelCommand service;

    @InjectMocks
    private DbConnector init;

    @Mock
    private HotelDaoImpl dao;

    final String OPEN_HOTEL_LIST_ACTION = "/MainServlet?action=open_hotel_list";

    @BeforeEach
    void setUp() throws IOException, DaoException {
        MockitoAnnotations.initMocks(this);
        init.createTables();
    }

    @Test
    public void updateHotelTest() throws IOException, DaoException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(2L));
        when(req.getParameter("name")).thenReturn("Luxor Hotel");
        when(req.getParameter("address")).thenReturn("USA/New-York");
        when(req.getParameter("rating")).thenReturn(String.valueOf(5));
        when(req.getParameter("ownerName")).thenReturn("Shpak Zoreslava");
        when(dao.update(any(Hotel.class))).thenReturn(true);
        String result = service.execute(req, resp);
        assertEquals(OPEN_HOTEL_LIST_ACTION, result);
    }
}
