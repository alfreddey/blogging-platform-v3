package com.example.demo.mapper;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class MongoUserMapper implements Mapper<User, UserResponse, UserRequest> {
    @Override
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        var userResponse = new UserResponse();

        userResponse.id = user.getId();
        userResponse.email = user.getEmail();
        userResponse.name = user.getName();

        return userResponse;
    }

    @Override
    public User toEntity(UserRequest request) {
        return request == null ? null : map(null, request.name, request.email, request.password);
    }

    private static User map(String id, String name, String email, String password) {
        var user = new User();

        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
