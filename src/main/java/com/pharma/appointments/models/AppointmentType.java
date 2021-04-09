package com.pharma.appointments.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @OneToMany(mappedBy = "appointmentType")
    private List<Appointment> appointments;

    public AppointmentType() {
    }

    public AppointmentType(long id, String name, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.appointments = appointments;
    }
}
