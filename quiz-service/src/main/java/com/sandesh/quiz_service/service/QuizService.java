package com.sandesh.quiz_service.service;

import com.sandesh.quiz_service.dao.QuizDao;
import com.sandesh.quiz_service.feign.QuizInterface;
import com.sandesh.quiz_service.model.QuestionWrapper;
import com.sandesh.quiz_service.model.Quiz;
import com.sandesh.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;


    // this will create quiz
    public ResponseEntity<String> createQuiz(String category, int numQues, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQues).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz Created", HttpStatus.OK);
    }

    // this will fetch all the question from a quiz
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quiz = quizDao.findById(id).get();

        List<Integer> questionIds = quiz.getQuestionIds();

        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;
    }


    // this function will calculate score based on user responses
    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
