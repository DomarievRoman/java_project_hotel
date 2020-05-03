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
import java.util.List;

public class HotelListCommand implements Command {
    private static final Logger log = LogManager.getLogger(HotelDAOImpl.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException {
        HotelDAOImpl hotelDao = new HotelDAOImpl();
        try {
            List<Hotel> list = hotelDao.getAll();
            request.setAttribute("list", list);
        } catch (DAOException e) {
            log.error("Cannot get list of hotels", e);
        }
        return Url.HOTEL_LIST;
    }
}
