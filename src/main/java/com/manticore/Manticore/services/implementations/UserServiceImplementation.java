package com.manticore.Manticore.services.implementations;

import com.manticore.Manticore.mappers.UserMapper;
import com.manticore.Manticore.user_dtos.UserDto;
import com.manticore.Manticore.models.user_models.User;
import com.manticore.Manticore.repositories.UserRepository;
import com.manticore.Manticore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    @Override
    public UserDto getAdminProfileData() {
        return userMapper.toDto(getCurrentUser());
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsersSortedByFullName() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "fullName"));
    }

    @Override
    public List<User> getAllUsersSortedByEmail() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "email"));
    }

    @Override
    public List<User> getAllUsersSortedByRole() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "permission"));
    }

    @Override
    public User registerUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserDtoByEmail(String email) {
        return userMapper.toDto(getUserByEmail(email));
    }

    @Override
    public List<UserDto> getAllUserDtos() {
        return userMapper.toDtoList(getAllUsers());
    }

    @Override
    public List<UserDto> getAllUserDtosSortedByFullName() {
        return userMapper.toDtoList(getAllUsersSortedByFullName());
    }

    @Override
    public List<UserDto> getAllUserDtosSortedByEmail() {
        return userMapper.toDtoList(getAllUsersSortedByEmail());
    }

    @Override
    public List<UserDto> getAllUserDtosSortedByRole() {
        return userMapper.toDtoList(getAllUsersSortedByRole());
    }
}
