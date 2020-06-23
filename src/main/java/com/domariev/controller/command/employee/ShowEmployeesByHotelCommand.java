package com.domariev.controller.command.employee;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.EmployeeDAOImpl;
import com.domariev.controller.dao.exception.DAOException;
import com.domariev.model.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowEmployeesByHotelCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        long hotelFk = Long.parseLong(request.getParameter("hotel"));
        List<Employee> employee = employeeDAO.getByForeignKey(hotelFk);
        request.setAttribute("list", employee);
        return Url.EMPLOYEE_LIST;
    }
}
