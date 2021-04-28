package com.pharma.appointments.events;

import com.pharma.appointments.models.dto.AppointmentDto;

public class CreateAppointmentEvent {
    private String street;
    private String streetNumber;
    private String city;
    private String country;
    private String zipCode;

    public CreateAppointmentEvent() {
    }

    public CreateAppointmentEvent(String street, String streetNumber, String city, String country, String zipCode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    public CreateAppointmentEvent(AppointmentDto appointmentDto) {
        this.street = appointmentDto.getStreet();
        this.city = appointmentDto.getCity();
        this.country = appointmentDto.getCountry();
        this.zipCode = appointmentDto.getZipCode();
        this.streetNumber = appointmentDto.getHouseNumber();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
