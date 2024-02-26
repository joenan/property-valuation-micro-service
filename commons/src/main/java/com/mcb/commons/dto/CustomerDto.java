package com.mcb.commons.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CustomerDto {

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
