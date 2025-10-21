package com.example.quiz_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.quiz_service.domains.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {}
