package com.example.demo.repository.interfaces;

import com.example.demo.model.entity.Post;
import com.example.demo.repository.interfaces.custom.CustomPostRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String>, CustomPostRepository {
    Post findByTitle(String title);
}
