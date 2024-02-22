package com.mcb.commons.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "property_valuation_borrowers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyValuationBorrowers {

    @Id
    @ManyToOne
    @JoinColumn(name = "property_valuation_id")
    private PropertyValuation propertyValuation;

    @Id
    @ManyToOne
    @JoinColumn(name = "borrowers_id")
    private Customer borrower;
}
