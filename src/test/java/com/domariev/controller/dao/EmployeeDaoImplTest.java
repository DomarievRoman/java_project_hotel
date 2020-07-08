package com.domariev.controller.dao;

import com.domariev.controller.dao.exception.DaoException;
import com.domariev.model.Employee;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeDaoImplTest {
    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    public EmployeeDaoImplTest() {
    }

    public void separator() {
        System.out.println("-----------------------------------------------------------------------");
    }

    @BeforeAll
    public static void setUp() throws IOException, DaoException {
        DbConnector init = new DbConnector();
        init.createTables();
    }

    @Test
    public void createEmployeeTest() throws DaoException {
        Employee expectedEmployee = new Employee(5, "Test Name", "Director", 2500, 2);
        employeeDao.add(expectedEmployee);
        assertEquals(expectedEmployee, employeeDao.getById(5L));
        System.out.println("New line - " + employeeDao.getById(5L));
    }

    @Test
    public void getAllEmployeesTest() throws DaoException {
        List<Employee> expList = this.employeeDao.getAll();
        assertEquals(4, expList.size());
        separator();
        System.out.println("Employee table size - " + expList.size());
    }

    @Test
    public void getEmployeeByIdTest() throws DaoException {
        Employee employee = this.employeeDao.getById(1L);
        assertEquals("Yura Moroziuk", employee.getNameSurname());
        separator();
        System.out.println("Id #1 from employee table - " + employee.getNameSurname());
    }

    @Test
    public void getEmployeesByForeignKeyTest() throws DaoException {
        List<Employee> employeeList = this.employeeDao.getByForeignKey(2L);
        assertEquals(2, employeeList.size());
        separator();
        System.out.println("Records with fk id #2 - " + employeeList.size());
    }

    @Test
    public void deleteEmployeeTest() throws DaoException {
        assertTrue(employeeDao.delete(4L));
    }

    @Test
    public void updateEmployeeTest() throws DaoException {
        Employee employee = this.employeeDao.getById(2L);
        employee.setProfession("Manager");
        employeeDao.update(employee);
        assertEquals(new Employee(2, "Nazar Martynyshyn", "Manager", 1000, 1), employeeDao.getById(2L));
        separator();
        System.out.println("New id #2 profession - " + employee.getProfession());
    }
}
