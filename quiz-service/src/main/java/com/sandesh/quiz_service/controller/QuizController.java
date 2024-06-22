package com.sandesh.quiz_service.controller;

import com.sandesh.quiz_service.model.QuestionWrapper;
import com.sandesh.quiz_service.model.Response;
import com.sandesh.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQues, @RequestParam String title){
        return quizService.createQuiz(category, numQues, title);
    }

    @GetMapping("/get/{quiz_id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("quiz_id") int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{quiz_id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable("quiz_id") int id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}
