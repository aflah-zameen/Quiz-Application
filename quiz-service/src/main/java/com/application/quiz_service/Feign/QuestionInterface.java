package com.application.quiz_service.Feign;

import com.application.quiz_service.Model.QuestionWrapper;
import com.application.quiz_service.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuestionInterface {
    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> generateQuestionForQuiz(@RequestParam("numberOfQuestions") Integer numQues, @RequestParam("category")  String category);

    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionIDs);

    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
