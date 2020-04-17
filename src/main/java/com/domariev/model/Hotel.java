package com.domariev.model;

import java.util.Objects;

public class Hotel {
    private long id;
    private String name;
    private String address;
    private int rating;
    private String ownerName;

    public Hotel() {
    }

    public Hotel(long id, String name, String address, int rating, String ownerName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.ownerName = ownerName;
    }

    public Hotel(String name, String address, int rating, String ownerName) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.ownerName = ownerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id &&
                rating == hotel.rating &&
                Objects.equals(name, hotel.name) &&
                Objects.equals(address, hotel.address) &&
                Objects.equals(ownerName, hotel.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, rating, ownerName);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
