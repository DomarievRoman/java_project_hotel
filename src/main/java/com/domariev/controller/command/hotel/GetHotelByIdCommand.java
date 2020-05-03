package com.domariev.controller.command.hotel;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.HotelDAOImpl;
import com.domariev.controller.dao.exception.DAOException;
import com.domariev.model.Hotel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetHotelByIdCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException {
        HotelDAOImpl hotelDao = new HotelDAOImpl();
        long id = Long.parseLong(request.getParameter("id"));
        Hotel hotel = hotelDao.getById(id);
        request.setAttribute("hotel", hotel);
        return Url.EDIT_HOTEL;
    }
}
