package com.mcb.bpea.converter;

import com.mcb.bpea.dto.CommentDto;
import com.mcb.bpea.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CommentConverter {
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "propertyValuationId", source = "propertyValuation.id")
    CommentDto toCommentDto(Comment source);

    @Mapping(target = "user.username", source = "username")
    @Mapping(target = "propertyValuation.id", source = "propertyValuationId")
    Comment toComment(CommentDto source);
}
