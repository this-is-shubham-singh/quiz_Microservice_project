package com.example.quiz_service.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.quiz_service.repository.QuizRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizUsecase {

    @Autowired
    QuizRepository quizRepository;

    public ResponseEntity<String> createQuiz(String category, Integer noOfQuestions, String title) {
        List<Integer> quizQuestions = questionRepository.generateQuizQuestions(category, noOfQuestions);

        Quiz quiz = new Quiz();
        quiz.setQuizTitle(title);
        quiz.setQuestions(quizQuestions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionResponse>> getQuizById(int id) {

        try {

            Optional<Quiz> quiz = quizRepository.findById(id);
            List<Question> ques = quiz.get().getQuestions();
            List<QuestionResponse> quesResponse = new ArrayList<>();

            for (Question q : ques) {
                QuestionResponse questionResponse = new QuestionResponse();

                questionResponse.setCategory(q.getCategory());
                questionResponse.setQuestion_title(q.getQuestionTitle());
                questionResponse.setOption1(q.getOption1());
                questionResponse.setOption2(q.getOption2());
                questionResponse.setOption3(q.getOption3());
                questionResponse.setOption4(q.getOption4());

                quesResponse.add(questionResponse);
            }

            return new ResponseEntity<>(quesResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> calculateResult(int quizId, List<AnswerResponse> answers) {

        Optional<Quiz> quiz = quizRepository.findById(quizId);

        if (quiz.isEmpty()) {
            return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
        }

        List<Question> questions = quiz.get().getQuestions();
        int i = 0;
        int count = 0;

        for (Question ques : questions) {
            if (ques.getRightAnswer().equals(answers.get(i).getResponse())) {
                count++;
            }

            i++;
        }

        return new ResponseEntity<>(count, HttpStatus.OK);

    }
}
