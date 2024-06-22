package com.sandesh.question_service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor // this will give parametrized and default constructor by lombok
public class Response {
    private int id;
    private String response;
}
