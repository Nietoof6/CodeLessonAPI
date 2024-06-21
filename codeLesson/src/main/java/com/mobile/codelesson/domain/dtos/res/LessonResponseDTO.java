package com.mobile.codelesson.domain.dtos.res;

import lombok.Data;

@Data
public class LessonResponseDTO {

    private String id;
    private String title;
    private String lesson;
    private String recap;
}
