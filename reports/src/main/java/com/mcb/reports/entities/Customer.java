package com.mcb.reports.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerNumber;
    private String shortName;
    private boolean isIndividual;
    private String nationality;
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
    private String phoneNumber;
    private String officePhoneNumber;
    private String faxNumber;
    private String mobileOperatorISO;
    private String mobileOperatorCode;
    private String smsNumber;
    private String email;
}
