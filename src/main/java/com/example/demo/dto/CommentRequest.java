package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentRequest {
    @NotBlank(message = "Author email must not be blank")
    @Email(message = "Author email must be a valid email address")
    private String authorEmail;

    @NotBlank(message = "Text must not be blank")
    private String text;
}
