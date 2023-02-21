package com.manticore.Manticore.services.implementations;

import com.manticore.Manticore.dtos.TicketDto;
import com.manticore.Manticore.mappers.TicketMapper;
import com.manticore.Manticore.models.Project;
import com.manticore.Manticore.models.Ticket;
import com.manticore.Manticore.repositories.DeveloperRepository;
import com.manticore.Manticore.repositories.SubmitterRepository;
import com.manticore.Manticore.repositories.TicketRepository;
import com.manticore.Manticore.services.ProjectService;
import com.manticore.Manticore.services.TicketService;
import com.manticore.Manticore.services.UserService;
import com.manticore.Manticore.models.user_models.Developer;
import com.manticore.Manticore.models.user_models.Submitter;
import com.manticore.Manticore.models.user_models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImplementation implements TicketService {

    private final UserService userService;
    private final ProjectService projectService;
    private final DeveloperRepository developerRepository;
    private final SubmitterRepository submitterRepository;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Ticket> getAllTicketsByProjectId(Long projectId) {
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }



    @Override
    public Ticket addTicket(Ticket ticket) {

        // TODO: SET CURRENT USER AS THE OWNER OF THE TICKET
        // SETTING CURRENT USER AS THE OWNER OF THE TICKET
        User currentUser = userService.getCurrentUser();
        Submitter submitter = submitterRepository.findSubmitterByEmail(currentUser.getEmail());
        if (submitter != null) ticket.setOwner(submitter);

        Project project = projectService.getProjectById(ticket.getProjectId());
        List<Ticket> projectTickets = project.getTickets();
        ticketRepository.save(ticket);
        projectTickets.add(ticket);
        project.setTickets(projectTickets);
        projectService.updateProject(project);

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket assignDeveloperToTicket(Long ticketId, String developerId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();

        if (!developerId.equals("null")) {
            Developer developer = developerRepository.findById(Long.parseLong(developerId)).orElseThrow();
            ticket.setAssignee(developer);
            if (ticket.getStatus().equals("Pending")) ticket.setStatus("Assigned");
        } else {
            ticket.setAssignee(null);
            ticket.setStatus("Pending");
        }

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket selectTicketStatus(Long ticketId, String status) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();

        if (status.equals("Assigned") || status.equals("In Progress") || status.equals("Closed")) {
            ticket.setStatus(status);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        projectService.deleteProjectTicketById(id);
    }


    // <------------- Data Transfer Object Methods ------------->
    @Override
    public TicketDto getTicketDtoById(Long id) {
        return ticketMapper.toDto(getTicketById(id));
    }

    @Override
    public List<TicketDto> getAllTicketDtosByProjectId(Long projectId) {
        return null;
    }

    @Override
    public List<TicketDto> getAllTicketDtos() {
        return ticketMapper.toDtoList(getAllTickets());
    }

    @Override
    public TicketDto addTicketDto(TicketDto ticketDto) {
        return ticketMapper.toDto(addTicket(ticketMapper.toEntity(ticketDto)));
    }

    @Override
    public TicketDto assignDeveloperToTicketDto(Long ticketId, String developerId) {
        return ticketMapper.toDto(assignDeveloperToTicket(ticketId, developerId));
    }

    @Override
    public TicketDto selectTicketDtoStatus(Long ticketId, String status) {
        return ticketMapper.toDto(selectTicketStatus(ticketId, status));
    }

    @Override
    public TicketDto updateTicketDto(TicketDto ticketDto) {
        return ticketMapper.toDto(updateTicket(ticketMapper.toEntity(ticketDto)));
    }
}
