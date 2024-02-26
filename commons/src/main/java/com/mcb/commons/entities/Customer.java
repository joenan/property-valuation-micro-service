package com.mcb.commons.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = {"nationalityNumber"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String customerNumber;
    @NotBlank
    private String shortName;
    private boolean isIndividual;
    private String nationality;
    @Column(unique = true)
    @NotBlank
    private String nationalityNumber;
    private String nationalityDescription;
    private String streetAddress;
    private String addressLine2;
    private String addressLine3;
    private String townCountry;
    private Integer postCode;
    private String country;
    private String countryCode;
    private String countryCodeNumber;
    private String dispatchCode;
    private String communicationChannel;
    @NotBlank
    @Column(unique = true)
    private String phoneNumber;
    private String officePhoneNumber;
    private String faxNumber;
    private String mobileOperatorISO;
    private String mobileOperatorCode;
    private String smsNumber;
    @Column(unique = true)
    @Email
    private String email;
}
