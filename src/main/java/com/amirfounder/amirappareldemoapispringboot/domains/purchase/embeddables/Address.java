package com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Address {

    private String street;
    private String streetOptional;
    private String city;
    private String state;
    private String zipCode;

    public Address() {
    }
}
