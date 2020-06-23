package com.domariev.model;

import java.util.Objects;

public class Employee {
    private long id;
    private String nameSurname;
    private String profession;
    private int salary;
    private long hotel;

    public Employee() {
    }

    public Employee(long id, String nameSurname, String profession, int salary, long hotel) {
        this.id = id;
        this.nameSurname = nameSurname;
        this.profession = profession;
        this.salary = salary;
        this.hotel = hotel;
    }

    public Employee(String nameSurname, String profession, int salary, long hotel) {
        this.nameSurname = nameSurname;
        this.profession = profession;
        this.salary = salary;
        this.hotel = hotel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getHotel() {
        return hotel;
    }

    public void setHotel(long hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                salary == employee.salary &&
                hotel == employee.hotel &&
                Objects.equals(nameSurname, employee.nameSurname) &&
                Objects.equals(profession, employee.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameSurname, profession, salary, hotel);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + id +
                ", nameSurname='" + nameSurname + '\'' +
                ", profession='" + profession + '\'' +
                ", salary=" + salary +
                ", hotelId=" + hotel +
                '}';
    }
}
