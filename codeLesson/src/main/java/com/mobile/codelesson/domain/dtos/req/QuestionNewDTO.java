package com.mobile.codelesson.domain.dtos.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class QuestionNewDTO {

    @NotNull
    private Integer type;
    private String code;
    @NotBlank
    private String hint;
    private String question;
    @NotBlank
    private String correctAnswer;
    private List<String> options;

    private String LessonTitle;
}
