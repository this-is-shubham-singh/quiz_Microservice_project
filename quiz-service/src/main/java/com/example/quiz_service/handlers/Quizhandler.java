package com.example.quiz_service.handlers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_service.domains.AnswerResponse;
import com.example.quiz_service.domains.QuestionResponse;
import com.example.quiz_service.domains.QuizRequestDto;
import com.example.quiz_service.usecase.QuizUsecase;


@RestController
@RequestMapping("/quiz")
public class Quizhandler {

    @Autowired
    QuizUsecase quizUsecase;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizRequestDto quizRequestDto) {

        return quizUsecase.createQuiz(quizRequestDto);
    }

    @GetMapping("/getQuizById/{id}")
    public ResponseEntity<List<QuestionResponse>> getQuizById(@PathVariable int id) {

        return quizUsecase.getQuizById(id);
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> calculateResult(@RequestBody List<AnswerResponse> answers) {
        
        return quizUsecase.calculateResult(answers);
    }

}
