package com.pharma.appointments.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Appointment
{
    @GetMapping("/")
    public String greeting() {
        return "Hello world";
    }
}
