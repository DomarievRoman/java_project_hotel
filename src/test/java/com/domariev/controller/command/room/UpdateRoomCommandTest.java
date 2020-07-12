package com.domariev.controller.command.room;

import com.domariev.controller.dao.DbConnector;
import com.domariev.controller.dao.RoomDaoImpl;
import com.domariev.controller.dao.exception.DaoException;
import com.domariev.model.Room;
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

public class UpdateRoomCommandTest {
    @InjectMocks
    private UpdateRoomCommand service;

    @InjectMocks
    private DbConnector init;

    @Mock
    private RoomDaoImpl dao;

    final String OPEN_ROOM_LIST_ACTION = "/MainServlet?action=open_room_list";

    @BeforeEach
    void setUp() throws IOException, DaoException {
        MockitoAnnotations.initMocks(this);
        init.createTables();
    }

    @Test
    public void updateRoomTest() throws IOException, DaoException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(3L));
        when(req.getParameter("roomType")).thenReturn("Apartment");
        when(req.getParameter("amountOfSleepingPlaces")).thenReturn(String.valueOf(4));
        when(req.getParameter("floor")).thenReturn(String.valueOf(2));
        when(req.getParameter("available")).thenReturn(String.valueOf(true));
        when(req.getParameter("price")).thenReturn(String.valueOf(4000));
        when(req.getParameter("hotel")).thenReturn(String.valueOf(2L));
        when(dao.update(any(Room.class))).thenReturn(true);
        String result = service.execute(req, resp);
        assertEquals(OPEN_ROOM_LIST_ACTION, result);
    }
}
