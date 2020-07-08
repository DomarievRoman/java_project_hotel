package com.domariev.controller.dao;

import com.domariev.controller.dao.exception.DaoException;
import com.domariev.model.Room;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class RoomDaoImplTest {
    private RoomDaoImpl roomDao = new RoomDaoImpl();

    public RoomDaoImplTest() throws DaoException {
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
    public void createRoomTest() throws DaoException {
        Room expectedRoom = new Room(6, "Test Room", 3, 10, true, 1750, 1);
        roomDao.add(expectedRoom);
        assertEquals(expectedRoom, roomDao.getById(6L));
    }

    @Test
    public void getAllRoomsTest() throws DaoException {
        List<Room> expList = this.roomDao.getAll();
        assertEquals(6, expList.size());
        separator();
        System.out.println("Room table size - " + expList.size());
    }

    @Test
    public void getRoomByIdTest() throws DaoException {
        Room room = this.roomDao.getById(3L);
        assertEquals("Apartment", room.getRoomType());
        separator();
        System.out.println("Id #3 from room table - " + room.getRoomType());
    }

    @Test
    public void getRoomsByForeignKeyTest() throws DaoException {
        List<Room> roomList = this.roomDao.getByForeignKey(1L);
        assertEquals(3, roomList.size());
        separator();
        System.out.println("Records with fk id #1 - " + roomList.size());
    }

    @Test
    public void deleteRoomTest() throws DaoException {
        assertTrue(roomDao.delete(2L));
    }

    @Test
    public void updateRoomTest() throws DaoException {
        Room room = this.roomDao.getById(4L);
        room.setAmountOfSleepingPlaces(2);
        roomDao.update(room);
        assertEquals(2, room.getAmountOfSleepingPlaces());
        separator();
        System.out.println("New amount of sleeping places - " + room.getAmountOfSleepingPlaces());
    }
}
