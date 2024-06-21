package com.mobile.codelesson.service.contracts;

import com.mobile.codelesson.domain.dtos.req.LessonNewDTO;
import com.mobile.codelesson.domain.entities.Lesson;
import com.mobile.codelesson.domain.entities.Question;

import java.util.List;

public interface LessonService {
    void createLesson(LessonNewDTO lesson);
    void updateLesson(Lesson lesson, Question question);
    List<Lesson> getLessons();
    Lesson getLessonById(String id);
    Lesson getLessonByTitle(String title);
}
