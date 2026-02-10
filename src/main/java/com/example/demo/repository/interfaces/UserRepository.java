package com.example.demo.repository.interfaces;

import com.example.demo.model.entity.User;
import com.example.demo.repository.interfaces.custom.CustomUserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CustomUserRepository, MongoRepository<User, String> {
}
