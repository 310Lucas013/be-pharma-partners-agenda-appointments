package com.pharma.appointments.controllers;

import com.google.gson.Gson;
import com.pharma.appointments.events.CreateAppointmentEvent;
import com.pharma.appointments.models.dto.AppointmentDto;
import com.pharma.appointments.models.Appointment;
import com.pharma.appointments.services.AppointmentService;
import com.pharma.appointments.services.AppointmentTypeService;
import com.pharma.appointments.services.ReasonTypeService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(value = "/appointments")
@CrossOrigin(origins = {"http://localhost:4201", "http://localhost:8080", "http://localhost:8081",
        "http://localhost:8082", "http://localhost:8083", "http://localhost:8084", "http://localhost:8085",
        "http://localhost:5672", "http://localhost:15672"})
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final AppointmentTypeService appointmentTypeService;

    private final ReasonTypeService reasonTypeService;

    private final AmqpTemplate rabbitTemplate;

    private final Gson gson;

    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routingKey}")
    private String routingkey;

    public AppointmentController(AppointmentService appointmentService, AppointmentTypeService appointmentTypeService, ReasonTypeService reasonTypeService, AmqpTemplate rabbitTemplate, Gson gson) {
        this.appointmentService = appointmentService;
        this.appointmentTypeService = appointmentTypeService;
        this.reasonTypeService = reasonTypeService;
        this.rabbitTemplate = rabbitTemplate;
        this.gson = gson;
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

    @RequestMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> getAllAppointments() {
        List<Appointment> ls = appointmentService.getAllAppointments();

        CreateAppointmentEvent event = new CreateAppointmentEvent();
        event.setId(ls.get(0).getId());
        event.setEmployeeId(ls.get(0).getEmployeeId());
        event.setPatientId(ls.get(0).getPatientId());
        event.setLocationid(ls.get(0).getLocationId());
        String json = gson.toJson(event);
        Message message = MessageBuilder
                .withBody(json.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
        rabbitTemplate.convertAndSend(exchange, "create-appointment", message);
        return ResponseEntity.ok(ls);
    }
}
