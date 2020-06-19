package com.domariev.controller.command.room;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.RoomDAOImpl;
import com.domariev.controller.dao.exception.DAOException;
import com.domariev.model.Room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateRoomCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException {
        RoomDAOImpl roomDAO = new RoomDAOImpl();
        long id = Long.parseLong(request.getParameter("id"));
        String type = request.getParameter("roomType");
        int sleepingPlaces = Integer.parseInt(request.getParameter("amountOfSleepingPlaces"));
        int floor = Integer.parseInt(request.getParameter("floor"));
        String availableStr = request.getParameter("available");
        boolean available = availableStr.equalsIgnoreCase("Yes");
        int price = Integer.parseInt(request.getParameter("price"));
        long hotel = Long.parseLong(request.getParameter("hotel"));
        Room room = new Room(id, type, sleepingPlaces, floor, available, price, hotel);
        roomDAO.update(room);
        return Url.OPEN_ROOM_LIST_ACTION;
    }
}
