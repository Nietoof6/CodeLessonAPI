package com.mobile.codelesson.controller;

import com.mobile.codelesson.domain.dtos.req.LessonNewDTO;
import com.mobile.codelesson.domain.dtos.res.GeneralResponse;
import com.mobile.codelesson.domain.entities.Lesson;
import com.mobile.codelesson.repositories.LessonRepository;
import com.mobile.codelesson.service.contracts.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    private final LessonService lessonService;
    private final LessonRepository lessonRepository;

    public LessonController(LessonService lessonService, LessonRepository lessonRepository) {
        this.lessonService = lessonService;
        this.lessonRepository = lessonRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createLesson(@RequestBody LessonNewDTO lesson) {
        try {
            Lesson lesson1 = lessonRepository.findByTitle(lesson.getTitle());
            if(lesson1 != null){
                return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST,"Lesson already exists!");
            }
            lessonService.createLesson(lesson);
            return GeneralResponse.getResponse(HttpStatus.OK, "Lesson created successfully!");
        }catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }

}
