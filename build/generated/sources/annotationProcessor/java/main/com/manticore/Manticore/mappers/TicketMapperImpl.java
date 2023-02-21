package com.manticore.Manticore.mappers;

import com.manticore.Manticore.dtos.TicketDto;
import com.manticore.Manticore.models.Ticket;
import com.manticore.Manticore.models.user_models.Developer;
import com.manticore.Manticore.models.user_models.Submitter;
import com.manticore.Manticore.user_dtos.DeveloperDto;
import com.manticore.Manticore.user_dtos.SubmitterDto;
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
public class TicketMapperImpl implements TicketMapper {

    @Override
    public TicketDto toDto(Ticket ticket) {
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

    @Override
    public Ticket toEntity(TicketDto ticketDto) {
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

    @Override
    public List<TicketDto> toDtoList(List<Ticket> tickets) {
        if ( tickets == null ) {
            return null;
        }

        List<TicketDto> list = new ArrayList<TicketDto>( tickets.size() );
        for ( Ticket ticket : tickets ) {
            list.add( toDto( ticket ) );
        }

        return list;
    }

    @Override
    public List<Ticket> toEntityList(List<TicketDto> ticketDtos) {
        if ( ticketDtos == null ) {
            return null;
        }

        List<Ticket> list = new ArrayList<Ticket>( ticketDtos.size() );
        for ( TicketDto ticketDto : ticketDtos ) {
            list.add( toEntity( ticketDto ) );
        }

        return list;
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
}
