package com.domariev.controller.command.service;

public interface Url {
    String OPEN_HOTEL_LIST_ACTION = "/MainServlet?action=open_hotel_list";
    String HOTEL_LIST = "/view/hotel/hotelList.jsp";
    String EDIT_HOTEL = "/view/hotel/editHotel.jsp";
    String ADD_HOTEL = "/view/hotel/addHotel.jsp";

    String OPEN_ROOM_LIST_ACTION = "/MainServlet?action=open_room_list";
    String ROOM_LIST = "/view/room/roomList.jsp";
    String EDIT_ROOM = "/view/room/editRoom.jsp";
    String ADD_ROOM = "/view/room/addRoom.jsp";

    String OPEN_EMPLOYEE_LIST_ACTION = "/MainServlet?action=open_employee_list";
    String EMPLOYEE_LIST = "/view/employee/employeeList.jsp";
    String EDIT_EMPLOYEE = "/view/employee/editEmployee.jsp";
    String ADD_EMPLOYEE = "/view/employee/addEmployee.jsp";

    String ERROR_PAGE = "/view/error.jsp";
}
