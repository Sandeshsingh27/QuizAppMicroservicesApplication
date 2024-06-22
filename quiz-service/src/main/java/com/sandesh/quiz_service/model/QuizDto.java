package com.sandesh.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {  // DTO means Data Transfer Object
    String categoryName;
    Integer numQues;
    String title;
}
