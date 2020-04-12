package com.domariev.model;

import java.util.Objects;

public class Employee {
    private long employeeId;
    private String nameSurname;
    private String profession;
    private int salary;
    private long hotelId;

    public Employee(long employeeId, String nameSurname, String profession, int salary, long hotelId) {
        this.employeeId = employeeId;
        this.nameSurname = nameSurname;
        this.profession = profession;
        this.salary = salary;
        this.hotelId = hotelId;
    }

    public Employee() {
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
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
                Objects.equals(nameSurname, employee.nameSurname) &&
                Objects.equals(profession, employee.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, nameSurname, profession, salary, hotelId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", nameSurname='" + nameSurname + '\'' +
                ", profession='" + profession + '\'' +
                ", salary=" + salary +
                ", hotelId=" + hotelId +
                '}';
    }
}
