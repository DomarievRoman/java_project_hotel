package com.domariev.model;

import java.util.Objects;

public class Room {
    private long id;
    private String roomType;
    private int amountOfSleepingPlaces;
    private int floor;
    private boolean available;
    private int price;
    private long hotel;

    public Room() {

    }

    public Room(long id, String roomType, int amountOfSleepingPlaces, int floor, boolean available, int price, long hotel) {
        this.id = id;
        this.roomType = roomType;
        this.amountOfSleepingPlaces = amountOfSleepingPlaces;
        this.floor = floor;
        this.available = available;
        this.price = price;
        this.hotel = hotel;
    }

    public Room(String roomType, int amountOfSleepingPlaces, int floor, boolean available, int price, long hotel) {
        this.roomType = roomType;
        this.amountOfSleepingPlaces = amountOfSleepingPlaces;
        this.floor = floor;
        this.available = available;
        this.price = price;
        this.hotel = hotel;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getAmountOfSleepingPlaces() {
        return amountOfSleepingPlaces;
    }

    public void setAmountOfSleepingPlaces(int amountOfSleepingPlaces) {
        this.amountOfSleepingPlaces = amountOfSleepingPlaces;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        Room room = (Room) o;
        return id == room.id &&
                amountOfSleepingPlaces == room.amountOfSleepingPlaces &&
                floor == room.floor &&
                available == room.available &&
                price == room.price &&
                hotel == room.hotel &&
                Objects.equals(roomType, room.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomType, amountOfSleepingPlaces, floor, available, price, hotel);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + id +
                ", roomType='" + roomType + '\'' +
                ", amountOfSleepingPlaces=" + amountOfSleepingPlaces +
                ", floor=" + floor +
                ", isAvailable=" + available +
                ", price=" + price +
                ", hotelId=" + hotel +
                '}';
    }
}
