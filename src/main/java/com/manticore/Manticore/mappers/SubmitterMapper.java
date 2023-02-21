package com.manticore.Manticore.mappers;

import com.manticore.Manticore.user_dtos.SubmitterDto;
import com.manticore.Manticore.models.user_models.Submitter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubmitterMapper {

    SubmitterDto toDto(Submitter submitter);
    Submitter toEntity(SubmitterDto submitterDto);
    List<SubmitterDto> toDtoList(List<Submitter> submitters);
    List<Submitter> toEntityList(List<SubmitterDto> submitterDtos);
}
