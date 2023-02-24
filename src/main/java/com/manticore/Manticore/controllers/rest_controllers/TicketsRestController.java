package com.manticore.Manticore.controllers.rest_controllers;

import com.manticore.Manticore.dtos.TicketDto;
import com.manticore.Manticore.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tickets", produces = "application/json")
@RequiredArgsConstructor
public class TicketsRestController {
    private final TicketService ticketService;

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public TicketDto getTicket(@PathVariable(name = "id") Long id) {
        return ticketService.getTicketDtoById(id);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<TicketDto> getAllTickets() {
        return ticketService.getAllTicketDtos();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUB')")
    public TicketDto createNewTicket(@RequestBody TicketDto newTicketDto) {
        return ticketService.addTicketDto(newTicketDto);
    }

    @PutMapping(params = {"ticket_id", "dev_id"})
    @PreAuthorize("hasAnyRole('ADMIN', 'PM')")
    public TicketDto assignDeveloperToTicket(@RequestParam("ticket_id") Long ticketId, @RequestParam("dev_id") String developerId) {
        return ticketService.assignDeveloperToTicketDto(ticketId, developerId);
    }

    @PutMapping(params = {"ticket_id", "status"})
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public TicketDto assignStatusToTicket(@RequestParam("ticket_id") Long ticketId, @RequestParam("status") String status) {
        return ticketService.selectTicketDtoStatus(ticketId, status);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUB')")
    public TicketDto updateTicket(@RequestBody TicketDto ticketDto) {
        return ticketService.updateTicketDto(ticketDto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUB')")
    public void deleteTicket(@PathVariable(name = "id") Long id) {
        ticketService.deleteTicket(id);
    }
}
