package com.manticore.Manticore.dtos;

import com.manticore.Manticore.user_dtos.DeveloperDto;
import com.manticore.Manticore.user_dtos.SubmitterDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TicketDto {

    private Long id;
    private Long projectId;
    private String title;
    private String type;
    private String content;
    private String priority;
    private String status;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;
    private SubmitterDto owner;
    private DeveloperDto assignee;
}
