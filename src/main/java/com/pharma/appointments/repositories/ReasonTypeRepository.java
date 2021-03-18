package com.pharma.appointments.repositories;

import com.pharma.appointments.models.ReasonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonTypeRepository extends JpaRepository<ReasonType, Long> {
}
