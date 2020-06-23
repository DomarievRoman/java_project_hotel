package com.domariev.controller.dao;


import com.domariev.controller.dao.exception.DAOException;
import com.domariev.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl extends DBConnector implements DAO<Employee> {
    private static final String INSERT_INTO_EMPLOYEE = "INSERT INTO employee(nameSurname, profession, salary, hotel) VALUES" +
            "(?, ?, ?, ?)";
    private static final String SELECT_ALL_FROM_EMPLOYEE = "SELECT * FROM employee";
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT id, nameSurname, profession, salary, hotel FROM employee WHERE id = ?";
    private static final String SELECT_EMPLOYEE_BY_FOREIGN_KEY = "SELECT id, nameSurname, profession, salary, hotel FROM employee WHERE hotel = ?";
    private static final String UPDATE_EMPLOYEE = "UPDATE employee SET nameSurname = ?, profession = ?, salary = ?, hotel = ? WHERE id = ?";
    private static final String DELETE_FROM_EMPLOYEE = "DELETE FROM employee WHERE id = ?";

    private static final Logger log = LogManager.getLogger(EmployeeDAOImpl.class);

    public EmployeeDAOImpl() {
    }

    @Override
    public void add(Employee employee) throws DAOException {
        PreparedStatement prStatement = null;
        Connection connection = getConnection();
        try {
            prStatement = connection.prepareStatement(INSERT_INTO_EMPLOYEE);
            log.info("PreparedStatement get connection");
            prStatement.setString(1, employee.getNameSurname());
            prStatement.setString(2, employee.getProfession());
            prStatement.setInt(3, employee.getSalary());
            prStatement.setLong(4, employee.getHotel());
            prStatement.executeUpdate();
            log.debug("Employee " + "№" + employee.getId() + " created");
        } catch (SQLException e) {
            log.error("Error during insert employee's record into DB", e);
            throw new DAOException("Cannot insert record into employee table", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
    }

    @Override
    public List<Employee> getAll() throws DAOException {
        List<Employee> employeeList = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet rSet = null;
        try {
            statement = connection.createStatement();
            log.info("Statement get connection");
            try {
                rSet = statement.executeQuery(SELECT_ALL_FROM_EMPLOYEE);
                log.info("Get ResultSet");
                while (rSet.next()) {
                    Employee employee = new Employee();
                    employee.setId(rSet.getInt("id"));
                    employee.setNameSurname(rSet.getString("nameSurname"));
                    employee.setProfession(rSet.getString("profession"));
                    employee.setSalary(rSet.getInt("salary"));
                    employee.setHotel(rSet.getLong("hotel"));
                    employeeList.add(employee);
                    log.debug("Employee " + "№" + employee.getId() + " gotten");
                }
            } finally {
                closeResultSet(rSet);
            }
        } catch (SQLException e) {
            log.error("Error during get all employee's records from DB", e);
            throw new DAOException("Cannot get all records from employee table", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    log.info("Statement closed");
                } catch (SQLException e) {
                    log.error("Cannot close statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    log.info("Connection closed");
                } catch (SQLException e) {
                    log.error("Cannot close connection", e);
                }
            }
        }
        return employeeList;
    }

    @Override
    public Employee getById(long id) throws DAOException {
        Employee employee = null;
        PreparedStatement prStatement = null;
        ResultSet rSet = null;
        Connection connection = getConnection();
        try {
            prStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
            log.info("prStatement get connection");
            prStatement.setLong(1, id);
            try {
                rSet = prStatement.executeQuery();
                while (rSet.next()) {
                    String nameSurname = rSet.getString("nameSurname");
                    String profession = rSet.getString("profession");
                    int salary = rSet.getInt("salary");
                    long hotelId = rSet.getLong("hotel");
                    employee = new Employee(id, nameSurname, profession, salary, hotelId);
                    log.debug("Employee " + "with id " + employee.getId() + " gotten");
                }
            } finally {
                closeResultSet(rSet);
            }
        } catch (SQLException e) {
            log.error("Error during get employee's record by ID from DB", e);
            throw new DAOException("Cannot get record by ID from employee table", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
        return employee;
    }

    public List<Employee> getByForeignKey(long hotelId) throws DAOException {
        List<Employee> hotelEmployee = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet rSet = null;
        Connection connection = getConnection();
        try {
            prStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_FOREIGN_KEY);
            log.info("prStatement get connection");
            prStatement.setLong(1, hotelId);
            try {
                rSet = prStatement.executeQuery();
                while (rSet.next()) {
                    Employee employee = new Employee();
                    employee.setId(rSet.getInt("id"));
                    employee.setNameSurname(rSet.getString("nameSurname"));
                    employee.setProfession(rSet.getString("profession"));
                    employee.setSalary(rSet.getInt("salary"));
                    employee.setHotel(rSet.getLong("hotel"));
                    hotelEmployee.add(employee);
                    log.debug("Employee " + "from hotel №" + employee.getHotel() + " gotten");
                }
            } finally {
                closeResultSet(rSet);
            }
        } catch (SQLException e) {
            log.error("Error during get employee's record by foreign key from DB", e);
            throw new DAOException("Cannot get record by foreign key from employee table", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
        return hotelEmployee;
    }

    @Override
    public boolean update(Employee employee) throws DAOException {
        boolean updatedRow;
        Connection connection = getConnection();
        PreparedStatement prStatement = null;
        try {
            prStatement = connection.prepareStatement(UPDATE_EMPLOYEE);
            prStatement.setString(1, employee.getNameSurname());
            prStatement.setString(2, employee.getProfession());
            prStatement.setInt(3, employee.getSalary());
            prStatement.setLong(4, employee.getHotel());
            prStatement.setLong(5, employee.getId());
            updatedRow = prStatement.executeUpdate() > 0;
            log.info("Row " + "with id " + employee.getId() + " updated");
        } catch (SQLException e) {
            log.error("Error during update employee's table row", e);
            throw new DAOException("Cannot update row from employee table", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
        return updatedRow;
    }

    @Override
    public boolean delete(long id) throws DAOException {
        boolean deletedRow;
        Connection connection = getConnection();
        PreparedStatement prStatement = null;
        try {
            prStatement = connection.prepareStatement(DELETE_FROM_EMPLOYEE);
            prStatement.setLong(1, id);
            deletedRow = prStatement.executeUpdate() > 0;
            log.debug("Row with id " + id + " deleted");
        } catch (SQLException e) {
            log.error("Error during delete employee's table row", e);
            throw new DAOException("Cannot delete row from employee table", e);
        } finally {
            closePrStatementConnection(prStatement, connection);
        }
        return deletedRow;
    }

    private void closePrStatementConnection(PreparedStatement prStatement, Connection connection) {
        if (prStatement != null) {
            try {
                prStatement.close();
                log.info("PreparedStatement closed");
            } catch (SQLException e) {
                log.error("Cannot close statement", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
                log.info("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }

    private void closeResultSet(ResultSet rSet) {
        if (rSet != null) {
            try {
                rSet.close();
                log.info("ResultSet closed");
            } catch (SQLException e) {
                log.error("Cannot close resultSet", e);
            }
        }
    }
}
