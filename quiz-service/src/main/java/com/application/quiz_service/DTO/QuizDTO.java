package com.application.quiz_service.DTO;

import lombok.Data;

@Data
public class QuizDTO {
    private String categoryName;
    private Integer numberOfQuestions;
    private String title;
}
