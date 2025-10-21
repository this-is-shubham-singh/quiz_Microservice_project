package com.example.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.quiz_service.domains.AnswerResponse;
import com.example.quiz_service.domains.QuestionResponse;


// define which service you want to connect. ex: question-service 
@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam Integer noOfQuestions);

    @PostMapping("/question/get-questions-from-questionids")
    public ResponseEntity<List<QuestionResponse>> getQuestionFromQuestionIds(@RequestBody List<Integer> questionIds);

    @PostMapping("/question/submit")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<AnswerResponse> answerResponses);
}
