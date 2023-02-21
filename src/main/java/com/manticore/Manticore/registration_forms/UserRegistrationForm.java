package com.manticore.Manticore.registration_forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class UserRegistrationForm {
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
}
