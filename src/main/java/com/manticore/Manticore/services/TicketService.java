package com.manticore.Manticore.services;

import com.manticore.Manticore.dtos.TicketDto;
import com.manticore.Manticore.models.Ticket;

import java.util.List;

public interface TicketService {
    Ticket getTicketById(Long id);
    List<Ticket> getAllTicketsByProjectId(Long projectId);
    List<Ticket> getAllTickets();
    Ticket addTicket(Ticket ticket);
    Ticket assignDeveloperToTicket(Long ticketId, String developerId);
    Ticket selectTicketStatus(Long ticketId, String status);
    Ticket updateTicket(Ticket ticket);
    void deleteTicket(Long id);
//    List<Project> filterTicketsForCurrentUser(List<Project> currentUserProjects);

    // <------------- Data Transfer Object Methods ------------->
    TicketDto getTicketDtoById(Long id);
    List<TicketDto> getAllTicketDtosByProjectId(Long projectId);
    List<TicketDto> getAllTicketDtos();
    TicketDto addTicketDto(TicketDto ticketDto);
    TicketDto assignDeveloperToTicketDto(Long ticketId, String developerId);
    TicketDto selectTicketDtoStatus(Long ticketId, String status);
    TicketDto updateTicketDto(TicketDto ticketDto);
}
