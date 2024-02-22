package com.mcb.app.repository;


import com.mcb.commons.entities.PropertyValuation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyValuationRepository extends JpaRepository<PropertyValuation, Long> {
    Optional<PropertyValuation> findByEvaluationType(String type);

    Optional<PropertyValuation> findFirstByOrderByIdDesc();
}