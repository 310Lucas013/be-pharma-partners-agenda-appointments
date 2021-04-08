package com.pharma.appointments.services;

import com.pharma.appointments.models.Appointment;
import com.pharma.appointments.models.dto.AppointmentDto;
import com.pharma.appointments.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointmentsByEmployeeId(long id) {
        System.out.println(id);
        List<Appointment> appointments = appointmentRepository.findAllByEmployeeId(id);
        System.out.println("something");
        // System.out.println(appointments);
        return appointments;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
