package com.domariev.controller.command.employee;

import com.domariev.controller.dao.DbConnector;
import com.domariev.controller.dao.EmployeeDaoImpl;
import com.domariev.controller.dao.exception.DaoException;
import com.domariev.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetEmployeeByIdCommandTest {
    @InjectMocks
    private GetEmployeeByIdCommand service;

    @InjectMocks
    private DbConnector init;

    @Mock
    private EmployeeDaoImpl dao;

    final String EDIT_EMPLOYEE = "/view/employee/editEmployee.jsp";

    @BeforeEach
    void setUp() throws IOException, DaoException {
        MockitoAnnotations.initMocks(this);
        init.createTables();
    }

    @Test
    public void getEmployeeByIdTest() throws IOException, DaoException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(4L));
        when(dao.getById(4L)).thenReturn(new Employee());
        String result = service.execute(req, resp);
        assertEquals(EDIT_EMPLOYEE, result);
    }
}
