package com.learn.Questions.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learn.Questions.QuestionDao.QuestionDao;
import com.learn.Questions.QuestionDao.QuizDao;
import com.learn.Questions.model.Question;
import com.learn.Questions.model.QuestionWrapper;
import com.learn.Questions.model.Quiz;
import com.learn.Questions.model.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao qdao;
	@Autowired
	QuestionDao qsdao;

	// This method creates a quiz and persists it
	public String createQuiz(String category, int numQ, String title) {
		List<Question> questions = qsdao.findQuestionsByCategory(category);
		Collections.shuffle(questions);

		// Get the first 'numQ' random questions
		List<Question> selectedQuestions = questions.subList(0, Math.min(numQ, questions.size()));

		// Create a new Quiz object
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(selectedQuestions); // Set the questions list
		System.out.println(selectedQuestions);
		// Save the quiz in the database
		qdao.save(quiz);
		// Return a success message
		return "Quiz created successfully";
	}

	public List<QuestionWrapper> getQuizQuestions(int id) {
		Optional<Quiz> quiz = qdao.findById(id);
		List<Question> questions = quiz.get().getQuestions();
		List<QuestionWrapper> questionsforUser = new ArrayList<>();
		for (Question q : questions) {
			QuestionWrapper qw = new QuestionWrapper();
			qw.setQuestionTitle(q.getQuestionTitle());
			qw.setOption1(q.getOption1());
			qw.setOption2(q.getOption2());
			qw.setOption3(q.getOption3());
			qw.setOption4(q.getOption4());
			questionsforUser.add(qw);
		}

		return questionsforUser;
	}

	public int submitQuiz(int id, List<Response> responses) {
		Quiz quiz = qdao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		System.out.println("questions ::" + questions);
		System.out.println("response ::" + responses);
		int right = 0;
		int i = 0;
		for (Response res : responses) {
			if (res.getResponse().equals(questions.get(i).getRightAnswer())) {
				System.out.println("hehehe :: "+ "i :::"+i+"right:::"+right+ "::::isequals::::" + res.getResponse() + "  ==  " + questions.get(i).getRightAnswer());
				right++;

			}
			i++;
		}
		return right;
	}
}
