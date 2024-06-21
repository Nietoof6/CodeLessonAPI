package com.mobile.codelesson.domain.entities;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "questions")
public class Question {
    @Id
    private String id;
    private Integer type;
    private String code;
    private String hint;
    private String question;
    private String correctAnswer;
    private List<String> options;

    private String lessonTitle;
}
