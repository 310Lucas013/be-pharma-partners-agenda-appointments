package com.pharma.appointments.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDto {
    private Long id;
    private Date startTime;
    private Date endTime;
    private String description;
    private String location;
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

    public AppointmentDto(Long id, Date startTime, Date endTime, String description, String location, String reason, String attention, String color, Long appointmentTypeId, String appointmentTypeName, Long reasonTypeId, String reasonTypeName, String patientName, String patientStreetNameNumber, String patientDateOfBirth, String patientPostalCode, Long employeeId, Long patientId, Long locationId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.location = location;
        this.reason = reason;
        this.attention = attention;
        this.color = color;
        this.appointmentTypeId = appointmentTypeId;
        this.appointmentTypeName = appointmentTypeName;
        this.reasonTypeId = reasonTypeId;
        this.reasonTypeName = reasonTypeName;
        this.patientName = patientName;
        this.patientStreetNameNumber = patientStreetNameNumber;
        this.patientDateOfBirth = patientDateOfBirth;
        this.patientPostalCode = patientPostalCode;
        this.employeeId = employeeId;
        this.patientId = patientId;
        this.locationId = locationId;
    }
}
