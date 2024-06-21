package com.mobile.codelesson.domain.dtos.req;

import lombok.Data;

@Data
public class UserProfileDTO {

    private String id;
    private String name;
    private String lastName;
    private String email;
}
