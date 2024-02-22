package com.mcb.reports.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "property_valuation_purpose")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyValuationPurpose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
