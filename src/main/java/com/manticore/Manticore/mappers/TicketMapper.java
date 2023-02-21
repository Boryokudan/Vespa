package com.manticore.Manticore.mappers;

import com.manticore.Manticore.dtos.TicketDto;
import com.manticore.Manticore.models.Ticket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketDto toDto(Ticket ticket);
    Ticket toEntity(TicketDto ticketDto);
    List<TicketDto> toDtoList(List<Ticket> tickets);
    List<Ticket> toEntityList(List<TicketDto> ticketDtos);
}
