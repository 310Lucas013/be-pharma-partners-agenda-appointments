package com.pharma.appointments.controllers;


import com.pharma.appointments.models.Appointment;
import com.pharma.appointments.services.AppointmentService;
import com.pharma.appointments.services.AppointmentTypeService;
import com.pharma.appointments.services.ReasonTypeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/appointments")
@CrossOrigin(origins = {"http://localhost:4201"})
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentTypeService appointmentTypeService;

    @Autowired
    private ReasonTypeService reasonTypeService;

    @GetMapping("/employee-id/{id}")
    public ResponseEntity<List<Appointment>> getAll(@PathVariable("id") long id) {
        return new ResponseEntity<>(appointmentService.getAllAppointmentsByEmployeeId(id), HttpStatus.OK);
    }

}
