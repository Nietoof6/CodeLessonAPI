package com.mobile.codelesson.repositories;

import com.mobile.codelesson.domain.entities.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}
