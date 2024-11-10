package com.learn.Questions.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.Questions.QuestionDao.QuestionDao;
import com.learn.Questions.model.Question;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao qsdao;

    // Get all questions
    public List<Question> getAllQuestions() {
        return qsdao.findAll();
    }

    // Save all questions
    public List<Question> saveAllQuestions(List<Question> questions) {
        return qsdao.saveAll(questions);
    }

    // Save a single question
    public Question saveQuestion(Question question) {
        return qsdao.save(question);
    }

    // Get a question by ID
    public Optional<Question> getQuestionById(int id) {
        return qsdao.findById(id);
    }
}
