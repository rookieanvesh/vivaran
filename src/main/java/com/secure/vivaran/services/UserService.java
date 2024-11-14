package com.secure.vivaran.services;

import com.secure.vivaran.dtos.UserDTO;
import com.secure.vivaran.models.User;

import java.util.List;

public interface UserService {
    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserDTO getUserById(Long id);
}
