package com.pharma.appointments.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ReasonType")
@Data
public class ReasonType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @OneToOne(mappedBy = "reasonType")
    private Appointment appointment;

    public ReasonType() {
    }

    public ReasonType(long id, String name, Appointment appointment) {
        this.id = id;
        this.name = name;
        this.appointment = appointment;
    }
}
