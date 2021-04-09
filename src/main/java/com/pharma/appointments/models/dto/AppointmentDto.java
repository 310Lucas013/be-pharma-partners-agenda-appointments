package com.pharma.appointments.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDto {
    private Long id;
    private Date startTime;
    private Date endTime;
    private String description;
    private String street;
    private String city;
    private String houseNumber;
    private String country;
    private String postalCode;
    private String reason;
    private String attention;
    private String color;

    //appointmentType
    private Long appointmentTypeId;
        private String appointmentTypeName;
    //reasonType
    private Long reasonTypeId;
    private String reasonTypeName;

    //patient
    private String patientName;
    private String patientStreetNameNumber;
    private String patientDateOfBirth;
    private String patientPostalCode;

    private Long employeeId;
    private Long patientId;
    private Long locationId;

    public AppointmentDto() {
    }


}
