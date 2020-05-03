package com.domariev.controller.command.service;

import com.domariev.controller.command.hotel.*;
import com.domariev.controller.dao.exception.DAOException;

import java.io.IOException;

public enum CommandEnum {
    OPEN_HOTEL_LIST {{
        this.command = new HotelListCommand();
    }},
    DELETE_LIST {{
        this.command = new DeleteHotelCommand();
    }},
    EDIT_LIST {{
        this.command = new GetHotelByIdCommand();
    }},
    UPDATE_HOTEL {{
        this.command = new EditHotelCommand();
    }},

    ADD_HOTEL {{
        this.command = new AddHotelCommand();
    }},
    OPEN_ADD_HOTEL_TAB {{
        this.command = new OpenAddHotelTabCommand();
    }};

    Command command;

    public Command getCommand() {
        return command;
    }
}
