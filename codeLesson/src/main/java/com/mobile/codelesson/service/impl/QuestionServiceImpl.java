package com.mobile.codelesson.service.impl;

import com.mobile.codelesson.domain.dtos.req.QuestionNewDTO;
import com.mobile.codelesson.domain.entities.Lesson;
import com.mobile.codelesson.domain.entities.Question;
import com.mobile.codelesson.repositories.LessonRepository;
import com.mobile.codelesson.repositories.QuestionRepository;
import com.mobile.codelesson.service.contracts.QuestionService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;
    private final LessonRepository lessonRepository;

    public QuestionServiceImpl(ModelMapper modelMapper, QuestionRepository questionRepository, LessonRepository lessonRepository) {
        this.modelMapper = modelMapper;
        this.questionRepository = questionRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Question createQuestion(QuestionNewDTO question) {
        Question newQuestion = modelMapper.map(question, Question.class);
        return questionRepository.save(newQuestion);
    }

    @Override
    public List<Question> getQuestionsByLessonTitle(String lessonTitle) {
        return questionRepository.findAllByLessonTitle(lessonTitle);
    }

    @Override
    public List<Question> getQuestionsByLessonId(String lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        return lesson.getQuestions();
    }
}
