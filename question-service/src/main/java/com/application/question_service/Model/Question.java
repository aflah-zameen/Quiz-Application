package com.application.question_service.Model;

import com.application.question_service.ENUMS.Level;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String question;
    @ElementCollection
    private List<String> options;
    private String answer;
    @Enumerated(EnumType.STRING)
    private Level level;
    private String category;
}

