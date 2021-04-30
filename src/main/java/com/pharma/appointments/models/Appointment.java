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
    @Column(name = "reason")
    private String reason;
    @Column(name = "attention")
    private String attention;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "appointment_status")
    private AppointmentStatus appointmentStatus;
    @Column(name = "colorPrimary")
    private String colorPrimary;
    @Column(name = "colorSecondary")
    private String colorSecondary;
    @Column(name = "priority")
    private boolean priority;
    @Column(name = "mgn")
    private boolean mgn;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "appointment_type_id", referencedColumnName = "id")
    @JsonIgnore
    private AppointmentType appointmentType;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "reason_type_id", referencedColumnName = "id")
    @JsonIgnore
    private ReasonType reasonType;

    @Column(name = "employee_id")
    private long employeeId;
    @Column(name = "patient_id")
    private long patientId;
    @Column(name = "location_id")
    private long locationId;


    public Appointment() {
    }

    public Appointment(long id, Date date, Date startTime, Date endTime, String reason, String attention, AppointmentStatus appointmentStatus, String colorPrimary, String colorSecondary, boolean priority, boolean mgn, AppointmentType appointmentType, ReasonType reasonType, long employeeId, long patientId, long locationId) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.attention = attention;
        this.appointmentStatus = appointmentStatus;
        this.colorPrimary = colorPrimary;
        this.colorSecondary = colorSecondary;
        this.priority = priority;
        this.mgn = mgn;
        this.appointmentType = appointmentType;
        this.reasonType = reasonType;
        this.employeeId = employeeId;
        this.patientId = patientId;
        this.locationId = locationId;
    }

    public Appointment(AppointmentDto dto) {
        this.date = dto.getDate();
        this.startTime = dto.getStartTime();
        this.endTime = dto.getEndTime();
        this.reason = dto.getReason();
        this.attention = dto.getAttention();
        this.colorSecondary = dto.getColorSecondary();
        this.colorPrimary = dto.getColorPrimary();
        this.priority = dto.isPriority();
        this.mgn = dto.isMgn();
        this.appointmentType = dto.getAppointmentType();
        this.reasonType = dto.getReasonType();
        this.employeeId = dto.getEmployeeId();
        this.patientId = dto.getPatientId();
        this.locationId = dto.getLocationId();
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


    public boolean isMgn() {
        return mgn;
    }

    public void setMgn(boolean mgn) {
        this.mgn = mgn;
    }

    public String getColorPrimary() {
        return colorPrimary;
    }

    public void setColorPrimary(String colorPrimary) {
        this.colorPrimary = colorPrimary;
    }

    public String getColorSecondary() {
        return colorSecondary;
    }

    public void setColorSecondary(String colorSecondary) {
        this.colorSecondary = colorSecondary;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", reason='" + reason + '\'' +
                ", attention='" + attention + '\'' +
                ", appointmentStatus=" + appointmentStatus +
                ", colorPrimary='" + colorPrimary + '\'' +
                ", colorSecondary='" + colorSecondary + '\'' +
                ", priority=" + priority +
                ", mgn=" + mgn +
                ", appointmentType=" + appointmentType +
                ", reasonType=" + reasonType +
                ", employeeId=" + employeeId +
                ", patientId=" + patientId +
                ", locationId=" + locationId +
                '}';
    }
}
