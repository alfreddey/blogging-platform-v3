package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.entity.User;
import com.example.demo.repository.interfaces.UserRepository;
import com.example.demo.service.interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MongoUserService implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public MongoUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));
    }

    @Override
    public List<User> getAll(int page, int size, String sortBy) {
        var pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return userRepository.findAll(pageable).getContent();
    }

    @Transactional
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(String userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public User updatePassword(String userId, String password) {
        return userRepository.updatePasswordById(userId, password);
    }
}
