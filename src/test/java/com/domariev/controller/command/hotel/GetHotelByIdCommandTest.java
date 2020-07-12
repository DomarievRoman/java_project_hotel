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

public class GetHotelByIdCommandTest {
    @InjectMocks
    private GetHotelByIdCommand service;

    @InjectMocks
    private DbConnector init;

    @Mock
    private HotelDaoImpl dao;

    final String EDIT_HOTEL = "/view/hotel/editHotel.jsp";

    @BeforeEach
    void setUp() throws IOException, DaoException {
        MockitoAnnotations.initMocks(this);
        init.createTables();
    }

    @Test
    public void getHotelByIdTest() throws IOException, DaoException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(2L));
        when(dao.getById(2L)).thenReturn(new Hotel());
        String result = service.execute(req, resp);
        assertEquals(EDIT_HOTEL, result);
    }
}
