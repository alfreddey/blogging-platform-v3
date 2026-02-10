package com.example.demo.utils;

import com.example.demo.model.entity.Post;

public interface PostSearch {
    Post findByTitle(String title);
}
