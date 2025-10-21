package com.example.quiz_service.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quiz_service.domains.AnswerResponse;
import com.example.quiz_service.domains.QuestionResponse;
import com.example.quiz_service.domains.Quiz;
import com.example.quiz_service.domains.QuizRequestDto;
import com.example.quiz_service.feign.QuizInterface;
import com.example.quiz_service.repository.QuizRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizUsecase {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(QuizRequestDto quizRequestDto) {
        List<Integer> quizQuestions = quizInterface.generateQuestions(quizRequestDto.getCategory(), quizRequestDto.getNoOfQuestion()).getBody();

        Quiz quiz = new Quiz();
        quiz.setQuizTitle(quizRequestDto.getTitle());
        quiz.setQuestions(quizQuestions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }



    public ResponseEntity<List<QuestionResponse>> getQuizById(int id) {

        try {

            Quiz quiz = quizRepository.findById(id).get();
            List<Integer> ques = quiz.getQuestions();

            List<QuestionResponse> quesResponse = quizInterface.getQuestionFromQuestionIds(ques).getBody();

            return new ResponseEntity<>(quesResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> calculateResult(List<AnswerResponse> answers) {

        Integer count = quizInterface.calculateScore(answers).getBody();
        
        return new ResponseEntity<>(count, HttpStatus.OK);

    }
}
