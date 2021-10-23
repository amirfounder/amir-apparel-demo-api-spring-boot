package com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String streetOptional;
    private String city;
    private String state;
    private String zipCode;

    public Address() {}

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetOptional() {
        return streetOptional;
    }

    public void setStreetOptional(String streetOptional) {
        this.streetOptional = streetOptional;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
