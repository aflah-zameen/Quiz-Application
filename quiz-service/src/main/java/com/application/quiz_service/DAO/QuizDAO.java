package com.application.quiz_service.DAO;

import com.application.quiz_service.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz,Integer> {

}
