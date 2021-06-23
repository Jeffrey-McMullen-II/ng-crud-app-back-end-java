package com.crud.app.user;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    UserService (UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    UserDTO createUser(User user) {
        return userMapper.explicitMapModelToDTO(userRepository.save(user));
    }

    List<UserDTO> findAllUsers() {
        return userMapper.mapModelsToDTOS((userRepository.findAll()));
    }

    List<UserDTO> findAllUsersByFirstName() {
        return userMapper.mapModelsToDTOS(userRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName")));
    }

    UserDTO findUserByUserId(int userId) {
        return userMapper.explicitMapModelToDTO(userRepository.findUserByUserId(userId));
    }

    UserDTO updateUser(User user) {
        return userMapper.explicitMapModelToDTO(userRepository.save(user));
    }

    UserDTO deleteUserByUserId(int userId) {
        User user = findUserByUserIdForDeletion(userId);
        if (user != null) {
            userRepository.delete(user);
        }
        return userMapper.explicitMapModelToDTO(user);
    }

    private User findUserByUserIdForDeletion(int userId) {
        return userRepository.findUserByUserId(userId);
    }
}
