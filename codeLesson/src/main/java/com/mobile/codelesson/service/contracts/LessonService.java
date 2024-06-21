package com.mobile.codelesson.service.contracts;

import com.mobile.codelesson.domain.dtos.req.LessonNewDTO;
import com.mobile.codelesson.domain.entities.Lesson;

import java.util.List;

public interface LessonService {
    void createLesson(LessonNewDTO lesson);
    List<Lesson> getLessons();
    Lesson getLessonById(String id);
}
