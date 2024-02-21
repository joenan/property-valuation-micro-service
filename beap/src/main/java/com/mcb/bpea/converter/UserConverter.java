package com.mcb.bpea.converter;

import com.mcb.bpea.dto.UserDto;
import com.mcb.bpea.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserConverter {
    UserDto toUserDto(User source);

    User toUser(UserDto source);
}
