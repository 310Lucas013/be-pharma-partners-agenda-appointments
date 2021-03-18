package com.pharma.appointments.services;

import com.pharma.appointments.repositories.ReasonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReasonTypeService {
    @Autowired
    private ReasonTypeRepository reasonTypeRepository;
}
