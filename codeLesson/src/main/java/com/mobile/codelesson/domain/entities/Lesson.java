package com.mobile.codelesson.domain.entities;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "lessons")
public class Lesson {
    @Id
    private String id;
    private String title;
    private String lesson;
    private String recap;

    @DBRef
    private List<Question> questions;

}
