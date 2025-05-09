package com.application.question_service.DTO;

import com.application.question_service.ENUMS.Level;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    @NotNull
    private String question;
    @NotNull
    private List<String> options;
    @NotNull
    private String answer;
    @NotNull
    private Level level;
    @NotNull
    private String category;
}
