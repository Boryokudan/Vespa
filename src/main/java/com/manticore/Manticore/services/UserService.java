package com.manticore.Manticore.services;

import com.manticore.Manticore.user_dtos.UserDto;
import com.manticore.Manticore.models.user_models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User getCurrentUser();
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> getAllUsersSortedByFullName();
    List<User> getAllUsersSortedByEmail();
    List<User> getAllUsersSortedByRole();
    User registerUser(User user);
    User updateUser(User user);
    void deleteUserById(Long id);
    void deleteUser(User user);

    UserDto getAdminProfileData();
    UserDto getUserDtoByEmail(String email);
    List<UserDto> getAllUserDtos();
    List<UserDto> getAllUserDtosSortedByFullName();
    List<UserDto> getAllUserDtosSortedByEmail();
    List<UserDto> getAllUserDtosSortedByRole();
}
