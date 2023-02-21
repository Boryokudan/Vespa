package com.manticore.Manticore.dtos;

import com.manticore.Manticore.user_dtos.DeveloperDto;
import com.manticore.Manticore.user_dtos.ProjectManagerDto;
import com.manticore.Manticore.user_dtos.SubmitterDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjectDto {

    private Long id;
    private String title;
    private String status;
    private String overview;
    private LocalDate initiationDate;
    private LocalDate completionDate;
    private ProjectManagerDto projectManager;
    private List<DeveloperDto> team;
    private List<SubmitterDto> submitters;
    private List<TicketDto> tickets;
}
