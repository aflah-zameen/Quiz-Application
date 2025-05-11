package com.application.quiz_service.Service;

import com.application.quiz_service.DAO.QuizDAO;
import com.application.quiz_service.DTO.QuizDTO;
import com.application.quiz_service.Feign.QuestionInterface;
import com.application.quiz_service.Model.QuestionWrapper;
import com.application.quiz_service.Model.Quiz;
import com.application.quiz_service.Model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    QuestionInterface questionInterface;
    QuizDAO quizDAO;
    QuizService(QuestionInterface questionInterface, QuizDAO quizDAO){
        this.questionInterface = questionInterface;
        this.quizDAO = quizDAO;
    }

    public ResponseEntity<Map<String, String>> createQuiz(QuizDTO quizDTO) {
            List<Integer> questionIDs = questionInterface.generateQuestionForQuiz(quizDTO.getNumberOfQuestions(),quizDTO.getCategoryName()).getBody();
            Quiz quiz = new Quiz();
            quiz.setTitle(quizDTO.getTitle());
            quiz.setQuestionIDs(questionIDs);
            quiz =  quizDAO.save(quiz);

            return ResponseEntity.ok(Map.of("status","success","message","Successfully created a quiz","quiz_id",quiz.getId().toString()));
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Quiz quiz = quizDAO.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        return questionInterface.getQuestions(quiz.getQuestionIDs());
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        return questionInterface.getScore(responses);
    }

    public ResponseEntity<List<Quiz>> getAllQuiz() {
        return ResponseEntity.ok(quizDAO.findAll());
    }
}
