package com.domariev.controller.command.room;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.RoomDAOImpl;
import com.domariev.controller.dao.exception.DAOException;
import com.domariev.model.Room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetRoomByIdCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException {
        RoomDAOImpl roomDAO = new RoomDAOImpl();
        long id = Long.parseLong(request.getParameter("id"));
        Room room = roomDAO.getById(id);
        request.setAttribute("room", room);
        return Url.EDIT_ROOM;
    }
}
