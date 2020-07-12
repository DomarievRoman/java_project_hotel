package com.domariev.controller.command.employee;

import com.domariev.controller.dao.DbConnector;
import com.domariev.controller.dao.EmployeeDaoImpl;
import com.domariev.controller.dao.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeListCommandTest {
    @InjectMocks
    private EmployeeListCommand service;

    @InjectMocks
    private DbConnector init;

    @Mock
    private EmployeeDaoImpl dao;

    final String EMPLOYEE_LIST = "/view/employee/employeeList.jsp";

    @BeforeEach
    void setUp() throws IOException, DaoException {
        MockitoAnnotations.initMocks(this);
        init.createTables();
    }

    @Test
    public void getAllEmployeesTest() throws DaoException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(dao.getAll()).thenReturn(new ArrayList<>());
        String result = service.execute(req, resp);
        assertEquals(EMPLOYEE_LIST, result);
    }
}
