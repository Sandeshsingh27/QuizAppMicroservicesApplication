// this class is created so that user could not have access to some items such as right_answer

package com.sandesh.quiz_service.model;

import lombok.Data;

@Data
public class QuestionWrapper {
    private int id;
    private String category;
    private String difficultyLevel;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionWrapper(int id, String category, String difficultyLevel, String questionTitle, String option1, String option2, String option3, String option4) {
        this.id = id;
        this.category = category;
        this.difficultyLevel = difficultyLevel;
        this.questionTitle = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
}
