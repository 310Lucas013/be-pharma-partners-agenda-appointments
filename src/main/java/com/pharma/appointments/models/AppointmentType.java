package com.pharma.appointments.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "AppointmentType")
@Data
public class AppointmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "appointmentType")
    private Appointment appointment;

    public AppointmentType() {
    }
}
