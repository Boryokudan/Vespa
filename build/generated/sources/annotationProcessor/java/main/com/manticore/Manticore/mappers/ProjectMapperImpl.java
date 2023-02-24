package com.manticore.Manticore.mappers;

import com.manticore.Manticore.dtos.ProjectDto;
import com.manticore.Manticore.dtos.TicketDto;
import com.manticore.Manticore.dtos.user_dtos.DeveloperDto;
import com.manticore.Manticore.dtos.user_dtos.ProjectManagerDto;
import com.manticore.Manticore.dtos.user_dtos.SubmitterDto;
import com.manticore.Manticore.models.Project;
import com.manticore.Manticore.models.Ticket;
import com.manticore.Manticore.models.user_models.Developer;
import com.manticore.Manticore.models.user_models.ProjectManager;
import com.manticore.Manticore.models.user_models.Submitter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-23T13:40:15+0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.4.1 (BellSoft)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectDto toDto(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectDto projectDto = new ProjectDto();

        projectDto.setId( project.getId() );
        projectDto.setTitle( project.getTitle() );
        projectDto.setStatus( project.getStatus() );
        projectDto.setOverview( project.getOverview() );
        projectDto.setInitiationDate( project.getInitiationDate() );
        projectDto.setCompletionDate( project.getCompletionDate() );
        projectDto.setProjectManager( projectManagerToProjectManagerDto( project.getProjectManager() ) );
        projectDto.setTeam( developerListToDeveloperDtoList( project.getTeam() ) );
        projectDto.setSubmitters( submitterListToSubmitterDtoList( project.getSubmitters() ) );
        projectDto.setTickets( ticketListToTicketDtoList( project.getTickets() ) );

        return projectDto;
    }

    @Override
    public Project toEntity(ProjectDto projectDto) {
        if ( projectDto == null ) {
            return null;
        }

        Project project = new Project();

        project.setId( projectDto.getId() );
        project.setTitle( projectDto.getTitle() );
        project.setStatus( projectDto.getStatus() );
        project.setOverview( projectDto.getOverview() );
        project.setInitiationDate( projectDto.getInitiationDate() );
        project.setCompletionDate( projectDto.getCompletionDate() );
        project.setProjectManager( projectManagerDtoToProjectManager( projectDto.getProjectManager() ) );
        project.setTeam( developerDtoListToDeveloperList( projectDto.getTeam() ) );
        project.setSubmitters( submitterDtoListToSubmitterList( projectDto.getSubmitters() ) );
        project.setTickets( ticketDtoListToTicketList( projectDto.getTickets() ) );

        return project;
    }

    @Override
    public List<ProjectDto> toDtoList(List<Project> projects) {
        if ( projects == null ) {
            return null;
        }

        List<ProjectDto> list = new ArrayList<ProjectDto>( projects.size() );
        for ( Project project : projects ) {
            list.add( toDto( project ) );
        }

        return list;
    }

    @Override
    public List<Project> toEntityList(List<ProjectDto> projectDtos) {
        if ( projectDtos == null ) {
            return null;
        }

        List<Project> list = new ArrayList<Project>( projectDtos.size() );
        for ( ProjectDto projectDto : projectDtos ) {
            list.add( toEntity( projectDto ) );
        }

        return list;
    }

    protected ProjectManagerDto projectManagerToProjectManagerDto(ProjectManager projectManager) {
        if ( projectManager == null ) {
            return null;
        }

        ProjectManagerDto projectManagerDto = new ProjectManagerDto();

        projectManagerDto.setId( projectManager.getId() );
        projectManagerDto.setFullName( projectManager.getFullName() );
        projectManagerDto.setEmail( projectManager.getEmail() );

        return projectManagerDto;
    }

    protected DeveloperDto developerToDeveloperDto(Developer developer) {
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

    protected List<DeveloperDto> developerListToDeveloperDtoList(List<Developer> list) {
        if ( list == null ) {
            return null;
        }

        List<DeveloperDto> list1 = new ArrayList<DeveloperDto>( list.size() );
        for ( Developer developer : list ) {
            list1.add( developerToDeveloperDto( developer ) );
        }

        return list1;
    }

    protected SubmitterDto submitterToSubmitterDto(Submitter submitter) {
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

    protected List<SubmitterDto> submitterListToSubmitterDtoList(List<Submitter> list) {
        if ( list == null ) {
            return null;
        }

        List<SubmitterDto> list1 = new ArrayList<SubmitterDto>( list.size() );
        for ( Submitter submitter : list ) {
            list1.add( submitterToSubmitterDto( submitter ) );
        }

        return list1;
    }

    protected TicketDto ticketToTicketDto(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketDto ticketDto = new TicketDto();

        ticketDto.setId( ticket.getId() );
        ticketDto.setProjectId( ticket.getProjectId() );
        ticketDto.setTitle( ticket.getTitle() );
        ticketDto.setType( ticket.getType() );
        ticketDto.setContent( ticket.getContent() );
        ticketDto.setPriority( ticket.getPriority() );
        ticketDto.setStatus( ticket.getStatus() );
        ticketDto.setCreationDate( ticket.getCreationDate() );
        ticketDto.setDeadline( ticket.getDeadline() );
        ticketDto.setOwner( submitterToSubmitterDto( ticket.getOwner() ) );
        ticketDto.setAssignee( developerToDeveloperDto( ticket.getAssignee() ) );

        return ticketDto;
    }

    protected List<TicketDto> ticketListToTicketDtoList(List<Ticket> list) {
        if ( list == null ) {
            return null;
        }

        List<TicketDto> list1 = new ArrayList<TicketDto>( list.size() );
        for ( Ticket ticket : list ) {
            list1.add( ticketToTicketDto( ticket ) );
        }

        return list1;
    }

    protected ProjectManager projectManagerDtoToProjectManager(ProjectManagerDto projectManagerDto) {
        if ( projectManagerDto == null ) {
            return null;
        }

        ProjectManager projectManager = new ProjectManager();

        projectManager.setId( projectManagerDto.getId() );
        projectManager.setFullName( projectManagerDto.getFullName() );
        projectManager.setEmail( projectManagerDto.getEmail() );

        return projectManager;
    }

    protected Developer developerDtoToDeveloper(DeveloperDto developerDto) {
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

    protected List<Developer> developerDtoListToDeveloperList(List<DeveloperDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Developer> list1 = new ArrayList<Developer>( list.size() );
        for ( DeveloperDto developerDto : list ) {
            list1.add( developerDtoToDeveloper( developerDto ) );
        }

        return list1;
    }

    protected Submitter submitterDtoToSubmitter(SubmitterDto submitterDto) {
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

    protected List<Submitter> submitterDtoListToSubmitterList(List<SubmitterDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Submitter> list1 = new ArrayList<Submitter>( list.size() );
        for ( SubmitterDto submitterDto : list ) {
            list1.add( submitterDtoToSubmitter( submitterDto ) );
        }

        return list1;
    }

    protected Ticket ticketDtoToTicket(TicketDto ticketDto) {
        if ( ticketDto == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        ticket.setId( ticketDto.getId() );
        ticket.setProjectId( ticketDto.getProjectId() );
        ticket.setTitle( ticketDto.getTitle() );
        ticket.setType( ticketDto.getType() );
        ticket.setContent( ticketDto.getContent() );
        ticket.setPriority( ticketDto.getPriority() );
        ticket.setStatus( ticketDto.getStatus() );
        ticket.setCreationDate( ticketDto.getCreationDate() );
        ticket.setDeadline( ticketDto.getDeadline() );
        ticket.setOwner( submitterDtoToSubmitter( ticketDto.getOwner() ) );
        ticket.setAssignee( developerDtoToDeveloper( ticketDto.getAssignee() ) );

        return ticket;
    }

    protected List<Ticket> ticketDtoListToTicketList(List<TicketDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Ticket> list1 = new ArrayList<Ticket>( list.size() );
        for ( TicketDto ticketDto : list ) {
            list1.add( ticketDtoToTicket( ticketDto ) );
        }

        return list1;
    }
}
