package com.example.quiz_service.domains;

import lombok.Data;

@Data
public class QuizRequestDto {
    private String category;
    private Integer noOfQuestion;
    private String title;
}
