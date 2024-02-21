package com.mcb.bpea.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "property_valuation_purpose")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PropertyValuationPurpose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
