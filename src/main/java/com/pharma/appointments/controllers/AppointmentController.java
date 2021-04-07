package com.pharma.appointments.controllers;

import com.pharma.appointments.models.dto.AppointmentDto;
import com.pharma.appointments.models.Appointment;
import com.pharma.appointments.services.AppointmentService;
import com.pharma.appointments.services.AppointmentTypeService;
import com.pharma.appointments.services.ReasonTypeService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final AppointmentTypeService appointmentTypeService;

    private final ReasonTypeService reasonTypeService;

    private final AmqpTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routingKey}")
    private String routingkey;

    public AppointmentController(AppointmentService appointmentService, AppointmentTypeService appointmentTypeService, ReasonTypeService reasonTypeService, AmqpTemplate rabbitTemplate) {
        this.appointmentService = appointmentService;
        this.appointmentTypeService = appointmentTypeService;
        this.reasonTypeService = reasonTypeService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody AppointmentDto appointmentDto) {
        return ResponseEntity.ok(appointmentService.addAppointment(appointmentDto));
    }

    @GetMapping("/employee-id/{id}")
    public ResponseEntity<?> getAll(@PathVariable("id") long id) {
        List<Appointment> ls = appointmentService.getAllAppointmentsByEmployeeId(id);
        return ResponseEntity.ok(ls);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllAppointments() {
        List<Appointment> ls = appointmentService.getAllAppointments();
        rabbitTemplate.convertAndSend(exchange, routingkey, ls);
        return ResponseEntity.ok(ls);
    }
}
