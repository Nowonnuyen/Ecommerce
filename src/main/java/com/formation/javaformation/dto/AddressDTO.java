package com.formation.javaformation.dto;

public class AddressDTO {
    private int zipCode;
    private String city;
    private String road;
    public int getZipCode() {
        return zipCode;
    }
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getRoad() {
        return road;
    }
    public void setRoad(String road) {
        this.road = road;
    }
}
