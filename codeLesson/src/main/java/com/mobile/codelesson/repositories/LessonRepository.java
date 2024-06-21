package com.mobile.codelesson.repositories;

import com.mobile.codelesson.domain.entities.Lesson;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LessonRepository extends MongoRepository<Lesson, String> {
    Optional<Lesson> findById(String id);
    Lesson findByTitle(String title);
}
