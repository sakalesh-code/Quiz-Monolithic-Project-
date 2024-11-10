package com.learn.Questions.ontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.Questions.model.QuestionWrapper;
import com.learn.Questions.model.Response;
import com.learn.Questions.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	@Autowired
	QuizService qzService;

	// Use POST instead of GET for creating resources like quizzes
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ,
			@RequestParam String title) {
		// Call the service to create a quiz and return the response
		String result = qzService.createQuiz(category, numQ, title);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
		return new ResponseEntity<>(qzService.getQuizQuestions(id), HttpStatus.OK);
	}

	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses) {
		return new ResponseEntity<>(qzService.submitQuiz(id, responses), HttpStatus.OK);
	}

}
