package com.manticore.Manticore.mappers;

import com.manticore.Manticore.dtos.user_dtos.DeveloperDto;
import com.manticore.Manticore.models.user_models.Developer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-23T13:40:14+0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.4.1 (BellSoft)"
)
@Component
public class DeveloperMapperImpl implements DeveloperMapper {

    @Override
    public DeveloperDto toDto(Developer developer) {
        if ( developer == null ) {
            return null;
        }

        DeveloperDto developerDto = new DeveloperDto();

        developerDto.setId( developer.getId() );
        developerDto.setFullName( developer.getFullName() );
        developerDto.setEmail( developer.getEmail() );
        developerDto.setGrade( developer.getGrade() );
        developerDto.setSpecialization( developer.getSpecialization() );

        return developerDto;
    }

    @Override
    public Developer toEntity(DeveloperDto developerDto) {
        if ( developerDto == null ) {
            return null;
        }

        Developer developer = new Developer();

        developer.setId( developerDto.getId() );
        developer.setFullName( developerDto.getFullName() );
        developer.setEmail( developerDto.getEmail() );
        developer.setGrade( developerDto.getGrade() );
        developer.setSpecialization( developerDto.getSpecialization() );

        return developer;
    }

    @Override
    public List<DeveloperDto> toDtoList(List<Developer> developers) {
        if ( developers == null ) {
            return null;
        }

        List<DeveloperDto> list = new ArrayList<DeveloperDto>( developers.size() );
        for ( Developer developer : developers ) {
            list.add( toDto( developer ) );
        }

        return list;
    }

    @Override
    public List<Developer> toEntityList(List<DeveloperDto> developerDtos) {
        if ( developerDtos == null ) {
            return null;
        }

        List<Developer> list = new ArrayList<Developer>( developerDtos.size() );
        for ( DeveloperDto developerDto : developerDtos ) {
            list.add( toEntity( developerDto ) );
        }

        return list;
    }
}
