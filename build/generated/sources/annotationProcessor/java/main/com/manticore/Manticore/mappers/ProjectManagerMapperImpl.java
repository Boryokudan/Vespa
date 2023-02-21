package com.manticore.Manticore.mappers;

import com.manticore.Manticore.models.user_models.ProjectManager;
import com.manticore.Manticore.user_dtos.ProjectManagerDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-21T11:53:34+0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.4.1 (BellSoft)"
)
@Component
public class ProjectManagerMapperImpl implements ProjectManagerMapper {

    @Override
    public ProjectManagerDto toDto(ProjectManager projectManager) {
        if ( projectManager == null ) {
            return null;
        }

        ProjectManagerDto projectManagerDto = new ProjectManagerDto();

        projectManagerDto.setId( projectManager.getId() );
        projectManagerDto.setFullName( projectManager.getFullName() );
        projectManagerDto.setEmail( projectManager.getEmail() );

        return projectManagerDto;
    }

    @Override
    public ProjectManager toEntity(ProjectManagerDto projectManagerDto) {
        if ( projectManagerDto == null ) {
            return null;
        }

        ProjectManager projectManager = new ProjectManager();

        projectManager.setId( projectManagerDto.getId() );
        projectManager.setFullName( projectManagerDto.getFullName() );
        projectManager.setEmail( projectManagerDto.getEmail() );

        return projectManager;
    }

    @Override
    public List<ProjectManagerDto> toDtoList(List<ProjectManager> projectManagers) {
        if ( projectManagers == null ) {
            return null;
        }

        List<ProjectManagerDto> list = new ArrayList<ProjectManagerDto>( projectManagers.size() );
        for ( ProjectManager projectManager : projectManagers ) {
            list.add( toDto( projectManager ) );
        }

        return list;
    }

    @Override
    public List<ProjectManager> toEntityList(List<ProjectManagerDto> projectManagerDtos) {
        if ( projectManagerDtos == null ) {
            return null;
        }

        List<ProjectManager> list = new ArrayList<ProjectManager>( projectManagerDtos.size() );
        for ( ProjectManagerDto projectManagerDto : projectManagerDtos ) {
            list.add( toEntity( projectManagerDto ) );
        }

        return list;
    }
}
