package com.mcb.bpea.repository;

import com.mcb.bpea.dto.ValuationProjection;
import com.mcb.bpea.entities.PropertyValuation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PropertyValuationRepository extends JpaRepository<PropertyValuation, Long> {
    Optional<PropertyValuation> findByEvaluationType(String type);

    Optional<PropertyValuation> findFirstByOrderByIdDesc();
}