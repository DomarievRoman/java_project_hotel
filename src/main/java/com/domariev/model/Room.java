package com.domariev.model;

import java.util.Objects;

public class Room {
    private long roomId;
    private String roomType;
    private int amountOfSleepingPlaces;
    private int floor;
    private boolean isAvailable;
    private int price;
    private long hotelId;

    public Room(long roomId, String roomType, int amountOfSleepingPlaces, int floor, boolean isAvailable, int price, long hotelId) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.amountOfSleepingPlaces = amountOfSleepingPlaces;
        this.floor = floor;
        this.isAvailable = isAvailable;
        this.price = price;
        this.hotelId = hotelId;
    }

    public Room() {

    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        Room room = (Room) o;
        return roomId == room.roomId &&
                amountOfSleepingPlaces == room.amountOfSleepingPlaces &&
                floor == room.floor &&
                isAvailable == room.isAvailable &&
                price == room.price &&
                hotelId == room.hotelId &&
                Objects.equals(roomType, room.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomType, amountOfSleepingPlaces, floor, isAvailable, price, hotelId);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomType='" + roomType + '\'' +
                ", amountOfSleepingPlaces=" + amountOfSleepingPlaces +
                ", floor=" + floor +
                ", isAvailable=" + isAvailable +
                ", price=" + price +
                ", hotelId=" + hotelId +
                '}';
    }
}
