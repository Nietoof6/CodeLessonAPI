package com.mobile.codelesson.controller;

import com.mobile.codelesson.domain.dtos.req.QuestionNewDTO;
import com.mobile.codelesson.domain.dtos.res.GeneralResponse;
import com.mobile.codelesson.domain.entities.Lesson;
import com.mobile.codelesson.domain.entities.Question;
import com.mobile.codelesson.repositories.LessonRepository;
import com.mobile.codelesson.service.contracts.LessonService;
import com.mobile.codelesson.service.contracts.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;
    private final LessonService lessonService;

    public QuestionController(QuestionService questionService, LessonService lessonService, LessonRepository lessonRepository) {
        this.questionService = questionService;
        this.lessonService = lessonService;
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createQuestion(@RequestBody @Valid QuestionNewDTO question) {
        try {
            Lesson lesson = lessonService.getLessonByTitle(question.getLessonTitle());
            if (lesson == null) {
                return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Lesson does not exist!");
            }
            Question question1 = questionService.createQuestion(question);
            lessonService.updateLesson(lesson, question1);
            return GeneralResponse.getResponse(HttpStatus.OK, "Question created successfully!");
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<GeneralResponse> getQuestionsByLessonId(@PathVariable String id) {
        try {
            List<Question> questions = questionService.getQuestionsByLessonId(id);
            return GeneralResponse.getResponse(HttpStatus.OK, questions);
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }
}
