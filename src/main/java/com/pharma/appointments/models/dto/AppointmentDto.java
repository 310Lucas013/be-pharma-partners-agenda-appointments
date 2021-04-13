package com.pharma.appointments.models.dto;

import com.pharma.appointments.models.AppointmentType;
import com.pharma.appointments.models.ReasonType;
import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDto {
    private Date startTime;
    private Date endTime;
    private String street;
    private String city;
    private String houseNumber;
    private String country;
    private String postalCode;
    private String reason;
    private String attention;
    private String color;

    //appointmentType
    private AppointmentType appointmentType;
    //reasonType
    private ReasonType reasonType;

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
