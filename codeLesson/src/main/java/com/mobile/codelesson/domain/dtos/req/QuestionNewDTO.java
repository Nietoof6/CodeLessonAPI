package com.mobile.codelesson.domain.dtos.req;

import lombok.Data;

import java.util.List;

@Data
public class QuestionNewDTO {

    private Integer type;
    private String code;
    private String question;
    private String correctAnswer;
    private List<String> options;

    private String lessonId;
}
