package com.sandesh.question_service.service;


import com.sandesh.question_service.dao.QuestionDao;
import com.sandesh.question_service.model.Question;
import com.sandesh.question_service.model.QuestionWrapper;
import com.sandesh.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = questionDao.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            List<Question> questions = questionDao.findByCategoryIgnoreCase(category);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByDifficultyLevel(String level) {
        try {
            List<Question> questions = questionDao.findByDifficultyLevelIgnoreCase(level);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        try {
            Question savedQuestion = questionDao.save(question);
            return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> updateQuestion(int id, Question question) {
        try {
            Optional<Question> optionalQuestion = questionDao.findById(id);
            if (optionalQuestion.isPresent()) {
                Question existingQuestion = optionalQuestion.get();
                existingQuestion.setCategory(question.getCategory());
                existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
                existingQuestion.setQuestionTitle(question.getQuestionTitle());
                existingQuestion.setOption1(question.getOption1());
                existingQuestion.setOption2(question.getOption2());
                existingQuestion.setOption3(question.getOption3());
                existingQuestion.setOption4(question.getOption4());
                existingQuestion.setRightAnswer(question.getRightAnswer());
                Question updatedQuestion = questionDao.save(existingQuestion);
                return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> deleteQuestion(int id) {
        try {
            if (questionDao.existsById(id)) {
                questionDao.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionIds){
            questions.add(questionDao.findById(id).get());
        }

        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper(question.getId(), question.getCategory(), question.getDifficultyLevel(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            wrappers.add(wrapper);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    // this function will calculate score based on user responses
    public ResponseEntity<Integer> calculateScore(List<Response> responses) {
        int score = 0;

        for(Response response : responses){
            Question questionFromDB = questionDao.findById(response.getId()).get();
            String correctAnswerFromDB = questionFromDB.getRightAnswer();

            String userResponse = response.getResponse();

            if(userResponse.equals(correctAnswerFromDB))
                score++;
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
