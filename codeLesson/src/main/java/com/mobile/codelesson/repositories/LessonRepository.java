package com.mobile.codelesson.repositories;

import com.mobile.codelesson.domain.entities.Lesson;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LessonRepository extends MongoRepository<Lesson, String> {
}
