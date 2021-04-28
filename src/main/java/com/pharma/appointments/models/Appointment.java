package com.pharma.appointments.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pharma.appointments.models.dto.AppointmentDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Appointment")
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "date")
    private Date date;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "appointment_type_id", referencedColumnName = "id")
    @JsonIgnore
    private AppointmentType appointmentType;
    @ManyToOne(cascade = {CascadeType.MERGE})
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

    public Appointment(long id, Date startTime, Date endTime, AppointmentType appointmentType, ReasonType reasonType, String reason, String attention, Date date) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.appointmentType = appointmentType;
        this.reasonType = reasonType;
        this.reason = reason;
        this.attention = attention;
    }

    public Appointment(AppointmentDto dto) {
        this.date = dto.getDate();
        this.startTime = dto.getStartTime();
        this.endTime = dto.getEndTime();
        this.reason = dto.getReason();
        this.attention = dto.getAttention();
        this.appointmentType = dto.getAppointmentType();
        this.reasonType = dto.getReasonType();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public ReasonType getReasonType() {
        return reasonType;
    }

    public void setReasonType(ReasonType reasonType) {
        this.reasonType = reasonType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }
}
