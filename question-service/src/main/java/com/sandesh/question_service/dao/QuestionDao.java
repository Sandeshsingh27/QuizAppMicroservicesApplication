package com.sandesh.question_service.dao;

import com.sandesh.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategoryIgnoreCase(String category);

    List<Question> findByDifficultyLevelIgnoreCase(String level);

    @Query(value = "select q.id from question q where q.category = :category ORDER BY RANDOM() LIMIT :numQues", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQues);
}
