package com.application.question_service.Service;

import com.application.question_service.DAO.QuestionDAO;
import com.application.question_service.DTO.QuestionDTO;
import com.application.question_service.DTO.QuestionWrapper;
import com.application.question_service.DTO.Response;
import com.application.question_service.Model.Question;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.print.Pageable;
import java.sql.Wrapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {
    QuestionDAO questionDAO;
    QuestionService(QuestionDAO questionDAO){
        this.questionDAO = questionDAO;
    }
    public ResponseEntity<Map<String, String>> addQuestion( QuestionDTO questionDTO) {
        Question question = new Question();
        question.setQuestion(questionDTO.getQuestion());
        question.setCategory(questionDTO.getCategory());
        question.setAnswer(questionDTO.getAnswer());
        question.setLevel(questionDTO.getLevel());
        question.setOptions(questionDTO.getOptions());

        questionDAO.save(question);
        return ResponseEntity.ok(Map.of("status","success","message","Question has added successfully"));
    }

    public ResponseEntity<List<Integer>> generateQuestion(Integer numQues, String category) {
            List<Integer> questionIDs = questionDAO.findRandomQuestionByCategory(category,numQues);
            return ResponseEntity.ok(questionIDs);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Integer> questionIDs) {
        List<QuestionWrapper> wrapperList = new LinkedList<>();
        for(int id : questionIDs){
            Question question = questionDAO.findById(id).orElseThrow(() -> new RuntimeException("not fount"));
            QuestionWrapper wrapper = new QuestionWrapper(question.getId(),question.getQuestion(),question.getCategory(),question.getLevel(),question.getOptions());
            wrapperList.add(wrapper);
        }
        return ResponseEntity.ok(wrapperList);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;
        for(Response response : responses){
            Question question = questionDAO.findById(response.getId()).orElseThrow(()-> new RuntimeException("not found"));
            if(question.getAnswer().equals(response.getResponse())){
                right++;
            }
        }
        return ResponseEntity.ok(right);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsByCategory(String category) {
        List<Question> list = questionDAO.findByCategory(category).orElseThrow(()-> new RuntimeException("not found"));
        List<QuestionWrapper> wrapperList = new LinkedList<>();
        for(Question question  : list){
            QuestionWrapper wrapper = new QuestionWrapper(question.getId(),question.getQuestion(),question.getCategory(),question.getLevel(),question.getOptions());
            wrapperList.add(wrapper);
        }
        return ResponseEntity.ok(wrapperList);
    }
}
