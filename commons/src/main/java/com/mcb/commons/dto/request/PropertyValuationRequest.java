package com.mcb.commons.dto.request;


import com.mcb.commons.entities.FacilityDetails;
import com.mcb.commons.enums.FacilityType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PropertyValuationRequest {

    private FacilityType facilityType;
    private String category;
    private int purpose;
    private int termMonths;
    private String currency;
    private double amount;
    private String fosReference;
    private String reference;
    private String evaluationType;
    private List<Long> borrowerIds;
    private FacilityDetails facilityDetails;
}
