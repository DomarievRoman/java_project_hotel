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

public class UpdateEmployeeCommandTest {
    @InjectMocks
    private UpdateEmployeeCommand service;

    @InjectMocks
    private DbConnector init;

    @Mock
    private EmployeeDaoImpl dao;

    final String OPEN_EMPLOYEE_LIST_ACTION = "/MainServlet?action=open_employee_list";

    @BeforeEach
    void setUp() throws IOException, DaoException {
        MockitoAnnotations.initMocks(this);
        init.createTables();
    }

    @Test
    public void updateEmployeeTest() throws IOException, DaoException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(1L));
        when(req.getParameter("nameSurname")).thenReturn("Yura Moroziuk");
        when(req.getParameter("profession")).thenReturn("Runner");
        when(req.getParameter("salary")).thenReturn(String.valueOf(700));
        when(req.getParameter("hotel")).thenReturn(String.valueOf(2L));
        when(dao.update(any(Employee.class))).thenReturn(true);
        String result = service.execute(req, resp);
        assertEquals(OPEN_EMPLOYEE_LIST_ACTION, result);
    }
}
