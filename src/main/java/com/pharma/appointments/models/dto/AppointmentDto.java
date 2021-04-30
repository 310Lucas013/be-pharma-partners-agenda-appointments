package com.pharma.appointments.models.dto;

import com.pharma.appointments.models.AppointmentType;
import com.pharma.appointments.models.ReasonType;

import java.util.Date;

public class AppointmentDto {
    private Date date;
    private Date startTime;
    private Date endTime;
    private String street;
    private String city;
    private String houseNumber;
    private String country;
    private String zipCode;
    private String reason;
    private String attention;
    private String colorPrimary;
    private String colorSecondary;
    private boolean priority;
    private boolean mgn;

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

    public AppointmentDto(Date date, Date startTime, Date endTime, String street, String city, String houseNumber, String country, String zipCode, String reason, String attention, String colorPrimary, String colorSecondary, boolean priority, boolean mgn, AppointmentType appointmentType, ReasonType reasonType, String patientName, String patientStreetNameNumber, String patientDateOfBirth, String patientPostalCode, Long employeeId, Long patientId, Long locationId) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.country = country;
        this.zipCode = zipCode;
        this.reason = reason;
        this.attention = attention;
        this.colorPrimary = colorPrimary;
        this.colorSecondary = colorSecondary;
        this.priority = priority;
        this.mgn = mgn;
        this.appointmentType = appointmentType;
        this.reasonType = reasonType;
        this.patientName = patientName;
        this.patientStreetNameNumber = patientStreetNameNumber;
        this.patientDateOfBirth = patientDateOfBirth;
        this.patientPostalCode = patientPostalCode;
        this.employeeId = employeeId;
        this.patientId = patientId;
        this.locationId = locationId;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientStreetNameNumber() {
        return patientStreetNameNumber;
    }

    public void setPatientStreetNameNumber(String patientStreetNameNumber) {
        this.patientStreetNameNumber = patientStreetNameNumber;
    }

    public String getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(String patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    public String getPatientPostalCode() {
        return patientPostalCode;
    }

    public void setPatientPostalCode(String patientPostalCode) {
        this.patientPostalCode = patientPostalCode;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
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
}
