package com.mobile.codelesson.repositories;

import com.mobile.codelesson.domain.entities.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Question> findAllByLessonTitle(String LessonTitle);
}
