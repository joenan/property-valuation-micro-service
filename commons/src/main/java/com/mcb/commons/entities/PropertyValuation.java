package com.mcb.commons.entities;


import com.mcb.commons.enums.EvaluationType;
import com.mcb.commons.enums.FacilityType;
import com.mcb.commons.enums.Purpose;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "property_valuation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
public class PropertyValuation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private FacilityType facilityType;
    private String category;
    private String purpose;
    private int termMonths;
    private com.mcb.commons.enums.Currency currency;
    private double amount;
    private String fosReference;
    private String reference;
    private EvaluationType evaluationType;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customer> borrowers;

    @OneToOne
    @JoinColumn(name = "facility_details_id")
    private FacilityDetails facilityDetails;
}