package com.mcb.reports.repository;

import com.mcb.reports.entities.views.PvsValuationRequestViews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PropertyValuationRepository extends JpaRepository<PvsValuationRequestViews, Long> {

    @Query("SELECT pv.id AS id, pv.reference AS reference, pv.createdAt AS receivedOn, " +
            "c.shortName AS borrowersName, pv.fosReference AS fosRef, pv.createdAt AS createdOn, " +
            "pv.updatedAt AS modifiedOn " +
            "FROM PropertyValuation pv " +
            "JOIN PropertyValuationBorrowers pvb ON pv.id = pvb.propertyValuation.id " +
            "JOIN Customer c ON pvb.borrower.id = c.id " +
            "WHERE (:referenceParam IS NULL OR pv.reference = :referenceParam) " +
            "AND (:createdAtParam IS NULL OR pv.createdAt = :createdAtParam) " +
            "AND (:fosRefParam IS NULL OR pv.fosReference = :fosRefParam)")
    List<PvsValuationRequestViews> findCustomProjection(
            @Param("referenceParam") String referenceParam,
            @Param("createdAtParam") LocalDateTime createdAtParam,
            @Param("fosRefParam") String fosRefParam
    );
}
