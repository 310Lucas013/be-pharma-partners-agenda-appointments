package com.pharma.appointments.controllers;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pharma.appointments.models.AppointmentStatus;
import com.pharma.appointments.models.HibernateProxyTypeAdapter;
import com.google.gson.GsonBuilder;
import com.pharma.appointments.events.CreateAppointmentEvent;
import com.pharma.appointments.models.HibernateProxyTypeAdapter;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/appointments")
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

    @GetMapping("/employee-id/{id}")
    public ResponseEntity<String> getAll(@PathVariable("id") long id) {
        System.out.println("id = " + id);
        List<Appointment> appointments = appointmentService.getAllAppointmentsByEmployeeId(id);
        System.out.println("list size = " + appointments.size());
        Gson gson = initiateGson();
        String result = gson.toJson(appointments);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> getAllAppointments() {
        List<Appointment> ls = appointmentService.getAllAppointments();
        return ResponseEntity.ok(ls);
    }

    @RequestMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<?> createAppointments(@RequestBody AppointmentDto appointmentDto) {
        Appointment appointment;
        try {
            appointment = new Appointment(appointmentDto);
            appointment.setAppointmentStatus(AppointmentStatus.ABSENT);
        } catch (NullPointerException exception) {
            System.out.println(exception);
            return new ResponseEntity<>("Failed to create appointment", HttpStatus.BAD_REQUEST);
        }
        appointment = appointmentService.addAppointment(appointment);
        if (appointment == null) {
            return new ResponseEntity<>("Failed to create appointment", HttpStatus.BAD_REQUEST);
        }
        CreateAppointmentEvent event = new CreateAppointmentEvent(appointmentDto);
        rabbitTemplate.convertAndSend(exchange, "create-appointment", gson.toJson(event));
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<?> changeAppointment(@RequestBody AppointmentDto newAppointment) {
        Appointment appointment;
        try {
            appointment = new Appointment(newAppointment);
            return ResponseEntity.ok(appointmentService.addAppointment(appointment));
        } catch (NullPointerException exception) {
            System.out.println(exception);
        }
        return new ResponseEntity<>("Failed to update appointment", HttpStatus.BAD_REQUEST);
    }

    private Gson initiateGson() {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        boolean exclude = false;
                        try {
                            exclude = EXCLUDE.contains(f.getName());
                        } catch (Exception ignore) {
                        }
                        return exclude;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                });
        return b.create();
    }

    private static final List<String> EXCLUDE = new ArrayList<>() {{
        add("appointment");
        add("appointments");
    }};
}
