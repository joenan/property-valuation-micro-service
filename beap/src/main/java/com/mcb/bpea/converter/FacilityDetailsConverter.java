package com.mcb.bpea.converter;

import com.mcb.bpea.dto.FacilityDetailsDto;
import com.mcb.bpea.entities.FacilityDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface FacilityDetailsConverter {
    FacilityDetailsDto toFacilityDetailsDto(FacilityDetails source);
    FacilityDetails toFacilityDetails(FacilityDetailsDto source);

}
