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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteEmployeeCommandTest {
    @InjectMocks
    private DeleteEmployeeCommand service;

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
    public void deleteEmployeeTest() throws IOException, DaoException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(3L));
        when(dao.delete(3L)).thenReturn(true);
        String result = service.execute(req, resp);
        assertEquals(OPEN_EMPLOYEE_LIST_ACTION, result);
    }
}
