package com.domariev.controller.command.room;

import com.domariev.controller.dao.DbConnector;
import com.domariev.controller.dao.RoomDaoImpl;
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

public class ShowRoomsByHotelCommandTest {
    @InjectMocks
    private ShowRoomsByHotelCommand service;

    @InjectMocks
    private DbConnector init;

    @Mock
    private RoomDaoImpl dao;

    final String ROOM_LIST = "/view/room/roomList.jsp";

    @BeforeEach
    void setUp() throws IOException, DaoException {
        MockitoAnnotations.initMocks(this);
        init.createTables();
    }

    @Test
    public void getRoomsByFkTest() throws IOException, DaoException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("hotel")).thenReturn(String.valueOf(2L));
        when(dao.getByForeignKey(2L)).thenReturn(new ArrayList<>());
        String result = service.execute(req, resp);
        assertEquals(ROOM_LIST, result);
    }
}
