package com.pharma.appointments.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDto {
    private Long id;
    private Date startTime;
    private Date endTime;
    private String description;
    //appointmentType
    private Long appointmentTypeId;
    private String appointmentTypeName;
    //reasonType
    private Long reasonTypeId;
    private String reasonTypeName;

    private String reason;
    private String attention;

    private Long employeeId;
    private Long patientId;
    private Long loationId;

    public AppointmentDto() {
    }

    public AppointmentDto(Long id, Date startTime, Date endTime, String description, Long appointmentTypeId, String appointmentTypeName, Long reasonTypeId, String reasonTypeName, String reason, String attention, Long employeeId, Long patientId, Long loationId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.appointmentTypeId = appointmentTypeId;
        this.appointmentTypeName = appointmentTypeName;
        this.reasonTypeId = reasonTypeId;
        this.reasonTypeName = reasonTypeName;
        this.reason = reason;
        this.attention = attention;
        this.employeeId = employeeId;
        this.patientId = patientId;
        this.loationId = loationId;
    }
}
