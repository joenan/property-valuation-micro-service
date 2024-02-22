package com.mcb.reports.entities;


import com.mcb.reports.enums.Category;
import com.mcb.reports.enums.Currency;
import com.mcb.reports.enums.FacilityType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "facility_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FacilityDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "facility_type")
    private FacilityType facilityType;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String purposeOfPropertyValuation;
    private int termInMonths;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal amount;

    @OneToOne(mappedBy = "facilityDetails", cascade = CascadeType.ALL)
    private PropertyValuation propertyValuation;
}