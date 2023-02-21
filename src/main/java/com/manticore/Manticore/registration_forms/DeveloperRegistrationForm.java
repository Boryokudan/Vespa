package com.manticore.Manticore.registration_forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DeveloperRegistrationForm extends UserRegistrationForm {
    private String grade;
    private String specialization;
}
