package com.application.question_service.Controller;

import com.application.question_service.DTO.QuestionDTO;
import com.application.question_service.DTO.QuestionWrapper;
import com.application.question_service.DTO.Response;
import com.application.question_service.Model.Question;
import com.application.question_service.Service.QuestionService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }
    @PostMapping("/add")
    public ResponseEntity<Map<String,String>> addQuestion(@Valid @RequestBody QuestionDTO question, BindingResult result){
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        return questionService.addQuestion(question);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuestionForQuiz(@RequestParam("numberOfQuestions") Integer numQues, @RequestParam("category") String category){
        return questionService.generateQuestion(numQues,category);
    }

    @GetMapping("/category")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByCategory(@RequestParam ("category") String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionIDs){
        return questionService.getQuestions(questionIDs);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
       return  questionService.getScore(responses);
    }
}
