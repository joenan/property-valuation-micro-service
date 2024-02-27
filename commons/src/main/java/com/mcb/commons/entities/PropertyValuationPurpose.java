package com.mcb.commons.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "purpose")
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
