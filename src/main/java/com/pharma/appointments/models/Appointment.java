package com.pharma.appointments.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Appointment")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "description")
    private String description;
    @OneToOne
    @JoinColumn(name = "appointment_type_id", referencedColumnName = "id")
    private AppointmentType appointmentType;
    @OneToOne
    @JoinColumn(name = "reason_type_id", referencedColumnName = "id")
    private ReasonType reasonType;
    @Column(name = "reason")
    private String reason;
    @Column(name = "attention")
    private String attention;
    @Column(name = "employee_id")
    private long employeeId;

    public Appointment() {
    }
}
