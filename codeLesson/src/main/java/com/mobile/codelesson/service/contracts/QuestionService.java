package com.mobile.codelesson.service.contracts;

import com.mobile.codelesson.domain.dtos.req.QuestionNewDTO;
import com.mobile.codelesson.domain.entities.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(QuestionNewDTO question);
    List<Question> getQuestionsByLessonTitle(String lessonTitle);
    List<Question> getQuestionsByLessonId(String lessonId);
}
