package com.domariev.controller.command.employee;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.EmployeeDaoImpl;
import com.domariev.controller.dao.exception.DaoException;
import com.domariev.model.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddEmployeeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DaoException {
        EmployeeDaoImpl employeeDAO = new EmployeeDaoImpl();
        String nameSurname = request.getParameter("nameSurname");
        String profession = request.getParameter("profession");
        int salary = Integer.parseInt(request.getParameter("salary"));
        long hotel = Long.parseLong(request.getParameter("hotel"));
        Employee employee = new Employee(nameSurname, profession, salary, hotel);
        employeeDAO.add(employee);
        return Url.OPEN_EMPLOYEE_LIST_ACTION;
    }
}
