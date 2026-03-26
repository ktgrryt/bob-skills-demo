package com.demo.service;

import com.demo.model.User;
import com.demo.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserService {
    
    @Inject
    private UserRepository userRepository;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public void createUser(User user) {
        validateUser(user);
        userRepository.save(user);
    }
    
    public void updateUser(User user) {
        validateUser(user);
        userRepository.update(user);
    }
    
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
    
    private void validateUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (user.getAge() < 0) {
            throw new IllegalArgumentException("Age must be positive");
        }
    }
}

// Made with Bob
