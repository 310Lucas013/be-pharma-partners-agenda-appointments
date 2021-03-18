package com.pharma.appointments.services;

import com.pharma.appointments.models.Appointment;
import com.pharma.appointments.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointmentsByEmployeeId(long id) {
        return appointmentRepository.getAllByEmployeeId(id);
    }
}
