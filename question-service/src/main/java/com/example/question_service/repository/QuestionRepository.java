package com.example.question_service.repository;
import com.example.question_service.domains.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    public List<Question> findByCategory(String category);

    @Query(value = "SELECT id FROM question WHERE category = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    public List<Integer> generateQuestions(String category, int noOfQuestions);

    
}
