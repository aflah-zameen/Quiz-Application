package com.application.question_service.DAO;

import com.application.question_service.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionDAO extends JpaRepository<Question,Integer> {
    Optional<List<Question>> findByCategory(String category);
    @Query("SELECT q.id FROM Question q where q.category = :category ORDER BY RANDOM() LIMIT :num")
    List<Integer> findRandomQuestionByCategory(String category,int num);
}
