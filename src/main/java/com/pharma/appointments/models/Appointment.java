package com.pharma.appointments.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pharma.appointments.models.dto.AppointmentDto;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Appointment")
@Data
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "appointment_type_id", referencedColumnName = "id")
    @JsonIgnore
    private AppointmentType appointmentType;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "reason_type_id", referencedColumnName = "id")
    @JsonIgnore
    private ReasonType reasonType;
    @Column(name = "reason")
    private String reason;
    @Column(name = "attention")
    private String attention;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "appointment_status")
    private AppointmentStatus appointmentStatus;
    @Column(name = "priority")
    private boolean priority;
    @Column(name = "employee_id")
    private long employeeId;
    @Column(name = "patient_id")
    private long patientId;
    @Column(name = "location_id")
    private long locationId;

    public Appointment() {
    }

    public Appointment(long id, Date startTime, Date endTime, AppointmentType appointmentType, ReasonType reasonType, String reason, String attention) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.appointmentType = appointmentType;
        this.reasonType = reasonType;
        this.reason = reason;
        this.attention = attention;
    }

    public Appointment(AppointmentDto dto) {
        this.startTime = dto.getStartTime();
        this.endTime = dto.getEndTime();
        this.reason = dto.getReason();
        this.attention = dto.getAttention();
        this.appointmentType = dto.getAppointmentType();
        this.reasonType = dto.getReasonType();
    }
}
