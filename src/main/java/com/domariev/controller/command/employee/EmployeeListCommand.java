package com.domariev.controller.command.employee;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.EmployeeDAOImpl;
import com.domariev.controller.dao.exception.DAOException;
import com.domariev.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmployeeListCommand implements Command {
    private static final Logger log = LogManager.getLogger(EmployeeListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        try {
            List<Employee> list = employeeDAO.getAll();
            request.setAttribute("list", list);
        } catch (DAOException e) {
            log.error("Cannot get list of employees", e);
        }
        return Url.EMPLOYEE_LIST;
    }
}
