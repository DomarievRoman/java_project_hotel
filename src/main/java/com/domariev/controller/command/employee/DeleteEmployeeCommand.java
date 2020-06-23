package com.domariev.controller.command.employee;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.EmployeeDAOImpl;
import com.domariev.controller.dao.exception.DAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEmployeeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        long id = Long.parseLong(request.getParameter("id"));
        employeeDAO.delete(id);
        return Url.OPEN_EMPLOYEE_LIST_ACTION;
    }
}
