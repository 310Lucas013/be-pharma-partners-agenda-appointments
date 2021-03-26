package com.pharma.appointments.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AppointmentType")
@Data
public class AppointmentType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @OneToOne(mappedBy = "appointmentType")
    private Appointment appointment;

    public AppointmentType() {
    }

    public AppointmentType(long id, String name, Appointment appointment) {
        this.id = id;
        this.name = name;
        this.appointment = appointment;
    }
}
