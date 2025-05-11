package com.application.quiz_service.Controller;

import com.application.quiz_service.DTO.QuizDTO;
import com.application.quiz_service.Model.QuestionWrapper;
import com.application.quiz_service.Model.Quiz;
import com.application.quiz_service.Model.Response;
import com.application.quiz_service.Service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    QuizService quizService;
    QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,String>> createQuiz(@RequestBody QuizDTO quizDTO){
        if(quizDTO.getCategoryName().isEmpty() || quizDTO.getTitle().isEmpty() || quizDTO.getNumberOfQuestions().equals(0)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status","error","message","The request is a BAD REQUEST"));
        }
        return quizService.createQuiz(quizDTO);
    }

    @GetMapping("/getAllQuiz")
    public ResponseEntity<List<Quiz>> getAllQuiz(){
        return quizService.getAllQuiz();
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable("id") Integer id){
        return quizService.getQuiz(id);
    }

    @PostMapping("/getResult")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return quizService.getScore(responses);
    }

}
