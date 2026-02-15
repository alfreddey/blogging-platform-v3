package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentResponse {
    private String id;
    private String authorEmail;
    private String text;
    private Date createdAt;
}
