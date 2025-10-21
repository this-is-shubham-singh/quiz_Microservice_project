package com.example.question_service.usecase;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.question_service.domains.AnswerResponse;
import com.example.question_service.domains.Question;
import com.example.question_service.domains.QuestionResponse;
import com.example.question_service.repository.QuestionRepository;

@Service
public class QuestionUsecase {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {

        try {

            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

        try {

            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {

            questionRepository.save(question);
            return new ResponseEntity<>("question added", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("question not added", HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Integer>> generateQuestions(String category, Integer noOfQuestions) {
        return new ResponseEntity<>(questionRepository.generateQuestions(category, noOfQuestions), HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionResponse>> getQuestionFromQuestionIds (List<Integer> questionIds) {
        List<QuestionResponse> questions  = new ArrayList<>();

        for(int id : questionIds) {

            QuestionResponse questionResponse = new QuestionResponse();
            Question question = questionRepository.findById(id).get();

            questionResponse.setId(question.getId());
            questionResponse.setQuestion_title(question.getQuestionTitle());
            questionResponse.setOption1(question.getOption1());
            questionResponse.setOption2(question.getOption2());
            questionResponse.setOption3(question.getOption3());
            questionResponse.setOption4(question.getOption4());
            
            questions.add(questionResponse);
        }

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<AnswerResponse> answerResponses) {

        int score = 0;

        for(AnswerResponse answer : answerResponses) {
            Question question = questionRepository.findById(answer.getId()).get();

            if(question.getRightAnswer().equals(answer.getResponse())) {
                score++;
            }
        }

        return new ResponseEntity<>(score, HttpStatus.OK);

    }    
}
