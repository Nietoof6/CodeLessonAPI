package com.mobile.codelesson.service.impl;

import com.mobile.codelesson.domain.dtos.req.QuestionNewDTO;
import com.mobile.codelesson.domain.entities.Question;
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

    public QuestionServiceImpl(ModelMapper modelMapper, QuestionRepository questionRepository) {
        this.modelMapper = modelMapper;
        this.questionRepository = questionRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createQuestion(QuestionNewDTO question) {
        Question newQuestion = modelMapper.map(question, Question.class);
        questionRepository.save(newQuestion);
    }

    @Override
    public List<Question> getQuestionsByLessonId(String lessonId) {
        return questionRepository.findAllByLessonId(lessonId);
    }
}
