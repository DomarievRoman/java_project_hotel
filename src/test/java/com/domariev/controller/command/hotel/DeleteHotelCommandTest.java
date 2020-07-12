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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteHotelCommandTest {
    @InjectMocks
    private DeleteHotelCommand service;

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
    public void deleteHotelTest() throws IOException, DaoException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(1L));
        when(dao.delete(1L)).thenReturn(true);
        String result = service.execute(req, resp);
        assertEquals(OPEN_HOTEL_LIST_ACTION, result);
    }
}
