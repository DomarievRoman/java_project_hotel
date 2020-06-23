package com.domariev.controller.command.room;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.RoomDAOImpl;
import com.domariev.controller.dao.exception.DAOException;
import com.domariev.model.Room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowRoomsByHotelCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException {
        RoomDAOImpl roomDAO = new RoomDAOImpl();
        long hotelFk = Long.parseLong(request.getParameter("hotel"));
        List<Room> room = roomDAO.getByForeignKey(hotelFk);
        request.setAttribute("list", room);
        return Url.ROOM_LIST;
    }
}
