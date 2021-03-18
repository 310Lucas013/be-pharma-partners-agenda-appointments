package com.pharma.appointments.controllers;

import com.pharma.appointments.models.dto.AppointmentDto;
import com.pharma.appointments.models.Appointment;
import com.pharma.appointments.services.AppointmentService;
import com.pharma.appointments.services.AppointmentTypeService;
import com.pharma.appointments.services.ReasonTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/appointments")
@CrossOrigin(origins = {"http://localhost:4201", "http://localhost:4200"})
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final AppointmentTypeService appointmentTypeService;

    private final ReasonTypeService reasonTypeService;

    public AppointmentController(AppointmentService appointmentService, AppointmentTypeService appointmentTypeService, ReasonTypeService reasonTypeService) {
        this.appointmentService = appointmentService;
        this.appointmentTypeService = appointmentTypeService;
        this.reasonTypeService = reasonTypeService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody AppointmentDto appointmentDto) {
        return ResponseEntity.ok(appointmentService.addAppointment(appointmentDto));
    }

    @GetMapping("/employee-id/{id}")
    public ResponseEntity<List<Appointment>> getAll(@PathVariable("id") long id) {
        return new ResponseEntity<>(appointmentService.getAllAppointmentsByEmployeeId(id), HttpStatus.OK);
    }

}
