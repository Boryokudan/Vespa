package com.manticore.Manticore.user_dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitterDto {
    private Long id;
    private String fullName;
    private String email;
    private String position;
    private String grade;
}
