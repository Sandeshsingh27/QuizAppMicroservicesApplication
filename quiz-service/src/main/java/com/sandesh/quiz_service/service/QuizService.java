package com.sandesh.quiz_service.service;

import com.sandesh.quiz_service.dao.QuizDao;
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

//    @Autowired
//    QuestionDao questionDao;


    // this will create quiz
    public ResponseEntity<String> createQuiz(String category, int numQues, String title) {
//        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQues);
//
//        Quiz quiz = new Quiz();
//        quiz.setTitle(title);
//        quiz.setQuestions(questions);
//
//        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz Created", HttpStatus.OK);
    }

    // this will fetch all the question from a quiz
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
//        Optional<Quiz> quiz = quizDao.findById(id);
//
//        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();
//
//        for(Question q : questionsFromDB){
//            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getCategory(), q.getDifficultyLevel(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
//            questionsForUsers.add(qw);  // adding it to List
//        }

        return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
    }


    // this function will calculate score based on user responses
    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
//        Optional<Quiz> quiz = quizDao.findById(id);
//
//        List<Question> questions = quiz.get().getQuestions();
//
        int score = 0;
//        int i = 0; // this variable is to iterate all the questions which we get from the DB
//
//        for(Response response : responses){
//            String correctAnswerFromDB = questions.get(i).getRightAnswer();
//            i++;
//
//            String userResponse = response.getResponse();
//
//            if(userResponse.equals(correctAnswerFromDB)){
//                score++;
//            }
//        }

        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
