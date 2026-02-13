package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private String id;
    private String title;
    private String content;
    private String authorEmail;
    private List<String> tags;
    private Date createdAt;
}
