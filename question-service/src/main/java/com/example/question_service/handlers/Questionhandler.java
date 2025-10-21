package com.example.question_service.handlers;

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

import com.example.question_service.domains.AnswerResponse;
import com.example.question_service.domains.Question;
import com.example.question_service.domains.QuestionResponse;
import com.example.question_service.usecase.QuestionUsecase;

@RestController
@RequestMapping("/question")
public class Questionhandler {

    @Autowired
    QuestionUsecase questionUsecase;

    @GetMapping("/get-all-questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionUsecase.getAllQuestions();
    }

    @GetMapping("/get-question-by-category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionUsecase.getQuestionsByCategory(category);
    }

    @PostMapping("/add-question")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionUsecase.addQuestion(question);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category,
            @RequestParam Integer noOfQuestions) {
        return questionUsecase.generateQuestions(category, noOfQuestions);
    }

    @PostMapping("/get-questions-from-questionids")
    public ResponseEntity<List<QuestionResponse>> getQuestionFromQuestionIds(@RequestBody List<Integer> questionIds) {
        return questionUsecase.getQuestionFromQuestionIds(questionIds);
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<AnswerResponse> answerResponses) {
        return questionUsecase.calculateScore(answerResponses);
    }

}
