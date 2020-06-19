package com.domariev.controller.command.room;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.RoomDAOImpl;
import com.domariev.controller.dao.exception.DAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRoomCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  throws IOException, DAOException {
        RoomDAOImpl roomDAO = new RoomDAOImpl();
        long id = Long.parseLong(request.getParameter("id"));
        roomDAO.delete(id);
        return Url.OPEN_ROOM_LIST_ACTION;
    }
}
