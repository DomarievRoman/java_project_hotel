package com.domariev.controller.command.room;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.RoomDAOImpl;
import com.domariev.controller.dao.exception.DAOException;
import com.domariev.model.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoomListCommand implements Command {
    private static final Logger log = LogManager.getLogger(RoomListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException {
        RoomDAOImpl roomDAO = new RoomDAOImpl();
        try {
            List<Room> list = roomDAO.getAll();
            request.setAttribute("list", list);
        } catch (DAOException e) {
            log.error("Cannot get list of rooms", e);
        }
        return Url.ROOM_LIST;
    }
}
