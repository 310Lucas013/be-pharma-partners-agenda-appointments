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
        return appointmentRepository.findAllByEmployeeId(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void deleteAppointment(long id) {
        System.out.println("deleting: " + id);
        appointmentRepository.deleteById(id);
    }
}
