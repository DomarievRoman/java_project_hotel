package com.domariev.model.enums;

public enum Table {
    HOTEL("CREATE TABLE IF NOT EXISTS hotel(id long NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(255)," +
            " address varchar(255), rating int, ownerName varchar(255));"),
    EMPLOYEE("CREATE TABLE IF NOT EXISTS employee(id long NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "nameSurname varchar(255), profession varchar(255), salary int, hotel long, FOREIGN KEY(hotel) REFERENCES hotel(id) ON DELETE CASCADE ON UPDATE CASCADE);"),
    ROOM("CREATE TABLE IF NOT EXISTS room(id long NOT NULL AUTO_INCREMENT PRIMARY KEY," +
            " roomType varchar(255), amountOfSleepingPlaces int, floor int, available boolean, price int," +
            " hotel long, FOREIGN KEY(hotel) REFERENCES hotel(id) ON DELETE CASCADE ON UPDATE CASCADE);");

    private String sqlCommand;

    Table(String sqlCommand) {
        this.sqlCommand = sqlCommand;
    }

    public String getSqlCommand() {
        return sqlCommand;
    }
}
