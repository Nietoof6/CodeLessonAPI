package com.mobile.codelesson.domain.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "lessons")
public class Lesson {
    private String id;
    private String title;
    private String lesson;
    private String recap;

}
