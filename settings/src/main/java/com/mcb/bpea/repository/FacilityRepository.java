package com.mcb.bpea.repository;

import com.mcb.bpea.entities.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
