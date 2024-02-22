package com.mcb.app.repository;


import com.mcb.commons.entities.ValuationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValuationTypeRepository extends JpaRepository<ValuationType, Long> {
}
