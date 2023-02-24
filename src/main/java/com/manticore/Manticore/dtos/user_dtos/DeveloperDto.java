package com.manticore.Manticore.dtos.user_dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeveloperDto {
    private Long id;
    private String fullName;
    private String email;
    private String grade;
    private String specialization;
}
