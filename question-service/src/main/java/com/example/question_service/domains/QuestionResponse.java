package com.example.question_service.domains;

import lombok.Data;

@Data
public class QuestionResponse {
    private Integer id;
    private String question_title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
