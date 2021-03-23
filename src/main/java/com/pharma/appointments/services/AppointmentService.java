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

    public Appointment addAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment(appointmentDto);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointmentsByEmployeeId(long id) {
        return appointmentRepository.getAllByEmployeeId(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
