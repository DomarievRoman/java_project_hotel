package com.domariev.controller.command.hotel;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.HotelDAOImpl;
import com.domariev.controller.dao.exception.DAOException;
import com.domariev.model.Hotel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditHotelCommand implements Command {
    private static final Logger log = LogManager.getLogger(EditHotelCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException {
        HotelDAOImpl hotelDao = new HotelDAOImpl();
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String ownerName = request.getParameter("ownerName");
        Hotel hotel = new Hotel(id, name, address, rating, ownerName);
        hotelDao.update(hotel);
        return Url.OPEN_HOTEL_LIST_ACTION;
    }
}
