package com.formation.javaformation.dto;

import java.util.UUID;

public class CustomerDTO {
    private String firstName;
    private String lastName;
    private AddressDTO addressDTO;
    private UUID id;
    private String avis;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public AddressDTO getAddressDTO() {
        return addressDTO;
    }
    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }
}
