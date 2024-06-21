package com.mobile.codelesson.service.contracts;

import com.mobile.codelesson.domain.dtos.req.QuestionNewDTO;
import com.mobile.codelesson.domain.entities.Question;

import java.util.List;

public interface QuestionService {
    void createQuestion(QuestionNewDTO question);
    List<Question> getQuestionsByLessonId(String lessonId);
}
