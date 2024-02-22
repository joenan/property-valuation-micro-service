package com.mcb.app.repository;


import com.mcb.commons.entities.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
