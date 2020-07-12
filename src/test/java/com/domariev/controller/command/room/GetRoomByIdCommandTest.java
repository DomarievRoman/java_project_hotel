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

public class GetRoomByIdCommandTest {
    @InjectMocks
    private GetRoomByIdCommand service;

    @InjectMocks
    private DbConnector init;

    @Mock
    private RoomDaoImpl dao;

    final String EDIT_ROOM = "/view/room/editRoom.jsp";

    @BeforeEach
    void setUp() throws IOException, DaoException {
        MockitoAnnotations.initMocks(this);
        init.createTables();
    }

    @Test
    public void getRoomByIdTest() throws IOException, DaoException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(3L));
        when(dao.getById(3L)).thenReturn(new Room());
        String result = service.execute(req, resp);
        assertEquals(EDIT_ROOM, result);
    }
}
