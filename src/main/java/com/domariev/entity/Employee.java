package com.domariev.entity;

import java.util.Objects;

public class Employee {
    private int employeeId;
    private String nameSurname;
    private int salary;
    private int hotelId;

    public Employee(int employeeId, String nameSurname, int salary, int hotelId) {
        this.employeeId = employeeId;
        this.nameSurname = nameSurname;
        this.salary = salary;
        this.hotelId = hotelId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId &&
                salary == employee.salary &&
                hotelId == employee.hotelId &&
                Objects.equals(nameSurname, employee.nameSurname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, nameSurname, salary, hotelId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", nameSurname='" + nameSurname + '\'' +
                ", salary=" + salary +
                ", hotelId=" + hotelId +
                '}';
    }
}
