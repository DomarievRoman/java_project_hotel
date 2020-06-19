package com.domariev.controller.command.service;

import com.domariev.controller.command.hotel.*;
import com.domariev.controller.command.room.*;

public enum CommandEnum {
    OPEN_HOTEL_LIST {{
        this.command = new HotelListCommand();
    }},
    DELETE_HOTEL {{
        this.command = new DeleteHotelCommand();
    }},
    EDIT_HOTEL {{
        this.command = new GetHotelByIdCommand();
    }},
    UPDATE_HOTEL {{
        this.command = new UpdateHotelCommand();
    }},
    ADD_HOTEL {{
        this.command = new AddHotelCommand();
    }},
    OPEN_ADD_HOTEL_TAB {{
        this.command = new OpenAddHotelTabCommand();
    }},
    OPEN_ROOM_LIST {{
        this.command = new RoomListCommand();
    }},
    DELETE_ROOM {{
        this.command = new DeleteRoomCommand();
    }},
    EDIT_ROOM {{
        this.command = new GetRoomByIdCommand();
    }},
    UPDATE_ROOM {{
        this.command = new UpdateRoomCommand();
    }},
    ADD_ROOM {{
        this.command = new AddRoomCommand();
    }},
    OPEN_ADD_ROOM_TAB {{
        this.command = new OpenAddRoomTabCommand();
    }},
    SHOW_ROOMS {{
        this.command = new ShowRoomsByHotelCommand();
    }};

    Command command;

    public Command getCommand() {
        return command;
    }
}
