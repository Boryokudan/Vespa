package com.manticore.Manticore.registration_forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SubmitterRegistrationForm extends UserRegistrationForm {
    private String position;
    private String grade;
}
