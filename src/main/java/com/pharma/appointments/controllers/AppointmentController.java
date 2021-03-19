package com.pharma.appointments.controllers;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pharma.appointments.models.HibernateProxyTypeAdapter;
import com.pharma.appointments.models.dto.AppointmentDto;
import com.pharma.appointments.models.Appointment;
import com.pharma.appointments.services.AppointmentService;
import com.pharma.appointments.services.AppointmentTypeService;
import com.pharma.appointments.services.ReasonTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
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
    public ResponseEntity<String> getAll(@PathVariable("id") long id) {
        System.out.println(id);
        List<Appointment> appointments = appointmentService.getAllAppointmentsByEmployeeId(id);
        Gson gson = initiateGson();
        String result = gson.toJson(appointments);
        return new ResponseEntity<>(result, HttpStatus.OK);
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
    }};

}
