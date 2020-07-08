package com.domariev.controller.dao;

import com.domariev.controller.dao.exception.DaoException;
import com.domariev.model.Hotel;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class HotelDaoImplTest {
    private HotelDaoImpl hotelDao = new HotelDaoImpl();

    public HotelDaoImplTest() throws IOException, DaoException {
    }

    public void separator() {
        System.out.println("-----------------------------------------------------------------------");
    }

    @BeforeAll
    public static void setUp() throws IOException, DaoException {
        DbConnector init = new DbConnector();
        init.createTables();
    }

    @Test
    public void createHotelTest() throws DaoException {
        Hotel expectedHotel = new Hotel(3, "Test Hotel", "Test address", 5, "Test owner");
        hotelDao.add(expectedHotel);
        assertEquals(expectedHotel, hotelDao.getById(3L));
    }

    @Test
    public void getAllHotelsTest() throws DaoException {
        List<Hotel> expList = this.hotelDao.getAll();
        assertEquals(3, expList.size());
        separator();
        System.out.println("Hotel table size - " + expList.size());
    }

    @Test
    public void getHotelByIdTest() throws DaoException {
        Hotel hotel = this.hotelDao.getById(1L);
        assertEquals("Grand Hotel", hotel.getName());
        separator();
        System.out.println("Id #1 from hotel table - " + hotel.getName());
    }

    @Test
    public void deleteHotelTest() throws DaoException {
        assertTrue(hotelDao.delete(1L));
    }

    @Test
    public void updateHotelTest() throws DaoException {
        Hotel hotel = this.hotelDao.getById(2L);
        hotel.setName("Test Hotel");
        hotelDao.update(hotel);
        assertEquals("Test Hotel", hotel.getName());
        separator();
        System.out.println("New hotel name - " + hotel.getName());
    }
}
