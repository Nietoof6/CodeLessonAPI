package com.mobile.codelesson.service.impl;

import com.mobile.codelesson.domain.dtos.req.LessonNewDTO;
import com.mobile.codelesson.domain.entities.Lesson;
import com.mobile.codelesson.domain.entities.Question;
import com.mobile.codelesson.repositories.LessonRepository;
import com.mobile.codelesson.service.contracts.LessonService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    private final ModelMapper modelMapper;
    private final LessonRepository lessonRepository;

    public LessonServiceImpl(ModelMapper modelMapper, LessonRepository lessonRepository) {
        this.modelMapper = modelMapper;
        this.lessonRepository = lessonRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createLesson(LessonNewDTO lesson) {
        Lesson newLesson = modelMapper.map(lesson, Lesson.class);
        lessonRepository.save(newLesson);
    }

    @Override
    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson getLessonById(String id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateLesson(Lesson lesson, Question question) {
        List<Question> questions = lesson.getQuestions();
        if (questions == null) {
            questions = new ArrayList<>();
        }
        questions.add(question);
        lesson.setQuestions(questions);
        lessonRepository.save(lesson);
    }

    @Override
    public Lesson getLessonByTitle(String title) {
        return lessonRepository.findByTitle(title);
    }
}
