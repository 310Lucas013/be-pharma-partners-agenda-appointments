package com.pharma.appointments.events;

public class CreateAppointmentEvent {
    private Long id;
    private Long employeeId;
    private String patientName;
    private String patientStringNameNumber;
    private String patientDateOfBirth;
    private String patientPostalCode;
    private String location;

    public CreateAppointmentEvent() {
    }

    public CreateAppointmentEvent(Long id, Long employeeId, String patientName, String patientStringNameNumber, String patientDateOfBirth, String patientPostalCode, String location) {
        this.id = id;
        this.employeeId = employeeId;
        this.patientName = patientName;
        this.patientStringNameNumber = patientStringNameNumber;
        this.patientDateOfBirth = patientDateOfBirth;
        this.patientPostalCode = patientPostalCode;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientStringNameNumber() {
        return patientStringNameNumber;
    }

    public void setPatientStringNameNumber(String patientStringNameNumber) {
        this.patientStringNameNumber = patientStringNameNumber;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
