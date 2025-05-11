package com.application.quiz_service.Model;

import com.application.quiz_service.ENUMS.Level;
import lombok.Data;

import java.util.List;
@Data
public class QuestionWrapper {
    private Integer id;
    private String question;
    private String category;
    private Level level;
    private List<String> options;
    public QuestionWrapper(int id, String question,String category,Level level,List<String> options){
        this.id = id;
        this.category = category;
        this.question = question;
        this.level = level;
        this.options = options;
    }
}
