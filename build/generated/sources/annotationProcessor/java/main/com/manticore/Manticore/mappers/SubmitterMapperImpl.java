package com.manticore.Manticore.mappers;

import com.manticore.Manticore.dtos.user_dtos.SubmitterDto;
import com.manticore.Manticore.models.user_models.Submitter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-24T12:56:05+0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.4.1 (BellSoft)"
)
@Component
public class SubmitterMapperImpl implements SubmitterMapper {

    @Override
    public SubmitterDto toDto(Submitter submitter) {
        if ( submitter == null ) {
            return null;
        }

        SubmitterDto submitterDto = new SubmitterDto();

        submitterDto.setId( submitter.getId() );
        submitterDto.setFullName( submitter.getFullName() );
        submitterDto.setEmail( submitter.getEmail() );
        submitterDto.setPosition( submitter.getPosition() );
        submitterDto.setGrade( submitter.getGrade() );

        return submitterDto;
    }

    @Override
    public Submitter toEntity(SubmitterDto submitterDto) {
        if ( submitterDto == null ) {
            return null;
        }

        Submitter submitter = new Submitter();

        submitter.setId( submitterDto.getId() );
        submitter.setFullName( submitterDto.getFullName() );
        submitter.setEmail( submitterDto.getEmail() );
        submitter.setPosition( submitterDto.getPosition() );
        submitter.setGrade( submitterDto.getGrade() );

        return submitter;
    }

    @Override
    public List<SubmitterDto> toDtoList(List<Submitter> submitters) {
        if ( submitters == null ) {
            return null;
        }

        List<SubmitterDto> list = new ArrayList<SubmitterDto>( submitters.size() );
        for ( Submitter submitter : submitters ) {
            list.add( toDto( submitter ) );
        }

        return list;
    }

    @Override
    public List<Submitter> toEntityList(List<SubmitterDto> submitterDtos) {
        if ( submitterDtos == null ) {
            return null;
        }

        List<Submitter> list = new ArrayList<Submitter>( submitterDtos.size() );
        for ( SubmitterDto submitterDto : submitterDtos ) {
            list.add( toEntity( submitterDto ) );
        }

        return list;
    }
}
