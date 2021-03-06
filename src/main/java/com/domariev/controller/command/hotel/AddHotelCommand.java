package com.domariev.controller.command.hotel;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.HotelDaoImpl;
import com.domariev.controller.dao.exception.DaoException;
import com.domariev.model.Hotel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddHotelCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DaoException {
        HotelDaoImpl hotelDao = new HotelDaoImpl();
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String ownerName = request.getParameter("ownerName");
        Hotel hotel = new Hotel(name, address, rating, ownerName);
        hotelDao.add(hotel);
        return Url.OPEN_HOTEL_LIST_ACTION;
    }
}
