package com.mcb.bpea.dto.request;

import com.mcb.bpea.entities.Customer;
import com.mcb.bpea.entities.FacilityDetails;
import com.mcb.bpea.enums.FacilityType;
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
