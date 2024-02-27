package com.mcb.reports.repository;


import com.mcb.commons.entities.PvsValuationRequestViews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PropertyValuationRepository extends JpaRepository<PvsValuationRequestViews, Long> {

    @Query(value = "SELECT pv.id AS id, pv.reference AS reference, pv.created_at AS receivedOn, " +
            "c.short_name AS borrowersName, pv.fos_reference AS fosRef, pv.created_at AS createdOn, " +
            "pv.updated_at AS modifiedOn " +
            "FROM property_valuation pv " +
            "JOIN property_valuation_borrowers pvb ON pv.id = pvb.property_valuation_id " +
            "JOIN customers c ON pvb.borrowers_id = c.id " +
            "WHERE (:referenceParam IS NULL OR pv.reference = :referenceParam) " +
            "AND (:createdAtParam IS NULL OR pv.created_at = :createdAtParam) " +
            "AND (:fosRefParam IS NULL OR pv.fos_reference = :fosRefParam)", nativeQuery = true)
    List<Object[]> findCustomProjection(
            @Param("referenceParam") String referenceParam,
            @Param("createdAtParam") LocalDateTime createdAtParam,
            @Param("fosRefParam") String fosRefParam
    );
}
