package com.domariev.controller.command.hotel;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.HotelDAOImpl;
import com.domariev.controller.dao.exception.DAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteHotelCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException {
        HotelDAOImpl hotelDao = new HotelDAOImpl();
        long id = Long.parseLong(request.getParameter("id"));
        hotelDao.delete(id);
        return Url.OPEN_HOTEL_LIST_ACTION;
    }
}
