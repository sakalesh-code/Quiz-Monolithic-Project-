package com.learn.Questions.QuestionDao;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learn.Questions.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

	 // Fetch questions by category without ordering them randomly
    @Query(value = "SELECT * FROM question WHERE category = :category", nativeQuery = true)
    List<Question> findQuestionsByCategory(@Param("category") String category);
}
	
