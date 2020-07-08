package com.domariev.controller.command.employee;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.EmployeeDaoImpl;
import com.domariev.controller.dao.exception.DaoException;
import com.domariev.model.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetEmployeeByIdCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DaoException {
        EmployeeDaoImpl employeeDAO = new EmployeeDaoImpl();
        long id = Long.parseLong(request.getParameter("id"));
        Employee employee = employeeDAO.getById(id);
        request.setAttribute("employee", employee);
        return Url.EDIT_EMPLOYEE;
    }
}
