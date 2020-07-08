package com.domariev.controller.command.hotel;

import com.domariev.controller.dao.HotelDaoImpl;
import com.domariev.controller.dao.exception.DaoException;
import com.domariev.model.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class AddHotelCommandTest {

    private Hotel testHotel = new Hotel();

    @BeforeEach
    void setUp() {
        testHotel.setId(3L);
        testHotel.setName("Test");
        testHotel.setAddress("Testing");
        testHotel.setRating(5);
        testHotel.setOwnerName("Tests");
    }

    @Test
    public void addHotel() throws DaoException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HotelDaoImpl dao = mock(HotelDaoImpl.class);
        GetHotelByIdCommand serv = mock(GetHotelByIdCommand.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(3));
        when(req.getParameter("name")).thenReturn("Test");
        when(req.getParameter("address")).thenReturn("Testing");
        when(req.getParameter("rating")).thenReturn(String.valueOf(5));
        when(req.getParameter("ownerName")).thenReturn("Tests");
        serv.execute(req, resp);
        verify(serv, times(1)).execute(req, resp);

    }
}
